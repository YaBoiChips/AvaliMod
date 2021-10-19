package tombchips.avalimod.common.items.tools;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class SpearItem extends TieredItem {
    private final float attackDamage;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;
    public static boolean canLunge = true;
    public static int coolDown = 70;

    public SpearItem(IItemTier iItemTier, int i, float v, Item.Properties properties) {
        super(iItemTier, properties);
        this.attackDamage = (float)i + iItemTier.getAttackDamageBonus();
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)v, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if(canLunge){
            System.out.println("used");
            Vector3d vector3d = player.getLookAngle();
            player.lerpMotion(vector3d.x, vector3d.y, vector3d.z);
            canLunge = false;
        }
        return super.use(world, player, hand);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, World world, Entity entity, int i, boolean b) {
        //System.out.println(coolDown);

        if(!canLunge){
            --coolDown;
            if(coolDown <= 0){

                canLunge = true;
                coolDown = 70;

            }
        }

        super.inventoryTick(itemStack, world, entity, i, b);
    }

    public float getDamage() {
        return this.attackDamage;
    }

    public boolean canAttackBlock(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player) {
        return !player.isCreative();
    }

    public float getDestroySpeed(ItemStack itemStack, BlockState blockState) {
        if (blockState.is(Blocks.COBWEB)) {
            return 15.0F;
        } else {
            Material material = blockState.getMaterial();
            return material != Material.PLANT && material != Material.REPLACEABLE_PLANT && material != Material.CORAL && !blockState.is(BlockTags.LEAVES) && material != Material.VEGETABLE ? 1.0F : 1.5F;
        }
    }

    public boolean hurtEnemy(ItemStack itemStack, LivingEntity livingEntity, LivingEntity livingEntity1) {
        itemStack.hurtAndBreak(1, livingEntity1, (livingEntity2) -> {
            livingEntity2.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
        });
        return true;
    }

    public boolean mineBlock(ItemStack itemStack, World world, BlockState blockState, BlockPos blockPos, LivingEntity livingEntity) {
        if (blockState.getDestroySpeed(world, blockPos) != 0.0F) {
            itemStack.hurtAndBreak(2, livingEntity, (livingEntity1) -> {
                livingEntity1.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
            });
        }

        return true;
    }



    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType p_111205_1_) {
        return p_111205_1_ == EquipmentSlotType.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(p_111205_1_);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, World world, List<ITextComponent> iTextComponents, ITooltipFlag iTooltipFlag) {
        iTextComponents.add(new StringTextComponent("\u00A73\u00A7nRight click to lunge forward"));
        super.appendHoverText(itemStack, world, iTextComponents, iTooltipFlag);
    }
}