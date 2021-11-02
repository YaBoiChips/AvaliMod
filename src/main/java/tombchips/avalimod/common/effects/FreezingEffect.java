package tombchips.avalimod.common.effects;


import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import tombchips.avalimod.core.AEffects;

public class FreezingEffect extends MobEffect {
    public FreezingEffect(MobEffectCategory p_i50391_1_, int p_i50391_2_) {
        super(p_i50391_1_, p_i50391_2_);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int i) {
        if (this == AEffects.FREEZING) {
            entity.hurt(DamageSource.IN_WALL, (float)i / 2);
        }
    }

    @Override
    public boolean isDurationEffectTick(int p_76397_1_, int p_76397_2_) {
        if (this == AEffects.FREEZING) {
            int i = 40 >> p_76397_2_;
            if (i > 0) {
                return p_76397_1_ % i == 0;
            } else {
                return true;
            }
        } else {
            return this == MobEffects.HUNGER;
        }
    }
}
