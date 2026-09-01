package io.mainframe.siliconery.world;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class RubberFoliagePlacer extends BlobFoliagePlacer {
    public static final MapCodec<RubberFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            foliagePlacerParts(instance).apply(instance, (radius, offset) -> new RubberFoliagePlacer(radius, offset, 3))
    );

    public RubberFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset, height);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return SiliconeryFoliagePlacerTypes.RUBBER;
    }

    @Override
    protected void createFoliage(WorldGenLevel level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliageAttachment attachment, int foliageHeight, int radius, int offset) {
        super.createFoliage(level, foliageSetter, random, config, maxFreeTreeHeight, attachment, foliageHeight, radius, offset);

        BlockPos top = attachment.pos();
        int spikeHeight = 2 + random.nextInt(3);
        for (int i = 1; i <= spikeHeight; i++) {
            BlockPos pos = top.above(i);
            foliageSetter.set(pos, config.foliageProvider.getState(level, random, pos));
        }
    }
}