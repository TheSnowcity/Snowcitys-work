package com.snowcity.snowcityswork.data;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.UnificationEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.machines.GTAEMachines;
import com.gregtechceu.gtceu.common.data.machines.GTResearchMachines;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

import com.snowcity.snowcityswork.Snowcityswork;
import com.snowcity.snowcityswork.data.SWRecipeTypes;
import com.tterrag.registrate.util.entry.ItemEntry;

import java.util.List;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLER_RECIPES;


public class SWRecipe {
    public static void init(Consumer<FinishedRecipe> provider){
        SWRecipeTypes.VOLTAIC_PILE_RECIPE.recipeBuilder("useless_fluid")
                .inputItems(plate,Copper)
                .inputItems(plate,Zinc)
                .inputFluids(SulfuricAcid.getFluid(16000))
                .outputFluids(SWMaterials.UselessFluid.getFluid(16000))
                .duration(1000)
                .EUt(-GTValues.VA[GTValues.HV])
                .save(provider);
    }
}
