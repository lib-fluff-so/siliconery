package io.mainframe.siliconery.block.entity;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

// Boilerplate-killer for Container so block entities don't have to hand-write all of it themselves
public interface ImplementedContainer extends Container {
    NonNullList<ItemStack> getItems();

    static ImplementedContainer of(NonNullList<ItemStack> items) {
        return () -> items;
    }

    @Override
    default int getContainerSize() {
        return getItems().size();
    }

    @Override
    default boolean isEmpty() {
        for (int i = 0; i < getContainerSize(); i++) {
            if (!getItem(i).isEmpty()) return false;
        }
        return true;
    }

    @Override
    default ItemStack getItem(int slot) {
        return getItems().get(slot);
    }

    @Override
    default ItemStack removeItem(int slot, int count) {
        ItemStack result = ContainerHelper.removeItem(getItems(), slot, count);
        if (!result.isEmpty()) setChanged();
        return result;
    }

    @Override
    default ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(getItems(), slot);
    }

    @Override
    default void setItem(int slot, ItemStack stack) {
        getItems().set(slot, stack);
        stack.limitSize(getMaxStackSize(stack));
        setChanged();
    }

    @Override
    default void clearContent() {
        getItems().clear();
    }

    @Override
    default void setChanged() {
        // Override where actually needed (BlockEntity already does via its own setChanged)
    }

    @Override
    default boolean stillValid(Player player) {
        return true;
    }
}
