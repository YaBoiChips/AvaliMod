package tombchips.avalimod.client.events;


import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmlserverevents.FMLServerStartedEvent;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.core.AEffects;
import tombchips.avalimod.core.AItems;
import tombchips.avalimod.core.world.ADimensionTypes;

import java.util.HashMap;
import java.util.UUID;


@Mod.EventBusSubscriber(modid = AvaliMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEvents {

    private static final HashMap<UUID, Boolean> fireUuidMap = new HashMap<>();

//                        fireUuidMap.put(player.getUUID(), true);

//    if (!fireUuidMap.containsKey(player.getUUID())){
//        fireUuidMap.put(player.getUUID(), false);
//    }
    // System.out.println(fireUuidMap.get(player.getUUID()));

    @SubscribeEvent
    public static void initialize(FMLServerStartedEvent e) {
    }


    @SubscribeEvent
    public static void checkIsWarm(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Level world = player.level;
        if (!fireUuidMap.containsKey(player.getUUID())) {
            fireUuidMap.put(player.getUUID(), false);
        }
        if (event.phase != TickEvent.Phase.END || world.isClientSide) return;
        if (player.level.dimension() == ADimensionTypes.AVALON_WORLD_KEY) {
            if (!fireUuidMap.get(player.getUUID())) {
                loop:
                for (int x1 = player.blockPosition().getX() - 4; x1 <= player.blockPosition().getX() + 4; ++x1) {
                    for (int y1 = player.blockPosition().getY() - 2; y1 <= player.blockPosition().getY() + 2; ++y1) {
                        for (int z1 = player.blockPosition().getZ() - 4; z1 <= player.blockPosition().getZ() + 4; ++z1) {
                            fireUuidMap.put(player.getUUID(), false);
                            if (world.getBlockState(new BlockPos(x1, y1, z1)) == Blocks.FIRE.defaultBlockState()) {
                                fireUuidMap.put(player.getUUID(), true);
                                break loop;
                            }
                        }
                    }
                }
            }
        }
    }


    @SubscribeEvent
    public static void doDamage(TickEvent.PlayerTickEvent event){
        Player player = event.player;
        if (fireUuidMap.get(player.getUUID()) != null) {
            if (fireUuidMap.get(player.getUUID())) {
                player.addEffect(new MobEffectInstance(AEffects.FREEZING, 20, suitPeices(player), false, false));
            }
        }
    }

    public static int suitPeices(Player player) {
        int k = 0;
        if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() != AItems.AVALON_HELMET) {
            k++;
        }
        if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() != AItems.AVALON_CHESTPLATE) {
            k++;
        }
        if (player.getItemBySlot(EquipmentSlot.LEGS).getItem() != AItems.AVALON_LEGGINGS) {
            k++;
        }
        if (player.getItemBySlot(EquipmentSlot.FEET).getItem() != AItems.AVALON_BOOTS) {
            k++;
        }
        return k;
    }



//    @SubscribeEvent
//    public static void playerHasNoSuit(TickEvent.PlayerTickEvent event) {
//        Player player = event.player;
//        Level world = player.level;
//        if (event.phase != TickEvent.Phase.END || world.isClientSide) return;
//        if (player.level.dimension() == RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(AvaliMod.MOD_ID, "avalon"))) {
//            if (!player.isCreative()) {
//                if (suitPeices(player) != 0) {
//                    if(fireUuidMap.get(player.getUUID()))
//                }
//            }
//                    Random rand = new Random();
//                    float i = (float) rand.nextInt(2) / 10;
//                    float j = (float) rand.nextInt(2) / 10;
//                    player.move(MoverType.SELF, new Vector3d(i, 0, j));
        }
//    }
//}

