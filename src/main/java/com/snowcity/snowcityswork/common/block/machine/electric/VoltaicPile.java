package com.snowcity.snowcityswork.common.block.machine.electric;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import com.gregtechceu.gtceu.common.data.machines.GTMachineUtils;

public class VoltaicPile extends SimpleTieredMachine{
    public VoltaicPile(IMachineBlockEntity holder, int tier, Object... args) {
        super(holder, tier, GTMachineUtils.defaultTankSizeFunction, args);
    }

    @Override
    public boolean shouldWeatherOrTerrainExplosion() {
        return false;
    }
}