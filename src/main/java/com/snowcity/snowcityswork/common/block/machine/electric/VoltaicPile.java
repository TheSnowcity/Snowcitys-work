package com.snowcity.snowcityswork.common.block.machine.electric;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import com.gregtechceu.gtceu.common.data.machines.GTMachineUtils;

import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

public class VoltaicPile extends SimpleTieredMachine{
    public VoltaicPile(IMachineBlockEntity holder, int tier, Object... args) {
        super(holder, tier, GTMachineUtils.defaultTankSizeFunction, args);
    }

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(VoltaicPile.class,
            SimpleTieredMachine.MANAGED_FIELD_HOLDER);

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Override
    public boolean shouldWeatherOrTerrainExplosion() {
        return false;
    }
}