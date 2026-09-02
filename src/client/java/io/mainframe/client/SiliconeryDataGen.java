package io.mainframe.client;

import io.mainframe.client.datagen.ModModelProvider;
import io.mainframe.siliconery.datagen.ModBlockLootProvider;
import io.mainframe.siliconery.datagen.ModBlockTagsProvider;
import io.mainframe.siliconery.datagen.ModRecipeProvider;
import io.mainframe.siliconery.world.ModConfiguredFeatures;
import io.mainframe.siliconery.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import org.jspecify.annotations.NonNull;

public class SiliconeryDataGen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(
            FabricDataGenerator fabricDataGenerator
    ) {
        FabricDataGenerator.Pack pack =
                fabricDataGenerator.createPack();

        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModRecipeProvider::new);

        pack.addProvider((output, registriesFuture) ->
                new FabricDynamicRegistryProvider(output, registriesFuture) {
                    @Override
                    protected void configure(
                            net.minecraft.core.HolderLookup.@NonNull Provider registries,
                            @NonNull Entries entries) {
                        entries.addAll(registries.lookupOrThrow(Registries.CONFIGURED_FEATURE));
                        entries.addAll(registries.lookupOrThrow(Registries.PLACED_FEATURE));
                    }

                    @Override
                    public @NonNull String getName() {
                        return "Dynamic Registries";
                    }
                }
        );

        pack.addProvider(ModBlockLootProvider::new);
        pack.addProvider(ModBlockTagsProvider::new);
    }

    @Override
    public void buildRegistry(
            RegistrySetBuilder registryBuilder
    ) {
        registryBuilder.add(
                Registries.CONFIGURED_FEATURE,
                ModConfiguredFeatures::bootstrap
        );

        registryBuilder.add(
                Registries.PLACED_FEATURE,
                ModPlacedFeatures::bootstrap
        );
    }
}