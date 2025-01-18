package com.snowcity.snowcityswork.data.recipes;

import com.gregtechceu.gtceu.api.GTValues;
import com.snowcity.snowcityswork.data.SWRecipeTypes;
import com.snowcity.snowcityswork.data.SWMaterials;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;

public class VoltaicPileRecipe {
    public static void init(Consumer<FinishedRecipe> provider) {

        SWRecipeTypes.VOLTAIC_PILE_RECIPE.recipeBuilder("useless_fluid")
                .inputItems(plate,Copper)
                .inputItems(plate,Zinc)
                .inputFluids(SulfuricAcid.getFluid(1000))
                .outputFluids(SWMaterials.UselessFluid.getFluid(1000))
                .duration(1000)
                .EUt(-GTValues.VA[GTValues.HV])
                .save(provider);
    }
}
