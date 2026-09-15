package io.mainframe.siliconery.recipe;

import io.mainframe.siliconery.Siliconery;
import io.mainframe.siliconery.generated.ModOreable;
import io.mainframe.siliconery.generated.ModProcessable;
import io.mainframe.siliconery.item.ModItems;
import io.mainframe.siliconery.item.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.CookingBookCategory;

import static io.mainframe.siliconery.recipe.RecipeHelper.tagIngredient;

public final class OreSmeltingRecipes {
    private OreSmeltingRecipes() {}

    public static void build(HolderLookup.Provider registries, RecipeOutput output, ModRecipeProviderImpl provider) {
        for (ModOreable ore : ModOreable.values()) {
            ModProcessable processable = ModProcessable.valueOf(ore.name());
            Item raw = ModItems.RAW_ORES.get(ore);
            Item ingot = ModItems.INGOTS.get(processable);

            SimpleCookingRecipeBuilder.smelting(
                            tagIngredient(registries, ModItemTags.rawMaterials(ore.name)),
                            RecipeCategory.MISC,
                            CookingBookCategory.MISC,
                            ingot,
                            0.7F,
                            ore.smeltingCookTime
                    ).unlockedBy(provider.hasName(raw), provider.hasItem(raw))
                    .save(output, ResourceKey.create(Registries.RECIPE, Siliconery.id(ore.name + "_ingot_from_smelting")));

            SimpleCookingRecipeBuilder.blasting(
                            tagIngredient(registries, ModItemTags.rawMaterials(ore.name)),
                            RecipeCategory.MISC,
                            CookingBookCategory.MISC,
                            ingot,
                            0.7F,
                            ore.blastingCookTime
                    ).unlockedBy(provider.hasName(raw), provider.hasItem(raw))
                    .save(output, ResourceKey.create(Registries.RECIPE, Siliconery.id(ore.name + "_ingot_from_blasting")));
        }
    }
}