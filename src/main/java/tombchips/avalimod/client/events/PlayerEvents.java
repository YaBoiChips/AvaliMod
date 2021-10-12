package tombchips.avalimod.client.events;


import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.core.AEffects;
import tombchips.avalimod.core.AItems;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import static sun.audio.AudioPlayer.player;

@Mod.EventBusSubscriber(modid = AvaliMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEvents {

    private static final HashMap<UUID, Boolean> warmthUuidMap = new HashMap<>();


    @SubscribeEvent
    public static void initialize(TickEvent.PlayerTickEvent event) {
        if (!warmthUuidMap.containsKey(event.player.getUUID())){
            warmthUuidMap.put(event.player.getUUID(), false);
        }
    }

    @SubscribeEvent
    public static void checkIsWarm(TickEvent.PlayerTickEvent event){
        PlayerEntity player = event.player;
        World world = player.level;
        if (event.phase != TickEvent.Phase.END || world.isClientSide) return;
        for (int x1 = player.blockPosition().getX() - 4; x1 <= player.blockPosition().getX() + 4; ++x1) {
            for (int y1 = player.blockPosition().getY() - 2; y1 <= player.blockPosition().getY() + 2; ++y1) {
                for (int z1 = player.blockPosition().getZ() - 4; z1 <= player.blockPosition().getZ() + 4; ++z1) {
                    if (world.getBlockState(new BlockPos(x1, y1, z1)) == Blocks.FIRE.defaultBlockState()) {
                        player.removeEffect(AEffects.FREEZING);
                            warmthUuidMap.put(player.getUUID(), true);
                        }else{
                        warmthUuidMap.put(player.getUUID(), false);
                    }
                }
            }
        }
    }


    @SubscribeEvent
    public static void playerHasNoSuit(TickEvent.PlayerTickEvent event){
        PlayerEntity player = event.player;
        World world = player.level;
        System.out.println(warmthUuidMap.get(player.getUUID()));
        if (event.phase != TickEvent.Phase.END || world.isClientSide) return;
        if (player.level.dimension() == RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(AvaliMod.MOD_ID, "avalon"))) {
            if (!player.isCreative()) {
                if (player.getItemBySlot(EquipmentSlotType.HEAD).getItem() != AItems.AVALON_HELMET) {
                    int k = 1;
                    if (player.getItemBySlot(EquipmentSlotType.CHEST).getItem() != AItems.AVALON_CHESTPLATE) {
                        k++;
                    }
                    if (player.getItemBySlot(EquipmentSlotType.LEGS).getItem() != AItems.AVALON_LEGGINGS) {
                        k++;
                    }
                    if (player.getItemBySlot(EquipmentSlotType.FEET).getItem() != AItems.AVALON_BOOTS) {
                        k++;
                    }
                    if (!warmthUuidMap.get(player.getUUID())) {
                        player.addEffect(new EffectInstance(AEffects.FREEZING, 20, k, false, false));
                    }
//                    Random rand = new Random();
//                    float i = (float) rand.nextInt(2) / 10;
//                    float j = (float) rand.nextInt(2) / 10;
//                    player.move(MoverType.SELF, new Vector3d(i, 0, j));
                }
            }
        }
    }
}
