package tombchips.avalimod.common.entity.avali;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import tombchips.avalimod.AvaliMod;

import javax.annotation.Nonnull;
import java.util.Map;

public class AvaliEntityRenderer<T extends AvaliEntity> extends GeoEntityRenderer<T> {

    public static final Map<AvaliEntity.SkinColors, ResourceLocation> AVALI_TEXTURE = Util.make(Maps.newEnumMap(AvaliEntity.SkinColors.class), (map) -> {
        map.put(AvaliEntity.SkinColors.BLUE, createTexture("blue"));
        map.put(AvaliEntity.SkinColors.YELLOW, createTexture("yellow"));
        map.put(AvaliEntity.SkinColors.GREEN, createTexture("green"));
        map.put(AvaliEntity.SkinColors.PURPLE, createTexture("purple"));
        map.put(AvaliEntity.SkinColors.RED, createTexture("red"));
        map.put(AvaliEntity.SkinColors.ORANGE, createTexture("orange"));
    });

    public AvaliEntityRenderer(EntityRendererProvider.Context manager) {
        super(manager, new AvaliEntityModel());
        this.shadowRadius = 0.4F;

    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return AVALI_TEXTURE.get(entity.getSkinColor());
    }

    private static @Nonnull
    ResourceLocation createTexture(String name) {
        return AvaliMod.createResource("textures/entity/avalitextures/" + name + "_avali.png");
    }

    @Override
    public void renderRecursively(GeoBone bone, PoseStack stack, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (bone.getName().equals("righthand")) {
            stack.pushPose();
            stack.mulPose(Vector3f.XP.rotationDegrees(-88));
            stack.mulPose(Vector3f.YP.rotationDegrees(0));
            stack.mulPose(Vector3f.ZP.rotationDegrees(0));
            stack.translate(0.27D, 0.02D, 0.57D);
            stack.scale(0.70f, 0.70f, 0.70f);
            Minecraft.getInstance().getItemRenderer().renderStatic(mainHand, ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, packedLightIn, packedOverlayIn, stack, this.rtb, 1);
            stack.popPose();
            bufferIn = rtb.getBuffer(RenderType.entityTranslucent(whTexture));
        }
        super.renderRecursively(bone, stack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}
