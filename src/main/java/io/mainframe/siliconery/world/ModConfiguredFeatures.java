package io.mainframe.siliconery.world;

import io.mainframe.siliconery.block.ModBlockList;
import io.mainframe.siliconery.generated.ModOreable;
import io.mainframe.siliconery.world.rubber.ModRubberFoliagePlacer;
import io.mainframe.siliconery.world.rubber.ModRubberSapDecorator;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> RUBBER_TREE =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    io.mainframe.siliconery.Siliconery.id("rubber_tree")
            );

    public static final Map<ModOreable, ResourceKey<ConfiguredFeature<?, ?>>> ORES = new EnumMap<>(ModOreable.class);
    static {
        for (ModOreable ore : ModOreable.values()) {
            ORES.put(ore, ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    io.mainframe.siliconery.Siliconery.id("ore_" + ore.name)
            ));
        }
    }

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        context.register(
                RUBBER_TREE,
                new ConfiguredFeature<>(Feature.TREE,
                        new TreeConfiguration.TreeConfigurationBuilder(
                                BlockStateProvider.simple(ModBlockList.RUBBER_LOG.defaultBlockState()),
                                new StraightTrunkPlacer(5, 2, 0),
                                BlockStateProvider.simple(ModBlockList.RUBBER_LEAVES.defaultBlockState()),
                                new ModRubberFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                                new TwoLayersFeatureSize(1, 0, 1),
                                BlockStateProvider.simple(net.minecraft.world.level.block.Blocks.DIRT.defaultBlockState())
                        ).decorators(List.of(new ModRubberSapDecorator())).ignoreVines().build()
                )
        );

        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        for (ModOreable ore : ModOreable.values()) {
            context.register(
                    ORES.get(ore),
                    new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(List.of(
                                    OreConfiguration.target(stoneReplaceable, ModBlockList.ORES.get(ore).defaultBlockState()),
                                    OreConfiguration.target(deepslateReplaceable, ModBlockList.DEEPSLATE_ORES.get(ore).defaultBlockState())
                            ), ore.veinSize)
                    )
            );
        }
    }
}