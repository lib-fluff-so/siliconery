package io.mainframe.siliconery.world.fabric;

import io.mainframe.siliconery.generated.ModOreable;
import io.mainframe.siliconery.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModWorldGeneration {
    @SafeVarargs
    private static ResourceKey<Biome>[] biomes(ResourceKey<Biome>... keys) {
        return keys;
    }

    private static final ResourceKey<Biome>[] FOREST_BIOMES = biomes(
            Biomes.FOREST,
            Biomes.FLOWER_FOREST,
            Biomes.BIRCH_FOREST,
            Biomes.OLD_GROWTH_BIRCH_FOREST,
            Biomes.DARK_FOREST
    );

    private static final ResourceKey<Biome>[] TAIGA_BIOMES = biomes(
            Biomes.TAIGA,
            Biomes.SNOWY_TAIGA,
            Biomes.OLD_GROWTH_PINE_TAIGA,
            Biomes.OLD_GROWTH_SPRUCE_TAIGA
    );

    public static void register() {
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(FOREST_BIOMES),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacedFeatures.RUBBER_TREE_FOREST
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(FOREST_BIOMES),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacedFeatures.RUBBER_TREE_FOREST_SECTION
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(TAIGA_BIOMES),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacedFeatures.RUBBER_TREE_TAIGA
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(TAIGA_BIOMES),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacedFeatures.RUBBER_TREE_TAIGA_SECTION
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.SWAMP),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacedFeatures.RUBBER_TREE_SWAMPLAND
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacedFeatures.RUBBER_TREE_JUNGLE
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.SPARSE_JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacedFeatures.RUBBER_TREE_SPARSE_JUNGLE
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.BAMBOO_JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacedFeatures.RUBBER_TREE_BAMBOO_JUNGLE
        );

        for (ModOreable ore : ModOreable.values()) {
            BiomeModifications.addFeature(
                    BiomeSelectors.foundInOverworld(),
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    ModPlacedFeatures.ORES.get(ore)
            );
        }
    }
}