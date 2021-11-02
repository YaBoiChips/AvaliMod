package tombchips.avalimod.client.entity;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.AABB;
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

}
