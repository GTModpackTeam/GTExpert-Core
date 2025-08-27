package com.github.gtexpert.core.core;

import java.util.Iterator;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

import com.github.gtexpert.core.api.util.GTELog;

/**
 * ASM Transformer to patch NAE2's MixinDualityInterface by removing the problematic craftingList field.
 * This implements the diff change that removes the @Shadow craftingList field from the mixin.
 */
public class NAE2PatchTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if ("co.neeve.nae2.mixin.upgrades.gregcircuit.MixinDualityInterface".equals(transformedName)) {
            return patchMixinDualityInterface(basicClass);
        }
        return basicClass;
    }

    private byte[] patchMixinDualityInterface(byte[] classBytes) {
        try {
            GTELog.logger.info("Patching NAE2 MixinDualityInterface to remove craftingList field");

            ClassReader classReader = new ClassReader(classBytes);
            ClassNode classNode = new ClassNode();
            classReader.accept(classNode, 0);

            // Remove the craftingList field
            Iterator<FieldNode> fieldIterator = classNode.fields.iterator();
            boolean fieldRemoved = false;

            while (fieldIterator.hasNext()) {
                FieldNode field = fieldIterator.next();
                if ("craftingList".equals(field.name)) {
                    // Check if this field has @Shadow annotation
                    if (field.visibleAnnotations != null) {
                        for (AnnotationNode annotation : field.visibleAnnotations) {
                            if ("Lorg/spongepowered/asm/mixin/Shadow;".equals(annotation.desc)) {
                                GTELog.logger
                                        .info("Removing @Shadow craftingList field from NAE2 MixinDualityInterface");
                                fieldIterator.remove();
                                fieldRemoved = true;
                                break;
                            }
                        }
                    }
                }
            }

            if (fieldRemoved) {
                ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
                classNode.accept(classWriter);
                GTELog.logger.info("Successfully patched NAE2 MixinDualityInterface");
                return classWriter.toByteArray();
            } else {
                GTELog.logger.info("craftingList field not found or already removed in MixinDualityInterface");
            }

        } catch (Exception e) {
            GTELog.logger.error("Failed to patch NAE2 MixinDualityInterface: " + e.getMessage(), e);
        }

        return classBytes;
    }
}
