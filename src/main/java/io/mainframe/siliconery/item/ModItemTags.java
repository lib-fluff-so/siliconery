package io.mainframe.siliconery.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

/**
 * Hierarchical `c:` convention item tags (c:ingots/<name>, c:ores/<name>, ...), since 1.21.
 * Single source of truth for the tag paths — used both to generate the tags (ModItemTagsProvider)
 * and to consume them as recipe ingredients (ModRecipeProvider), so a material resolves against
 * any mod's matching item, not just ours.
 */
public class ModItemTags {
    public static TagKey<Item> rawMaterials(String materialName) { return c("raw_materials/" + materialName); }
    public static TagKey<Item> ingots(String materialName) { return c("ingots/" + materialName); }
    public static TagKey<Item> nuggets(String materialName) { return c("nuggets/" + materialName); }
    public static TagKey<Item> dusts(String materialName) { return c("dusts/" + materialName); }
    public static TagKey<Item> plates(String materialName) { return c("plates/" + materialName); }

    private static TagKey<Item> c(String path) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", path));
    }
}
