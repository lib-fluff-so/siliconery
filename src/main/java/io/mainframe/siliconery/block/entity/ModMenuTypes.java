package io.mainframe.siliconery.block.entity;

import io.mainframe.siliconery.Siliconery;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.MenuType;

public class ModMenuTypes {
    public static final MenuType<TemplateWorkbenchMenu> TEMPLATE_WORKBENCH = Registry.register(
            BuiltInRegistries.MENU,
            Siliconery.id("template_workbench"),
            new MenuType<>(TemplateWorkbenchMenu::new, FeatureFlagSet.of())
    );

    @SuppressWarnings("EmptyMethod")
    public static void initialize() { }
}
