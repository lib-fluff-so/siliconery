package io.mainframe.siliconery.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

public final class RecipeHelper {
    private RecipeHelper() {}

    public static Ingredient tagIngredient(HolderLookup.Provider registries, TagKey<Item> tag) {
        return Ingredient.of(registries.lookupOrThrow(Registries.ITEM).getOrThrow(tag));
    }
}