package gtexpert.api.unification;

import gregtech.api.unification.Element;

import java.util.*;

public class GTEElements {

    private GTEElements() {}

    private static final Map<String, Element> elements = new HashMap<>();
    public static final Element De = add(1, 0, -1, null, "Draconium", "De", false);
    public static final Element De1 = add(1, 0, -1, null, "AwakenedDraconium", "De+", false);
    public static final Element If = add(1, 0, -1, null, "Infinity", "If", false);

    public static Element add(long protons, long neutrons, long halfLifeSeconds, String decayTo, String name,
                              String symbol, boolean isIsotope) {
        Element element = new Element(protons, neutrons, halfLifeSeconds, decayTo, name, symbol, isIsotope);
        elements.put(name, element);
        return element;
    }
}
