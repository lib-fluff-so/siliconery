package io.mainframe.siliconery.world;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class SiliconeryFoliagePlacerTypes {
    public static final FoliagePlacerType<RubberFoliagePlacer> RUBBER = Registry.register(
        BuiltInRegistries.FOLIAGE_PLACER_TYPE,
        Identifier.fromNamespaceAndPath("siliconery", "rubber"),
        new FoliagePlacerType<>(RubberFoliagePlacer.CODEC)
    );

    public static void initialize() {}
}