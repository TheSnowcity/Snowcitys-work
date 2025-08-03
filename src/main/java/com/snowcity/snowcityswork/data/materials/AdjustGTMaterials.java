package com.snowcity.snowcityswork.data.materials;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.snowcity.snowcityswork.api.item.properties.GenerateMilledProperty;
import com.snowcity.snowcityswork.data.SWPropertyKeys;

public class AdjustGTMaterials {
    public static void init(){
        GTMaterials.Redstone.setProperty(SWPropertyKeys.GENERATE_MILLED, new GenerateMilledProperty(true));
        GTMaterials.Platinum.setProperty(SWPropertyKeys.GENERATE_MILLED, new GenerateMilledProperty(true));
        GTMaterials.Almandine.setProperty(SWPropertyKeys.GENERATE_MILLED, new GenerateMilledProperty(true));
        GTMaterials.Chalcopyrite.setProperty(SWPropertyKeys.GENERATE_MILLED, new GenerateMilledProperty(true));
        GTMaterials.Grossular.setProperty(SWPropertyKeys.GENERATE_MILLED, new GenerateMilledProperty(true));
        GTMaterials.Monazite.setProperty(SWPropertyKeys.GENERATE_MILLED, new GenerateMilledProperty(true));
        GTMaterials.Nickel.setProperty(SWPropertyKeys.GENERATE_MILLED, new GenerateMilledProperty(true));
        GTMaterials.Pyrope.setProperty(SWPropertyKeys.GENERATE_MILLED, new GenerateMilledProperty(true));
        GTMaterials.Spessartine.setProperty(SWPropertyKeys.GENERATE_MILLED, new GenerateMilledProperty(true));
        GTMaterials.Sphalerite.setProperty(SWPropertyKeys.GENERATE_MILLED, new GenerateMilledProperty(true));
        GTMaterials.Pentlandite.setProperty(SWPropertyKeys.GENERATE_MILLED, new GenerateMilledProperty(true));
    }
}
