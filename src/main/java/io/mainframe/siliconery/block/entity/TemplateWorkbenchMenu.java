package io.mainframe.siliconery.block.entity;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class TemplateWorkbenchMenu extends AbstractContainerMenu {
    // Only the base slot exists for now. Once mode-driven sub-slots show up, this becomes
    // "however many slots the biggest mode needs", pre-allocated and toggled active/inactive.
    private static final int BASE_SLOT_COUNT = 1;
    private static final int BASE_SLOT_END = BASE_SLOT_COUNT;
    private static final int INVENTORY_START = BASE_SLOT_END;
    private static final int INVENTORY_END = INVENTORY_START + Inventory.INVENTORY_SIZE;

    // Lines up with the input slot cutout on the borrowed furnace texture
    private static final int BASE_SLOT_X = 56;
    private static final int BASE_SLOT_Y = 17;
    private static final int INVENTORY_START_X = 8;
    private static final int INVENTORY_START_Y = 84;

    private final Container container;

    // Client-side constructor - server tells us to open, contents sync in afterward
    public TemplateWorkbenchMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, new SimpleContainer(BASE_SLOT_COUNT));
    }

    // Server-side constructor - real block entity backs the container
    public TemplateWorkbenchMenu(int containerId, Inventory playerInventory, Container container) {
        super(ModMenuTypes.TEMPLATE_WORKBENCH, containerId);
        checkContainerSize(container, BASE_SLOT_COUNT);
        this.container = container;
        container.startOpen(playerInventory.player);

        addSlot(new Slot(container, 0, BASE_SLOT_X, BASE_SLOT_Y));

        addStandardInventorySlots(playerInventory, INVENTORY_START_X, INVENTORY_START_Y);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        Slot slot = slots.get(slotIndex);

        if (!slot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack stack = slot.getItem();
        ItemStack clicked = stack.copy();

        if (slotIndex < BASE_SLOT_END) {
            // Out of the base slot, into the player inventory
            if (!moveItemStackTo(stack, INVENTORY_START, INVENTORY_END, true)) {
                return ItemStack.EMPTY;
            }
        } else {
            // From the player inventory into the base slot
            if (!moveItemStackTo(stack, 0, BASE_SLOT_END, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (stack.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        return clicked;
    }

    @Override
    public boolean stillValid(Player player) {
        return container.stillValid(player);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        container.stopOpen(player);
    }
}
