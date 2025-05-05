package com.snowcity.snowcityswork.data;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.tterrag.registrate.util.entry.ItemEntry;

import static com.snowcity.snowcityswork.common.registry.SWRegistration.SNOWCITYSWORK_REGISTRATE;

public class SWItems {

    static {
        SNOWCITYSWORK_REGISTRATE.creativeModeTab(() -> SWCreativeModeTabs.ITEMS);
    }

    public static ItemEntry<ComponentItem> AluminiumGrindBall = SNOWCITYSWORK_REGISTRATE
            .item("aluminium_grindball", ComponentItem::create)
            .lang("Aluminium GrindBall")
            .register();
    public static void init() {
    }
}
