package io.mainframe.siliconery.datagen;

import io.mainframe.siliconery.block.ModBlockItemIds;
import io.mainframe.siliconery.misc.ModOreable;
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

        var pickaxe = builder(BlockTags.MINEABLE_WITH_PICKAXE);
        var stoneTool = builder(BlockTags.NEEDS_STONE_TOOL);
        for (ModOreable ore : ModOreable.values()) {
            pickaxe.add(ModBlockItemIds.ore(ore.name)).add(ModBlockItemIds.deepslateOre(ore.name));
            stoneTool.add(ModBlockItemIds.ore(ore.name)).add(ModBlockItemIds.deepslateOre(ore.name));
        }
    }

    @Override public @NonNull String getName() { return "Block Tags"; }
}