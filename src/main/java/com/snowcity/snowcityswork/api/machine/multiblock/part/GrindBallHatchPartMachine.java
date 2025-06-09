package com.snowcity.snowcityswork.api.machine.multiblock.part;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.widget.SlotWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.transfer.item.CustomItemStackHandler;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.ISubscription;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.snowcity.snowcityswork.common.item.behaviors.GrindBallBehavior;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class GrindBallHatchPartMachine extends TieredIOPartMachine {

    @Persisted
    private final NotifiableItemStackHandler grindBallInventory = createGrindBallInventory();
    @Persisted
    private final NotifiableItemStackHandler buffer = new NotifiableItemStackHandler(this, 4, IO.NONE, IO.BOTH);

    private ISubscription inventorySubs;
    private TickableSubscription transferSubs;

    public GrindBallHatchPartMachine(IMachineBlockEntity holder) {
        super(holder, GTValues.IV, IO.IN);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (!isRemote()) {
            inventorySubs = grindBallInventory.addChangedListener(this::onInventoryChanged);
            buffer.addChangedListener(this::onInventoryChanged);
        }
    }

    @Override
    public void onUnload() {
        super.onUnload();
        if (inventorySubs != null) inventorySubs.unsubscribe();
        if (transferSubs != null) transferSubs.unsubscribe();
    }

    private NotifiableItemStackHandler createGrindBallInventory() {
        return new NotifiableItemStackHandler(this, 4, IO.IN, IO.OUT, slots -> new CustomItemStackHandler(slots) {
            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }
        }) {
            @Override
            public Set<String> getSlotNames() {
                return Set.of(GRINDBALL);
            }

            @Override
            public List<Ingredient> handleRecipeInner(IO io, GTRecipe recipe, List<Ingredient> left, String slotName, boolean simulate) {
                if (io != handlerIO || (slotName != null && !GRINDBALL.equals(slotName))) return left;

                CustomItemStackHandler handler;
                if (simulate) {
                    NonNullList<ItemStack> items = NonNullList.create();
                    for (int i = 0; i < storage.getSlots(); i++) {
                        items.add(storage.getStackInSlot(i));
                    }
                    handler = new CustomItemStackHandler(items);
                } else {
                    handler = storage;
                }
                Iterator<Ingredient> it = left.iterator();

                while (it.hasNext()) {
                    Ingredient ingredient = it.next();

                    for (int i = 0; i < handler.getSlots(); i++) {
                        ItemStack stack = handler.getStackInSlot(i);
                        if (ingredient.test(stack)) {
                            GrindBallBehavior behavior = GrindBallBehavior.getBehavior(stack);
                            int damage = simulate ? 1 : calculateDamageAmount(stack);

                            if (behavior != null) {
                                behavior.applyGrindBallDamage(stack, damage);
                            } else {
                                handler.extractItem(i, damage, false);
                            }

                            if (!simulate) {
                                break;
                                //ingredient.shrink(damage);
                            }

                            if (ingredient.isEmpty()) {
                                it.remove();
                                break;
                            }
                        }
                    }
                }

                return left.isEmpty() ? null : left;
            }
        };
    }

    private int calculateDamageAmount(ItemStack stack) {
        float chance = 1.0f; // Replace with logic if needed
        double mean = chance;
        double stdDev = chance * (1 - chance);
        return (int) Math.ceil(Math.sqrt(stdDev) * GTValues.RNG.nextGaussian() + mean);
    }

    private void onInventoryChanged() {
        if (isWorkingEnabled() && !buffer.isEmpty()) {
            transferSubs = subscribeServerTick(transferSubs, this::transferItems);
        } else {
            unsubscribe();
        }
    }

    private void transferItems() {
        for (int i = 0; i < buffer.getSlots(); i++) {
            ItemStack stack = buffer.getStackInSlot(i);
            if (stack.isEmpty() || !grindBallInventory.getStackInSlot(i).isEmpty()) continue;
            if (!buffer.extractItem(i, 1, true).isEmpty()) {
                ItemStack copy = stack.copyWithCount(1);
                if (grindBallInventory.insertItemInternal(i, copy, true).isEmpty()) {
                    buffer.extractItem(i, 1, false);
                    grindBallInventory.insertItemInternal(i, copy, false);
                }
            }
        }
        unsubscribe();
    }
    private void unsubscribe() {
        if (transferSubs != null) {
            transferSubs.unsubscribe();
            transferSubs = null;
        }
    }

    @Override
    public Widget createUIWidget() {
        WidgetGroup group = new WidgetGroup(0, 0, 18 * 4 + 31, 18 * 2 + 16);
        WidgetGroup container = new WidgetGroup(4, 4, 18 * 4 + 23, 18 * 2 + 8);

//        // 箭头指示器
//        container.addWidget(new ImageWidget(75, 13, 18, 18, CatalystHatchPartMachine.SMALL_ARROW_OVERLAY));

        // 添加左侧 buffer 槽
        addSlots(container, buffer, 4, 4, true);

        // 添加右侧 grindBallInventory 槽
        addSlots(container, grindBallInventory, 54, 4, false);

        container.setBackground(GuiTextures.BACKGROUND_INVERSE);
        group.addWidget(container);
        return group;
    }

    private void addSlots(WidgetGroup container, NotifiableItemStackHandler handler, int x, int y, boolean canPut) {
        int index = 0;
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 2; i++) {
                container.addWidget(new SlotWidget(handler, index++, x + i * 18, y + j * 18, true, canPut)
                        .setBackground(GuiTextures.SLOT));
            }
        }
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Override
    public void setWorkingEnabled(boolean workingEnable){
        super.setWorkingEnabled(workingEnable);
        onInventoryChanged();
    }

    public static final ManagedFieldHolder MANAGED_FIELD_HOLDER =
            new ManagedFieldHolder(GrindBallHatchPartMachine.class, TieredIOPartMachine.MANAGED_FIELD_HOLDER);

    public static final String GRINDBALL = "grindball";
}