package io.mainframe.siliconery.misc;

import io.mainframe.siliconery.Siliconery;
import io.mainframe.siliconery.block.Blocks;
import io.mainframe.siliconery.item.Items;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreativeTab {
    public static final ResourceKey<CreativeModeTab> CREATIVE_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(Siliconery.MOD_ID, "creative_tab")
    );
    public static final CreativeModeTab CREATIVE_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(Items.MGSI))
            .title(Component.translatable("creativeTab.siliconery"))
            .displayItems((params, output) -> {
                output.accept(Items.MGSI);
                output.accept(Items.MGSI_POWDER);
                output.accept(Items.SLAG);
                output.accept(Items.FORGE_HAMMER);
                output.accept(Items.CUTTER);
                output.accept(Items.TREETAP);
                output.accept(Blocks.CASING.asItem());
                for (Plateable mat : Plateable.values()) {
                    output.accept(Items.PLATES.get(mat));
                    output.accept(Items.CASINGS.get(mat));
                }
            })
            
            .build();

    public static void initialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CREATIVE_TAB_KEY, CREATIVE_TAB);
    }
}
