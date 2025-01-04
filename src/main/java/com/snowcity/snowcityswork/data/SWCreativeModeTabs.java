package com.snowcity.snowcityswork.data;

import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;
import com.gregtechceu.gtceu.common.data.GTBlocks;

import net.minecraft.world.item.CreativeModeTab;

import com.snowcity.snowcityswork.Snowcityswork;
import com.tterrag.registrate.util.entry.RegistryEntry;

import static com.snowcity.snowcityswork.common.registry.SWRegistration.SNOWCITYSWORK_REGISTRATE;

public class SWCreativeModeTabs {

    public static RegistryEntry<CreativeModeTab> MACHINES = SNOWCITYSWORK_REGISTRATE.defaultCreativeTab("machines",
                    builder -> builder.displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator("machines", SNOWCITYSWORK_REGISTRATE))
                            .icon(() -> GTBlocks.COIL_CUPRONICKEL.asStack())
                            .title(SNOWCITYSWORK_REGISTRATE.addLang("machinesGroup", Snowcityswork.id("machines"), "MACHINES"))
                            .build())
            .register();

    public static void init() {}
}
