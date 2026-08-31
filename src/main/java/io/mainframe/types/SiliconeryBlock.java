package io.mainframe.types;

import io.mainframe.RubberLogBlock;
import io.mainframe.RubberLeavesBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Function;

public class SiliconeryBlock {
    public static final Block CASING = registerBlock(
            SiliconeryBlockIds.CASING,
            Block::new,
            BlockBehaviour.Properties.of().sound(SoundType.METAL)
    );
    public static final Block RUBBER_LOG = registerBlock(
            SiliconeryBlockIds.RUBBER_LOG,
            RubberLogBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD).randomTicks()
    );

    public static final Block RUBBER_LEAVES = registerBlock(
            SiliconeryBlockIds.RUBBER_LEAVES,
            RubberLeavesBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F).sound(SoundType.GRASS)
                    .randomTicks().noOcclusion().isValidSpawn((state, level, pos, type) -> false)
                    .isSuffocating((state, level, pos) -> false).isViewBlocking((state, level, pos) -> false)
    );

//    public static final Block RUBBER_SAPLING = registerBlock(
//            SiliconeryBlockIds.RUBBER_SAPLING,
//            props -> new RubberSaplingBlock(SiliconeryTreeGrowers.RUBBER, props),
//            BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().randomTicks()
//                    .instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)
//    );

    private static Block registerBlock(ResourceKey<Block> id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        // Create the block instance
        Block block = blockFactory.apply(properties.setId(id));

        return Registry.register(BuiltInRegistries.BLOCK, id, block);
    }

    private static Block registerBlock(BlockItemId id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        // Create the block instance
        Block block = registerBlock(id.block(), blockFactory, properties);

        // Create the block item instance
        BlockItem blockItem = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(id.item()));
        Registry.register(BuiltInRegistries.ITEM, id.item(), blockItem);

        return block;
    }

    public static void initialize() {
    }
}