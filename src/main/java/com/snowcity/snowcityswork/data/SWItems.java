package com.snowcity.snowcityswork.data;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.snowcity.snowcityswork.common.item.behaviors.GrindBallBehavior;
import com.tterrag.registrate.util.entry.ItemEntry;

import static com.gregtechceu.gtceu.common.data.GTItems.attach;
import static com.snowcity.snowcityswork.common.registry.SWRegistration.SNOWCITYSWORK_REGISTRATE;

public class SWItems {

    static {
        SNOWCITYSWORK_REGISTRATE.creativeModeTab(() -> SWCreativeModeTabs.ITEMS);
    }

    public static ItemEntry<ComponentItem> AluminiumGrindBall = SNOWCITYSWORK_REGISTRATE
            .item("aluminium_grindball", ComponentItem::create)
            .lang("Aluminium GrindBall")
            .onRegister(attach(new GrindBallBehavior(100)))
            .register();
    public static ItemEntry<ComponentItem> SoapStoneGrindBall = SNOWCITYSWORK_REGISTRATE
            .item("soapstone_grindball", ComponentItem::create)
            .lang("SoapStone GrindBall")
            .register();
    public static ItemEntry<ComponentItem> GenerateMilledRedstone = SNOWCITYSWORK_REGISTRATE
            .item("generate_milled_redstone", ComponentItem::create)
            .lang("Generate Milled Redstone")
            .register();

    public static void init() {
    }
}
