package io.mainframe.siliconery.item;

import io.mainframe.siliconery.Siliconery;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class ModItem {
    private static final List<net.minecraft.world.item.Item> ALL_ITEMS = new ArrayList<>();

    public static net.minecraft.world.item.Item registerItem(ResourceKey<net.minecraft.world.item.Item> itemKey, Function<net.minecraft.world.item.Item.Properties, net.minecraft.world.item.Item> itemFactory, net.minecraft.world.item.Item.Properties settings) {
        net.minecraft.world.item.Item item = itemFactory.apply(settings.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        ALL_ITEMS.add(item);

        return item;
    }

    public static Collection<net.minecraft.world.item.Item> allRegisteredItems() {
        return Collections.unmodifiableList(ALL_ITEMS);
    }

    public static ResourceKey<net.minecraft.world.item.Item> createItemId(String name) {
        return ResourceKey.create(Registries.ITEM, Siliconery.id(name));
    }

    @SuppressWarnings("EmptyMethod")
    public static void initialize() { }
}