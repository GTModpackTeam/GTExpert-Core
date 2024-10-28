package gtexpert.integration.crafttweaker;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.GTEModule;
import gtexpert.api.util.Mods;
import gtexpert.integration.GTEIntegrationSubmodule;
import gtexpert.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_CT,
           containerID = GTEValues.MODID,
           modDependencies = Mods.Names.CRAFT_TWEAKER,
           name = "GTExpert CraftTweaker Integration",
           description = "CraftTweaker Integration Module")
public class CraftTweakerModule extends GTEIntegrationSubmodule {}
