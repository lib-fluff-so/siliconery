package io.mainframe.siliconery.datagen;

import io.mainframe.siliconery.block.ModBlockItemIds;
import io.mainframe.siliconery.generated.ModOreable;
import io.mainframe.siliconery.generated.ModProcessable;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {

    public ModBlockTagsProvider(
            FabricPackOutput output,
            CompletableFuture<HolderLookup.Provider> registriesFuture
    ) {
        super(output, registriesFuture);
    }

    private static TagKey<Block> c(String path) {
        return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("c", path));
    }

    private static TagKey<Block> mineableTag(ModOreable.HarvestTool tool) {
        return switch (tool) {
            case PICKAXE -> BlockTags.MINEABLE_WITH_PICKAXE;
            case AXE -> BlockTags.MINEABLE_WITH_AXE;
            case SHOVEL -> BlockTags.MINEABLE_WITH_SHOVEL;
            case HOE -> BlockTags.MINEABLE_WITH_HOE;
        };
    }

    // null means no minimum tier - vanilla has no "NEEDS_WOOD_TOOL" tag, wood/gold-tier is the baseline
    private static TagKey<Block> tierTag(ModOreable.HarvestTier tier) {
        return switch (tier) {
            case STONE -> BlockTags.NEEDS_STONE_TOOL;
            case IRON -> BlockTags.NEEDS_IRON_TOOL;
            case DIAMOND -> BlockTags.NEEDS_DIAMOND_TOOL;
            case NONE -> null;
        };
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
        for (ModOreable ore : ModOreable.values()) {
            builder(mineableTag(ore.tool)).add(ModBlockItemIds.ore(ore.name)).add(ModBlockItemIds.deepslateOre(ore.name));

            TagKey<Block> tierTag = tierTag(ore.tier);
            if (tierTag != null) {
                builder(tierTag).add(ModBlockItemIds.ore(ore.name)).add(ModBlockItemIds.deepslateOre(ore.name));
            }

            builder(c("ores/" + ore.name)).add(ModBlockItemIds.ore(ore.name)).add(ModBlockItemIds.deepslateOre(ore.name));
            builder(c("ores")).add(ModBlockItemIds.ore(ore.name)).add(ModBlockItemIds.deepslateOre(ore.name));
        }

        for (ModProcessable mat : ModProcessable.values()) {
            if (!mat.hasBlock) continue;
            pickaxe.add(ModBlockItemIds.storageBlock(mat.name));
            builder(c("storage_blocks/" + mat.name)).add(ModBlockItemIds.storageBlock(mat.name));
            builder(c("storage_blocks")).add(ModBlockItemIds.storageBlock(mat.name));
        }
    }

    @Override public @NonNull String getName() { return "Block Tags"; }
}