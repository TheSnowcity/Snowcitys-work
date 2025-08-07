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
import com.snowcity.snowcityswork.api.item.behaviors.GrindBallBehavior;
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
    private ISubscription bufferSubs;
    private TickableSubscription transferSubs;

    public GrindBallHatchPartMachine(IMachineBlockEntity holder) {
        super(holder, GTValues.IV, IO.IN);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (!isRemote()) {
            inventorySubs = grindBallInventory.addChangedListener(this::onInventoryChanged);
            bufferSubs = buffer.addChangedListener(this::onInventoryChanged);
        }
    }

    @Override
    public void onUnload() {
        super.onUnload();
        if (inventorySubs != null) inventorySubs.unsubscribe();
        if (bufferSubs != null) bufferSubs.unsubscribe();
    }

    private NotifiableItemStackHandler createGrindBallInventory() {
        return new NotifiableItemStackHandler(this, 4, IO.IN, IO.OUT, slots -> new CustomItemStackHandler(slots) {
            @Override
            public int getSlotLimit(int slot) {
                return 1; // 每槽最多一个物品
            }
        }) {
            @Override
            public Set<String> getSlotNames() {
                return Set.of(GRINDBALL);
            }

            @Override
            public List<Ingredient> handleRecipeInner(IO io, GTRecipe recipe, List<Ingredient> left, String slotName, boolean simulate) {
                if (io != handlerIO) return left;
                if (slotName != null && !GRINDBALL.equals(slotName)) return left;

                IItemHandlerModifiable capability;

                if (simulate) {
                    NonNullList<ItemStack> items = NonNullList.create();
                    for (int i = 0; i < storage.getSlots(); i++) {
                        items.add(storage.getStackInSlot(i).copy());
                    }
                    capability = new CustomItemStackHandler(items);
                } else {
                    capability = storage;
                }

                Iterator<Ingredient> iterator = left.iterator();

                if (io == IO.IN) {
                    while (iterator.hasNext()) {
                        Ingredient ingredient = iterator.next();

                        System.out.println("If correct");

                        // 遍历槽中的物品
                        for (int i = 0; i < capability.getSlots(); i++) {
                            ItemStack item = capability.getStackInSlot(i);
                            ItemStack itemStack = simulate ? item.copy() : item;
                            if (ingredient.test(itemStack)) {
                                for (ItemStack ingredientStack : ingredient.getItems()) {
                                    if (ingredientStack.is(itemStack.getItem())) {
                                        GrindBallBehavior behavior = GrindBallBehavior.getBehavior(itemStack);
                                        int count = ingredientStack.getCount();
                                        if (!simulate) {
                                            System.out.println("Not simulate");
                                        }
                                        int damage = 1;
                                        if (behavior != null) {
                                            int applyDamage = Math.min(damage, 100 - behavior.getDamage(itemStack));
                                            behavior.applyGrindBallDamage(itemStack, applyDamage);
                                            ingredientStack.shrink(applyDamage);
                                            // 如果研磨球物品耗尽，转移物品
                                            if (itemStack.isEmpty() || ingredientStack.isEmpty()) {
                                                transferItems();
                                            }
                                        } else {
                                            ItemStack extracted = capability.extractItem(i, count, false);
                                            ingredientStack.shrink(extracted.getCount());
                                        }
                                        // 如果 ingredientStack 为空，移除当前配方项
                                        if (ingredientStack.isEmpty()) {
                                            iterator.remove();
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                return left.isEmpty() ? null : left;
            }
        };
    }

    private int calculateDamageAmount(ItemStack stack) {
//        float chance = 1.0f; // Replace with logic if needed
//        double mean = chance;
//        double stdDev = chance * (1 - chance);
//        return (int) Math.ceil(Math.sqrt(stdDev) * GTValues.RNG.nextGaussian() + mean);
        return 1;
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
        addSlots(container, grindBallInventory, 55, 4, false);

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