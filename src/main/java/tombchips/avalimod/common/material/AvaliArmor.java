package tombchips.avalimod.common.material;


import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.core.AItems;

import java.util.function.Supplier;

public enum AvaliArmor implements ArmorMaterial {
    AVALI(AvaliMod.MOD_ID + ":avali", 37, new int[]{3, 6, 8, 3}, 420, SoundEvents.ARMOR_EQUIP_NETHERITE, 0.1F, 3.0F, () -> {
        return Ingredient.of(AItems.AVALI_LOGO);
    });

    public static final int[] HEALTH_PER_SLOT = new int[]{1, 3, 3, 1};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairMaterial;

    AvaliArmor(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountArrayIn, int enchantabilityIn, SoundEvent soundEventIn, float resistance, float toughnessIn, Supplier<Ingredient> repairMaterialIn) {
        this.name = nameIn;
        this.maxDamageFactor = maxDamageFactorIn;
        this.damageReductionAmountArray = damageReductionAmountArrayIn;
        this.enchantability = enchantabilityIn;
        this.soundEvent = soundEventIn;
        this.toughness = toughnessIn;
        this.knockbackResistance = resistance;
        this.repairMaterial = new LazyLoadedValue<>(repairMaterialIn);
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlot slot) {
        return HEALTH_PER_SLOT[slot.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot slot) {
        return damageReductionAmountArray[slot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.soundEvent;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairMaterial.get();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
