package com.snowcity.snowcityswork.data.tags;

import com.gregtechceu.gtceu.data.tags.ItemTagLoader;
import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.world.item.Item;

public class TagsHandler {
    public static void initItem(RegistrateItemTagsProvider provider) {
        ItemTagLoader.init(provider);
    }
}
