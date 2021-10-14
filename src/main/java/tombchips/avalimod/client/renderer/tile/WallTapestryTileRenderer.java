package tombchips.avalimod.client.renderer.tile;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;
import tombchips.avalimod.client.model.WallTapestryTileModel;
import tombchips.avalimod.common.te.WallTapestryTE;

public class WallTapestryTileRenderer extends GeoBlockRenderer<WallTapestryTE> {
    public WallTapestryTileRenderer(TileEntityRendererDispatcher rendererDispatcherIn, AnimatedGeoModel modelProvider) {
        super(rendererDispatcherIn, new WallTapestryTileModel());
    }
}
