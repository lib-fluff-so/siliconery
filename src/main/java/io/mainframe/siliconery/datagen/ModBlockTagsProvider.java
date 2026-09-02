package io.mainframe.siliconery.datagen;

import io.mainframe.siliconery.block.ModBlockItemIds;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {

    public ModBlockTagsProvider(
            FabricPackOutput output,
            CompletableFuture<HolderLookup.Provider> registriesFuture
    ) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider wrapperLookup) {
        builder(BlockTags.LEAVES)
                .add(ModBlockItemIds.RUBBER_LEAVES);

        builder(BlockTags.LOGS)
                .add(ModBlockItemIds.RUBBER_LOG);

        builder(BlockTags.OVERWORLD_NATURAL_LOGS)
                .add(ModBlockItemIds.RUBBER_LOG);
    }

    @Override public @NonNull String getName() { return "Block Tags"; }
}