package tombchips.avalimod.core.world;


import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import tombchips.avalimod.common.world.structure.village.villagepools.AvaliVillagePool;

public class AConfiguredStructures {

    public static final ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> VILLAGE_AVALI = register("village_avali", StructureFeature.VILLAGE.configured(new JigsawConfiguration(() -> {
        return AvaliVillagePool.AvaliVillageJigsaw;
    }, 6)));

    private static <FC extends FeatureConfiguration, F extends StructureFeature<FC>> ConfiguredStructureFeature<FC, F> register(String name, ConfiguredStructureFeature<FC, F> structure) {
        return BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, name, structure);
    }
}
