package io.mainframe.siliconery.block;

import io.mainframe.siliconery.Siliconery;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class BlockProperties {
    public static BlockBehaviour.Properties rubberLeaves(String name) {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES)
                .mapColor(MapColor.PODZOL)
                .setId(key(name));
    }

    public static BlockBehaviour.Properties rubberSapling(String name) {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)
                .mapColor(MapColor.PODZOL)
                .setId(key(name));
    }

    public static BlockBehaviour.Properties rubberLog(String name) {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LOG)
                .randomTicks()
                .mapColor(MapColor.PODZOL)
                .setId(key(name));
    }

    public static ResourceKey<Block> key(String name) {
        return ResourceKey.create(BuiltInRegistries.BLOCK.key(), Identifier.fromNamespaceAndPath(Siliconery.MOD_ID, name));
    }
}
