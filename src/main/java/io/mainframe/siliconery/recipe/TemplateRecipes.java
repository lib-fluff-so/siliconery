package io.mainframe.siliconery.recipe;

import io.mainframe.siliconery.Siliconery;
import io.mainframe.siliconery.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public final class TemplateRecipes {
    private TemplateRecipes() {}

    public static void build(HolderLookup.Provider registries, RecipeOutput output, ModRecipeProviderImpl provider) {
        ShapelessRecipeBuilder.shapeless(registries.lookupOrThrow(Registries.ITEM), RecipeCategory.FOOD, new ItemStackTemplate(ModItems.CHEWING_GUM, 4))
                .requires(ModItems.RUBBER)
                .requires(Items.SUGAR)
                .requires(Items.DYE.pink())
                .unlockedBy(provider.hasName(ModItems.RUBBER), provider.hasItem(ModItems.RUBBER))
                .save(output, ResourceKey.create(Registries.RECIPE, Siliconery.id("chewing_gum_from_rubber")));

        ShapedRecipeBuilder.shaped(registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, ModItems.CROSS_HEAD_BLANK, 32)
                .pattern(" # ")
                .pattern("###")
                .pattern(" # ")
                .define('#', Ingredient.of(Items.BRICK))
                .unlockedBy(provider.hasName(Items.BRICK), provider.hasItem(Items.BRICK))
                .save(output, ResourceKey.create(Registries.RECIPE, Siliconery.id("cross_head_blank_from_bricks")));

        ShapedRecipeBuilder.shaped(registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, ModItems.THREAD_BLANK, 32)
                .pattern("  #")
                .pattern(" # ")
                .pattern("#  ")
                .define('#', Ingredient.of(Items.BRICK))
                .unlockedBy(provider.hasName(Items.BRICK), provider.hasItem(Items.BRICK))
                .save(output, ResourceKey.create(Registries.RECIPE, Siliconery.id("thread_blank_from_bricks")));

        ShapelessRecipeBuilder.shapeless(registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, new ItemStackTemplate(ModItems.SCREW_TEMPLATE, 1))
                .requires(ModItems.CROSS_HEAD_BLANK)
                .requires(ModItems.THREAD_BLANK)
                .unlockedBy(provider.hasName(ModItems.THREAD_BLANK), provider.hasItem(ModItems.THREAD_BLANK))
                .unlockedBy(provider.hasName(ModItems.CROSS_HEAD_BLANK), provider.hasItem(ModItems.CROSS_HEAD_BLANK))
                .save(output, ResourceKey.create(Registries.RECIPE, Siliconery.id("screw_template_from_blanks")));

        ShapedRecipeBuilder.shaped(registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, ModItems.CASING_TEMPLATE, 4)
                .pattern("###")
                .pattern("# #")
                .pattern("###")
                .define('#', Ingredient.of(Items.BRICK))
                .unlockedBy(provider.hasName(Items.BRICK), provider.hasItem(Items.BRICK))
                .save(output, ResourceKey.create(Registries.RECIPE, Siliconery.id("casing_template_from_bricks")));
    }
}