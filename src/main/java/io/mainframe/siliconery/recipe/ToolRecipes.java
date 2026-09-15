package io.mainframe.siliconery.recipe;

import io.mainframe.siliconery.Siliconery;
import io.mainframe.siliconery.block.ModBlocks;
import io.mainframe.siliconery.item.ModItems;
import io.mainframe.siliconery.item.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import static io.mainframe.siliconery.recipe.RecipeHelper.tagIngredient;

public final class ToolRecipes {
    private ToolRecipes() {}

    public static void build(HolderLookup.Provider registries, RecipeOutput output, ModRecipeProviderImpl provider) {
        ShapelessRecipeBuilder.shapeless(registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, new ItemStackTemplate(ModItems.SCREW, 1))
                .requires(tagIngredient(registries, ModItemTags.nuggets("iron")))
                .requires(ModItems.SCREW_TEMPLATE)
                .unlockedBy(provider.hasName(ModItems.SCREW_TEMPLATE), provider.hasItem(ModItems.SCREW_TEMPLATE))
                .save(output, ResourceKey.create(Registries.RECIPE, Siliconery.id("screw_from_template")));

        ShapedRecipeBuilder.shaped(registries.lookupOrThrow(Registries.ITEM), RecipeCategory.TOOLS, ModItems.FORGE_HAMMER, 1)
                .pattern("II ")
                .pattern("ISS")
                .pattern("II ")
                .define('I', tagIngredient(registries, ModItemTags.ingots("iron")))
                .define('S', Ingredient.of(Items.STICK))
                .unlockedBy(provider.hasName(Items.IRON_INGOT), provider.hasTag(ModItemTags.ingots("iron")))
                .save(output, ResourceKey.create(Registries.RECIPE, Siliconery.id("forge_hammer_from_iron")));

        ShapedRecipeBuilder.shaped(registries.lookupOrThrow(Registries.ITEM), RecipeCategory.DECORATIONS, ModBlocks.TEMPLATE_WORKBENCH.asItem(), 1)
                .pattern("IWI")
                .pattern("WCW")
                .pattern("IWI")
                .define('I', tagIngredient(registries, ModItemTags.plates("iron")))
                .define('W', tagIngredient(registries, ItemTags.PLANKS))
                .define('C', Ingredient.of(Items.CRAFTING_TABLE))
                .unlockedBy(provider.hasName(Items.CRAFTING_TABLE), provider.hasItem(Items.CRAFTING_TABLE))
                .save(output, ResourceKey.create(Registries.RECIPE, Siliconery.id("template_workbench_from_plates")));
    }
}