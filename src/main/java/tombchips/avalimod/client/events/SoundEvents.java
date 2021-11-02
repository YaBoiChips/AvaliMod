package tombchips.avalimod.client.events;


import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.core.AItems;
import tombchips.avalimod.core.ASounds;

import java.util.HashMap;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = AvaliMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SoundEvents {

    private static final HashMap<UUID, Boolean> zoop = new HashMap<>();


    @SubscribeEvent
    public static void isHoldingNanoTool(LivingEquipmentChangeEvent event) {
            LivingEntity player = (LivingEntity) event.getEntity();
            Level world = player.level;
            ItemStack item = player.getItemBySlot(EquipmentSlot.MAINHAND);
            if (item.getItem() == AItems.NANOBLADE_AXE || item.getItem() == AItems.NANOBLADE_SPEAR) {
                world.playSound(null, new BlockPos(player.getPosition(0)), ASounds.NANOBLADE_EQUIP, SoundSource.PLAYERS, 1.0f, 1.0f);
            }
        }


    @SubscribeEvent
    public static void spearRecharge(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Level world = player.level;
        ItemStack item = player.getItemBySlot(EquipmentSlot.MAINHAND);
        if (!zoop.containsKey(player.getUUID())){
            zoop.put(player.getUUID(), false);
    }
        if (!zoop.get(player.getUUID())) {
            if (!player.getCooldowns().isOnCooldown(item.getItem()) && item.getItem() == AItems.NANOBLADE_SPEAR) {
                zoop.put(player.getUUID(), true);
                world.playSound(null, new BlockPos(player.getPosition(0)), ASounds.NANOBLADE_EQUIP, SoundSource.PLAYERS, 0.40f, 1.4f);
            }
        }
        if (player.getCooldowns().isOnCooldown(item.getItem())){
            zoop.put(player.getUUID(), false);
        }
    }
}

