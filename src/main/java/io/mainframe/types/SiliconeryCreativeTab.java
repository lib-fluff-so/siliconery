package io.mainframe.types;

import io.mainframe.Siliconery;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class SiliconeryCreativeTab {
    public static final ResourceKey<CreativeModeTab> CREATIVE_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(Siliconery.MOD_ID, "creative_tab")
    );
    public static final CreativeModeTab CREATIVE_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(SiliconeryItems.MGSI))
            .title(Component.translatable("creativeTab.siliconery"))
            .displayItems((params, output) -> {
                output.accept(SiliconeryItems.MGSI);
                output.accept(SiliconeryItems.MGSI_POWDER);
                output.accept(SiliconeryItems.SLAG);
                output.accept(SiliconeryItems.FORGE_HAMMER);
                output.accept(SiliconeryItems.CUTTER);
                output.accept(SiliconeryBlock.CASING.asItem());
                for (Plateable mat : Plateable.values()) {
                    output.accept(SiliconeryItems.PLATES.get(mat));
                    output.accept(SiliconeryItems.CASINGS.get(mat));
                }
            })
            
            .build();

    public static void initialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CREATIVE_TAB_KEY, CREATIVE_TAB);
    }
}
