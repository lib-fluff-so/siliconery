package io.mainframe.siliconery.recipe;

import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

public class ModRecipeProviderImpl extends RecipeProvider {

    public ModRecipeProviderImpl(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    public void buildRecipes() {
        RubberRecipes.build(registries, output, this);
        OreSmeltingRecipes.build(registries, output, this);
        PlatesAndCasingsRecipes.build(registries, output, this);
        NuggetsAndBlocksRecipes.build(registries, output, this);
        TemplateRecipes.build(registries, output, this);
        ToolRecipes.build(registries, output, this);
    }

    public Criterion<?> hasItem(ItemLike item) {
        return has(item);
    }

    public Criterion<?> hasTag(TagKey<Item> tag) {return has(tag); }

    public String hasName(ItemLike item) { return getHasName(item); }
}