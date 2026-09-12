package io.mainframe.siliconery.block;

import io.mainframe.siliconery.block.entity.TemplateWorkbenchBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.NonNull;

// Opens the base-slot Menu for now; dynamic sub-slots come once mode logic exists
public class TemplateWorkbenchBlock extends BaseEntityBlock {
    public static final MapCodec<TemplateWorkbenchBlock> CODEC = simpleCodec(TemplateWorkbenchBlock::new);

    public TemplateWorkbenchBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NonNull MapCodec<TemplateWorkbenchBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        return new TemplateWorkbenchBlockEntity(pos, state);
    }

    // No ticking needed - it's just a menu opener for now, not a machine with EU/recipe logic yet
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NonNull Level level, @NonNull BlockState state, @NonNull BlockEntityType<T> type) {
        return null;
    }

    @Override
    public @NonNull RenderShape getRenderShape(@NonNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected @NonNull InteractionResult useWithoutItem(@NonNull BlockState state, Level level, @NonNull BlockPos pos, @NonNull Player player, @NonNull BlockHitResult hit) {
        if (!level.isClientSide() && level.getBlockEntity(pos) instanceof TemplateWorkbenchBlockEntity workbench) {
            player.openMenu(workbench);
        }
        return InteractionResult.SUCCESS;
    }
}
