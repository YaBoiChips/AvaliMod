package tombchips.avalimod.client.entity;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.vector.Vector3f;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.client.model.AvaliEntityModel;
import tombchips.avalimod.common.entity.AvaliEntity;

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

    public AvaliEntityRenderer(EntityRendererManager manager) {
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
    public void render(T entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer bufferIn, int packedLightIn) {
        if (entity.isBaby()){
            stack.scale(0.5f, 0.5f, 0.5f);
        }
        stack.scale(1f, 1f, 1f);
        super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
    }

    @Override
    public void renderRecursively(GeoBone bone, MatrixStack stack, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (bone.getName().equals("righthand")) { // rArmRuff is the name of the bone you to set the item to attach too. Please see Note
            stack.pushPose();
            //You'll need to play around with these to get item to render in the correct orientation
            stack.mulPose(Vector3f.XP.rotationDegrees(-88));
            stack.mulPose(Vector3f.YP.rotationDegrees(0));
            stack.mulPose(Vector3f.ZP.rotationDegrees(0));
            //You'll need to play around with this to render the item in the correct spot.
            stack.translate(0.27D, 0.02D, 0.57D);
            //Sets the scaling of the item.
            stack.scale(0.70f, 0.70f, 0.70f);
            // Change mainHand to predefined Itemstack and TransformType to what transform you would want to use.
            Minecraft.getInstance().getItemRenderer().renderStatic(mainHand, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, packedLightIn, packedOverlayIn, stack, this.rtb);
            //Minecraft.getInstance().getItemRenderer().renderItem(mainHand, TransformType.THIRD_PERSON_RIGHT_HAND, packedLightIn, packedOverlayIn, stack, this.rtb);
            stack.popPose();
            bufferIn = rtb.getBuffer(RenderType.entityTranslucent(whTexture));
        }
        super.renderRecursively(bone, stack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}
