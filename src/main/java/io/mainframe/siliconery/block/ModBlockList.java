package io.mainframe.siliconery.block;

import io.mainframe.siliconery.block.rubber.ModBlockRubberLeaves;
import io.mainframe.siliconery.block.rubber.ModBlockRubberLog;
import io.mainframe.siliconery.block.rubber.ModBlockRubberSapling;
import io.mainframe.siliconery.world.ModTreeGrowers;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import static io.mainframe.siliconery.block.ModBlockTools.registerBlock;

public class ModBlockList {
    public static final net.minecraft.world.level.block.Block CASING = registerBlock(
            ModBlockItemIds.CASING,
            net.minecraft.world.level.block.Block::new,
            BlockBehaviour.Properties.of().sound(SoundType.METAL)
    );
    public static final net.minecraft.world.level.block.Block RUBBER_LOG = ModBlockTools.registerBlock(
            ModBlockItemIds.RUBBER_LOG,
            ModBlockRubberLog::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD).randomTicks()
    );

    public static final net.minecraft.world.level.block.Block RUBBER_LEAVES = ModBlockTools.registerBlock(
            ModBlockItemIds.RUBBER_LEAVES,
            ModBlockRubberLeaves::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F).sound(SoundType.GRASS)
                    .randomTicks().noOcclusion().isValidSpawn((_, _, _, _) -> false)
                    .isSuffocating((_, _, _) -> false).isViewBlocking((_, _, _) -> false)
    );

    public static final net.minecraft.world.level.block.Block RUBBER_SAPLING = registerBlock(
            ModBlockItemIds.RUBBER_SAPLING,
            props -> new ModBlockRubberSapling(ModTreeGrowers.RUBBER, props),
            BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().randomTicks()
                    .instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)
    );

    @SuppressWarnings("EmptyMethod")
    public static void initialize() { }
}