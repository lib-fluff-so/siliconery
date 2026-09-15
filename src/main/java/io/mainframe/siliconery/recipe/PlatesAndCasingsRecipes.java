package io.mainframe.siliconery.recipe;

import io.mainframe.siliconery.Siliconery;
import io.mainframe.siliconery.generated.ModProcessable;
import io.mainframe.siliconery.item.ModItems;
import io.mainframe.siliconery.item.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;

import static io.mainframe.siliconery.recipe.RecipeHelper.tagIngredient;

public final class PlatesAndCasingsRecipes {
    private PlatesAndCasingsRecipes() {}

    public static void build(HolderLookup.Provider registries, RecipeOutput output, ModRecipeProviderImpl provider) {
        for (ModProcessable mat : ModProcessable.values()) {
            if (!mat.hasPlate) continue;
            Item plate = ModItems.PLATES.get(mat);
            output.accept(
                    ResourceKey.create(Registries.RECIPE, Siliconery.id(mat.name + "_plate_from_hammer")),
                    new ModRecipeItemTool(
                            Ingredient.of(ModItems.FORGE_HAMMER),
                            tagIngredient(registries, ModItemTags.ingots(mat.name)),
                            new ItemStackTemplate(plate),
                            1
                    ),
                    null
            );

            if (!mat.hasCasing) continue;
            Item casing = ModItems.CASINGS.get(mat);
            output.accept(
                    ResourceKey.create(Registries.RECIPE, Siliconery.id(mat.name + "_casing_from_hammer")),
                    new ModRecipeItemTool(
                            Ingredient.of(ModItems.FORGE_HAMMER),
                            tagIngredient(registries, ModItemTags.plates(mat.name)),
                            new ItemStackTemplate(casing, 2),
                            2
                    ),
                    null
            );
        }
    }
}