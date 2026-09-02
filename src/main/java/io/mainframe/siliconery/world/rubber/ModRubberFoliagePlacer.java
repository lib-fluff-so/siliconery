package io.mainframe.siliconery.world.rubber;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.mainframe.siliconery.world.ModFoliagePlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jspecify.annotations.NonNull;

public class ModRubberFoliagePlacer extends BlobFoliagePlacer {
    public static final MapCodec<ModRubberFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            foliagePlacerParts(instance).apply(instance, (radius, offset) -> new ModRubberFoliagePlacer(radius, offset, 3))
    );

    public ModRubberFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset, height);
    }

    @Override
    protected @NonNull FoliagePlacerType<?> type() {
        return ModFoliagePlacerTypes.RUBBER;
    }

    @Override
    protected void createFoliage(@NonNull WorldGenLevel level, @NonNull FoliageSetter foliageSetter, @NonNull RandomSource random, @NonNull TreeConfiguration config, int maxFreeTreeHeight, @NonNull FoliageAttachment attachment, int foliageHeight, int radius, int offset) {
        super.createFoliage(level, foliageSetter, random, config, maxFreeTreeHeight, attachment, foliageHeight, radius, offset);

        BlockPos top = attachment.pos();
        int spikeHeight = 2 + random.nextInt(3);
        for (int i = 1; i <= spikeHeight; i++) {
            BlockPos pos = top.above(i);
            foliageSetter.set(pos, config.foliageProvider.getState(level, random, pos));
        }
    }
}