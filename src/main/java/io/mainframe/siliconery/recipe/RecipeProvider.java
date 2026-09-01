package io.mainframe.siliconery.recipe;

import io.mainframe.siliconery.misc.Plateable;
import io.mainframe.siliconery.item.Items;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected net.minecraft.data.recipes.@NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull RecipeOutput output) {
        return new net.minecraft.data.recipes.RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                for (Plateable mat : Plateable.values()) {
                    net.minecraft.world.item.Item plate = Items.PLATES.get(mat);
                    output.accept(
                            ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("siliconery", mat.name + "_plate_from_hammer")),
                            new RecipeItemTool(
                                    Ingredient.of(Items.FORGE_HAMMER),
                                    Ingredient.of(mat.ingotInput),
                                    new ItemStackTemplate(plate),
                                    1
                            ),
                            null
                    );
                }
                for (Plateable mat : Plateable.values()) {
                    net.minecraft.world.item.Item plate = Items.PLATES.get(mat);
                    net.minecraft.world.item.Item casing = Items.CASINGS.get(mat);

                    output.accept(
                            ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("siliconery", mat.name + "_casing_from_hammer")),
                            new RecipeItemTool(
                                    Ingredient.of(Items.FORGE_HAMMER),
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
    public @NonNull String getName() {
        return "Siliconery Recipes";
    }
}
