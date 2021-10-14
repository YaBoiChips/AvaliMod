package tombchips.avalimod.client.model;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class WallTapestryTileModel extends AnimatedGeoModel {
    @Override
    public ResourceLocation getAnimationFileLocation(Object entity) {
        return new ResourceLocation(GeckoLib.ModID, "animations/tapestry.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(Object animatable) {
        return new ResourceLocation(GeckoLib.ModID, "geo/softtapestry.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Object entity) {
        return new ResourceLocation(GeckoLib.ModID, "textures/block/walltapestry.png");
    }

}
