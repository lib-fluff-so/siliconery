package io.mainframe.siliconery.datagen;

import io.mainframe.siliconery.item.ModItemIds;
import io.mainframe.siliconery.item.ModItemTags;
import io.mainframe.siliconery.generated.ModOreable;
import io.mainframe.siliconery.generated.ModProcessable;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

/**
 * Populates the `c:` convention item tags declared in ModItemTags, so other mods' matching
 * materials (e.g. someone else's zinc ingot) resolve into the same recipes as ours, instead of
 * every mod's zinc being its own disconnected item.
 */
public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {

    public ModItemTagsProvider(
            FabricPackOutput output,
            CompletableFuture<HolderLookup.Provider> registriesFuture
    ) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider wrapperLookup) {
        for (ModOreable ore : ModOreable.values()) {
            builder(ModItemTags.rawMaterials(ore.name)).add(ModItemIds.raw(ore.name));
        }

        for (ModProcessable mat : ModProcessable.values()) {
            if (mat.hasIngot) builder(ModItemTags.ingots(mat.name)).add(ModItemIds.ingot(mat.name));
            if (mat.hasNugget) builder(ModItemTags.nuggets(mat.name)).add(ModItemIds.nugget(mat.name));
            if (mat.hasDust) builder(ModItemTags.dusts(mat.name)).add(ModItemIds.dust(mat.name));
            if (mat.hasPlate) builder(ModItemTags.plates(mat.name)).add(ModItemIds.plate(mat.name));
        }
    }

    @Override public @NonNull String getName() { return "Item Tags"; }
}
