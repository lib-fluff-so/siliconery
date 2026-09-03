package io.mainframe.siliconery.misc;

import io.mainframe.siliconery.Siliconery;
import io.mainframe.siliconery.block.ModBlockList;
import io.mainframe.siliconery.item.ModItemList;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTab {
    public static final ResourceKey<CreativeModeTab> CREATIVE_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), Siliconery.id("creative_tab")
    );
    public static final CreativeModeTab CREATIVE_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItemList.MGSI))
            .title(Component.translatable("creativeTab.siliconery"))
            .displayItems((_, output) -> {
                output.accept(ModItemList.MGSI);
                output.accept(ModItemList.MGSI_POWDER);
                output.accept(ModItemList.SLAG);
                output.accept(ModItemList.FORGE_HAMMER);
                output.accept(ModItemList.CUTTER);
                output.accept(ModItemList.TREETAP);
                output.accept(ModItemList.LATEX);
                output.accept(ModItemList.RUBBER);
                output.accept(ModItemList.CHEWING_GUM);
                output.accept(ModBlockList.CASING.asItem());
                output.accept(ModBlockList.RUBBER_LEAVES.asItem());
                output.accept(ModBlockList.RUBBER_LOG.asItem());
                output.accept(ModBlockList.RUBBER_SAPLING.asItem());
                for (ModPlateable mat : ModPlateable.values()) {
                    output.accept(ModItemList.PLATES.get(mat));
                    output.accept(ModItemList.CASINGS.get(mat));
                }
            })
            
            .build();

    public static void initialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CREATIVE_TAB_KEY, CREATIVE_TAB);
    }
}
