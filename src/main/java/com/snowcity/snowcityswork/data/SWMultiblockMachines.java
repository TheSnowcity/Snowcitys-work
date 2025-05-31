package com.snowcity.snowcityswork.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;

import com.snowcity.snowcityswork.api.machine.multiblock.part.GrindBallHatchPartMachine;
import com.snowcity.snowcityswork.common.block.machine.multiblock.electric.IsaMill;

import static com.gregtechceu.gtceu.api.GTValues.LuV;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.snowcity.snowcityswork.data.SWRecipeTypes.ISAMILL_RECIPE;

import static com.snowcity.snowcityswork.common.registry.SWRegistration.SNOWCITYSWORK_REGISTRATE;

public class SWMultiblockMachines {
    static {
        SNOWCITYSWORK_REGISTRATE.creativeModeTab(() -> SWCreativeModeTabs.MACHINES);
    }

    public static final MachineDefinition GRINDBALL_HATCH = SNOWCITYSWORK_REGISTRATE.machine("grindball_hatch", GrindBallHatchPartMachine::new)
            .rotationState(RotationState.ALL)
            .langValue("GrindBall Hatch")
            .tier(LuV)
            .register();

    public static final MultiblockMachineDefinition IsaMill = SNOWCITYSWORK_REGISTRATE.multiblock("isamill", IsaMill::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(ISAMILL_RECIPE)
            .recipeModifier(GTRecipeModifiers.OC_PERFECT)
            .appearanceBlock(CASING_STAINLESS_CLEAN)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("EEE", "EEE", "EEE")
                    .aisle("EEE", "EGE", "EEE")
                    .aisle("EEE", "EGE", "EEE")
                    .aisle("EEE", "EGE", "EEE")
                    .aisle("EEE", "EGE", "EEE")
                    .aisle("EEE", "EGE", "EEE")
                    .aisle("CCC", "CKC", "CCC")
                    .where("K", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("G", Predicates.blocks(CASING_STAINLESS_STEEL_GEARBOX.get()))
                    .where("C", Predicates.blocks(CASING_STAINLESS_TURBINE.get()))
                    .where("E", Predicates.blocks(CASING_STAINLESS_CLEAN.get()).setMinGlobalLimited(4)
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                    )
                    .where(" ", Predicates.air())
                    .build()
            )
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel"), GTCEu.id("block/multiblock/generator/large_steam_turbine"), false)
            .register();

    public static void init() {

    }
}
