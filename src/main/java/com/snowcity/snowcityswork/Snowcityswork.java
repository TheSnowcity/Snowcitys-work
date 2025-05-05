package com.snowcity.snowcityswork;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.DistExecutor;

import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;

import com.snowcity.snowcityswork.common.CommonProxy;
import com.snowcity.snowcityswork.client.ClientProxy;
import com.snowcity.snowcityswork.event.EventHandler;

@Mod(Snowcityswork.MODID)
public class Snowcityswork
{
    public static final String MODID = "snowcityswork";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Snowcityswork() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addGenericListener(MachineDefinition.class, EventHandler::registerMachines);
        modEventBus.addGenericListener(GTRecipeType.class, EventHandler::registerRecipeTypes);
        DistExecutor.unsafeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    }

    public static ResourceLocation id(String name) {
        return new ResourceLocation(MODID, name);
    }

}
