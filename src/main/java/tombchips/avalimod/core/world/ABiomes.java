package tombchips.avalimod.core.world;

import net.minecraft.world.biome.Biome;
import tombchips.avalimod.AvaliMod;

import java.util.ArrayList;
import java.util.List;

public class ABiomes {
    public static List<PreserveBiomeOrder> biomes = new ArrayList<>();





    public static Biome registerBiome(String id, Biome biome, int numid) {
        biome.setRegistryName(AvaliMod.createResource(id));

        biomes.add(new PreserveBiomeOrder(biome, numid));

        return biome;
    }

    public static void init() {
    }

    public static class PreserveBiomeOrder {
        private final Biome biome;
        private final int orderPosition;

        public PreserveBiomeOrder(Biome biome, int orderPosition) {
            this.biome = biome;
            this.orderPosition = orderPosition;
        }

        public Biome getBiome() {
            return biome;
        }

        public int getOrderPosition() {
            return orderPosition;
        }
    }
}
