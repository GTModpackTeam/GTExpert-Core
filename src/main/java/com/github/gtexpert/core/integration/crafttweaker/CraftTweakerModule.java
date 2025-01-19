package com.github.gtexpert.core.integration.crafttweaker;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.modules.GTEModule;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.integration.GTEIntegrationSubmodule;
import com.github.gtexpert.core.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_CT,
           containerID = GTEValues.MODID,
           modDependencies = Mods.Names.CRAFT_TWEAKER,
           name = "GTExpert CraftTweaker Integration",
           description = "CraftTweaker Integration Module")
public class CraftTweakerModule extends GTEIntegrationSubmodule {}
