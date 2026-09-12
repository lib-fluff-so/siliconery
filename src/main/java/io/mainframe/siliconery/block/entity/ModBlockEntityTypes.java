package io.mainframe.siliconery.block.entity;

import io.mainframe.siliconery.Siliconery;
import io.mainframe.siliconery.block.ModBlockList;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntityTypes {
    public static final BlockEntityType<TemplateWorkbenchBlockEntity> TEMPLATE_WORKBENCH = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE,
            Siliconery.id("template_workbench"),
            FabricBlockEntityTypeBuilder.create(TemplateWorkbenchBlockEntity::new, ModBlockList.TEMPLATE_WORKBENCH).build()
    );

    @SuppressWarnings("EmptyMethod")
    public static void initialize() { }
}
