package io.mainframe.client.datagen;

import io.mainframe.siliconery.block.ModBlockList;
import io.mainframe.siliconery.item.ModItemList;
import io.mainframe.siliconery.generated.ModOreable;
import io.mainframe.siliconery.generated.ModProcessable;
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
        blockModelGenerators.createCrossBlock(ModBlockList.RUBBER_SAPLING, BlockModelGenerators.PlantType.TINTED);
        blockModelGenerators.createTrivialCube(ModBlockList.TEMPLATE_WORKBENCH); // texture TBD, will be checkerboard for now
        for (ModOreable ore : ModOreable.values()) {
            blockModelGenerators.createTrivialCube(ModBlockList.ORES.get(ore));
            blockModelGenerators.createTrivialCube(ModBlockList.DEEPSLATE_ORES.get(ore));
        }
        for (ModProcessable mat : ModProcessable.values()) {
            if (mat.hasBlock) blockModelGenerators.createTrivialCube(ModBlockList.BLOCKS.get(mat));
        }
    }

    @Override
    public void generateItemModels(net.minecraft.client.data.models.@NonNull ItemModelGenerators itemModelGenerators) {
        for (ModProcessable mat : ModProcessable.values()) {
            if (mat.hasIngot) itemModelGenerators.generateFlatItem(ModItemList.INGOTS.get(mat), ModelTemplates.FLAT_ITEM);
            if (mat.hasPlate) itemModelGenerators.generateFlatItem(ModItemList.PLATES.get(mat), ModelTemplates.FLAT_ITEM);
            if (mat.hasCasing) itemModelGenerators.generateFlatItem(ModItemList.CASINGS.get(mat), ModelTemplates.FLAT_ITEM);
            if (mat.hasNugget) itemModelGenerators.generateFlatItem(ModItemList.NUGGETS.get(mat), ModelTemplates.FLAT_ITEM);
            if (mat.hasDust) itemModelGenerators.generateFlatItem(ModItemList.DUSTS.get(mat), ModelTemplates.FLAT_ITEM);
        }
        itemModelGenerators.generateFlatItem(ModItemList.MGSI, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItemList.MGSI_POWDER, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItemList.SLAG, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItemList.FORGE_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItemList.CUTTER, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItemList.TREETAP, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItemList.LATEX, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItemList.RUBBER, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItemList.CHEWING_GUM, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItemList.CROSS_HEAD_BLANK, ModelTemplates.FLAT_ITEM);
        for (ModOreable ore : ModOreable.values()) {
            itemModelGenerators.generateFlatItem(ModItemList.RAW_ORES.get(ore), ModelTemplates.FLAT_ITEM);
        }
    }

    @Override public @NonNull String getName() { return "Models"; }
}
