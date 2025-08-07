package com.snowcity.snowcityswork.api.item.behaviors;

import com.gregtechceu.gtceu.api.item.TagPrefixItem;
import net.minecraft.client.color.item.ItemColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TagPrefixBehavior {
    @OnlyIn(Dist.CLIENT)
    public static ItemColor tintColor() {
        return (itemStack, index) -> {
            if (itemStack.getItem() instanceof TagPrefixItem tagPrefixItem) {
                return tagPrefixItem.material.getLayerARGB(index);
            }
            return -1;
        };
    }
}
