package io.mainframe.siliconery.block.rubber;

import io.mainframe.siliconery.item.ModItemList;
import io.mainframe.siliconery.world.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.NonNull;

public class ModBlockRubberLog extends RotatedPillarBlock {
    public static final BooleanProperty HAS_SAP = BooleanProperty.create("has_sap");

    public static final BooleanProperty CAN_TAP = BooleanProperty.create("can_tap");

    public static final EnumProperty<Direction> SAP_SIDE =
            EnumProperty.create(
                    "sap_side",
                    Direction.class,
                    Direction.NORTH,
                    Direction.SOUTH,
                    Direction.EAST,
                    Direction.WEST
            );

    public ModBlockRubberLog(Properties properties) {
        super(properties);

        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(HAS_SAP, false)
                        .setValue(CAN_TAP, false)
                        .setValue(SAP_SIDE, Direction.NORTH)
                        .setValue(AXIS, Direction.Axis.Y)
        );
    }

    @Override
    protected void createBlockStateDefinition(
            StateDefinition.@NonNull Builder<net.minecraft.world.level.block.Block, BlockState> builder
    ) {
        super.createBlockStateDefinition(builder);
        builder.add(HAS_SAP, CAN_TAP, SAP_SIDE);
    }

    @Override
    protected void tick(
            BlockState state,
            @NonNull ServerLevel level,
            @NonNull BlockPos pos,
            @NonNull RandomSource random
    ) {
        if (state.getValue(HAS_SAP) && !state.getValue(CAN_TAP)) {
            level.setBlock(pos, state.setValue(CAN_TAP, true), 3);
        }
    }

    @Override
    protected @NonNull InteractionResult useItemOn(
            @NonNull ItemStack stack,
            BlockState state,
            @NonNull Level level,
            @NonNull BlockPos pos,
            @NonNull Player player,
            @NonNull InteractionHand hand,
            @NonNull BlockHitResult hitResult
    ) {
        if (state.getValue(HAS_SAP)
                && state.getValue(CAN_TAP)
                && hitResult.getDirection() == state.getValue(SAP_SIDE)
                && stack.is(ModItemList.TREETAP)) {

            if (!level.isClientSide()) {
                RandomSource random = level.getRandom();

                int amount = 1 + random.nextInt(Config.RubberLog.MAX_LATEX_RANDOM_BONUS);

                ItemEntity latex = new ItemEntity(
                        level,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        new ItemStack(ModItemList.LATEX, amount)
                );

                level.addFreshEntity(latex);

                if (random.nextFloat() < Config.RubberLog.SAP_EXHAUSTION_CHANCE) {
                    level.setBlock(pos, state.setValue(HAS_SAP, false).setValue(CAN_TAP, false), 3);
                } else {
                    level.setBlock(pos, state.setValue(CAN_TAP, false), 3);

                    int delay = Config.RubberLog.MIN_REGEN_TICKS +
                            random.nextInt(Config.RubberLog.MAX_REGEN_TICKS - Config.RubberLog.MIN_REGEN_TICKS);

                    level.scheduleTick(pos, this, delay);
                }

                stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}