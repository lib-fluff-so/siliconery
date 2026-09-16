package io.mainframe.siliconery.world;

import io.mainframe.siliconery.generated.ModOreable;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static io.mainframe.siliconery.Siliconery.id;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> RUBBER_TREE_FOREST =
            ResourceKey.create(Registries.PLACED_FEATURE, id("rubber_tree_forest"));

    public static final ResourceKey<PlacedFeature> RUBBER_TREE_FOREST_SECTION =
            ResourceKey.create(Registries.PLACED_FEATURE, id("rubber_tree_forest_section"));

    public static final ResourceKey<PlacedFeature> RUBBER_TREE_TAIGA =
            ResourceKey.create(Registries.PLACED_FEATURE, id("rubber_tree_taiga"));

    public static final ResourceKey<PlacedFeature> RUBBER_TREE_TAIGA_SECTION =
            ResourceKey.create(Registries.PLACED_FEATURE, id("rubber_tree_taiga_section"));

    public static final ResourceKey<PlacedFeature> RUBBER_TREE_SWAMPLAND =
            ResourceKey.create(Registries.PLACED_FEATURE, id("rubber_tree_swampland"));

    public static final ResourceKey<PlacedFeature> RUBBER_TREE_JUNGLE =
            ResourceKey.create(Registries.PLACED_FEATURE, id("rubber_tree_jungle"));

    public static final ResourceKey<PlacedFeature> RUBBER_TREE_SPARSE_JUNGLE =
            ResourceKey.create(Registries.PLACED_FEATURE, id("rubber_tree_sparse_jungle"));

    public static final ResourceKey<PlacedFeature> RUBBER_TREE_BAMBOO_JUNGLE =
            ResourceKey.create(Registries.PLACED_FEATURE, id("rubber_tree_bamboo_jungle"));

    public static final Map<ModOreable, ResourceKey<PlacedFeature>> ORES = new EnumMap<>(ModOreable.class);
    static {
        for (ModOreable ore : ModOreable.values()) {
            ORES.put(ore, ResourceKey.create(Registries.PLACED_FEATURE, id("ore_" + ore.name)));
        }
    }

    /**
     * Базовая фича: диапазон percentMin-percentMax усредняется в единственный RarityFilter
     * (честного "плавающего" процента без нойза Minecraft не даёт), countMin-countMax деревьев
     * кладётся за одно срабатывание через CountPlacement + UniformInt.
     */
    private static PlacedFeature rubberTreeFeature(Holder<ConfiguredFeature<?, ?>> rubberTree,
                                                     double percentMin, double percentMax,
                                                     int countMin, int countMax,
                                                     PlacementModifier surface) {
        double avgPercent = (percentMin + percentMax) / 2.0;
        int rarity = Math.max(1, (int) Math.round(100.0 / avgPercent));

        return new PlacedFeature(rubberTree, List.of(
                RarityFilter.onAverageOnceEvery(rarity),
                CountPlacement.of(UniformInt.of(countMin, countMax)),
                InSquarePlacement.spread(),
                surface,
                BiomeFilter.biome()
        ));
    }

    /**
     * Бонусная "section"-фича для биомов с низкой базовой плотностью: редкий шанс положить
     * отдельный плотный пятак деревьев поверх обычной генерации.
     */
    private static PlacedFeature rubberTreeSectionFeature(Holder<ConfiguredFeature<?, ?>> rubberTree,
                                                            int sectionRarity,
                                                            int countMin, int countMax,
                                                            PlacementModifier surface) {
        return new PlacedFeature(rubberTree, List.of(
                RarityFilter.onAverageOnceEvery(sectionRarity),
                CountPlacement.of(UniformInt.of(countMin, countMax)),
                InSquarePlacement.spread(),
                surface,
                BiomeFilter.biome()
        ));
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

        // Desert: 0% — фичу вообще не регистрируем

        // Forest: низкая базовая плотность (2-12% / 1-6 деревьев) + редкий плотный пятак (section)
        context.register(RUBBER_TREE_FOREST,
                rubberTreeFeature(rubberTree, 2, 12, 1, 6, surface));
        context.register(RUBBER_TREE_FOREST_SECTION,
                rubberTreeSectionFeature(rubberTree, 60, 3, 5, surface));

        // Taiga: ещё ниже плотность (0-6% / 0-3 дерева) + свой, более редкий section
        context.register(RUBBER_TREE_TAIGA,
                rubberTreeFeature(rubberTree, 0, 6, 0, 3, surface));
        context.register(RUBBER_TREE_TAIGA_SECTION,
                rubberTreeSectionFeature(rubberTree, 140, 2, 4, surface));

        // Swampland и джунгли — высокая базовая плотность, отдельный section не нужен
        context.register(RUBBER_TREE_SWAMPLAND,
                rubberTreeFeature(rubberTree, 10, 30, 5, 15, surface));

        context.register(RUBBER_TREE_JUNGLE,
                rubberTreeFeature(rubberTree, 10, 20, 5, 10, surface));
        context.register(RUBBER_TREE_SPARSE_JUNGLE,
                rubberTreeFeature(rubberTree, 10, 20, 5, 10, surface));
        context.register(RUBBER_TREE_BAMBOO_JUNGLE,
                rubberTreeFeature(rubberTree, 10, 20, 5, 10, surface));

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
