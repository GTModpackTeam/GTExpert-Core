package gtexpert.integration.ae;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;

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
            Materials.WroughtIron,
            Materials.Steel,
            Materials.Aluminium,
            Materials.StainlessSteel,
            Materials.Titanium,
            Materials.TungstenSteel,
            Materials.RhodiumPlatedPalladium,
            Materials.NaquadahAlloy,
            Materials.Darmstadtium,
            Materials.Neutronium
    };
}
