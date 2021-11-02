package tombchips.avalimod.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import tombchips.avalimod.AvaliMod;

public class WallTapestryTileModel extends AnimatedGeoModel {
    @Override
    public ResourceLocation getAnimationFileLocation(Object entity) {
        return new ResourceLocation(AvaliMod.MOD_ID, "animations/tapestry.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(Object animatable) {
        return new ResourceLocation(AvaliMod.MOD_ID, "geo/softtapestry.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Object entity) {
        return new ResourceLocation(AvaliMod.MOD_ID, "textures/block/walltapestry.png");
    }

}
