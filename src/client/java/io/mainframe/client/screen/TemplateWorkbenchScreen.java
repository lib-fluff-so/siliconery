package io.mainframe.client.screen;

import io.mainframe.siliconery.Siliconery;
import io.mainframe.siliconery.block.entity.TemplateWorkbenchMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import org.jspecify.annotations.NonNull;

public class TemplateWorkbenchScreen extends AbstractContainerScreen<TemplateWorkbenchMenu> {
    private static final Identifier TEXTURE = Siliconery.id("textures/gui/container/template_workbench.png");
    private static final Identifier SLOT_SPRITE = Identifier.withDefaultNamespace("slot");

    private static final int[] SUB_SLOT_X = {43, 61, 79};
    private static final int SLOT_Y = 34;

    public TemplateWorkbenchScreen(TemplateWorkbenchMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    public void extractBackground(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractBackground(graphics, mouseX, mouseY, delta);
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight, 256, 256);

        int active = menu.activeSubSlots();
        for (int i = 0; i < active && i < SUB_SLOT_X.length; i++) {
            int x = leftPos + SUB_SLOT_X[i];
            int y = topPos + SLOT_Y;
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, SLOT_SPRITE, x - 1, y - 1, 18, 18);
        }
    }
}
