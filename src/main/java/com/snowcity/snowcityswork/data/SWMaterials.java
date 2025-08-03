package com.snowcity.snowcityswork.data;

import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.OreProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.snowcity.snowcityswork.Snowcityswork;
import com.snowcity.snowcityswork.data.materials.AdjustGTMaterials;
import net.minecraft.world.level.redstone.Redstone;
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.ROUGH;

public class SWMaterials{
    public static final Material UselessFluid = new Material.Builder(GTCEu.id("useless_fluid"))
            .liquid()
            .color(0x999999)
            .buildAndRegister();

    public static final Material RedstoneFront = new Material.Builder(GTCEu.id("redstone_front"))
            .liquid()
            .color(0xff0000).secondaryColor(0x340605).iconSet(ROUGH)
            .buildAndRegister();

    public static void init() {
        AdjustGTMaterials.init();
    }
}
