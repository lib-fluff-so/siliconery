package io.mainframe.siliconery.misc;

import io.mainframe.siliconery.Siliconery;
import io.mainframe.siliconery.block.ModBlockList;
import io.mainframe.siliconery.generated.ModOreable;
import io.mainframe.siliconery.generated.ModProcessable;
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
//                output.accept(ModItemList.MGSI);
//                output.accept(ModItemList.MGSI_POWDER);
//                output.accept(ModItemList.SLAG);
                output.accept(ModItemList.FORGE_HAMMER);
                output.accept(ModItemList.CUTTER);
                output.accept(ModItemList.TREETAP);
                output.accept(ModItemList.LATEX);
                output.accept(ModItemList.RUBBER);
                output.accept(ModItemList.CHEWING_GUM);
                output.accept(ModItemList.CROSS_HEAD_BLANK);
                output.accept(ModItemList.THREAD_BLANK);
                output.accept(ModItemList.SCREW_TEMPLATE);
                output.accept(ModBlockList.CASING.asItem());
                output.accept(ModBlockList.RUBBER_LEAVES.asItem());
                output.accept(ModBlockList.RUBBER_LOG.asItem());
                output.accept(ModBlockList.RUBBER_SAPLING.asItem());
                output.accept(ModBlockList.TEMPLATE_WORKBENCH.asItem());
                for (ModOreable ore : ModOreable.values()) {
                    output.accept(ModItemList.RAW_ORES.get(ore));
                    output.accept(ModBlockList.ORES.get(ore).asItem());
                    output.accept(ModBlockList.DEEPSLATE_ORES.get(ore).asItem());
                }
                for (ModProcessable mat : ModProcessable.values()) {
                    if (mat.hasIngot) output.accept(ModItemList.INGOTS.get(mat));
                    if (mat.hasPlate) output.accept(ModItemList.PLATES.get(mat));
                    if (mat.hasCasing) output.accept(ModItemList.CASINGS.get(mat));
                    if (mat.hasNugget) output.accept(ModItemList.NUGGETS.get(mat));
                    if (mat.hasDust) output.accept(ModItemList.DUSTS.get(mat));
                    if (mat.hasBlock) output.accept(ModBlockList.BLOCKS.get(mat).asItem());
                }
            })
            
            .build();

    public static void initialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CREATIVE_TAB_KEY, CREATIVE_TAB);
    }
}
