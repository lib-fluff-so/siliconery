package io.mainframe.client;

import io.mainframe.siliconery.block.Blocks;
import io.mainframe.siliconery.item.Items;
import io.mainframe.siliconery.misc.Plateable;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import org.jspecify.annotations.NonNull;

public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricPackOutput output) { super(output); }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(Blocks.CASING);
        blockModelGenerators.createTrivialCube(Blocks.RUBBER_LEAVES);
        blockModelGenerators.createTrivialCube(Blocks.RUBBER_LOG);
        blockModelGenerators.createCrossBlock(Blocks.RUBBER_SAPLING, BlockModelGenerators.PlantType.TINTED);
    }

    @Override
    public void generateItemModels(net.minecraft.client.data.models.ItemModelGenerators itemModelGenerators) {
        for (Plateable mat : Plateable.values()) {
            net.minecraft.world.item.Item plate = Items.PLATES.get(mat);
            itemModelGenerators.generateFlatItem(plate, ModelTemplates.FLAT_ITEM);
        }
        for (Plateable mat : Plateable.values()) {
            net.minecraft.world.item.Item casing = Items.CASINGS.get(mat);
            itemModelGenerators.generateFlatItem(casing, ModelTemplates.FLAT_ITEM);
        }
        itemModelGenerators.generateFlatItem(Items.MGSI, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(Items.MGSI_POWDER, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(Items.SLAG, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(Items.FORGE_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(Items.CUTTER, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(Items.TREETAP, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(Items.LATEX, ModelTemplates.FLAT_ITEM);
    }

    @Override
    public @NonNull String getName() {
        return "Siliconery Models";
    }
}
