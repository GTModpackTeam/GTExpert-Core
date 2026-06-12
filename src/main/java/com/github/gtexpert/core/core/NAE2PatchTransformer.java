package com.github.gtexpert.core.core;

import java.util.Iterator;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import com.github.gtexpert.core.api.util.GTELog;

/**
 * ASM Transformer to patch NAE2's MixinDualityInterface by removing the @Shadow craftingList field
 * and replacing methods that reference it with no-ops. This fixes compatibility issues with newer
 * AE2 versions where the craftingList field no longer exists, while preserving Mixin injection points.
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
            GTELog.logger.info("Patching NAE2 MixinDualityInterface to remove craftingList references");

            ClassReader classReader = new ClassReader(classBytes);
            ClassNode classNode = new ClassNode();
            classReader.accept(classNode, 0);

            boolean modified = false;

            // Remove the craftingList field
            Iterator<FieldNode> fieldIterator = classNode.fields.iterator();
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
                                modified = true;
                                break;
                            }
                        }
                    }
                }
            }

            // Remove or modify methods that reference craftingList
            Iterator<MethodNode> methodIterator = classNode.methods.iterator();
            while (methodIterator.hasNext()) {
                MethodNode method = methodIterator.next();

                // Check if this is an injected method that might reference craftingList
                if (method.name.contains("injectInventoryChange") ||
                        method.name.contains("handler$")) {

                    // Check for any GETFIELD/PUTFIELD instructions that reference craftingList
                    if (method.instructions != null) {
                        boolean hasCraftingListRef = false;
                        Iterator<AbstractInsnNode> insnIterator = method.instructions.iterator();
                        while (insnIterator.hasNext()) {
                            AbstractInsnNode insn = insnIterator.next();
                            if (insn.getOpcode() == Opcodes.GETFIELD || insn.getOpcode() == Opcodes.PUTFIELD) {
                                FieldInsnNode fieldInsn = (FieldInsnNode) insn;
                                if ("craftingList".equals(fieldInsn.name)) {
                                    hasCraftingListRef = true;
                                    break;
                                }
                            }
                        }

                        if (hasCraftingListRef) {
                            GTELog.logger.info(
                                    "Found reference to craftingList in method {}, replacing with no-op",
                                    method.name);
                            method.instructions.clear();
                            if (method.tryCatchBlocks != null) {
                                method.tryCatchBlocks.clear();
                            }
                            method.localVariables = null;
                            method.instructions.add(new InsnNode(Opcodes.RETURN));
                            modified = true;
                        }
                    }
                }
            }

            if (modified) {
                ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
                classNode.accept(classWriter);
                GTELog.logger.info("Successfully patched NAE2 MixinDualityInterface");
                return classWriter.toByteArray();
            } else {
                GTELog.logger.info("No craftingList references found in MixinDualityInterface");
            }

        } catch (Exception e) {
            GTELog.logger.error("Failed to patch NAE2 MixinDualityInterface: {}", e.getMessage(), e);
        }

        return classBytes;
    }
}
