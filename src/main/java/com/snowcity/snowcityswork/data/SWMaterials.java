package com.snowcity.snowcityswork.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.snowcity.snowcityswork.Snowcityswork;

public class SWMaterials{
    public static final Material UselessFluid = new Material.Builder(GTCEu.id("useless_fluid"))
            .liquid()
            .color(0x999999)
            .buildAndRegister();
    public static void init() {

    }
}
