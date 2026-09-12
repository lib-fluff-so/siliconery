package io.mainframe.siliconery.datagen;

import io.mainframe.siliconery.block.ModBlockList;
import io.mainframe.siliconery.item.ModItemList;
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
        dropSelf(ModBlockList.CASING);
        dropSelf(ModBlockList.RUBBER_LOG);
        dropSelf(ModBlockList.RUBBER_SAPLING);
        dropSelf(ModBlockList.TEMPLATE_WORKBENCH);
        add(ModBlockList.RUBBER_LEAVES,
                createLeavesDrops(ModBlockList.RUBBER_LEAVES, ModBlockList.RUBBER_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES));

        for (ModOreable ore : ModOreable.values()) {
            net.minecraft.world.item.Item raw = ModItemList.RAW_ORES.get(ore);
            add(ModBlockList.ORES.get(ore), block -> createOreDrop(block, raw));
            add(ModBlockList.DEEPSLATE_ORES.get(ore), block -> createOreDrop(block, raw));
        }
    }

    @Override public @NonNull String getName() { return "Block Loot Tables"; }
}