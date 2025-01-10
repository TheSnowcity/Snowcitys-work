package com.snowcity.snowcityswork.common;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;

import com.snowcity.snowcityswork.common.registry.SWRegistration;
import com.snowcity.snowcityswork.data.*;
import com.snowcity.snowcityswork.data.SWRecipe;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class CommonProxy {

    public CommonProxy() {
        init();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }

    public static void init(){
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        SWCreativeModeTabs.init();
        SWRegistration.SNOWCITYSWORK_REGISTRATE.registerRegistrate();
    }
}
