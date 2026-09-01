package io.mainframe.siliconery.world;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import io.mainframe.siliconery.block.Blocks;

public class SiliconeryConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> RUBBER_TREE = ResourceKey.create(
        Registries.CONFIGURED_FEATURE,
        io.mainframe.siliconery.Siliconery.id("rubber_tree")
    );

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        context.register(RUBBER_TREE, new ConfiguredFeature<>(
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple((BlockState) Blocks.RUBBER_LOG.defaultBlockState()),
                        new StraightTrunkPlacer(5, 2, 0), // baseHeight=5, heightRandA=2, heightRandB=0 — как у дуба
                        BlockStateProvider.simple((BlockState) Blocks.RUBBER_LEAVES.defaultBlockState()),
                        new RubberFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1),
                        BlockStateProvider.simple(net.minecraft.world.level.block.Blocks.DIRT)
                ).ignoreVines().build()
        ));
    }
}