/*
 * This file is part of TechReborn, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2020 TechReborn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

// CHANGED A BIT BY fluff_.

package io.mainframe.siliconery.block.rubber;


import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import org.jspecify.annotations.NonNull;

public class ModBlockRubberLeaves extends LeavesBlock {
    public static final MapCodec<ModBlockRubberLeaves> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(propertiesCodec()).apply(instance, ModBlockRubberLeaves::new)
    );

    public ModBlockRubberLeaves(Properties settings) { super(0.01F, settings); }

    @Override
    public @NonNull MapCodec<ModBlockRubberLeaves> codec() { return CODEC; }

    @Override
    protected void spawnFallingLeavesParticle(@NonNull Level world, @NonNull BlockPos pos, @NonNull RandomSource random) {
        ColorParticleOption entityEffectParticleEffect = ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, 0xff4d6148);
        ParticleUtils.spawnParticleBelow(world, pos, random, entityEffectParticleEffect);
    }
}
