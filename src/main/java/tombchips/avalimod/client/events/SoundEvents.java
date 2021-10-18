package tombchips.avalimod.client.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.core.AItems;
import tombchips.avalimod.core.ASounds;

@Mod.EventBusSubscriber(modid = AvaliMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SoundEvents {
    public static boolean held = false;

    @SubscribeEvent
    public static void isHoldingNanoTool(TickEvent.PlayerTickEvent event){
        PlayerEntity player = event.player;
        World world = player.level;
        PlayerEntity playerIn = player;
        World worldIn = world;
        Hand handIn = Hand.MAIN_HAND;
        ItemStack item = playerIn.getItemInHand(handIn);
        if(item.getItem() == AItems.NANOBLADE_AXE && held){


            worldIn.playSound(player, new BlockPos(player.getPosition(0)), ASounds.NANOBLADE_EQUIP, SoundCategory.PLAYERS, 1.0f, 1.0f);
                held = false;


        }
        else {
            if(item.getItem() != AItems.NANOBLADE_AXE)
            {
                held = true;
            }

        }
    }




}
