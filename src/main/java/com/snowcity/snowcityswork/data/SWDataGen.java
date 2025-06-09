package com.snowcity.snowcityswork.data;

import com.snowcity.snowcityswork.common.registry.SWRegistration;
import com.snowcity.snowcityswork.data.tags.TagsHandler;
import com.tterrag.registrate.providers.ProviderType;

public class SWDataGen {
    public static void init(){
        SWRegistration.SNOWCITYSWORK_REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, TagsHandler::initItem);
    }
}
