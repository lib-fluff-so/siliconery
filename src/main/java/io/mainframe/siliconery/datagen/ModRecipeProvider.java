package io.mainframe.siliconery.datagen;

import io.mainframe.siliconery.Siliconery;
import io.mainframe.siliconery.misc.ModPlateable;
import io.mainframe.siliconery.item.ModItemList;
import io.mainframe.siliconery.recipe.ModRecipeItemTool;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected net.minecraft.data.recipes.@NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull RecipeOutput output) {
        return new net.minecraft.data.recipes.RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                SimpleCookingRecipeBuilder.smelting(
                                Ingredient.of(ModItemList.LATEX),
                                RecipeCategory.MISC,
                                CookingBookCategory.MISC,
                                ModItemList.RUBBER,
                                0.1F,
                                200
                        ).unlockedBy(getHasName(ModItemList.LATEX), has(ModItemList.LATEX))
                        .save(output, ResourceKey.create(Registries.RECIPE, Siliconery.id("rubber_from_smelting")));

                ShapelessRecipeBuilder.shapeless(registries.lookupOrThrow(Registries.ITEM), RecipeCategory.FOOD, new ItemStackTemplate(ModItemList.CHEWING_GUM, 4))
                        .requires(ModItemList.RUBBER)
                        .requires(Items.SUGAR)
                        .requires(Items.DYE.pink())
                        .unlockedBy(getHasName(ModItemList.RUBBER), has(ModItemList.RUBBER))
                        .save(output, ResourceKey.create(Registries.RECIPE, Siliconery.id("chewing_gum_from_rubber")));

                for (ModPlateable mat : ModPlateable.values()) {
                    net.minecraft.world.item.Item plate = ModItemList.PLATES.get(mat);
                    net.minecraft.world.item.Item casing = ModItemList.CASINGS.get(mat);
                    output.accept(
                            ResourceKey.create(Registries.RECIPE, Siliconery.id(mat.name + "_plate_from_hammer")),
                            new ModRecipeItemTool(
                                    Ingredient.of(ModItemList.FORGE_HAMMER),
                                    Ingredient.of(mat.ingotInput),
                                    new ItemStackTemplate(plate),
                                    1
                            ),
                            null
                    );
                    output.accept(
                            ResourceKey.create(Registries.RECIPE, Siliconery.id(mat.name + "_casing_from_hammer")),
                            new ModRecipeItemTool(
                                    Ingredient.of(ModItemList.FORGE_HAMMER),
                                    Ingredient.of(plate),
                                    new ItemStackTemplate(casing, 2),
                                    2
                            ),
                            null
                    );
                }
            }
        };
    }

    @Override
    public @NonNull String getName() { return "Recipes"; }
}