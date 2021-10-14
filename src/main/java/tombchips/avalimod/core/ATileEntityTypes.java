package tombchips.avalimod.core;

import com.mojang.datafixers.types.Type;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.datafix.TypeReferences;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.common.te.WallTapestryTE;

import java.util.ArrayList;
import java.util.List;

public class ATileEntityTypes {
    public static List<TileEntityType<?>> tileentity = new ArrayList<>();

    public static final TileEntityType<WallTapestryTE> WALL_TAPESTRY = register( "wall_tapestry", TileEntityType.Builder.of(WallTapestryTE::new, ABlocks.WALL_TAPESTRY) );




    private static <T extends TileEntity> TileEntityType<T> register(String key, TileEntityType.Builder<T> builder) {
        Type<?> type = Util.fetchChoiceType(TypeReferences.BLOCK_ENTITY, key);
        TileEntityType<T> tileEntityType = builder.build(type);
        tileEntityType.setRegistryName(new ResourceLocation(AvaliMod.MOD_ID, key));
        tileentity.add(tileEntityType);
        return tileEntityType;
    }


    public static void init() {
    }
}
