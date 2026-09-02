package io.mainframe.client.datagen;

import io.mainframe.siliconery.block.ModBlockList;
import io.mainframe.siliconery.item.ModItemList;
import io.mainframe.siliconery.misc.ModPlateable;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import org.jspecify.annotations.NonNull;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) { super(output); }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(ModBlockList.CASING);
        blockModelGenerators.createTrivialBlock(ModBlockList.RUBBER_LEAVES, TexturedModel.LEAVES);
        blockModelGenerators.createTrivialBlock(ModBlockList.RUBBER_LOG, TexturedModel.COLUMN_ALT);
        blockModelGenerators.createCrossBlock(ModBlockList.RUBBER_SAPLING, BlockModelGenerators.PlantType.TINTED);
    }

    @Override
    public void generateItemModels(net.minecraft.client.data.models.@NonNull ItemModelGenerators itemModelGenerators) {
        for (ModPlateable mat : ModPlateable.values()) {
            net.minecraft.world.item.Item plate = ModItemList.PLATES.get(mat);
            itemModelGenerators.generateFlatItem(plate, ModelTemplates.FLAT_ITEM);
            net.minecraft.world.item.Item casing = ModItemList.CASINGS.get(mat);
            itemModelGenerators.generateFlatItem(casing, ModelTemplates.FLAT_ITEM);
        }
        itemModelGenerators.generateFlatItem(ModItemList.MGSI, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItemList.MGSI_POWDER, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItemList.SLAG, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItemList.FORGE_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItemList.CUTTER, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItemList.TREETAP, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItemList.LATEX, ModelTemplates.FLAT_ITEM);
    }

    @Override public @NonNull String getName() { return "Models"; }
}
