package tombchips.avalimod.client.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.client.model.AvaliEntityModel;
import tombchips.avalimod.common.entity.AvaliEntity;

import javax.annotation.ParametersAreNonnullByDefault;

public class AvaliEntityRenderer<T extends AvaliEntity>  extends GeoEntityRenderer<T> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(AvaliMod.MOD_ID, "textures/entity/avali.png");

    public AvaliEntityRenderer(EntityRendererManager manager) {
        super(manager, new AvaliEntityModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public ResourceLocation getTextureLocation(T p_110775_1_) {
        return TEXTURE;
    }

    @Override
    public void render(T entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer bufferIn, int packedLightIn) {
        if (entity.isBaby()){
            stack.scale(0.5f, 0.5f, 0.5f);
        }
        stack.scale(1f, 1f, 1f);
        super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
    }
}
