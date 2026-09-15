package io.mainframe.client.datagen;

import io.mainframe.siliconery.block.ModBlocks;
import io.mainframe.siliconery.item.ModItems;
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
        blockModelGenerators.createTrivialCube(ModBlocks.CASING);
        blockModelGenerators.createTrivialBlock(ModBlocks.RUBBER_LEAVES, TexturedModel.LEAVES);
        blockModelGenerators.createCrossBlock(ModBlocks.RUBBER_SAPLING, BlockModelGenerators.PlantType.TINTED);
        blockModelGenerators.createTrivialCube(ModBlocks.TEMPLATE_WORKBENCH); // texture TBD, will be checkerboard for now
        for (ModOreable ore : ModOreable.values()) {
            blockModelGenerators.createTrivialCube(ModBlocks.ORES.get(ore));
            blockModelGenerators.createTrivialCube(ModBlocks.DEEPSLATE_ORES.get(ore));
        }
        for (ModProcessable mat : ModProcessable.values()) {
            if (mat.hasBlock) blockModelGenerators.createTrivialCube(ModBlocks.BLOCKS.get(mat));
        }
    }

    @Override
    public void generateItemModels(net.minecraft.client.data.models.@NonNull ItemModelGenerators itemModelGenerators) {
        for (ModProcessable mat : ModProcessable.values()) {
            if (mat.hasIngot) itemModelGenerators.generateFlatItem(ModItems.INGOTS.get(mat), ModelTemplates.FLAT_ITEM);
            if (mat.hasPlate) itemModelGenerators.generateFlatItem(ModItems.PLATES.get(mat), ModelTemplates.FLAT_ITEM);
            if (mat.hasCasing) itemModelGenerators.generateFlatItem(ModItems.CASINGS.get(mat), ModelTemplates.FLAT_ITEM);
            if (mat.hasNugget) itemModelGenerators.generateFlatItem(ModItems.NUGGETS.get(mat), ModelTemplates.FLAT_ITEM);
            if (mat.hasDust) itemModelGenerators.generateFlatItem(ModItems.DUSTS.get(mat), ModelTemplates.FLAT_ITEM);
        }
        itemModelGenerators.generateFlatItem(ModItems.MGSI, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.MGSI_POWDER, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.SLAG, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.FORGE_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.CUTTER, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.TREETAP, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.LATEX, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.RUBBER, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.CHEWING_GUM, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.CROSS_HEAD_BLANK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.THREAD_BLANK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.SCREW_TEMPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.CASING_TEMPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.SCREW, ModelTemplates.FLAT_ITEM);
        for (ModOreable ore : ModOreable.values()) {
            itemModelGenerators.generateFlatItem(ModItems.RAW_ORES.get(ore), ModelTemplates.FLAT_ITEM);
        }
    }

    @Override public @NonNull String getName() { return "Models"; }
}
