package tombchips.avalimod;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.resource.ResourceListener;
import tombchips.avalimod.client.entity.AvaliEntityRenderer;
import tombchips.avalimod.common.entity.AvaliEntity;
import tombchips.avalimod.core.ABlocks;
import tombchips.avalimod.core.AEntityTypes;
import tombchips.avalimod.core.AFluids;
import tombchips.avalimod.core.AItems;
import tombchips.avalimod.core.world.ABiomes;
import tombchips.avalimod.core.world.AConfiguredSurfaceBuilders;
import tombchips.avalimod.core.world.AFeatures;
import tombchips.avalimod.core.world.ASurfaceBuilders;

import javax.annotation.Nonnull;
import java.util.Comparator;
import java.util.stream.Collectors;

@Mod(AvaliMod.MOD_ID)
public class AvaliMod {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "avalimod";
    public static volatile boolean hasInitialized;


    public AvaliMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        GeckoLib.initialize();

        MinecraftForge.EVENT_BUS.register(this);

    }
    synchronized public static void initialize() {
        if (!hasInitialized) {
            DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ResourceListener::registerReloadListener);
        }
        hasInitialized = true;
    }

    public void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        AConfiguredSurfaceBuilders.register();
        GlobalEntityTypeAttributes.put(AEntityTypes.AVALI, AvaliEntity.setCustiomAttributes().build());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(AEntityTypes.AVALI, AvaliEntityRenderer::new);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("avalimod", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    public static @Nonnull
    ResourceLocation createResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
            LOGGER.info("HELLO from Register Block");
            ABlocks.init();
            ABlocks.blocks.forEach(block -> event.getRegistry().register(block));
            ABlocks.blocks.clear();
            ABlocks.blocks = null;
        }
        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
            LOGGER.info("HELLO from Register Items");
            AItems.init();
            AItems.items.forEach(item -> event.getRegistry().register(item));
            AItems.items.clear();
            AItems.items = null;
            LOGGER.info("BYE from Register Items");
        }

        @SubscribeEvent
        public static void onFluidRegistry(final RegistryEvent.Register<Fluid> event) {
            LOGGER.info("HELLO from Register Fluids");
            AFluids.init();
            AFluids.fluids.forEach(fluid -> event.getRegistry().register(fluid));
            AFluids.fluids.clear();
            AFluids.fluids = null;
            LOGGER.info("BYE from Register Fluids");
        }

        @SubscribeEvent
        public static void onEntityRegistry(final RegistryEvent.Register<EntityType<?>> event) {
            LOGGER.info("HELLO from Register Entities");
            AEntityTypes.init();
            AEntityTypes.entities.forEach(entity -> event.getRegistry().register(entity));
            AEntityTypes.entities.clear();
            AEntityTypes.entities = null;
            LOGGER.info("BYE from Register Entities");
        }

        @SubscribeEvent
        public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
            LOGGER.debug("Registering features...");
            AFeatures.init();
            AFeatures.features.forEach(feature -> event.getRegistry().register(feature));
            AFeatures.features.clear();
            AFeatures.features = null;
            LOGGER.info("Features registered!");
        }

        @SubscribeEvent
        public static void registerSurfaceBuilders(RegistryEvent.Register<SurfaceBuilder<?>> event) {
            LOGGER.debug("Registering surface builders...");
            ASurfaceBuilders.init();
            ASurfaceBuilders.surfaceBuilders.forEach(surfaceBuilder -> event.getRegistry().register(surfaceBuilder));
            ASurfaceBuilders.surfaceBuilders.clear();
            ASurfaceBuilders.surfaceBuilders = null;
            LOGGER.info("Surface builders Registered!");
        }

        @SubscribeEvent
        public static void registerBiomes(RegistryEvent.Register<Biome> event) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
            ABiomes.init();
            ABiomes.biomes.sort(Comparator.comparingInt(ABiomes.PreserveBiomeOrder::getOrderPosition));
            ABiomes.biomes.forEach(preserveBiomeOrder -> event.getRegistry().register(preserveBiomeOrder.getBiome()));
            ABiomes.biomes.clear();
            ABiomes.biomes = null;
        }
    }
}
