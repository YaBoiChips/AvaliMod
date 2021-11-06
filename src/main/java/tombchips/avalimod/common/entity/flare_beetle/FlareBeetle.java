package tombchips.avalimod.common.entity.flare_beetle;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import tombchips.avalimod.common.entity.avali.AvaliEntity;
import tombchips.avalimod.core.AEntityTypes;

import javax.annotation.Nullable;


public class FlareBeetle extends Animal implements FlyingAnimal, IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);


    //initialize entity
    public FlareBeetle(EntityType<? extends FlareBeetle> type, Level world) {
        super(type, world);
        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.FENCE, -1.0F);
    }

    public static AttributeSupplier.Builder setCustiomAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.18F)
                .add(Attributes.FOLLOW_RANGE, 48.0D)
                .add(Attributes.FLYING_SPEED, 0.6F)
                .add(Attributes.ATTACK_DAMAGE, 2.0D);
    }


    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, AvaliEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(ItemTags.FLOWERS), false));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag tag) {
        return super.finalizeSpawn(world, difficultyIn, reason, spawnData == null ? new AgeableMob.AgeableMobGroupData(1.0F) : spawnData, tag);
    }

    @Override
    protected PathNavigation createNavigation(Level p_27815_) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, p_27815_) {
            public boolean isStableDestination(BlockPos p_27947_) {
                return !this.level.getBlockState(p_27947_.below()).isAir();
            }
        };
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(false);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }

    //entity data
    @Override
    public boolean isFlying() {
        return !this.isOnGround();
    }

    @Override
    public void tick() {
        super.tick();
        createFlameParticle();
    }

    public void createFlameParticle(){
            if (level.isClientSide) {
                double x = Mth.lerp(random.nextDouble(), getX() - 0.3D, getX() + 0.3D);
                double z = Mth.lerp(random.nextDouble(), getZ() - 0.4D, getZ() + 0.4D);
                level.addParticle(ParticleTypes.FLAME, true, x, getY(0.4D), z, 0.0D, 0.0D, 0.D);
            }
        }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
        return false;
    }

    @Override
    public boolean canBreed() {
        return !this.isBaby();
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob mate) {
        return AEntityTypes.FLARE_BEETLE.create(world);
    }


    //GeckoLib
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        AnimationController controller = event.getController();
        controller.transitionLengthTicks = 0;
        if (this.isFlying()) {
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.fire_beetle.fly", true));
            return PlayState.CONTINUE;
        } else{
            return PlayState.STOP;
        }
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

}
