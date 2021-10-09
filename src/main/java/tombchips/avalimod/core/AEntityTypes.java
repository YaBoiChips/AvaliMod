package tombchips.avalimod.core;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import tombchips.avalimod.AvaliMod;

import java.util.ArrayList;
import java.util.List;

public class AEntityTypes {
    public static List<EntityType<?>> entities = new ArrayList<>();



    public static <E extends Entity, ET extends EntityType<E>> ET createEntity(String id, ET entityType) {
        entityType.setRegistryName(new ResourceLocation(AvaliMod.MOD_ID, id));
        entities.add(entityType);
        return entityType;
    }
    public static void init() {
    }
}
