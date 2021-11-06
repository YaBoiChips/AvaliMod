package tombchips.avalimod.common.entity.flare_beetle;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import tombchips.avalimod.AvaliMod;

public class FlareBeetleModel<T extends IAnimatable> extends AnimatedGeoModel<T> {
    @Override
    public ResourceLocation getModelLocation(T object) {
        return new ResourceLocation(AvaliMod.MOD_ID, "geo/flarebeetle.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(T object) {
        return FlareBeetleRenderer.TEXTURES;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(T animatable) {
        return new ResourceLocation(AvaliMod.MOD_ID, "animations/flarebeetle.animation.json");
    }
}
