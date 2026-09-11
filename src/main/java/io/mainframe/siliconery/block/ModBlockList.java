package io.mainframe.siliconery.block;

import io.mainframe.siliconery.block.rubber.ModBlockRubberLeaves;
import io.mainframe.siliconery.block.rubber.ModBlockRubberLog;
import io.mainframe.siliconery.block.rubber.ModBlockRubberSapling;
import io.mainframe.siliconery.misc.ModOreable;
import io.mainframe.siliconery.misc.ModProcessable;
import io.mainframe.siliconery.world.ModTreeGrowers;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.EnumMap;
import java.util.Map;

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

    public static final Map<ModOreable, net.minecraft.world.level.block.Block> ORES = new EnumMap<>(ModOreable.class);
    public static final Map<ModOreable, net.minecraft.world.level.block.Block> DEEPSLATE_ORES = new EnumMap<>(ModOreable.class);
    static {
        for (ModOreable ore : ModOreable.values()) {
            net.minecraft.world.level.block.Block oreBlock = registerBlock(
                    ModBlockItemIds.ore(ore.name),
                    net.minecraft.world.level.block.Block::new,
                    BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE)
                            .requiresCorrectToolForDrops().strength(ore.hardness, 3.0F)
            );
            ORES.put(ore, oreBlock);

            net.minecraft.world.level.block.Block deepslateOreBlock = registerBlock(
                    ModBlockItemIds.deepslateOre(ore.name),
                    net.minecraft.world.level.block.Block::new,
                    BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).sound(SoundType.DEEPSLATE)
                            .requiresCorrectToolForDrops().strength(ore.deepslateHardness, 3.0F)
            );
            DEEPSLATE_ORES.put(ore, deepslateOreBlock);
        }
    }

    public static final Map<ModProcessable, net.minecraft.world.level.block.Block> BLOCKS = new EnumMap<>(ModProcessable.class);
    static {
        for (ModProcessable mat : ModProcessable.values()) {
            if (!mat.hasBlock) continue;
            net.minecraft.world.level.block.Block storageBlock = registerBlock(
                    ModBlockItemIds.storageBlock(mat.name),
                    net.minecraft.world.level.block.Block::new,
                    BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL)
                            .requiresCorrectToolForDrops().strength(5.0F, 6.0F)
            );
            BLOCKS.put(mat, storageBlock);
        }
    }

    @SuppressWarnings("EmptyMethod")
    public static void initialize() { }
}