package io.mainframe.client;

import io.mainframe.siliconery.misc.SiliconeryLootTables;
import io.mainframe.siliconery.misc.SiliconeryTags;
import io.mainframe.siliconery.recipe.RecipeProvider;
import io.mainframe.siliconery.world.SiliconeryConfiguredFeatures;
import io.mainframe.siliconery.world.SiliconeryPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class SiliconeryDataGen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(
            FabricDataGenerator fabricDataGenerator
    ) {
        FabricDataGenerator.Pack pack =
                fabricDataGenerator.createPack();

        pack.addProvider(ModelProvider::new);
        pack.addProvider(RecipeProvider::new);

        pack.addProvider((output, registriesFuture) ->
                new FabricDynamicRegistryProvider(
                        output,
                        registriesFuture
                ) {
                    @Override
                    protected void configure(
                            net.minecraft.core.HolderLookup.Provider registries,
                            Entries entries
                    ) {
                        entries.addAll(
                                registries.lookupOrThrow(
                                        Registries.CONFIGURED_FEATURE
                                )
                        );

                        entries.addAll(
                                registries.lookupOrThrow(
                                        Registries.PLACED_FEATURE
                                )
                        );
                    }

                    @Override
                    public String getName() {
                        return "Siliconery Dynamic Registries";
                    }
                }
        );

        pack.addProvider(SiliconeryLootTables::new);
        pack.addProvider(SiliconeryTags::new);
    }

    @Override
    public void buildRegistry(
            RegistrySetBuilder registryBuilder
    ) {
        registryBuilder.add(
                Registries.CONFIGURED_FEATURE,
                SiliconeryConfiguredFeatures::bootstrap
        );

        registryBuilder.add(
                Registries.PLACED_FEATURE,
                SiliconeryPlacedFeatures::bootstrap
        );
    }
}