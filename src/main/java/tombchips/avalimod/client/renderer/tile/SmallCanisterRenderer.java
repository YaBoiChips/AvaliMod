package tombchips.avalimod.client.renderer.tile;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;
import tombchips.avalimod.client.model.SmallCanisterModel;
import tombchips.avalimod.common.te.SmallCanisterTE;

public class SmallCanisterRenderer extends GeoBlockRenderer<SmallCanisterTE> {
    public SmallCanisterRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new SmallCanisterModel());
    }

    @Override
    public boolean shouldRender(BlockEntity p_173568_, Vec3 p_173569_) {
        return true;
    }
}
