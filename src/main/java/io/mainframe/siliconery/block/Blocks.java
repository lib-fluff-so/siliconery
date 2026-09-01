package io.mainframe.siliconery.block;

import io.mainframe.siliconery.block.RubberLeavesBlock;
import io.mainframe.siliconery.block.RubberLogBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import static io.mainframe.siliconery.block.Block.registerBlock;

public class Blocks {
    public static final net.minecraft.world.level.block.Block CASING = registerBlock(
            io.mainframe.siliconery.block.BlockIds.CASING,
            net.minecraft.world.level.block.Block::new,
            BlockBehaviour.Properties.of().sound(SoundType.METAL)
    );
    public static final net.minecraft.world.level.block.Block RUBBER_LOG = io.mainframe.siliconery.block.Block.registerBlock(
            io.mainframe.siliconery.block.BlockIds.RUBBER_LOG,
            RubberLogBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD).randomTicks()
    );

    public static final net.minecraft.world.level.block.Block RUBBER_LEAVES = io.mainframe.siliconery.block.Block.registerBlock(
            io.mainframe.siliconery.block.BlockIds.RUBBER_LEAVES,
            RubberLeavesBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F).sound(SoundType.GRASS)
                    .randomTicks().noOcclusion().isValidSpawn((state, level, pos, type) -> false)
                    .isSuffocating((state, level, pos) -> false).isViewBlocking((state, level, pos) -> false)
    );

    public static void initialize() { }

//    public static final Blocks RUBBER_SAPLING = registerBlock(
//            BlockIds.RUBBER_SAPLING,
//            props -> new RubberSaplingBlock(SiliconeryTreeGrowers.RUBBER, props),
//            BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().randomTicks()
//                    .instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)
//    );
}