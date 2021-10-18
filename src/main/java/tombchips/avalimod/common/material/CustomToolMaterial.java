package tombchips.avalimod.common.material;


import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import tombchips.avalimod.core.AItems;

import java.util.function.Supplier;

public enum CustomToolMaterial implements IItemTier
{

    NANOBLADE_TOOLS (2, 400, 8f, 4f, 30, () -> Ingredient.of(AItems.NANOBLADE_AXE));

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDammage;
    private final int enchantability;
    private final Ingredient repairMaterial;

    private CustomToolMaterial(int harvestLevel, int maxUses, float efficiency, float attackDammage, int enchantability, Supplier<Ingredient> repairMaterial) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDammage = attackDammage;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial.get();
    }





    @Override
    public int getUses() {
        return maxUses;
    }

    @Override
    public float getSpeed() {
        return efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDammage;
    }

    @Override
    public int getLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairMaterial;
    }


}
