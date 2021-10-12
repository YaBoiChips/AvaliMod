package tombchips.avalimod.common.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import tombchips.avalimod.core.AEffects;

public class FreezingEffect extends Effect {
    public FreezingEffect(EffectType p_i50391_1_, int p_i50391_2_) {
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
            return this == Effects.HUNGER;
        }
    }
}
