package gtexpert.integration.crafttweaker;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.GTEModule;
import gtexpert.integration.GTEIntegrationSubmodule;
import gtexpert.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_CT,
           containerID = GTEValues.MODID,
           modDependencies = GTEValues.MODID_CT,
           name = "GTExpert CraftTweaker Integration",
           description = "CraftTweaker Integration Module")
public class CraftTweakerModule extends GTEIntegrationSubmodule {}
