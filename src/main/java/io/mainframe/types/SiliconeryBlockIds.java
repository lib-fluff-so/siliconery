package io.mainframe.types;

import io.mainframe.Siliconery;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;

public class SiliconeryBlockIds {

    public static final BlockItemId CASING = createBlockItemId("casing");
    public static final BlockItemId RUBBER_LOG = createBlockItemId("rubber_log");
    public static final BlockItemId RUBBER_LEAVES = createBlockItemId("rubber_leaves");
    public static final BlockItemId RUBBER_SAPLING = createBlockItemId("rubber_sapling");

    private static BlockItemId createBlockItemId(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(Siliconery.MOD_ID, name);
        return BlockItemId.create(id, id);
    }
}
