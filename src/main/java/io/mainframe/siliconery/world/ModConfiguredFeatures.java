package io.mainframe.siliconery.world;

import io.mainframe.siliconery.block.ModBlockList;
import io.mainframe.siliconery.world.rubber.ModRubberFoliagePlacer;
import io.mainframe.siliconery.world.rubber.ModRubberSapDecorator;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> RUBBER_TREE =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    io.mainframe.siliconery.Siliconery.id("rubber_tree")
            );

    public static void bootstrap(
            BootstrapContext<ConfiguredFeature<?, ?>> context
    ) {
        context.register(
                RUBBER_TREE,
                new ConfiguredFeature<>(
                        Feature.TREE,
                        new TreeConfiguration.TreeConfigurationBuilder(
                                BlockStateProvider.simple(
                                        ModBlockList.RUBBER_LOG.defaultBlockState()
                                ),
                                new StraightTrunkPlacer(5, 2, 0),
                                BlockStateProvider.simple(
                                        ModBlockList.RUBBER_LEAVES.defaultBlockState()
                                ),
                                new ModRubberFoliagePlacer(
                                        ConstantInt.of(2),
                                        ConstantInt.of(0),
                                        3
                                ),
                                new TwoLayersFeatureSize(1, 0, 1),
                                BlockStateProvider.simple(
                                        net.minecraft.world.level.block.Blocks.DIRT.defaultBlockState()
                                )
                        )
                        .decorators(List.of(
                                new ModRubberSapDecorator()
                        ))
                        .ignoreVines()
                        .build()
                )
        );
    }
}