package io.mainframe.siliconery.item;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import static io.mainframe.siliconery.item.ModItemTools.createItemId;

public class ModItemIds {
    public static final ResourceKey<Item> MGSI = createItemId("mgsi");
    public static final ResourceKey<Item> MGSI_POWDER = createItemId("mgsi_powder");
    public static final ResourceKey<Item> SLAG = createItemId("slag");
    public static final ResourceKey<Item> FORGE_HAMMER = createItemId("forge_hammer");
    public static final ResourceKey<Item> CUTTER = createItemId("cutter");
    public static final ResourceKey<Item> TREETAP = createItemId("treetap");
    public static final ResourceKey<Item> LATEX = createItemId("latex");
    public static final ResourceKey<Item> RUBBER = createItemId("rubber");
    public static final ResourceKey<Item> CHEWING_GUM = createItemId("chewing_gum");
    public static final ResourceKey<Item> CROSS_HEAD_BLANK = createItemId("cross_head_blank");
    public static final ResourceKey<Item> THREAD_BLANK = createItemId("thread_blank");
    public static final ResourceKey<Item> SCREW_TEMPLATE = createItemId("screw_template");
    public static ResourceKey<Item> plate(String materialName) { return createItemId(materialName + "_plate"); }
    public static ResourceKey<Item> casing(String materialName) { return createItemId(materialName + "_casing"); }
    public static ResourceKey<Item> raw(String materialName) { return createItemId("raw_" + materialName); }
    public static ResourceKey<Item> ingot(String materialName) { return createItemId(materialName + "_ingot"); }
    public static ResourceKey<Item> nugget(String materialName) { return createItemId(materialName + "_nugget"); }
    public static ResourceKey<Item> dust(String materialName) { return createItemId(materialName + "_dust"); }
}

