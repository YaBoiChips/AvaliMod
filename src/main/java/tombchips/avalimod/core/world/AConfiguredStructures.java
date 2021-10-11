package tombchips.avalimod.core.world;

import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import tombchips.avalimod.common.world.structure.village.villagepools.AvaliVillagePool;

public class AConfiguredStructures {

    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> VILLAGE_AVALI = register("village_avali", Structure.VILLAGE.configured(new VillageConfig(() -> {
        return AvaliVillagePool.AvaliVillageJigsaw;
    }, 6)));

    private static <FC extends IFeatureConfig, F extends Structure<FC>> StructureFeature<FC, F> register(String name, StructureFeature<FC, F> structure) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, name, structure);
    }
}
