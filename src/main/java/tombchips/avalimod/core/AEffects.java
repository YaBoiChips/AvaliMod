package tombchips.avalimod.core;


import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import tombchips.avalimod.common.effects.FreezingEffect;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AEffects {
    public static List<MobEffect> effects = new ArrayList<>();


    public static MobEffect FREEZING = createEffect(new FreezingEffect(MobEffectCategory.HARMFUL, Color.WHITE.getRGB()).addAttributeModifier(Attributes.ATTACK_SPEED,
            "55FCED67-E92A-486E-9800-B47F202C4386", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL), "freezing");



    public static MobEffect createEffect(MobEffect effect, String id){
        effect.setRegistryName(id);
        effects.add(effect);
        return effect;
    }


    public static void init() {
    }
}