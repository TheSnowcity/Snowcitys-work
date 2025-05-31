package com.snowcity.snowcityswork.common.item.behaviors;


import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.component.IInteractionItem;

import com.gregtechceu.gtceu.api.item.component.IItemComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GrindBallBehavior implements IItemComponent {

    private final int maxDurability;

    public GrindBallBehavior(int maxDurability) {
        this.maxDurability = maxDurability;
    }

    public int getBallDurabilityPercent(ItemStack itemStack) {
        return 100 - 100 * getDamage(itemStack) / maxDurability;
    }

    public int getDamage(ItemStack itemstack) {
        CompoundTag tag = itemstack.getTag();
        if (tag == null || !tag.contains("Damage", Tag.TAG_ANY_NUMERIC)) {
            return 0;
        }
        return tag.getInt("Damage");
    }

    public void setDamage(ItemStack itemstack, int damage) {
        CompoundTag tag = itemstack.getOrCreateTag();
        tag.putInt("Damage", Math.min(maxDurability, Math.max(0, damage)));
    }

    public void applyGrindBallDamage(ItemStack itemStack, int damageApplied) {
        int resultDamage = getDamage(itemStack) + damageApplied;
        if (resultDamage >= maxDurability) {
            itemStack.shrink(1);
        } else {
            setDamage(itemStack, resultDamage);
        }
    }

    public void appendHoverText(ItemStack itemstack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.tooltip.durability", maxDurability - getDamage(itemstack), maxDurability));
    }

    @Nullable
    public static GrindBallBehavior getBehavior(@NotNull ItemStack itemStack) {
        Item item = itemStack.getItem();
        if (item instanceof ComponentItem componentItem) {
            for (IItemComponent component : componentItem.getComponents()) {
                if (component instanceof GrindBallBehavior behavior) {
                    return behavior;
                }
            }
        }
        return null;
    }

}
