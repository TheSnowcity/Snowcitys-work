package com.snowcity.snowcityswork.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import com.gregtechceu.gtceu.client.renderer.machine.MinerRenderer;
import com.snowcity.snowcityswork.common.block.machine.electric.VoltaicPile;
import com.snowcity.snowcityswork.Snowcityswork;

import java.util.Locale;
import java.util.function.BiFunction;

import static com.snowcity.snowcityswork.common.registry.SWRegistration.SNOWCITYSWORK_REGISTRATE;
import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.snowcity.snowcityswork.data.SWRecipeTypes.VOLTAIC_PILE_RECIPE;

public class SWMachines {

    public static final int[] ALL_TIERS = GTValues.tiersBetween(ULV, GTCEuAPI.isHighTier() ? MAX : UHV);

    static {
        SNOWCITYSWORK_REGISTRATE.creativeModeTab(() -> SWCreativeModeTabs.MACHINES);
    }
    public static final MachineDefinition[] VOLTAICPILE = registerTieredMachines("voltaic_pile",
            VoltaicPile::new,
            (tier, builder) -> builder
                    .langValue("%s Voltaic Pile %s".formatted(VLVH[tier], VLVT[tier]))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .renderer(() -> new MinerRenderer(tier, GTCEu.id("block/machine/air_scrubber")))
                    .recipeType(VOLTAIC_PILE_RECIPE)
                    .register(),
            LV, MV, HV);

    public static MachineDefinition[] registerTieredMachines(String name,
                                                             BiFunction<IMachineBlockEntity, Integer, MetaMachine> factory,
                                                             BiFunction<Integer, MachineBuilder<MachineDefinition>, MachineDefinition> builder,
                                                             int... tiers) {
        MachineDefinition[] definitions = new MachineDefinition[GTValues.TIER_COUNT];
        for (int tier : tiers) {
            var register = SNOWCITYSWORK_REGISTRATE
                    .machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_" + name,
                            holder -> factory.apply(holder, tier))
                    .tier(tier);
            definitions[tier] = builder.apply(tier, register);
        }
        return definitions;
    }
    public static void init() {

    }
}
