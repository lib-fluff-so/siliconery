package io.mainframe.siliconery.datagen;

import io.mainframe.siliconery.recipe.ModRecipeProviderImpl;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected net.minecraft.data.recipes.@NonNull RecipeProvider createRecipeProvider(
            HolderLookup.@NonNull Provider registries, @NonNull RecipeOutput output) {
        return new ModRecipeProviderImpl(registries, output);
    }

    @Override
    public @NonNull String getName() { return "Recipes"; }
}