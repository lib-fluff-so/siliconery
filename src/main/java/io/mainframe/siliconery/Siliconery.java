package io.mainframe.siliconery;

import io.mainframe.siliconery.block.ModBlock;
import io.mainframe.siliconery.block.ModBlocks;
import io.mainframe.siliconery.block.entity.fabric.ModBlockEntityTypes;
import io.mainframe.siliconery.block.entity.ModMenuTypes;
import io.mainframe.siliconery.item.ModItem;
import io.mainframe.siliconery.item.ModItems;
import io.mainframe.siliconery.misc.ModCreativeTab;
import io.mainframe.siliconery.recipe.ModRecipeSerializers;
import io.mainframe.siliconery.world.ModFoliagePlacerTypes;
import io.mainframe.siliconery.world.ModTreeDecoratorTypes;
import io.mainframe.siliconery.world.fabric.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Siliconery implements ModInitializer {
    // Mod name
    public static final String MOD_ID = "siliconery";
    // My logger
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        LOGGER.info("Siliconery started!");
        LOGGER.info("Initializing...");
        long startTime = System.currentTimeMillis();
        ModItem.initialize();
        ModItems.initialize();
        ModBlock.initialize();
        ModBlocks.initialize();
        ModBlockEntityTypes.initialize();
        ModMenuTypes.initialize();
        ModCreativeTab.initialize();
        ModRecipeSerializers.initialize();
        ModFoliagePlacerTypes.initialize();
        ModTreeDecoratorTypes.register();
        ModWorldGeneration.register();
        long duration = System.currentTimeMillis() - startTime;
        LOGGER.info("Done in {} ms!", duration);
    }

    public static Identifier id(String path) { return Identifier.fromNamespaceAndPath(MOD_ID, path); }
}
