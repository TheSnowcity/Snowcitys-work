package com.snowcity.snowcityswork.data;

import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.snowcity.snowcityswork.api.item.properties.GenerateMilledProperty;

public class SWPropertyKeys {
    public static final PropertyKey<GenerateMilledProperty> GENERATE_MILLED =
            new PropertyKey<>("generate_milled", GenerateMilledProperty.class);
}
