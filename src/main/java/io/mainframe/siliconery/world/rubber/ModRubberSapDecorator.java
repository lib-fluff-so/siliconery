package io.mainframe.siliconery.world.rubber;

import com.mojang.serialization.MapCodec;
import io.mainframe.siliconery.Siliconery;
import io.mainframe.siliconery.block.ModBlockList;
import io.mainframe.siliconery.block.rubber.ModBlockRubberLog;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.jspecify.annotations.NonNull;

import java.util.Objects;

public class ModRubberSapDecorator extends TreeDecorator {

    public static final MapCodec<ModRubberSapDecorator> CODEC =
            MapCodec.unit(ModRubberSapDecorator::new);

    public ModRubberSapDecorator() {
    }

    @Override
    public void place(Context context) {
        RandomSource random = context.random();

        for (BlockPos pos : context.logs()) {
            BlockState state = context.level().getBlockState(pos);

            if (!state.is(ModBlockList.RUBBER_LOG)) {
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
                            .setValue(ModBlockRubberLog.HAS_SAP, true)
                            .setValue(ModBlockRubberLog.CAN_TAP, true)
                            .setValue(ModBlockRubberLog.SAP_SIDE, side)
            );
        }
    }

    @Override
    protected @NonNull TreeDecoratorType<?> type() {
        return Objects.requireNonNull(BuiltInRegistries.TREE_DECORATOR_TYPE.getValue(
                Siliconery.id("rubber_sap")
        ));
    }
}