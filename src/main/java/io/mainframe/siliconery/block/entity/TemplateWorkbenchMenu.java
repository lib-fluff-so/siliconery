package io.mainframe.siliconery.block.entity;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

public class TemplateWorkbenchMenu extends AbstractContainerMenu {
    private static final int INVENTORY_START = TemplateWorkbenchBlockEntity.SLOT_COUNT;
    private static final int INVENTORY_END = INVENTORY_START + Inventory.INVENTORY_SIZE;

    // A shitty shitty shitty code
    private static final int BASE_SLOT_X = 7;
    private static final int SUB_SLOT_1_X = 43;
    private static final int SUB_SLOT_2_X = 61;
    private static final int SUB_SLOT_3_X = 79;
    private static final int OUTPUT_SLOT_X = 141;
    private static final int SLOT_Y = 34;

    private static final int INVENTORY_START_X = 8;
    private static final int INVENTORY_START_Y = 84;

    private final Container container;
    private final ContainerData data;

    // Client-side constructor - server tells us to open, contents sync in afterward
    public TemplateWorkbenchMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, new SimpleContainer(TemplateWorkbenchBlockEntity.SLOT_COUNT), new SimpleContainerData(1));
    }

    // Server-side constructor - real block entity backs the container
    public TemplateWorkbenchMenu(int containerId, Inventory playerInventory, TemplateWorkbenchBlockEntity blockEntity) {
        this(containerId, playerInventory, blockEntity, new ContainerData() {
            @Override
            public int get(int index) {
                return blockEntity.getActiveSubSlots();
            }

            @Override
            public void set(int index, int value) {
                // Derived from the base slot's contents, never set directly
            }

            @Override
            public int getCount() {
                return 1;
            }
        });
    }

    private TemplateWorkbenchMenu(int containerId, Inventory playerInventory, Container container, ContainerData data) {
        super(ModMenuTypes.TEMPLATE_WORKBENCH, containerId);
        checkContainerSize(container, TemplateWorkbenchBlockEntity.SLOT_COUNT);
        this.container = container;
        this.data = data;
        container.startOpen(playerInventory.player);

        addSlot(new Slot(container, TemplateWorkbenchBlockEntity.BASE_SLOT, BASE_SLOT_X, SLOT_Y));
        addSlot(new TemplateSlot(container, TemplateWorkbenchBlockEntity.SUB_SLOT_3, SUB_SLOT_3_X, SLOT_Y,
                () -> activeSubSlots() >= 1));
        addSlot(new TemplateSlot(container, TemplateWorkbenchBlockEntity.SUB_SLOT_2, SUB_SLOT_2_X, SLOT_Y,
                () -> activeSubSlots() >= 2));
        addSlot(new TemplateSlot(container, TemplateWorkbenchBlockEntity.SUB_SLOT_1, SUB_SLOT_1_X, SLOT_Y,
                () -> activeSubSlots() >= 3));
        addSlot(new Slot(container, TemplateWorkbenchBlockEntity.OUTPUT_SLOT, OUTPUT_SLOT_X, SLOT_Y) {
            @Override
            public boolean mayPlace(@NonNull ItemStack stack) {
                return false; // output only - TODO: wire up real crafting once recipes exist
            }
        });

        addStandardInventorySlots(playerInventory, INVENTORY_START_X, INVENTORY_START_Y);

        addDataSlots(data);
    }

    /** How many sub-slots are currently active, read from synced ContainerData (client-safe). */
    public int activeSubSlots() {
        return data.get(0);
    }

    @Override
    public @NonNull ItemStack quickMoveStack(@NonNull Player player, int slotIndex) {
        Slot slot = slots.get(slotIndex);

        if (!slot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack stack = slot.getItem();
        ItemStack clicked = stack.copy();

        if (slotIndex < INVENTORY_START) {
            // Out of a workbench slot, into the player inventory
            if (!moveItemStackTo(stack, INVENTORY_START, INVENTORY_END, true)) {
                return ItemStack.EMPTY;
            }
        } else {
            // From the player inventory into the base slot only (sub-slots aren't a shift-click target)
            if (!moveItemStackTo(stack, TemplateWorkbenchBlockEntity.BASE_SLOT, TemplateWorkbenchBlockEntity.BASE_SLOT + 1, false)) {
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
    public boolean stillValid(@NonNull Player player) {
        return container.stillValid(player);
    }

    @Override
    public void removed(@NonNull Player player) {
        super.removed(player);
        container.stopOpen(player);
    }
}
