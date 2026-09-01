package io.mainframe.siliconery.recipe;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class RecipeItemTool implements CraftingRecipe {
    final Ingredient toolIngredient;
    final Ingredient inputIngredient;
    final ItemStackTemplate result;
    final int toolDamage;

    public RecipeItemTool(Ingredient toolIngredient, Ingredient inputIngredient, ItemStackTemplate result, int toolDamage) {
        this.toolIngredient = toolIngredient;
        this.inputIngredient = inputIngredient;
        this.result = result;
        this.toolDamage = toolDamage;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        boolean hasTool = false;
        boolean hasInput = false;
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;
            if (toolIngredient.test(stack)) hasTool = true;
            else if (inputIngredient.test(stack)) hasInput = true;
            else return false;
        }
        return hasTool && hasInput;
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        return result.create(); // ItemStackTemplate -> ItemStack, только во время игры, мир уже есть
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "";
    }

    @Override
    public RecipeSerializer<? extends CraftingRecipe> getSerializer() {
        return RecipeSerializers.TOOL_RECIPE_SERIALIZER;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.create(List.of(toolIngredient, inputIngredient));
    }

    @Override
    public CraftingBookCategory category() {
        return CraftingBookCategory.MISC;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> remainder = NonNullList.withSize(input.size(), ItemStack.EMPTY);
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (toolIngredient.test(stack)) {
                ItemStack damaged = stack.copy();
                damaged.setDamageValue(damaged.getDamageValue() + toolDamage);
                if (damaged.getDamageValue() < damaged.getMaxDamage()) {
                    remainder.set(i, damaged);
                }
            }
        }
        return remainder;
    }
}