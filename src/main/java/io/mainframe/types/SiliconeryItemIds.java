package io.mainframe.types;

import io.mainframe.Siliconery;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class SiliconeryItemIds {
	public static final ResourceKey<Item> MGSI = createItemId("mgsi");
	public static final ResourceKey<Item> MGSI_POWDER = createItemId("mgsi_powder");
	public static final ResourceKey<Item> SLAG = createItemId("slag");
	public static final ResourceKey<Item> FORGE_HAMMER = createItemId("forge_hammer");
	public static final ResourceKey<Item> CUTTER = createItemId("cutter");
	public static final ResourceKey<Item> LATEX = createItemId("latex");

	public static ResourceKey<Item> createItemId(String name) {
		return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Siliconery.MOD_ID, name));
	}

	public static ResourceKey<Item> plate(String materialName) {
		return createItemId(materialName + "_plate");
	}
	public static ResourceKey<Item> casing(String materialName) {
		return createItemId(materialName + "_casing");
	}
}

