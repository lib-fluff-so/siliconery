package io.mainframe.siliconery.block.entity;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

import java.util.function.BooleanSupplier;

/**
 * A sub-slot whose availability depends on the current "mode" (derived server-side from
 * whatever's in the base slot). When inactive it behaves like it doesn't exist: nothing can
 * be placed in or taken out of it. Position never changes - only whether it's usable, and
 * whether the Screen bothers drawing a frame around it.
 */
public class TemplateSlot extends Slot {
    private final BooleanSupplier active;

    public TemplateSlot(Container container, int index, int x, int y, BooleanSupplier active) {
        super(container, index, x, y);
        this.active = active;
    }

    public boolean isActive() {
        return active.getAsBoolean();
    }

    @Override
    public boolean mayPlace(@NonNull ItemStack stack) {
        return isActive() && super.mayPlace(stack);
    }

    @Override
    public boolean mayPickup(@NonNull Player player) {
        return isActive() && super.mayPickup(player);
    }
}
