package tombchips.avalimod.common.world.structure.village.villagepools;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPatternRegistry;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.template.ProcessorLists;

public class AvaliVillagePool {
    public static final JigsawPattern AvaliVillageJigsaw = JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation("avalimod:village/town_centers"),
            new ResourceLocation("empty"),
            ImmutableList.of(Pair.of(JigsawPiece.legacy("avalimod:village/town_centers/avali_meeting_point_1", ProcessorLists.MOSSIFY_10_PERCENT), 90),
                    Pair.of(JigsawPiece.legacy("avalimod:village/streets/corner_02"), 3),
                    Pair.of(JigsawPiece.legacy("avalimod:village/streets/corner_03"), 3),
                    Pair.of(JigsawPiece.legacy("avalimod:village/streets/straight_01"), 4),
                    Pair.of(JigsawPiece.legacy("avalimod:village/streets/straight_02"), 4),
                    Pair.of(JigsawPiece.legacy("avalimod:village/streets/straight_03"), 3),
                    Pair.of(JigsawPiece.legacy("avalimod:village/streets/crossroad_01"), 3),
                    Pair.of(JigsawPiece.legacy("avalimod:village/streets/crossroad_02"), 3),
                    Pair.of(JigsawPiece.legacy("avalimod:village/streets/crossroad_03"), 3),
                    Pair.of(JigsawPiece.legacy("avalimod:village/villagers/unemployed"), 1),
                    Pair.of(JigsawPiece.legacy("avalimod:village/town_centers/avali_meeting_point_2", ProcessorLists.MOSSIFY_10_PERCENT), 10)), JigsawPattern.PlacementBehaviour.RIGID));

}
