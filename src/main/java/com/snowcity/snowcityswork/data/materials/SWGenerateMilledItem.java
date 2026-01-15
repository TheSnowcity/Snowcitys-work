package com.snowcity.snowcityswork.data.materials;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.snowcity.snowcityswork.api.item.behaviors.TagPrefixBehavior;
import com.snowcity.snowcityswork.api.item.properties.GenerateMilledProperty;
import com.snowcity.snowcityswork.data.SWPropertyKeys;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelFile;

import java.util.function.Supplier;

import static com.snowcity.snowcityswork.common.registry.SWRegistration.SNOWCITYSWORK_REGISTRATE;
import static com.snowcity.snowcityswork.data.SWTagPrefix.generate_milled;

public class SWGenerateMilledItem {
    public static Table<TagPrefix, Material, ItemEntry<ComponentItem>> MILLED_ITEMS;

    public static void init() {
        generateMilledItems();
    }

    private static void generateMilledItems() {
        ImmutableTable.Builder<TagPrefix, Material, ItemEntry<ComponentItem>> builder = ImmutableTable.builder();

        final ResourceLocation modelLocation = new ResourceLocation("gtceu", "item/material_sets/dull/generate_milled");
        System.out.println("不对不对Model location: " + modelLocation);

        // 遍历所有材料
        GTCEuAPI.materialManager.getRegistries().forEach(registry -> {
            registry.getAllMaterials().forEach(material -> {
                // 检查是否有磨粉属性且设置为true
                if (material.hasProperty(SWPropertyKeys.GENERATE_MILLED)){
                    GenerateMilledProperty property = material.getProperty(SWPropertyKeys.GENERATE_MILLED);
                    if (property.shouldGenerateMilled()) {
                        final int color = property.getColor(material);
                        // 注册磨粉物品
                        builder.put(
                                generate_milled,
                                material,
                                SNOWCITYSWORK_REGISTRATE
                                        .item(generate_milled.idPattern().formatted(material.getName()), ComponentItem::create)
//                                        .color(() -> TagPrefixBehavior::tintColor)
                                        .setData(ProviderType.LANG, NonNullBiConsumer.noop())
                                        .model((ctx, prov) -> {
                                            String materialName = ctx.getName().replace("generate_milled_", "");

                                            prov.getBuilder(ctx.getName())
                                                    .parent(new ModelFile.UncheckedModelFile("item/generated"))
                                                    .texture("layer0", new ResourceLocation("snowcityswork", "item/generate_milled/base"))
                                                    .texture("layer1", new ResourceLocation("snowcityswork", "item/generate_milled/overlay"));
                                        })
                                        .transform(GTItems.unificationItem(generate_milled, material)) // 统一化物品
                                        .register()
                        );
                    }
                }
            });
        });

        MILLED_ITEMS = builder.build();
    }
}
