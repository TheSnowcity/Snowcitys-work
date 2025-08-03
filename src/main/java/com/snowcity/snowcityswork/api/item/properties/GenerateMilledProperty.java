package com.snowcity.snowcityswork.api.item.properties;

import com.gregtechceu.gtceu.api.data.chemical.material.properties.IMaterialProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.MaterialProperties;

public class GenerateMilledProperty implements IMaterialProperty {
    private final boolean generateMilled;

    public GenerateMilledProperty(boolean generateMilled) {
        this.generateMilled = generateMilled;
    }

    public boolean shouldGenerateMilled() {
        return generateMilled;
    }

    @Override
    public void verifyProperty(MaterialProperties materialProperties) {
        // 实现验证逻辑
    }
}
