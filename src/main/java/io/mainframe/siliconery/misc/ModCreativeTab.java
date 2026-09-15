package io.mainframe.siliconery.misc;

import io.mainframe.siliconery.Siliconery;
import io.mainframe.siliconery.block.ModBlocks;
import io.mainframe.siliconery.generated.ModOreable;
import io.mainframe.siliconery.generated.ModProcessable;
import io.mainframe.siliconery.item.ModItems;
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
    public static final CreativeModeTab CREATIVE_TAB = CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .icon(() -> new ItemStack(ModItems.MGSI))
            .title(Component.translatable("creativeTab.siliconery"))
            .displayItems((_, output) -> {
//                output.accept(ModItems.MGSI);
//                output.accept(ModItems.MGSI_POWDER);
//                output.accept(ModItems.SLAG);
                output.accept(ModItems.FORGE_HAMMER);
                output.accept(ModItems.CUTTER);
                output.accept(ModItems.TREETAP);
                output.accept(ModItems.LATEX);
                output.accept(ModItems.RUBBER);
                output.accept(ModItems.CHEWING_GUM);
                output.accept(ModItems.CROSS_HEAD_BLANK);
                output.accept(ModItems.THREAD_BLANK);
                output.accept(ModItems.SCREW_TEMPLATE);
                output.accept(ModItems.CASING_TEMPLATE);
                output.accept(ModItems.SCREW);
                output.accept(ModBlocks.CASING.asItem());
                output.accept(ModBlocks.RUBBER_LEAVES.asItem());
                output.accept(ModBlocks.RUBBER_LOG.asItem());
                output.accept(ModBlocks.RUBBER_SAPLING.asItem());
                output.accept(ModBlocks.TEMPLATE_WORKBENCH.asItem());
                for (ModOreable ore : ModOreable.values()) {
                    output.accept(ModItems.RAW_ORES.get(ore));
                    output.accept(ModBlocks.ORES.get(ore).asItem());
                    output.accept(ModBlocks.DEEPSLATE_ORES.get(ore).asItem());
                }
                for (ModProcessable mat : ModProcessable.values()) {
                    if (mat.hasIngot) output.accept(ModItems.INGOTS.get(mat));
                    if (mat.hasPlate) output.accept(ModItems.PLATES.get(mat));
                    if (mat.hasCasing) output.accept(ModItems.CASINGS.get(mat));
                    if (mat.hasNugget) output.accept(ModItems.NUGGETS.get(mat));
                    if (mat.hasDust) output.accept(ModItems.DUSTS.get(mat));
                    if (mat.hasBlock) output.accept(ModBlocks.BLOCKS.get(mat).asItem());
                }
            })
            
            .build();

    public static void initialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CREATIVE_TAB_KEY, CREATIVE_TAB);
    }
}
