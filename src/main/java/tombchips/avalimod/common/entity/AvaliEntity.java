package tombchips.avalimod.common.entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
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
import tombchips.avalimod.core.ASounds;
import tombchips.avalimod.util.Maths;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;

public class AvaliEntity extends AgeableEntity implements IAnimatable {

    private static final DataParameter<Boolean> SLEEPING = EntityDataManager.defineId(AvaliEntity.class, DataSerializers.BOOLEAN);

    private final AnimationFactory factory = new AnimationFactory(this);
    public static float movementSpeed = 0.45f;
    public static final EntitySize AVALI_SIZE = EntitySize.scalable(0.6f, 1.63f);
    public static final EntitySize BABY_SIZE = EntitySize.scalable(0.3f, 0.815f);
    private static final DataParameter<Integer> COLOUR = EntityDataManager.defineId(AvaliEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> SLEEP_TIMER = EntityDataManager.defineId(AvaliEntity.class, DataSerializers.INT);
    private static final DataParameter<Boolean> GUARD = EntityDataManager.defineId(AvaliEntity.class, DataSerializers.BOOLEAN);

    public AvaliEntity(EntityType<? extends AgeableEntity> type, World worldIn) {
        super(type, worldIn);
    }


    @Override
    public EntitySize getDimensions(Pose p_213305_1_) {
        if (this.isBaby()) {
            return BABY_SIZE;
        } else return AVALI_SIZE;
    }

    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
        setSkinColor(getRandomAvaliColor(random));
        setGuard(random.nextBoolean());
        if (this.isGuard()){
            this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(AItems.NANOBLADE_SPEAR));
        }
        return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
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
    public void readAdditionalSaveData(CompoundNBT compoundNBT) {
        super.readAdditionalSaveData(compoundNBT);
        this.setSleepTimer(compoundNBT.getInt("SleepTimer"));
        this.setSleeping(compoundNBT.getBoolean("Avali_Sleeping"));
        this.setRawFlag(compoundNBT.getInt("Flag"));
        this.setGuard(compoundNBT.getBoolean("Guard"));
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compoundNBT) {
        super.addAdditionalSaveData(compoundNBT);
        compoundNBT.putInt("SleepTimer", this.getSleepTimer());
        compoundNBT.putBoolean("Avali_Sleeping", this.isSleeping());
        compoundNBT.putInt("Flag", this.getRawFlag());
        compoundNBT.putBoolean("Guard", this.isGuard());
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
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0f));
        if (this.isGuard()) {
            this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
            this.targetSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, true));
        }
    }

    @Override
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        if (player.getItemInHand(hand).getItem() == AItems.AVALI_SPAWN_EGG) {
            AvaliEntity entity = new AvaliEntity(AEntityTypes.AVALI, this.level);
            entity.setAge(-1000);
            entity.setPos(this.getX(), this.getY(), this.getZ());
            entity.setSkinColor(getRandomAvaliColor(random));
            this.level.addFreshEntity(entity);
            return ActionResultType.SUCCESS;
        } else return ActionResultType.FAIL;
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



