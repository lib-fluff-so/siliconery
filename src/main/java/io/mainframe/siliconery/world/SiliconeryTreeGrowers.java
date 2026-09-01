package io.mainframe.siliconery.world;

import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class SiliconeryTreeGrowers {
    public static final TreeGrower RUBBER = new TreeGrower(
            "siliconery:rubber",
            Optional.of(SiliconeryConfiguredFeatures.RUBBER_TREE),
            Optional.of(SiliconeryConfiguredFeatures.RUBBER_TREE),
            Optional.of(SiliconeryConfiguredFeatures.RUBBER_TREE)
    );
}