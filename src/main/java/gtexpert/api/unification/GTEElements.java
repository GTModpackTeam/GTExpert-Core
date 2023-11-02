package gtexpert.api.unification;

import gregtech.api.unification.Element;

import java.util.*;

public class GTEElements {

    private GTEElements() {}

    private static final Map<String, Element> elements = new HashMap<>();
    public static final Element Dr = add(37, 51, -1, null, "Dragon", "Dr", false);
    public static final Element Dr1 = add(30, 39, -1, null, "Chaos", "Dr+", false);
    public static final Element De = add(9, 11, -1, null, "Draconium", "De", false);
    public static final Element De1 = add(43, 55, -1, null, "AwakenedDraconium", "De+", false);
    public static final Element If = add(1, 0, -1, null, "Infinity", "If", false);

    public static Element add(long protons, long neutrons, long halfLifeSeconds, String decayTo, String name,
                              String symbol, boolean isIsotope) {
        Element element = new Element(protons, neutrons, halfLifeSeconds, decayTo, name, symbol, isIsotope);
        elements.put(name, element);
        return element;
    }
}
