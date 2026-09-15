package io.mainframe.siliconery.recipe;

import io.mainframe.siliconery.Siliconery;
import io.mainframe.siliconery.block.ModBlocks;
import io.mainframe.siliconery.generated.ModProcessable;
import io.mainframe.siliconery.item.ModItems;
import io.mainframe.siliconery.item.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.level.block.Block;

import static io.mainframe.siliconery.recipe.RecipeHelper.tagIngredient;

public final class NuggetsAndBlocksRecipes {
    private NuggetsAndBlocksRecipes() {}

    public static void build(HolderLookup.Provider registries, RecipeOutput output, ModRecipeProviderImpl provider) {
        for (ModProcessable mat : ModProcessable.values()) {
            if (mat.hasNugget) {
                Item nugget = ModItems.NUGGETS.get(mat);
                Item ingot = ModItems.INGOTS.get(mat);

                ShapelessRecipeBuilder.shapeless(registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, new ItemStackTemplate(nugget, 9))
                        .requires(tagIngredient(registries, ModItemTags.ingots(mat.name)))
                        .unlockedBy(provider.hasName(ingot), provider.hasItem(ingot))
                        .save(output, ResourceKey.create(Registries.RECIPE, Siliconery.id(mat.name + "_nugget_from_ingot")));

                ShapedRecipeBuilder.shaped(registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, ingot, 1)
                        .pattern("III")
                        .pattern("III")
                        .pattern("III")
                        .define('I', tagIngredient(registries, ModItemTags.nuggets(mat.name)))
                        .unlockedBy(provider.hasName(nugget), provider.hasItem(nugget))
                        .save(output, ResourceKey.create(Registries.RECIPE, Siliconery.id(mat.name + "_ingot_from_nugget")));
            }

            if (mat.hasBlock) {
                Block block = ModBlocks.BLOCKS.get(mat);
                Item ingot = ModItems.INGOTS.get(mat);

                ShapedRecipeBuilder.shaped(registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, block.asItem(), 1)
                        .pattern("III")
                        .pattern("III")
                        .pattern("III")
                        .define('I', tagIngredient(registries, ModItemTags.ingots(mat.name)))
                        .unlockedBy(provider.hasName(ingot), provider.hasItem(ingot))
                        .save(output, ResourceKey.create(Registries.RECIPE, Siliconery.id(mat.name + "_block_from_ingot")));

                ShapelessRecipeBuilder.shapeless(registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, new ItemStackTemplate(ingot, 9))
                        .requires(block.asItem())
                        .unlockedBy(provider.hasName(block.asItem()), provider.hasItem(block.asItem()))
                        .save(output, ResourceKey.create(Registries.RECIPE, Siliconery.id(mat.name + "_ingot_from_block")));
            }
        }
    }
}