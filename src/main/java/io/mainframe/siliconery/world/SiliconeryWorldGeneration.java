package io.mainframe.siliconery.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public class SiliconeryWorldGeneration {

    public static void register() {
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                SiliconeryPlacedFeatures.RUBBER_TREE_JUNGLE
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                SiliconeryPlacedFeatures.RUBBER_TREE_JUNGLE_SECTION
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.SPARSE_JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                SiliconeryPlacedFeatures.RUBBER_TREE_SPARSE_JUNGLE
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.SPARSE_JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                SiliconeryPlacedFeatures.RUBBER_TREE_SPARSE_JUNGLE_SECTION
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.BAMBOO_JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                SiliconeryPlacedFeatures.RUBBER_TREE_BAMBOO_JUNGLE
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.BAMBOO_JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                SiliconeryPlacedFeatures.RUBBER_TREE_BAMBOO_JUNGLE_SECTION
        );
    }
}