package io.mainframe.client;

import io.mainframe.siliconery.block.Blocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagEntry;

import java.util.concurrent.CompletableFuture;

public class SiliconeryBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public SiliconeryBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        getOrCreateRawBuilder(BlockTags.LOGS).add(TagEntry.element(Identifier.fromNamespaceAndPath("siliconery", "rubber_log")));
    }
}