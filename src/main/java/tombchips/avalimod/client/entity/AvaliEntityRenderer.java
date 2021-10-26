package tombchips.avalimod.client.entity;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.client.model.AvaliEntityModel;
import tombchips.avalimod.common.entity.AvaliEntity;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
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
}
