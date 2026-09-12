package io.mainframe.siliconery.block;

import net.minecraft.references.BlockItemId;

import static io.mainframe.siliconery.block.ModBlockTools.createBlockItemId;

public class ModBlockItemIds {
    public static final BlockItemId CASING = createBlockItemId("casing");
    public static final BlockItemId RUBBER_LOG = createBlockItemId("rubber_log");
    public static final BlockItemId RUBBER_LEAVES = createBlockItemId("rubber_leaves");
    public static final BlockItemId RUBBER_SAPLING = createBlockItemId("rubber_sapling");
    public static final BlockItemId TEMPLATE_WORKBENCH = createBlockItemId("template_workbench");

    public static BlockItemId ore(String materialName) { return createBlockItemId(materialName + "_ore"); }
    public static BlockItemId deepslateOre(String materialName) { return createBlockItemId("deepslate_" + materialName + "_ore"); }
    public static BlockItemId storageBlock(String materialName) { return createBlockItemId(materialName + "_block"); }
}
