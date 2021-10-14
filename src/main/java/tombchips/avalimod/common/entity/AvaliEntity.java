package tombchips.avalimod.common.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import tombchips.avalimod.core.ABlocks;
import tombchips.avalimod.core.AEntityTypes;
import tombchips.avalimod.core.AItems;

import javax.annotation.Nullable;

public class AvaliEntity extends AgeableEntity implements IAnimatable {

    private static final DataParameter<Boolean> SLEEPING = EntityDataManager.defineId(AvaliEntity.class, DataSerializers.BOOLEAN);

    private AnimationFactory factory = new AnimationFactory(this);
    public static float movementSpeed = 0.45f;

    public AvaliEntity(EntityType<? extends AgeableEntity> type, World worldIn) {
         super(type, worldIn);


    }

//    @Override
//    public EntitySize getDimensions(Pose p_213305_1_) {
//        return super.getDimensions(p_213305_1_);
//    }


    @Override
    protected void defineSynchedData() {
        this.entityData.define(SLEEPING, false);
        super.defineSynchedData();
    }

    @Override
    public void aiStep() {
        if(!this.level.isClientSide){
            if(this.canSleep(this) && this.level.isNight()){
                this.setSleeping(true);
                movementSpeed = 0;
            }
            else {
                this.setSleeping(false);
                movementSpeed = 0.45f;

            }
        }
        super.aiStep();

    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compoundNBT) {
        super.readAdditionalSaveData(compoundNBT);
        this.setSleeping(compoundNBT.getBoolean("Avali_Sleeping"));
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compoundNBT) {
        super.addAdditionalSaveData(compoundNBT);
        compoundNBT.putBoolean("Avali_Sleeping", this.isSleeping());
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return null;
    }


    public static AttributeModifierMap.MutableAttribute setCustiomAttributes() {
        return MobEntity.createLivingAttributes().add(Attributes.MAX_HEALTH, 25.0d)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.ATTACK_SPEED, 0.4D)
                .add(Attributes.MOVEMENT_SPEED, movementSpeed)
                .add(Attributes.FOLLOW_RANGE, 8.0f);

    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));

        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, movementSpeed));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0f));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, false));

    }

    @Override
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        if (player.getItemInHand(hand).getItem() == AItems.AVALI_SPAWN_EGG){
            AvaliEntity entity = new AvaliEntity(AEntityTypes.AVALI, this.level);
            entity.isBaby();
            entity.setPos(this.getX(), this.getY(), this.getZ());
            this.level.addFreshEntity(entity);
            return ActionResultType.SUCCESS;
        }
        else return ActionResultType.FAIL;
    }

    @Override
    public boolean canBreed() {
        return !this.isBaby();
    }



    @Override
    protected SoundEvent getDeathSound() {

        return SoundEvents.CAT_DEATH;
    }


    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        AnimationController controller = event.getController();
        controller.transitionLengthTicks = 0;

        if(this.isOnGround() && event.isMoving()){
            controller.setAnimation(new AnimationBuilder().addAnimation("avali.animation.walk", true));
            return PlayState.CONTINUE;
        }
        else if(canSleep(this)){
            controller.setAnimation(new AnimationBuilder().addAnimation("avali.animation.sleep", true));
            return PlayState.CONTINUE;
        }
        else {return PlayState.STOP;}


    }

    public boolean canSleep(AvaliEntity entity) {
        return entity.getBlockStateOn() == ABlocks.FABRIC_BLOCK.defaultBlockState() || entity.getBlockStateOn() == ABlocks.FABRIC_SLAB.defaultBlockState();


    }



    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }


    //get the sets of setters and your nan

    public void setSleeping(boolean sit){
        this.entityData.set(SLEEPING, sit);
    }

    public boolean isSleeping(){
        return this.entityData.get(SLEEPING);
    }
}


