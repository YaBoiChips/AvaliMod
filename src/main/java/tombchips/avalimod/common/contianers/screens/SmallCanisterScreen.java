package tombchips.avalimod.common.contianers.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.common.contianers.SmallCanisterContainer;

public class SmallCanisterScreen extends AbstractContainerScreen<SmallCanisterContainer> {

    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(AvaliMod.MOD_ID, "textures/gui/small_can.png");


    public SmallCanisterScreen(SmallCanisterContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 175;
        this.imageHeight = 183;
    }

    @Override
    public void render(PoseStack matrixStack, final int mouseX, final int mouseY, final float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }


    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.clearColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
        int x = (this.width - this.getXSize()) / 2;
        int y = (this.height - this.getYSize()) / 2;
        this.blit(matrixStack, x, y, 0, 0, this.getXSize(), this.getYSize());
    }
}
