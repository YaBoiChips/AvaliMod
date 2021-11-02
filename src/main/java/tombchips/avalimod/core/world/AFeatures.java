package tombchips.avalimod.core.world;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.common.dimension.biomes.features.AmmoniaIceSpikeFeature;

import java.util.ArrayList;
import java.util.List;

public class AFeatures {
    public static List<Feature<?>> features = new ArrayList<>();
    public static final Feature<NoneFeatureConfiguration> AMMONIA_ICE_SPIKE = register("ammonia_ice_spike", new AmmoniaIceSpikeFeature(NoneFeatureConfiguration.CODEC));



    public static <C extends FeatureConfiguration, F extends Feature<C>> F register(String id, F feature) {
        ResourceLocation featureID = AvaliMod.createResource(id);
        feature.setRegistryName(featureID);
        features.add(feature);
        return feature;
    }

    public static void init() {

    }
}
