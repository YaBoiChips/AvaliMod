package tombchips.avalimod.client.events;


import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.core.AEffects;
import tombchips.avalimod.core.AItems;

import java.util.HashMap;
import java.util.UUID;


@Mod.EventBusSubscriber(modid = AvaliMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEvents {

    private static final HashMap<UUID, Boolean> fireUuidMap = new HashMap<>();



//    @SubscribeEvent
//    public static void initialize(FMLServerStartedEvent e) {
//
//    }



    @SubscribeEvent
    public static void checkIsWarm(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;
        World world = player.level;
        if (event.phase != TickEvent.Phase.END || world.isClientSide) return;
        if (!fireUuidMap.containsKey(player.getUUID())){
            fireUuidMap.put(player.getUUID(), false);
        }
       // System.out.println(fireUuidMap.get(player.getUUID()));
        for (int x1 = player.blockPosition().getX() - 4; x1 <= player.blockPosition().getX() + 4; ++x1) {
            for (int y1 = player.blockPosition().getY() - 2; y1 <= player.blockPosition().getY() + 2; ++y1) {
                for (int z1 = player.blockPosition().getZ() - 4; z1 <= player.blockPosition().getZ() + 4; ++z1) {
                    if (world.getBlockState(new BlockPos(x1, y1, z1)) == Blocks.FIRE.defaultBlockState()) {
                        fireUuidMap.put(player.getUUID(), true);
                        player.removeEffect(AEffects.FREEZING);
                    }
                }
            }
        }
    }

    public static int suitPeices(PlayerEntity player) {
        int k = 0;
        if (player.getItemBySlot(EquipmentSlotType.HEAD).getItem() != AItems.AVALON_HELMET) {
            k++;
        }
        if (player.getItemBySlot(EquipmentSlotType.CHEST).getItem() != AItems.AVALON_CHESTPLATE) {
            k++;
        }
        if (player.getItemBySlot(EquipmentSlotType.LEGS).getItem() != AItems.AVALON_LEGGINGS) {
            k++;
        }
        if (player.getItemBySlot(EquipmentSlotType.FEET).getItem() != AItems.AVALON_BOOTS) {
            k++;
        }
        return k;
    }


    @SubscribeEvent
    public static void playerHasNoSuit(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;
        World world = player.level;
        if (event.phase != TickEvent.Phase.END || world.isClientSide) return;
        if (player.level.dimension() == RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(AvaliMod.MOD_ID, "avalon"))) {
            if (!player.isCreative()) {
                if (suitPeices(player) != 0) {
                    if(fireUuidMap.get(player.getUUID()))
                    player.addEffect(new EffectInstance(AEffects.FREEZING, 20, suitPeices(player), false, false));
                }
            }
//                    Random rand = new Random();
//                    float i = (float) rand.nextInt(2) / 10;
//                    float j = (float) rand.nextInt(2) / 10;
//                    player.move(MoverType.SELF, new Vector3d(i, 0, j));
        }
    }
}

