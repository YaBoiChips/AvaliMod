package tombchips.avalimod.common.entity.avali;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
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
import tombchips.avalimod.core.ASounds;
import tombchips.avalimod.util.Maths;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class AvaliEntity extends AgeableMob implements IAnimatable {

    private static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(AvaliEntity.class, EntityDataSerializers.BOOLEAN);

    private final AnimationFactory factory = new AnimationFactory(this);
    public static float movementSpeed = 0.45f;
    public static final EntityDimensions AVALI_SIZE = EntityDimensions.scalable(0.6f, 1.63f);
    public static final EntityDimensions BABY_SIZE = EntityDimensions.scalable(0.3f, 0.815f);
    private static final EntityDataAccessor<Integer> COLOUR = SynchedEntityData.defineId(AvaliEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SLEEP_TIMER = SynchedEntityData.defineId(AvaliEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> GUARD = SynchedEntityData.defineId(AvaliEntity.class, EntityDataSerializers.BOOLEAN);

    public AvaliEntity(EntityType<? extends AgeableMob> type, Level worldIn) {
        super(type, worldIn);
    }


    @Override
    public EntityDimensions getDimensions(Pose p_213305_1_) {
        if (this.isBaby()) {
            return BABY_SIZE;
        } else return AVALI_SIZE;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_34297_, DifficultyInstance p_34298_, MobSpawnType p_34299_, @Nullable SpawnGroupData p_34300_, @Nullable CompoundTag p_34301_) {
        setSkinColor(getRandomAvaliColor(random));
        setGuard(random.nextBoolean());
        if (this.isGuard()){
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(AItems.NANOBLADE_SPEAR));
        }
        return super.finalizeSpawn(p_34297_, p_34298_, p_34299_, p_34300_, p_34301_);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        return null;
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(SLEEPING, false);
        this.entityData.define(COLOUR, 0);
        this.entityData.define(SLEEP_TIMER, this.random.nextInt(100));
        this.entityData.define(GUARD, false);
        super.defineSynchedData();
    }


    @Override
    public void tick() {
        if (!this.level.isClientSide) {
            setSleepTimer(getSleepTimer() - 1);
            if (getSleepTimer() <= 0) {
                setSleepTimer(this.random.nextInt(100));
                if (this.canSleep(this) && this.level.isNight()) {
                    this.setSleeping(true);
                    this.setNoAi(true);

                } else {
                    this.setSleeping(false);
                    this.setNoAi(false);
                }
            }
        }
        super.tick();
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundNBT) {
        super.readAdditionalSaveData(compoundNBT);
        this.setSleepTimer(compoundNBT.getInt("SleepTimer"));
        this.setSleeping(compoundNBT.getBoolean("Avali_Sleeping"));
        this.setRawFlag(compoundNBT.getInt("Flag"));
        this.setGuard(compoundNBT.getBoolean("Guard"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundNBT) {
        super.addAdditionalSaveData(compoundNBT);
        compoundNBT.putInt("SleepTimer", this.getSleepTimer());
        compoundNBT.putBoolean("Avali_Sleeping", this.isSleeping());
        compoundNBT.putInt("Flag", this.getRawFlag());
        compoundNBT.putBoolean("Guard", this.isGuard());
    }



    public static AttributeSupplier.Builder setCustiomAttributes() {
        return Mob.createLivingAttributes().add(Attributes.MAX_HEALTH, 25.0d)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.ATTACK_SPEED, 0.4D)
                .add(Attributes.MOVEMENT_SPEED, movementSpeed)
                .add(Attributes.FOLLOW_RANGE, 8.0f);

    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, movementSpeed));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0f));
        if (this.isGuard()) {
            this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
            this.targetSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, true));
        }
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (player.getItemInHand(hand).getItem() == AItems.AVALI_SPAWN_EGG) {
            AvaliEntity entity = new AvaliEntity(AEntityTypes.AVALI, this.level);
            entity.setAge(-1000);
            entity.setPos(this.getX(), this.getY(), this.getZ());
            entity.setSkinColor(getRandomAvaliColor(random));
            this.level.addFreshEntity(entity);
            return InteractionResult.SUCCESS;
        } else return InteractionResult.FAIL;
    }

    @Override
    public boolean canBreed() {
        return !this.isBaby();
    }


    @Override
    protected SoundEvent getDeathSound() {
        return ASounds.AVALI_DEATH;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ASounds.AVALI_GENERAL;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return ASounds.AVALI_SOUND_DAMAGE;
    }

    @Override
    public int getAmbientSoundInterval() {
        return 200;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        AnimationController controller = event.getController();
        controller.transitionLengthTicks = 0;

        if (this.isOnGround() && event.isMoving()) {
            controller.setAnimation(new AnimationBuilder().addAnimation("avali.animation.walk", true));
            return PlayState.CONTINUE;
        } else if (isSleeping()) {

            controller.setAnimation(new AnimationBuilder().addAnimation("avali.animation.sleep", true));
            return PlayState.CONTINUE;
        } else {
            return PlayState.STOP;
        }
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

    public void setGuard(boolean bool){
        this.entityData.set(GUARD, bool);
    }

    public boolean isGuard(){
        return this.entityData.get(GUARD);
    }

    public void setSleepTimer(int time) {
        this.entityData.set(SLEEP_TIMER, time);
    }

    public int getSleepTimer() {
        return this.entityData.get(SLEEP_TIMER);
    }

    public void setSleeping(boolean sit) {
        this.entityData.set(SLEEPING, sit);
    }

    public boolean isSleeping() {
        return this.entityData.get(SLEEPING);
    }

    public void setSkinColor(@Nonnull SkinColors color) {
        setFlags(color);
    }

    public void setFlags(@Nonnull SkinColors color) {
        setRawFlag((color.ordinal() & Byte.MAX_VALUE) << 16);
    }

    public int getRawFlag() {
        return entityData.get(COLOUR);
    }


    public void setRawFlag(int flag) {
        entityData.set(COLOUR, flag);
    }

    public SkinColors getSkinColor() {
        return SkinColors.byIndex((getRawFlag() >> 16) & Byte.MAX_VALUE);
    }

    public static SkinColors getRandomAvaliColor(@Nonnull Random random) {
        int i = random.nextInt(13);

        if (i <= 0) {
            return SkinColors.GREEN;
        } else if (i <= 3) {
            return SkinColors.YELLOW;
        } else if (i <= 5) {
            return SkinColors.PURPLE;
        } else if (i <= 7) {
            return SkinColors.ORANGE;
        } else if (i <= 9) {
            return SkinColors.BLUE;
        } else if (i <= 10) {
            return SkinColors.RED;
        } else {
            return SkinColors.BLUE;
        }
    }



    public enum SkinColors {
        BLUE(),
        GREEN(),
        YELLOW(),
        PURPLE(),
        RED(),
        ORANGE();

        public static SkinColors byIndex(int index) {
            return Maths.get(SkinColors.values(), index);
        }
    }
}



