package com.snowcity.snowcityswork.data;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class SWTagPrefix {
    public static final TagPrefix generate_milled = new TagPrefix("generate_milled")
            .langValue("精磨%s") // 本地化名称
            .unformattedTagPath("generate_milled");// 自动创建物品标签

    public static void init(){

    }
}