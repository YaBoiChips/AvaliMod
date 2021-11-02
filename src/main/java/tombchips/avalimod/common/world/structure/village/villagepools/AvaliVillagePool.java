package tombchips.avalimod.common.world.structure.village.villagepools;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.structures.StructurePoolElement;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;


public class AvaliVillagePool {
    public static final StructureTemplatePool AvaliVillageJigsaw = Pools.register(new StructureTemplatePool(new ResourceLocation("avalimod:village/town_centers"),
            new ResourceLocation("empty"),
            ImmutableList.of(Pair.of(StructurePoolElement.legacy("avalimod:village/town_centers/avali_meeting_point_1", ProcessorLists.MOSSIFY_10_PERCENT), 90),
                    Pair.of(StructurePoolElement.legacy("avalimod:village/streets/corner_02"), 3),
                    Pair.of(StructurePoolElement.legacy("avalimod:village/streets/corner_03"), 3),
                    Pair.of(StructurePoolElement.legacy("avalimod:village/streets/straight_01"), 4),
                    Pair.of(StructurePoolElement.legacy("avalimod:village/streets/straight_02"), 4),
                    Pair.of(StructurePoolElement.legacy("avalimod:village/streets/straight_03"), 3),
                    Pair.of(StructurePoolElement.legacy("avalimod:village/streets/crossroad_01"), 3),
                    Pair.of(StructurePoolElement.legacy("avalimod:village/streets/crossroad_02"), 3),
                    Pair.of(StructurePoolElement.legacy("avalimod:village/streets/crossroad_03"), 3),
                    Pair.of(StructurePoolElement.legacy("avalimod:village/villagers/unemployed"), 1),
                    Pair.of(StructurePoolElement.legacy("avalimod:village/town_centers/avali_meeting_point_2", ProcessorLists.MOSSIFY_10_PERCENT), 10)), StructureTemplatePool.Projection.RIGID));

}
