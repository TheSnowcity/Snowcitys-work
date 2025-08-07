package com.snowcity.snowcityswork.api.item.properties;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.IMaterialProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.MaterialProperties;

public class GenerateMilledProperty implements IMaterialProperty {
    private final boolean generateMilled;
    private int color = -1; // 默认-1表示使用原材料颜色

    public GenerateMilledProperty(boolean generateMilled) {
        this.generateMilled = generateMilled;
    }

    public int getColor(Material material) {
        return color != -1 ? color : material.getMaterialRGB();
    }

    public boolean shouldGenerateMilled() {
        return generateMilled;
    }

    @Override
    public void verifyProperty(MaterialProperties materialProperties) {
        // 实现验证逻辑
    }
}
