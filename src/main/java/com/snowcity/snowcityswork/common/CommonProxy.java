package com.snowcity.snowcityswork.common;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;

import com.snowcity.snowcityswork.common.registry.SWRegistration;
import com.snowcity.snowcityswork.data.*;
import com.snowcity.snowcityswork.data.SWRecipe;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class CommonProxy {

    public CommonProxy() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        // 立即执行的初始化
        SWRegistration.SNOWCITYSWORK_REGISTRATE.registerRegistrate();
    }


    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            // 在事件中执行初始化
            SWCreativeModeTabs.init();
            SWDataGen.init();
        });
    }
}
