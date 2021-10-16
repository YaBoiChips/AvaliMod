package tombchips.avalimod.client.model;


import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.common.entity.AvaliEntity;

public class AvaliEntityModel<T extends IAnimatable> extends AnimatedGeoModel<T> {




    @Override
    public ResourceLocation getModelLocation(T object) {
        return new ResourceLocation(AvaliMod.MOD_ID, "geo/avali.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(T object) {
        return new ResourceLocation(AvaliMod.MOD_ID, AvaliEntity.TEXTURES[AvaliEntity.VARIANT]);
    }

    @Override
    public ResourceLocation getAnimationFileLocation(T animatable) {
        return new ResourceLocation(AvaliMod.MOD_ID, "animations/avali.animation.json");
    }

    @Override
    public void setLivingAnimations(IAnimatable entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations((T) entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("Head");

        LivingEntity entityIn = (LivingEntity) entity;
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }


}
