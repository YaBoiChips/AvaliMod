package tombchips.avalimod.common.items.tools;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.mojang.math.Vector3d;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;


import javax.annotation.Nullable;
import java.util.List;

public class SpearItem extends TieredItem {
    private final float attackDamage;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public SpearItem(Tier iItemTier, int i, float v, Item.Properties properties) {
        super(iItemTier, properties);
        this.attackDamage = (float)i + iItemTier.getAttackDamageBonus();
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)v, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if(!player.getCooldowns().isOnCooldown(this)){
            Vec3 vector3d = player.getLookAngle();
            player.lerpMotion(vector3d.x, vector3d.y, vector3d.z);
            player.getCooldowns().addCooldown(this, 70);
        }
        return super.use(world, player, hand);
    }



    @Override
    public void inventoryTick(ItemStack itemStack, Level world, Entity entity, int i, boolean b) {
        super.inventoryTick(itemStack, world, entity, i, b);
    }

    public float getDamage() {
        return this.attackDamage;
    }

    public boolean canAttackBlock(BlockState blockState, Level world, BlockPos blockPos, Player player) {
        return !player.isCreative();
    }

    public float getDestroySpeed(ItemStack itemStack, BlockState blockState) {
        if (blockState.is(Blocks.COBWEB)) {
            return 15.0F;
        } else {
            Material material = blockState.getMaterial();
            return material != Material.PLANT && material != Material.REPLACEABLE_PLANT && !blockState.is(BlockTags.LEAVES) && material != Material.VEGETABLE ? 1.0F : 1.5F;
        }
    }

    public boolean hurtEnemy(ItemStack itemStack, LivingEntity livingEntity, LivingEntity livingEntity1) {
        itemStack.hurtAndBreak(1, livingEntity1, (livingEntity2) -> {
            livingEntity2.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }

    public boolean mineBlock(ItemStack itemStack, Level world, BlockState blockState, BlockPos blockPos, LivingEntity livingEntity) {
        if (blockState.getDestroySpeed(world, blockPos) != 0.0F) {
            itemStack.hurtAndBreak(2, livingEntity, (livingEntity1) -> {
                livingEntity1.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }

        return true;
    }



    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slotType) {
        return slotType == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(slotType);
    }


    @Override
    public void appendHoverText(ItemStack itemStack, Level world, List<Component> iTextComponents, TooltipFlag iTooltipFlag) {
        iTextComponents.add(new TextComponent("\u00A73\u00A7nRight click to lunge forward"));
        super.appendHoverText(itemStack, world, iTextComponents, iTooltipFlag);
    }
}