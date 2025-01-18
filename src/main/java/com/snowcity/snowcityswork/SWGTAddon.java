package com.snowcity.snowcityswork;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

import com.snowcity.snowcityswork.common.block.machine.electric.VoltaicPile;
import com.snowcity.snowcityswork.common.registry.*;

import com.snowcity.snowcityswork.data.SWRecipe;
import com.snowcity.snowcityswork.data.recipes.*;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

@GTAddon
public class SWGTAddon implements IGTAddon{
    @Override
    public GTRegistrate getRegistrate(){
        return SWRegistration.SNOWCITYSWORK_REGISTRATE;
    }

    @Override
    public void initializeAddon() {

    }

    @Override
    public String addonModId() {
        return Snowcityswork.MODID;
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        SWRecipe.init(provider);
        VoltaicPileRecipe.init(provider);
    }
}
