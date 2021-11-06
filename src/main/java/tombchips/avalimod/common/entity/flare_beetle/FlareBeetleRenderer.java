package tombchips.avalimod.common.entity.flare_beetle;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import tombchips.avalimod.AvaliMod;

public class FlareBeetleRenderer<T extends FlareBeetle> extends GeoEntityRenderer<T> {

    public static final ResourceLocation TEXTURES = new ResourceLocation(AvaliMod.MOD_ID,"textures/entity/pyre_fly.png");


    public FlareBeetleRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FlareBeetleModel());
        this.shadowRadius = 0.2F;

    }

    @Override
    public ResourceLocation getTextureLocation(T instance) {
        return TEXTURES;
    }
}
