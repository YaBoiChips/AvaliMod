package tombchips.avalimod.client.model;


import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.common.entity.AvaliEntity;

public class AvaliEntityModel<T extends AvaliEntity> extends AnimatedGeoModel<T> {


    @Override
    public ResourceLocation getModelLocation(AvaliEntity object) {
        return new ResourceLocation(AvaliMod.MOD_ID, "geo/avali.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(AvaliEntity object) {
        return new ResourceLocation(AvaliMod.MOD_ID, "textures/entity/avali.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(AvaliEntity animatable) {
        return new ResourceLocation(AvaliMod.MOD_ID, "animations/avali.animation.json");
    }
}
