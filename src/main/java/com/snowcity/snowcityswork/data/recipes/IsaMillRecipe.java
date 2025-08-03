package com.snowcity.snowcityswork.data.recipes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.snowcity.snowcityswork.data.SWRecipeTypes;
import com.snowcity.snowcityswork.data.SWMaterials;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.snowcity.snowcityswork.data.SWItems.*;
import static com.snowcity.snowcityswork.data.SWTagPrefix.generate_milled;

public class IsaMillRecipe {
    public static void init(Consumer<FinishedRecipe> provider){

        SWRecipeTypes.ISAMILL_RECIPE.recipeBuilder("generated_milled_redstone")
                .inputItems(rawOre, Redstone, 16)
                .inputItems(AluminiumGrindBall, 1)
                .outputItems(generate_milled, Redstone, 96)
                .duration(100)
                .EUt(GTValues.VA[GTValues.LV])
                .save(provider);

    }
}
