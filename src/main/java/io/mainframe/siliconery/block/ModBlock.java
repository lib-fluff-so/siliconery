package io.mainframe.siliconery.block;

import io.mainframe.siliconery.Siliconery;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class ModBlock {
    private static final List<net.minecraft.world.level.block.Block> ALL_BLOCKS = new ArrayList<>();

    public static net.minecraft.world.level.block.Block registerBlock(ResourceKey<net.minecraft.world.level.block.Block> id, Function<BlockBehaviour.Properties, net.minecraft.world.level.block.Block> blockFactory, BlockBehaviour.Properties properties) {
        // Create the block instance
        net.minecraft.world.level.block.Block block = blockFactory.apply(properties.setId(id));

        net.minecraft.world.level.block.Block registered = Registry.register(BuiltInRegistries.BLOCK, id, block);
        ALL_BLOCKS.add(registered);
        return registered;
    }

    public static net.minecraft.world.level.block.Block registerBlock(BlockItemId id, Function<BlockBehaviour.Properties, net.minecraft.world.level.block.Block> blockFactory, BlockBehaviour.Properties properties) {
        // Create the block instance
        net.minecraft.world.level.block.Block block = registerBlock(id.block(), blockFactory, properties);

        // Create the block item instance
        BlockItem blockItem = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(id.item()));
        Registry.register(BuiltInRegistries.ITEM, id.item(), blockItem);

        return block;
    }

    public static Collection<net.minecraft.world.level.block.Block> allRegisteredBlocks() {
        return Collections.unmodifiableList(ALL_BLOCKS);
    }

    public static BlockItemId createBlockItemId(String name) {
        Identifier id = Siliconery.id(name);
        return BlockItemId.create(id, id);
    }

    @SuppressWarnings("EmptyMethod")
    public static void initialize() { }
}