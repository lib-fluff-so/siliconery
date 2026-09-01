package io.mainframe.siliconery.world;

import io.mainframe.siliconery.Siliconery;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class SiliconeryTreeGrowers {
    public static final TreeGrower RUBBER = new TreeGrower(
            Siliconery.MOD_ID+"rubber",
            Optional.empty(),
            Optional.of(SiliconeryConfiguredFeatures.RUBBER_TREE),
            Optional.empty()
    );
}