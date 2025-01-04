package com.snowcity.snowcityswork.data.recipes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.snowcity.snowcityswork.data.SWRecipeTypes;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class VoltaicPile {
    public static void init(Consumer<FinishedRecipe> consumer) {

        SWRecipeTypes.VOLTAIC_PILE_RECIPE.recipeBuilder("voltaic_pile")
                .inputItems(TagPrefix.rod, GTMaterials.Copper)
                .inputItems(TagPrefix.rod, GTMaterials.Zinc)
                .inputFluids(GTMaterials.SulfuricAcid.getFluid(1000))
                .outputFluids(GTMaterials.SulfuricAcid.getFluid(1000))
                .duration(10000)
                .EUt(-GTValues.V[GTValues.LV])
                .save(consumer);
    }
}
