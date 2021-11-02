package tombchips.avalimod.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.common.entity.AvaliEntity;

import java.util.ArrayList;
import java.util.List;

public class AEntityTypes {
    public static List<EntityType<?>> entities = new ArrayList<>();

    public static final EntityType<AvaliEntity> AVALI = createEntity( "avali", EntityType.Builder.of(AvaliEntity::new,
            MobCategory.CREATURE).sized(0.6f, 1.63f).build("avali"));

    public static <E extends Entity, ET extends EntityType<E>> ET createEntity(String id, ET entityType) {
        entityType.setRegistryName(new ResourceLocation(AvaliMod.MOD_ID, id));
        entities.add(entityType);
        return entityType;
    }
    public static void init() {
    }
}
