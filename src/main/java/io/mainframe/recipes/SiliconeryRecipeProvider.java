package io.mainframe.recipes;

import io.mainframe.types.Plateable;
import io.mainframe.types.SiliconeryItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class SiliconeryRecipeProvider extends FabricRecipeProvider {
    public SiliconeryRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                for (Plateable mat : Plateable.values()) {
                    Item plate = SiliconeryItems.PLATES.get(mat);
                    output.accept(
                            ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("siliconery", mat.name + "_plate_from_hammer")),
                            new SiliconeryToolRecipe(
                                    Ingredient.of(SiliconeryItems.FORGE_HAMMER),
                                    Ingredient.of(mat.ingotInput),
                                    new ItemStackTemplate(plate),
                                    1
                            ),
                            null
                    );
                }
                for (Plateable mat : Plateable.values()) {
                    Item plate = SiliconeryItems.PLATES.get(mat);
                    Item casing = SiliconeryItems.CASINGS.get(mat);

                    output.accept(
                            ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("siliconery", mat.name + "_casing_from_hammer")),
                            new SiliconeryToolRecipe(
                                    Ingredient.of(SiliconeryItems.FORGE_HAMMER),
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
    public String getName() {
        return "Siliconery Recipes";
    }
}
