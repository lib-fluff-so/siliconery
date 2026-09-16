package io.mainframe.client.datagen;

import io.mainframe.siliconery.Siliconery;
import io.mainframe.siliconery.block.ModBlocks;
import io.mainframe.siliconery.item.ModItems;
import io.mainframe.siliconery.generated.ModOreable;
import io.mainframe.siliconery.generated.ModProcessable;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) { super(output); }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(ModBlocks.CASING);
        blockModelGenerators.createTrivialBlock(ModBlocks.RUBBER_LEAVES, TexturedModel.LEAVES);
        blockModelGenerators.createCrossBlock(ModBlocks.RUBBER_SAPLING, BlockModelGenerators.PlantType.TINTED);
        blockModelGenerators.createTrivialCube(ModBlocks.TEMPLATE_WORKBENCH);
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
            if (mat.hasIngot) registerTintedFlatItem(itemModelGenerators, ModItems.INGOTS.get(mat), "generic_ingot", mat.tintColor);
            if (mat.hasPlate) registerTintedFlatItem(itemModelGenerators, ModItems.PLATES.get(mat), "generic_plate", mat.tintColor);
            if (mat.hasCasing) registerTintedFlatItem(itemModelGenerators, ModItems.CASINGS.get(mat), "generic_casing", mat.tintColor);
            if (mat.hasNugget) registerTintedFlatItem(itemModelGenerators, ModItems.NUGGETS.get(mat), "generic_nugget", mat.tintColor);
            if (mat.hasDust) registerTintedFlatItem(itemModelGenerators, ModItems.DUSTS.get(mat), Items.SUGAR, mat.tintColor);
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

    /**
     * Flat item model that borrows another item's texture (so we never draw our own) and applies
     * a fixed color tint on top via the vanilla {@code minecraft:constant} tint source. No JSON
     * touched by hand - this emits both the item model and the client item through datagen.
     */
    @SuppressWarnings("SameParameterValue") // INTELLIJ SHUT UP
    private static void registerTintedFlatItem(ItemModelGenerators itemModelGenerators, Item item, Item textureSource, int color) {
        Identifier model = itemModelGenerators.createFlatItemModel(item, textureSource, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.itemModelOutput.accept(item,
                ItemModelUtils.tintedModel(model, ItemModelUtils.constantTint(color)));
    }

    /**
     * Same as above, but for when there's no vanilla item to borrow a texture from — points at
     * a named texture under our own namespace instead (assets/siliconery/textures/item/{textureName}.png),
     * shared across every material that passes the same name. Draw that one texture once.
     */
    private static void registerTintedFlatItem(ItemModelGenerators itemModelGenerators, Item item, String textureName, int color) {
        Identifier texture = Siliconery.id("item/" + textureName);
        Identifier model = ModelTemplates.FLAT_ITEM.create(item, TextureMapping.singleSlot(TextureSlot.LAYER0, new Material(texture)), itemModelGenerators.modelOutput);
        itemModelGenerators.itemModelOutput.accept(item,
                ItemModelUtils.tintedModel(model, ItemModelUtils.constantTint(color)));
    }

    @Override public @NonNull String getName() { return "Models"; }
}
