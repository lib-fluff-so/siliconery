package io.mainframe.siliconery.world;

import io.mainframe.siliconery.block.ModBlockList;
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

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> RUBBER_TREE =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    io.mainframe.siliconery.Siliconery.id("rubber_tree")
            );

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ZINC =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    io.mainframe.siliconery.Siliconery.id("ore_zinc")
            );

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

        context.register(
                ORE_ZINC,
                new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(List.of(
                                OreConfiguration.target(stoneReplaceable, ModBlockList.ZINC_ORE.defaultBlockState()),
                                OreConfiguration.target(deepslateReplaceable, ModBlockList.DEEPSLATE_ZINC_ORE.defaultBlockState())
                        ), 8)
                )
        );
    }
}