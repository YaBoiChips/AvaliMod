package tombchips.avalimod.core;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.common.blocks.TeleporterBlock;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ABlocks {

    public static List<Block> blocks = new ArrayList<>();

    public static final FlowingFluidBlock AMMONIA_SOURCE = registerBlock("ammonia_source", new FlowingFluidBlock(() -> AFluids.AMMONIA, AbstractBlock.Properties.of(Material.WATER).strength(100f).noDrops()));
//    public static final Block AMMONIA_ICE = registerBlock("ammonia_ice",new IceBlock(AbstractBlock.Properties.copy(Blocks.PACKED_ICE)));



    public static final Block AVALON_TELEPORTER = createTeleporterBlock("avalon_teleporter", RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(AvaliMod.MOD_ID, "avalon")));


    static @Nonnull Block createTeleporterBlock(String id, RegistryKey<World> worldRegistryKey) {
        Block createBlock = new TeleporterBlock(AbstractBlock.Properties.of(Material.METAL).sound(SoundType.STONE).strength(2.0f, 6.0f).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops(), worldRegistryKey);
        return registerBlock(id, createBlock);
    }

    static @Nonnull Block createConcreteBlock(String id) {
        Block dirt = new Block(AbstractBlock.Properties.copy(Blocks.WHITE_CONCRETE));
        return registerBlock(id, dirt);
    }

    static @Nonnull Block createPlankBlock(String id) {
        Block dirt = new Block(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS));
        return registerBlock(id, dirt);
    }

    static @Nonnull Block createCobbleBlock(String id) {
        Block dirt = new Block(AbstractBlock.Properties.copy(Blocks.COBBLESTONE));
        return registerBlock(id, dirt);
    }

    static @Nonnull Block createLog(String id) {
        Block createBlock = new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0f));
        return registerBlock(id, createBlock);
    }

    static @Nonnull Block createStoneBlock(String id) {
        Block stone = new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
        return registerBlock(id, stone);
    }

    static @Nonnull
    <T extends Block> T registerBlock(String id, @Nonnull T block) {
        block.setRegistryName(AvaliMod.createResource(id));

        blocks.add(block);

        return block;
    }

    public static void init() {
    }
}
