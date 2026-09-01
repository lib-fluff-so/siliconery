package io.mainframe.siliconery.misc;

import io.mainframe.siliconery.block.Blocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class SiliconeryLootTables extends FabricBlockLootSubProvider {
    public SiliconeryLootTables(
            FabricPackOutput output,
            CompletableFuture<HolderLookup.Provider> registries
    ) {
        super(output, registries);
    }

    @Override
    public void generate() {
        dropSelf(Blocks.CASING);
        dropSelf(Blocks.RUBBER_LOG);
        dropSelf(Blocks.RUBBER_SAPLING);
        add(Blocks.RUBBER_LEAVES,
                createLeavesDrops(
                        Blocks.RUBBER_LEAVES,
                        Blocks.RUBBER_SAPLING,
                        NORMAL_LEAVES_SAPLING_CHANCES
                ));
    }
}