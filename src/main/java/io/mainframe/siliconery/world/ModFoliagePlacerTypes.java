package io.mainframe.siliconery.world;

import io.mainframe.siliconery.Siliconery;
import io.mainframe.siliconery.world.rubber.ModRubberFoliagePlacer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class ModFoliagePlacerTypes {
    public static final FoliagePlacerType<ModRubberFoliagePlacer> RUBBER = Registry.register(
        BuiltInRegistries.FOLIAGE_PLACER_TYPE,
        Siliconery.id("rubber"),
        new FoliagePlacerType<>(ModRubberFoliagePlacer.CODEC)
    );

    public static void initialize() {}
}