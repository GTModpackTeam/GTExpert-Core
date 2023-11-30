package gtexpert.integration.ae;

import static gregtech.api.unification.material.Materials.*;

import gregtech.api.unification.material.Material;

import appeng.api.AEApi;
import appeng.api.definitions.IBlocks;
import appeng.api.definitions.IItems;
import appeng.api.definitions.IMaterials;
import appeng.api.definitions.IParts;

public class AEHelper {

    public static final IItems aeItems = AEApi.instance().definitions().items();
    public static final IBlocks aeBlocks = AEApi.instance().definitions().blocks();
    public static final IMaterials aeMaterials = AEApi.instance().definitions().materials();
    public static final IParts aeParts = AEApi.instance().definitions().parts();

    public static final Material[] tierMaterials = new Material[] {
            WroughtIron,
            Steel,
            Aluminium,
            StainlessSteel,
            Titanium,
            TungstenSteel,
            RhodiumPlatedPalladium,
            NaquadahAlloy,
            Darmstadtium,
            Neutronium
    };
}
