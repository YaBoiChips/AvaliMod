package tombchips.avalimod.client.events;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.core.AItems;
import tombchips.avalimod.core.ASounds;

@Mod.EventBusSubscriber(modid = AvaliMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SoundEvents {

    @SubscribeEvent
    public static void isHoldingNanoTool(LivingEquipmentChangeEvent event) {
            LivingEntity player = (LivingEntity) event.getEntity();
            World world = player.level;
            ItemStack item = player.getItemBySlot(EquipmentSlotType.MAINHAND);
            if (item.getItem() == AItems.NANOBLADE_AXE || item.getItem() == AItems.NANOBLADE_SPEAR) {
                world.playSound(null, new BlockPos(player.getPosition(0)), ASounds.NANOBLADE_EQUIP, SoundCategory.PLAYERS, 1.0f, 1.0f);
            }
        }


    @SubscribeEvent
    public static void spearRecharge(TickEvent.PlayerTickEvent event){
        PlayerEntity player = event.player;
        World world = player.level;
        ItemStack item = player.getItemBySlot(EquipmentSlotType.MAINHAND);
        if(player.getCooldowns().getCooldownPercent(item.getItem(), 1) == 0.1F && item.getItem() == AItems.NANOBLADE_SPEAR){
            world.playSound(null, new BlockPos(player.getPosition(0)), ASounds.NANOBLADE_EQUIP, SoundCategory.PLAYERS, 0.20f, 1.4f);
        }
    }
}
