package tombchips.avalimod.core;


import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.common.blocks.*;
import tombchips.avalimod.common.blocks.containers.SmallCanisterBlock;
import tombchips.avalimod.common.blocks.plants.SnowPlantBlock;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntFunction;

public class ABlocks {

    public static List<Block> blocks = new ArrayList<>();

    public static final AmmoniaFluidBlock AMMONIA_SOURCE = registerBlock("ammonia_source",
            new AmmoniaFluidBlock(() -> AFluids.AMMONIA, BlockBehaviour.Properties.of(Material.WATER).strength(100f).noDrops()));

    public static final Block AMMONIA_ICE = registerBlock("ammonia_ice",new AmmoniaIceBlock(BlockBehaviour.Properties.copy(Blocks.PACKED_ICE)));
    public static final Block CEILING_LIGHT = registerBlock("ceiling_light", new CeilingLightBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).lightLevel(litBlockEmission(15))));
    public static final Block SMALL_CANISTER = registerBlock("small_canister",new SmallCanisterBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));


    public static final Block AVALON_STONE = registerBlock("avalon_stone",new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final Block FROZEN_AVALON_STONE = registerBlock("frozen_avalon_stone",new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final Block FABRIC_BLOCK = registerBlock("fabric_block",new Block(BlockBehaviour.Properties.copy(Blocks.YELLOW_WOOL)));
    public static final Block FABRIC_SLAB = registerBlock("fabric_slab",new SlabBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_WOOL)));
    public static final Block WALL_TAPESTRY = registerBlock("wall_tepestry",new WallTapestryBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_WOOL).noOcclusion().noCollission()));
    public static final Block NANOCANVAS_BLOCK = registerBlock("nanocanvas_block",new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final Block NANOCANVAS_SLAB = registerBlock("nanocanvas_slab",new SlabBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final Block FABRIC_TRIM = registerBlock("fabric_trim", new RotateableBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final Block TECH_BLOCK = registerBlock("tech_block", new RotateableBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final Block NANOFIBER_POLE = registerBlock("nanofiber_pole", new RotateableBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));

    public static final Block SNOWDROP = registerBlock("snow_drop", new SnowPlantBlock(AEffects.FREEZING, 7, BlockBehaviour.Properties.copy(Blocks.POPPY)));



    public static final Block AVALON_TELEPORTER = createTeleporterBlock("avalon_teleporter",
            ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(AvaliMod.MOD_ID, "avalon")));


    static @Nonnull Block createTeleporterBlock(String id, ResourceKey<Level> worldRegistryKey) {
        Block createBlock = new TeleporterBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.STONE).strength
                (2.0f, 6.0f).requiresCorrectToolForDrops(), worldRegistryKey);
        return registerBlock(id, createBlock);
    }

    static @Nonnull Block createConcreteBlock(String id) {
        Block dirt = new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE));
        return registerBlock(id, dirt);
    }

    static @Nonnull Block createPlankBlock(String id) {
        Block dirt = new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS));
        return registerBlock(id, dirt);
    }

    static @Nonnull Block createCobbleBlock(String id) {
        Block dirt = new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE));
        return registerBlock(id, dirt);
    }

    static @Nonnull Block createLog(String id) {
        Block createBlock = new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0f));
        return registerBlock(id, createBlock);
    }

    static @Nonnull Block createStoneBlock(String id) {
        Block stone = new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
        return registerBlock(id, stone);
    }

    static @Nonnull
    <T extends Block> T registerBlock(String id, @Nonnull T block) {
        block.setRegistryName(AvaliMod.createResource(id));

        blocks.add(block);

        return block;
    }


    private static ToIntFunction<BlockState> litBlockEmission(int light) {
        return (block) -> {
            return block.getValue(BlockStateProperties.LIT) ? light: 0;
        };
    }

    public static void init() {
    }
}
