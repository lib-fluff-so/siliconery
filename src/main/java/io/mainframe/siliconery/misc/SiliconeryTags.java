package io.mainframe.siliconery.misc;

import io.mainframe.siliconery.block.BlockIds;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class SiliconeryTags extends FabricTagsProvider.BlockTagsProvider {

    public SiliconeryTags(
            FabricPackOutput output,
            CompletableFuture<HolderLookup.Provider> registriesFuture
    ) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        builder(BlockTags.LEAVES)
                .add(BlockIds.RUBBER_LEAVES);

        builder(BlockTags.LOGS)
                .add(BlockIds.RUBBER_LOG);

        builder(BlockTags.OVERWORLD_NATURAL_LOGS)
                .add(BlockIds.RUBBER_LOG);
    }
}