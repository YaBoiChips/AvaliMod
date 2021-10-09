package tombchips.avalimod.core.world;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import tombchips.avalimod.AvaliMod;

import java.util.ArrayList;
import java.util.List;

public class AFeatures {
    public static List<Feature<?>> features = new ArrayList<>();



    public static <C extends IFeatureConfig, F extends Feature<C>> F register(String id, F feature) {
        ResourceLocation featureID = AvaliMod.createResource(id);
        feature.setRegistryName(featureID);
        features.add(feature);
        return feature;
    }

    public static void init() {

    }
}
