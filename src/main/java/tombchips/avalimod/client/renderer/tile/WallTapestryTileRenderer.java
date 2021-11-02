package tombchips.avalimod.client.renderer.tile;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;
import tombchips.avalimod.client.model.WallTapestryTileModel;
import tombchips.avalimod.common.te.WallTapestryTE;

public class WallTapestryTileRenderer extends GeoBlockRenderer<WallTapestryTE> {
    public WallTapestryTileRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new WallTapestryTileModel());
    }

    @Override
    public boolean shouldRender(BlockEntity p_173568_, Vec3 p_173569_) {
        return true;
    }
}
