package com.snowcity.snowcityswork.data.tags;

import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.snowcity.snowcityswork.data.SWItems;
import com.snowcity.snowcityswork.data.SWTags;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ItemTagLoader {
    public static void init(RegistrateTagsProvider<Item> provider) {
        provider.addTag(SWTags.GRINDBALL);
    }
}
