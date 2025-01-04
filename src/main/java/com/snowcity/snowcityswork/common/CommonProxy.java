package com.snowcity.snowcityswork.common;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;

import com.snowcity.snowcityswork.common.registry.SWRegistration;
import com.snowcity.snowcityswork.data.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class CommonProxy {

    public static void init(){

        SWMachines.init();
        SWRegistration.SNOWCITYSWORK_REGISTRATE.registerRegistrate();
        SWMaterials.init();
        SWCreativeModeTabs.init();
    }
}
