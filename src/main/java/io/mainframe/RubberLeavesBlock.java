package io.mainframe;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jspecify.annotations.NonNull;

public class RubberLeavesBlock extends LeavesBlock {
    public static final MapCodec<RubberLeavesBlock> CODEC = simpleCodec(RubberLeavesBlock::new);

    public RubberLeavesBlock(BlockBehaviour.Properties properties) {
        super(0.1F, properties); // 0.1F — leafParticleChance, как у ванильных деревьев
    }

    @Override
    public @NonNull MapCodec<? extends LeavesBlock> codec() {
        return CODEC;
    }

    @Override
    protected void spawnFallingLeavesParticle(@NonNull Level level, @NonNull BlockPos pos, @NonNull RandomSource random) {
        // ванильные деревья используют LeavesBlock.SoundType-подобную логику через ParticleUtils,
        // проще всего скопировать поведение обычных OakLeaves — глянь в decompiled OakLeaves.java,
        // либо просто оставить пустым (без частиц) для первой версии:
    }
}