package io.mainframe.siliconery.world;

import io.mainframe.siliconery.Siliconery;
import io.mainframe.siliconery.world.rubber.ModRubberSapDecorator;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class ModTreeDecoratorTypes {

    // Is it even needed?
    @SuppressWarnings("unused")
    public static final TreeDecoratorType<ModRubberSapDecorator> RUBBER_SAP =
            Registry.register(
                    BuiltInRegistries.TREE_DECORATOR_TYPE,
                    Siliconery.id("rubber_sap"),
                    new TreeDecoratorType<>(ModRubberSapDecorator.CODEC)
            );

    public static void register() {
    }
}