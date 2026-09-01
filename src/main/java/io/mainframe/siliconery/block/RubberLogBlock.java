package io.mainframe.siliconery.block;

import io.mainframe.siliconery.item.Items;
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

public class RubberLogBlock extends RotatedPillarBlock {
    public static final BooleanProperty HAS_SAP = BooleanProperty.create("has_sap");
    public static final BooleanProperty CAN_TAP = BooleanProperty.create("can_tap");
    public static final EnumProperty<Direction> SAP_SIDE = EnumProperty.create("sap_side",
            Direction.class, Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST);

    private static final int MIN_REGEN_TICKS = 1200;
    private static final int MAX_REGEN_TICKS = 2400;

    public RubberLogBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(HAS_SAP, false)
                .setValue(CAN_TAP, false)
                .setValue(SAP_SIDE, Direction.NORTH)
                .setValue(AXIS, Direction.Axis.Y));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<net.minecraft.world.level.block.Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(HAS_SAP, CAN_TAP, SAP_SIDE);
    }

    /** Вызывается один раз при генерации дерева. */
    public static BlockState rollSapGeneration(BlockState state, RandomSource random) {
        if (random.nextFloat() < 0.3F) {
            Direction[] horizontals = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
            Direction side = horizontals[random.nextInt(4)];
            return state.setValue(HAS_SAP, true).setValue(CAN_TAP, true).setValue(SAP_SIDE, side);
        }
        return state.setValue(HAS_SAP, false).setValue(CAN_TAP, false);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, net.minecraft.world.entity.LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide()) {
            BlockState rolled = rollSapGeneration(state, level.getRandom());
            if (rolled != state) {
                level.setBlock(pos, rolled, 3);
            }
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(HAS_SAP) && !state.getValue(CAN_TAP)) {
            level.setBlock(pos, state.setValue(CAN_TAP, true), 3);
        }
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (state.getValue(HAS_SAP) && state.getValue(CAN_TAP)
                && hitResult.getDirection() == state.getValue(SAP_SIDE)
                && stack.is(Items.CUTTER)) {

            if (!level.isClientSide()) {
                RandomSource random = level.getRandom();
                int amount = 1 + random.nextInt(4);

                ItemEntity latex = new ItemEntity(
                        level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        new ItemStack(Items.LATEX, amount)
                );
                level.addFreshEntity(latex);

                if (random.nextFloat() < 0.05F) {
                    level.setBlock(pos, state.setValue(HAS_SAP, false).setValue(CAN_TAP, false), 3);
                } else {
                    level.setBlock(pos, state.setValue(CAN_TAP, false), 3);
                    int delay = MIN_REGEN_TICKS + random.nextInt(MAX_REGEN_TICKS - MIN_REGEN_TICKS);
                    ((ServerLevel) level).scheduleTick(pos, this, delay);
                }

                stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}