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

        SWRecipeTypes.ISAMILL_RECIPE.recipeBuilder("generated_milled_almandine")
                .inputItems(rawOre, Almandine, 16)
                .inputItems(AluminiumGrindBall, 1)
                .outputItems(generate_milled, Almandine, 96)
                .duration(100)
                .EUt(GTValues.VA[GTValues.LV])
                .save(provider);

        SWRecipeTypes.ISAMILL_RECIPE.recipeBuilder("generated_milled_chalcopyrite")
                .inputItems(rawOre, Chalcopyrite, 16)
                .inputItems(AluminiumGrindBall, 1)
                .outputItems(generate_milled, Chalcopyrite, 96)
                .duration(100)
                .EUt(GTValues.VA[GTValues.LV])
                .save(provider);

        SWRecipeTypes.ISAMILL_RECIPE.recipeBuilder("generated_milled_grossular")
                .inputItems(rawOre, Grossular, 16)
                .inputItems(AluminiumGrindBall, 1)
                .outputItems(generate_milled, Grossular, 96)
                .duration(100)
                .EUt(GTValues.VA[GTValues.LV])
                .save(provider);

        SWRecipeTypes.ISAMILL_RECIPE.recipeBuilder("generated_milled_monazite")
                .inputItems(rawOre, Monazite, 16)
                .inputItems(AluminiumGrindBall, 1)
                .outputItems(generate_milled, Monazite, 96)
                .duration(100)
                .EUt(GTValues.VA[GTValues.LV])
                .save(provider);

        SWRecipeTypes.ISAMILL_RECIPE.recipeBuilder("generated_milled_nickel")
                .inputItems(rawOre, Nickel, 16)
                .inputItems(AluminiumGrindBall, 1)
                .outputItems(generate_milled, Nickel, 96)
                .duration(100)
                .EUt(GTValues.VA[GTValues.LV])
                .save(provider);

        SWRecipeTypes.ISAMILL_RECIPE.recipeBuilder("generated_milled_platinum")
                .inputItems(rawOre, Platinum, 16)
                .inputItems(AluminiumGrindBall, 1)
                .outputItems(generate_milled, Platinum, 96)
                .duration(100)
                .EUt(GTValues.VA[GTValues.LV])
                .save(provider);

        SWRecipeTypes.ISAMILL_RECIPE.recipeBuilder("generated_milled_pyrope")
                .inputItems(rawOre, Pyrope, 16)
                .inputItems(AluminiumGrindBall, 1)
                .outputItems(generate_milled, Pyrope, 96)
                .duration(100)
                .EUt(GTValues.VA[GTValues.LV])
                .save(provider);

        SWRecipeTypes.ISAMILL_RECIPE.recipeBuilder("generated_milled_spessartine")
                .inputItems(rawOre, Spessartine, 16)
                .inputItems(AluminiumGrindBall, 1)
                .outputItems(generate_milled, Spessartine, 96)
                .duration(100)
                .EUt(GTValues.VA[GTValues.LV])
                .save(provider);

        SWRecipeTypes.ISAMILL_RECIPE.recipeBuilder("generated_milled_sphalerite")
                .inputItems(rawOre, Sphalerite, 16)
                .inputItems(AluminiumGrindBall, 1)
                .outputItems(generate_milled, Sphalerite, 96)
                .duration(100)
                .EUt(GTValues.VA[GTValues.LV])
                .save(provider);

        SWRecipeTypes.ISAMILL_RECIPE.recipeBuilder("generated_milled_pentlandite")
                .inputItems(rawOre, Pentlandite, 16)
                .inputItems(AluminiumGrindBall, 1)
                .outputItems(generate_milled, Pentlandite, 96)
                .duration(100)
                .EUt(GTValues.VA[GTValues.LV])
                .save(provider);
    }
}
