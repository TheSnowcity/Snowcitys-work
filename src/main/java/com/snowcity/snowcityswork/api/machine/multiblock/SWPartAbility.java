package com.snowcity.snowcityswork.api.machine.multiblock;

import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class SWPartAbility extends PartAbility {

    public SWPartAbility(String name) {
        super(name);
    }

    public static final PartAbility GRINDBALL = new PartAbility("grindball");

    public static <T> T getOrDefault(T value, Supplier<T> defaultSupplier) {
        return value != null ? value : defaultSupplier.get();
    }

    public static <T> T getOrDefault(BooleanSupplier canGet, Supplier<T> getter, T defaultValue) {
        return canGet.getAsBoolean() ? getter.get() : defaultValue;
    }
}