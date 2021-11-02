package tombchips.avalimod.core;

import com.mojang.datafixers.types.Type;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.common.te.SmallCanisterTE;
import tombchips.avalimod.common.te.WallTapestryTE;

import java.util.ArrayList;
import java.util.List;

public class ATileEntityTypes {
    public static List<BlockEntityType<?>> tileentity = new ArrayList<>();

    public static final BlockEntityType<WallTapestryTE> WALL_TAPESTRY = register( "wall_tapestry", BlockEntityType.Builder.of(WallTapestryTE::new, ABlocks.WALL_TAPESTRY) );
    public static final BlockEntityType<SmallCanisterTE> SMALL_CANISTER = register( "small_canister", BlockEntityType.Builder.of(SmallCanisterTE::new, ABlocks.SMALL_CANISTER) );



    private static <T extends BlockEntity> BlockEntityType<T> register(String key, BlockEntityType.Builder<T> builder) {
        Type<?> type = Util.fetchChoiceType(References.BLOCK_ENTITY, key);
        BlockEntityType<T> tileEntityType = builder.build(type);
        tileEntityType.setRegistryName(new ResourceLocation(AvaliMod.MOD_ID, key));
        tileentity.add(tileEntityType);
        return tileEntityType;
    }


    public static void init() {
    }
}
