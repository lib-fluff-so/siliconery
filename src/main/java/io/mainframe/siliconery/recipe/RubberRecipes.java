package io.mainframe.siliconery.recipe;

import io.mainframe.siliconery.Siliconery;
import io.mainframe.siliconery.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;

public final class RubberRecipes {
    private RubberRecipes() {}

    public static void build(HolderLookup.Provider registries, RecipeOutput output, ModRecipeProviderImpl provider) {
        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(ModItems.LATEX),
                        RecipeCategory.MISC,
                        CookingBookCategory.MISC,
                        ModItems.RUBBER,
                        0.1F,
                        200
                ).unlockedBy(provider.hasName(ModItems.LATEX), provider.hasItem(ModItems.LATEX))
                .save(output, ResourceKey.create(Registries.RECIPE, Siliconery.id("rubber_from_smelting")));
    }
}