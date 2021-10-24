package tombchips.avalimod.client.renderer.tile;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;
import tombchips.avalimod.client.model.SmallCanisterModel;
import tombchips.avalimod.common.te.WallTapestryTE;

public class SmallCanisterRenderer extends GeoBlockRenderer<WallTapestryTE> {
    public SmallCanisterRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new SmallCanisterModel());
    }


}
