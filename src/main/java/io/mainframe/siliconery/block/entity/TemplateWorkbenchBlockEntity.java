package io.mainframe.siliconery.block.entity;

import io.mainframe.siliconery.item.ModItemList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;

public class TemplateWorkbenchBlockEntity extends BlockEntity implements ImplementedContainer, MenuProvider {
    // Slot layout: 0 = base, 1..3 = sub-slots (slot1/slot2/slot3), 4 = output.
    // Sub-slots are pre-allocated for the biggest mode we support right now (Casing Template
    // needs 2); they're gated active/inactive by getActiveSubSlots(), not by existing or not.
    public static final int BASE_SLOT = 0;
    public static final int SUB_SLOT_1 = 1;
    public static final int SUB_SLOT_2 = 2;
    public static final int SUB_SLOT_3 = 3;
    public static final int OUTPUT_SLOT = 4;
    public static final int SLOT_COUNT = 5;

    private final NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);

    public TemplateWorkbenchBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.TEMPLATE_WORKBENCH, pos, state);
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return items;
    }

    /**
     * How many sub-slots (counting inward from SUB_SLOT_3 towards SUB_SLOT_1) are active for
     * whatever's currently in the base slot. Hardcoded per-mode for now; once a real
     * data-driven TemplateWorkbenchRecipe exists this should look it up instead.
     */
    public int getActiveSubSlots() {
        Item base = items.get(BASE_SLOT).getItem();
        if (base == ModItemList.SCREW_TEMPLATE) {
            return 1;
        }
        if (base == ModItemList.CASING_TEMPLATE) {
            return 2;
        }
        return 0;
    }

    @Override
    public boolean stillValid(@NonNull Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, items);
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        ContainerHelper.saveAllItems(output, items);
        super.saveAdditional(output);
    }

    @Override
    public @NonNull Component getDisplayName() {
        return Component.translatable("block.siliconery.template_workbench");
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, @NonNull Inventory playerInventory, @NonNull Player player) {
        return new TemplateWorkbenchMenu(containerId, playerInventory, this);
    }
}
