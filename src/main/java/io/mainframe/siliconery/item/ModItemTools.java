package io.mainframe.siliconery.item;

import io.mainframe.siliconery.Siliconery;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;

import java.util.function.Function;

public class ModItemTools {
    public static net.minecraft.world.item.Item registerItem(ResourceKey<net.minecraft.world.item.Item> itemKey, Function<net.minecraft.world.item.Item.Properties, net.minecraft.world.item.Item> itemFactory, net.minecraft.world.item.Item.Properties settings) {
        net.minecraft.world.item.Item item = itemFactory.apply(settings.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }


    public static ResourceKey<net.minecraft.world.item.Item> createItemId(String name) {
        return ResourceKey.create(Registries.ITEM, Siliconery.id(name));
    }

    @SuppressWarnings("EmptyMethod")
    public static void initialize() {
    }
}
