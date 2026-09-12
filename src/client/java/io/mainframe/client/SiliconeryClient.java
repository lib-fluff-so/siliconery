package io.mainframe.client;

import io.mainframe.client.screen.TemplateWorkbenchScreen;
import io.mainframe.siliconery.block.entity.ModMenuTypes;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;

public class SiliconeryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        MenuScreens.register(ModMenuTypes.TEMPLATE_WORKBENCH, TemplateWorkbenchScreen::new);
    }
}