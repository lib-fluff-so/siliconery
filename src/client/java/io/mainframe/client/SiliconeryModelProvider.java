package io.mainframe.client;

import io.mainframe.types.SiliconeryBlock;
import io.mainframe.types.SiliconeryItems;
import io.mainframe.types.Plateable;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.NonNull;

public class SiliconeryModelProvider extends FabricModelProvider {
    public SiliconeryModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(SiliconeryBlock.CASING);
        blockModelGenerators.createTrivialCube(SiliconeryBlock.RUBBER_LEAVES);
        blockModelGenerators.createTrivialCube(SiliconeryBlock.RUBBER_LOG);
    }

    @Override
    public void generateItemModels(net.minecraft.client.data.models.ItemModelGenerators itemModelGenerators) {
        for (Plateable mat : Plateable.values()) {
            Item plate = SiliconeryItems.PLATES.get(mat);
            itemModelGenerators.generateFlatItem(plate, ModelTemplates.FLAT_ITEM);
        }
        for (Plateable mat : Plateable.values()) {
            Item casing = SiliconeryItems.CASINGS.get(mat);
            itemModelGenerators.generateFlatItem(casing, ModelTemplates.FLAT_ITEM);
        }
        itemModelGenerators.generateFlatItem(SiliconeryItems.MGSI, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(SiliconeryItems.MGSI_POWDER, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(SiliconeryItems.SLAG, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(SiliconeryItems.FORGE_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(SiliconeryItems.CUTTER, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(SiliconeryItems.LATEX, ModelTemplates.FLAT_ITEM);
    }

    @Override
    public @NonNull String getName() {
        return "Siliconery Models";
    }
}
