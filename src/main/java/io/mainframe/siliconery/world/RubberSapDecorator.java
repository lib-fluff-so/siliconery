package io.mainframe.siliconery.world;

import com.mojang.serialization.MapCodec;
import io.mainframe.siliconery.Siliconery;
import io.mainframe.siliconery.block.Blocks;
import io.mainframe.siliconery.block.RubberLogBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class RubberSapDecorator extends TreeDecorator {

    public static final MapCodec<RubberSapDecorator> CODEC =
            MapCodec.unit(RubberSapDecorator::new);

    public RubberSapDecorator() {
    }

    @Override
    public void place(Context context) {
        RandomSource random = context.random();

        for (BlockPos pos : context.logs()) {
            BlockState state = context.level().getBlockState(pos);

            if (!state.is(Blocks.RUBBER_LOG)) {
                continue;
            }

            if (random.nextFloat() >= 0.2F) {
                continue;
            }

            Direction[] sides = {
                    Direction.NORTH,
                    Direction.SOUTH,
                    Direction.EAST,
                    Direction.WEST
            };

            Direction side = sides[random.nextInt(4)];

            context.setBlock(
                    pos,
                    state
                            .setValue(RubberLogBlock.HAS_SAP, true)
                            .setValue(RubberLogBlock.CAN_TAP, true)
                            .setValue(RubberLogBlock.SAP_SIDE, side)
            );
        }
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return BuiltInRegistries.TREE_DECORATOR_TYPE.getValue(
                Siliconery.id("rubber_sap")
        );
    }
}