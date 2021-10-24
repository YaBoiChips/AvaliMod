package tombchips.avalimod.client.model;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import tombchips.avalimod.AvaliMod;


public class SmallCanisterModel extends AnimatedGeoModel {
    @Override
    public ResourceLocation getAnimationFileLocation(Object entity) {
        return new ResourceLocation(AvaliMod.MOD_ID, "animations/smallcanister.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(Object animatable) {
        return new ResourceLocation(AvaliMod.MOD_ID, "geo/smallcanister.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Object entity) {
        return new ResourceLocation(AvaliMod.MOD_ID, "textures/block/small_canister.png");
    }

}
