package tombchips.avalimod.core;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import tombchips.avalimod.common.effects.FreezingEffect;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AEffects {
    public static List<Effect> effects = new ArrayList<>();


    public static Effect FREEZING = createEffect(new FreezingEffect(EffectType.HARMFUL, Color.WHITE.getRGB()).addAttributeModifier(Attributes.ATTACK_SPEED,
            "55FCED67-E92A-486E-9800-B47F202C4386", -1.0D,AttributeModifier.Operation.MULTIPLY_TOTAL), "freezing");



    public static Effect createEffect(Effect effect, String id){
        effect.setRegistryName(id);
        effects.add(effect);
        return effect;
    }


    public static void init() {
    }
}