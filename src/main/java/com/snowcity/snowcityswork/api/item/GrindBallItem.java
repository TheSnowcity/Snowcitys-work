package com.snowcity.snowcityswork.api.item;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.component.IAddInformation;
import com.gregtechceu.gtceu.api.item.component.IItemComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GrindBallItem extends ComponentItem {
    public GrindBallItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(
            @NotNull ItemStack stack,
            @Nullable Level level,
            @NotNull List<Component> tooltip,
            @NotNull TooltipFlag flag
    ) {
        super.appendHoverText(stack, level, tooltip, flag);

        // ✅ 主动调用所有组件的 tooltip 方法
        for (IItemComponent component : getComponents()) {
            if (component instanceof IAddInformation info) {
                info.appendHoverText(stack, level, tooltip, flag);
            }
        }
    }
}