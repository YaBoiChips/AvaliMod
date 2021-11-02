package tombchips.avalimod.common.blocks;


import com.mojang.math.Vector3d;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class TeleporterBlock extends Block {
    private final ResourceKey<Level> worldKey;
    private final ResourceKey<Level> overworldKey;


    public TeleporterBlock(Properties properties, ResourceKey<Level> worldKey) {
        super(properties);
        this.worldKey = worldKey;
        this.overworldKey = ResourceKey.create(Registry.DIMENSION_REGISTRY, DimensionType.OVERWORLD_EFFECTS);
    }



    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide) {
            ServerLevel world = worldIn.getServer().getLevel(worldKey);
            ServerLevel overworld = worldIn.getServer().getLevel(overworldKey);
            if (world != null) {
                if (player.level != world) {
                    sendPlayerToDimension((ServerPlayer) player, world, new Vec3(player.getX(), player.getY(), player.getZ()));
                    player.displayClientMessage(new TranslatableComponent("avalon.teleport.success"), true);
                    return InteractionResult.SUCCESS;
                }else{
                    if (overworld != null) {
                        sendPlayerToDimension((ServerPlayer) player, overworld, new Vec3(player.getX(), player.getY(), player.getZ()));
                    }
                }
            }
            else {
                player.displayClientMessage(new TranslatableComponent("avalon.teleport.failed"), true);
                return InteractionResult.FAIL;
            }
        }

        return InteractionResult.PASS;
    }


    public static void sendPlayerToDimension(ServerPlayer serverPlayer, ServerLevel targetWorld, Vec3 targetVec) {
        // ensure destination chunk is loaded before we put the player in it
        ServerLevel world = targetWorld.getLevel();
        targetWorld.getChunk(new BlockPos(targetVec));
            serverPlayer.teleportTo(targetWorld, targetVec.x(), targetWorld.getHeight(Heightmap.Types.MOTION_BLOCKING, (int) targetVec.x(), (int) targetVec.z()), targetVec.z(), serverPlayer.getYRot(), serverPlayer.getXRot());
    }
}