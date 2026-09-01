package io.mainframe.siliconery.item;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import static io.mainframe.siliconery.item.Item.createItemId;

public class ItemIds {
	public static final ResourceKey<Item> MGSI = createItemId("mgsi");
	public static final ResourceKey<Item> MGSI_POWDER = createItemId("mgsi_powder");
	public static final ResourceKey<Item> SLAG = createItemId("slag");
	public static final ResourceKey<Item> FORGE_HAMMER = createItemId("forge_hammer");
	public static final ResourceKey<Item> CUTTER = createItemId("cutter");
	public static final ResourceKey<Item> TREETAP = createItemId("treetap");
	public static final ResourceKey<Item> LATEX = createItemId("latex");

	public static ResourceKey<Item> plate(String materialName) { return createItemId(materialName + "_plate"); }
	public static ResourceKey<Item> casing(String materialName) { return createItemId(materialName + "_casing"); }
}

