package io.mainframe.siliconery.datagen;

import io.mainframe.siliconery.block.ModBlocks;
import io.mainframe.siliconery.item.ModItems;
import io.mainframe.siliconery.generated.ModOreable;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootProvider extends FabricBlockLootSubProvider {
    public ModBlockLootProvider(
            FabricPackOutput output,
            CompletableFuture<HolderLookup.Provider> registries
    ) {
        super(output, registries);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.CASING);
        dropSelf(ModBlocks.RUBBER_LOG);
        dropSelf(ModBlocks.RUBBER_SAPLING);
        dropSelf(ModBlocks.TEMPLATE_WORKBENCH);
        add(ModBlocks.RUBBER_LEAVES,
                createLeavesDrops(ModBlocks.RUBBER_LEAVES, ModBlocks.RUBBER_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES));

        for (ModOreable ore : ModOreable.values()) {
            net.minecraft.world.item.Item raw = ModItems.RAW_ORES.get(ore);
            add(ModBlocks.ORES.get(ore), block -> createOreDrop(block, raw));
            add(ModBlocks.DEEPSLATE_ORES.get(ore), block -> createOreDrop(block, raw));
        }
    }

    @Override public @NonNull String getName() { return "Block Loot Tables"; }
}