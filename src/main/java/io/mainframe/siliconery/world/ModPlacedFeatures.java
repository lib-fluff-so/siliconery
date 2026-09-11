package io.mainframe.siliconery.world;

import io.mainframe.siliconery.misc.ModOreable;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static io.mainframe.siliconery.Siliconery.id;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> RUBBER_TREE_JUNGLE =
            ResourceKey.create(
                    Registries.PLACED_FEATURE,
                    id("rubber_tree_jungle")
            );

    public static final ResourceKey<PlacedFeature> RUBBER_TREE_JUNGLE_SECTION =
            ResourceKey.create(
                    Registries.PLACED_FEATURE,
                    id("rubber_tree_jungle_section")
            );

    public static final ResourceKey<PlacedFeature> RUBBER_TREE_SPARSE_JUNGLE =
            ResourceKey.create(
                    Registries.PLACED_FEATURE,
                    id("rubber_tree_sparse_jungle")
            );

    public static final ResourceKey<PlacedFeature> RUBBER_TREE_SPARSE_JUNGLE_SECTION =
            ResourceKey.create(
                    Registries.PLACED_FEATURE,
                    id("rubber_tree_sparse_jungle_section")
            );

    public static final ResourceKey<PlacedFeature> RUBBER_TREE_BAMBOO_JUNGLE =
            ResourceKey.create(
                    Registries.PLACED_FEATURE,
                    id("rubber_tree_bamboo_jungle")
            );

    public static final ResourceKey<PlacedFeature> RUBBER_TREE_BAMBOO_JUNGLE_SECTION =
            ResourceKey.create(
                    Registries.PLACED_FEATURE,
                    id("rubber_tree_bamboo_jungle_section")
            );

    public static final Map<ModOreable, ResourceKey<PlacedFeature>> ORES = new EnumMap<>(ModOreable.class);
    static {
        for (ModOreable ore : ModOreable.values()) {
            ORES.put(ore, ResourceKey.create(Registries.PLACED_FEATURE, id("ore_" + ore.name)));
        }
    }

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        Holder<ConfiguredFeature<?, ?>> rubberTree =
                context.lookup(Registries.CONFIGURED_FEATURE)
                        .getOrThrow(
                                ModConfiguredFeatures.RUBBER_TREE
                        );

        PlacementModifier surface =
                HeightmapPlacement.onHeightmap(
                        Heightmap.Types.WORLD_SURFACE_WG
                );

        context.register(
                RUBBER_TREE_JUNGLE,
                new PlacedFeature(rubberTree, List.of(
                                RarityFilter.onAverageOnceEvery(30),
                                InSquarePlacement.spread(),
                                surface,
                                BiomeFilter.biome()))
        );

        context.register(
                RUBBER_TREE_JUNGLE_SECTION,
                new PlacedFeature(rubberTree, List.of(
                                RarityFilter.onAverageOnceEvery(128),
                                CountPlacement.of(3),
                                InSquarePlacement.spread(),
                                surface,
                                BiomeFilter.biome()))
        );

        context.register(RUBBER_TREE_SPARSE_JUNGLE, new PlacedFeature(
                        rubberTree,
                        List.of(
                                RarityFilter.onAverageOnceEvery(60),
                                InSquarePlacement.spread(),
                                surface,
                                BiomeFilter.biome()))
        );

        context.register(RUBBER_TREE_SPARSE_JUNGLE_SECTION, new PlacedFeature(
                        rubberTree,
                        List.of(
                                RarityFilter.onAverageOnceEvery(256),
                                CountPlacement.of(3),
                                InSquarePlacement.spread(),
                                surface,
                                BiomeFilter.biome()))
        );

        context.register(RUBBER_TREE_BAMBOO_JUNGLE, new PlacedFeature(
                        rubberTree,
                        List.of(
                                RarityFilter.onAverageOnceEvery(60),
                                InSquarePlacement.spread(),
                                surface,
                                BiomeFilter.biome()))
        );

        context.register(RUBBER_TREE_BAMBOO_JUNGLE_SECTION, new PlacedFeature(
                        rubberTree,
                        List.of(
                                RarityFilter.onAverageOnceEvery(256),
                                CountPlacement.of(3),
                                InSquarePlacement.spread(),
                                surface,
                                BiomeFilter.biome()))
        );

        for (ModOreable ore : ModOreable.values()) {
            Holder<ConfiguredFeature<?, ?>> oreFeature = context.lookup(Registries.CONFIGURED_FEATURE)
                            .getOrThrow(ModConfiguredFeatures.ORES.get(ore));

            context.register(ORES.get(ore), new PlacedFeature(
                    oreFeature, List.of(
                            CountPlacement.of(ore.veinsPerChunk),
                            InSquarePlacement.spread(),
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(ore.minY),
                                    VerticalAnchor.absolute(ore.maxY)), BiomeFilter.biome())
                    )
            );
        }
    }
}