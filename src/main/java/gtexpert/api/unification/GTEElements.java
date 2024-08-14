package gtexpert.api.unification;

import gregtech.api.unification.Element;
import gregtech.api.unification.Elements;

import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;

@ZenClass("mods.gregtech.material.Elements")
@ZenRegister
public class GTEElements {

    private GTEElements() {}

    public static final Element Dr = Elements.add(37, 51, -1, null, "Dragon", "Dr", false);
    public static final Element Dr1 = Elements.add(30, 39, -1, null, "Chaos", "Dr+", false);
    public static final Element De = Elements.add(9, 11, -1, null, "Draconium", "De", false);
    public static final Element De1 = Elements.add(43, 55, -1, null, "AwakenedDraconium", "De+", false);
    public static final Element If = Elements.add(1, 0, -1, null, "Infinity", "If", false);
    public static final Element FeMa = Elements.add(1, 0, -1, null, "Thaumium", "FeMa", false);
}
