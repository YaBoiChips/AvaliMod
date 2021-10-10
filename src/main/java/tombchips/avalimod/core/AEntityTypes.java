package tombchips.avalimod.core;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.common.entity.AvaliEntity;

import java.util.ArrayList;
import java.util.List;

public class AEntityTypes {
    public static List<EntityType<?>> entities = new ArrayList<>();

    public static final EntityType<AvaliEntity> AVALI = createEntity( "avali", EntityType.Builder.of(AvaliEntity::new,
            EntityClassification.CREATURE ).sized(0.6f, 1.63f).build("avali"));

    public static <E extends Entity, ET extends EntityType<E>> ET createEntity(String id, ET entityType) {
        entityType.setRegistryName(new ResourceLocation(AvaliMod.MOD_ID, id));
        entities.add(entityType);
        return entityType;
    }
    public static void init() {
    }
}
