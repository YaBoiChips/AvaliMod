package tombchips.avalimod.core.world;

import net.minecraft.world.biome.Biome;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.common.dimension.biomes.AvalonDesert;
import tombchips.avalimod.common.dimension.biomes.AvalonFrozenBiome;
import tombchips.avalimod.common.dimension.biomes.AvalonOceanBiome;

import java.util.ArrayList;
import java.util.List;

public class ABiomes {
    public static List<PreserveBiomeOrder> biomes = new ArrayList<>();


    public static final Biome AVALON_DESERT = registerBiome("avalon_desert", new AvalonDesert().getBiome(), 1);
    public static final Biome AVALON_FROZEN = registerBiome("avalon_frozen", new AvalonFrozenBiome().getBiome(), 2);
    public static final Biome AVALON_OCEAN = registerBiome("avalon_ocean", new AvalonOceanBiome().getBiome(), 3);



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
