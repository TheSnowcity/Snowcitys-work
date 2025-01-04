//package com.snowcity.snowcityswork.api.machine;
//
//import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
//import com.gregtechceu.gtceu.api.machine.MetaMachine;
//import com.gregtechceu.gtceu.api.machine.SimpleGeneratorMachine;
//import com.gregtechceu.gtceu.api.recipe.GTRecipe;
//import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
//import it.unimi.dsi.fastutil.ints.Int2LongFunction;
//
//public class SWGeneratorMachine extends SimpleGeneratorMachine{
//    private final int efficiency;
//
//    public SWGeneratorMachine(IMachineBlockEntity holder, int tier,
//                              Int2LongFunction tankScalingFunction, Object... args) {
//        super(holder, tier, tankScalingFunction, args);
//        this.efficiency = getEfficiency(tier);
//    }
//
//    public static int getEfficiency(int tier) {
//        return tier * 20 + 100;
//    }
//
//    public static GTRecipe nonParallel(MetaMachine machine, GTRecipe recipe) {
//        if (machine instanceof SWGeneratorMachine) {
//            long eut = RecipeHelper.getOutputEUt(recipe);
//            GTRecipe recipeModified = recipe.copy();
//            RecipeHelper.setOutputEUt(recipeModified, eut * ((SWGeneratorMachine) machine).efficiency / 100);
//            return recipeModified;
//        }
//        return null;
//    }
//
//    public static GTRecipe parallel(MetaMachine machine, GTRecipe recipe) {
//        GTRecipe recipeModifier = nonParallel(machine, recipe);
//        return recipeModifier != null ? nonParallel(machine, recipeModifier) : null;
//    }
//}
