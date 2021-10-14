package tombchips.avalimod.common.te;

import net.minecraft.tileentity.TileEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import tombchips.avalimod.core.ATileEntityTypes;

public class WallTapestryTE extends TileEntity implements IAnimatable {


    public WallTapestryTE() {
        super(ATileEntityTypes.WALL_TAPESTRY);
    }

    private final AnimationFactory manager = new AnimationFactory(this);

    private <E extends TileEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        AnimationController controller = event.getController();
        controller.transitionLengthTicks = 0;

        controller.setAnimation(new AnimationBuilder().addAnimation("Botarium.anim.deploy", true));
        return PlayState.CONTINUE;
    }



    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.manager;
    }
}
