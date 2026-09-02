package io.mainframe.siliconery.world;

import io.mainframe.siliconery.Siliconery;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class SiliconeryTreeDecoratorTypes {

    public static final TreeDecoratorType<RubberSapDecorator> RUBBER_SAP =
            Registry.register(
                    BuiltInRegistries.TREE_DECORATOR_TYPE,
                    Siliconery.id("rubber_sap"),
                    new TreeDecoratorType<>(RubberSapDecorator.CODEC)
            );

    public static void register() {
    }
}