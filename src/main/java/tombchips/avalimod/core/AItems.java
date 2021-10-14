package tombchips.avalimod.core;

import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import tombchips.avalimod.AvaliMod;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AItems {

    public static List<Item> items = new ArrayList<>();

    public static final ItemGroup TAB = new ItemGroup(AvaliMod.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(AItems.AVALI_LOGO);
        }

        @Override
        public boolean hasSearchBar() {
            return true;
        }

        @Override
        public ResourceLocation getBackgroundImage() {
            return new ResourceLocation("minecraft", "textures/gui/container/creative_inventory/tab_item_search.png");
        }
    };

    //ITEMS
    public static final Item AVALI_LOGO = createItem(new Item
            (new Item.Properties().fireResistant()), "avali_logo");
    public static final Item AMMONIA_BUCKET = createItem(new BucketItem(AFluids.AMMONIA,
            (new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1).tab(TAB)), "ammonia_bucket");
    public static final Item AVALI_SPAWN_EGG = createItem(new SpawnEggItem(AEntityTypes.AVALI,
            new Color(190, 205, 255).getRGB(), new Color(100, 105, 255).getRGB(), new Item.Properties().tab(TAB)), "avali_spawn_egg");

    //BLOCK ITEMS
    public static final Item AVALON_TELEPORTER = createBlockItem(ABlocks.AVALON_TELEPORTER,
            new Item.Properties().tab(TAB));
    public static final Item AMMONIA_ICE = createBlockItem(ABlocks.AMMONIA_ICE,
            new Item.Properties().tab(TAB));
    public static final Item FABRIC_BLOCK = createBlockItem(ABlocks.FABRIC_BLOCK,
            new Item.Properties().tab(TAB));
    public static final Item FABRIC_SLAB = createBlockItem(ABlocks.FABRIC_SLAB,
            new Item.Properties().tab(TAB));
    public static final Item NANOCANVAS_BLOCK = createBlockItem(ABlocks.NANOCANVAS_BLOCK,
            new Item.Properties().tab(TAB));
    public static final Item NANOCANVAS_SLAB = createBlockItem(ABlocks.NANOCANVAS_SLAB,
            new Item.Properties().tab(TAB));
    public static final Item FABRIC_TRIM = createBlockItem(ABlocks.FABRIC_TRIM,
            new Item.Properties().tab(TAB));
    public static final Item TECH_BLOCK = createBlockItem(ABlocks.TECH_BLOCK,
            new Item.Properties().tab(TAB));
    public static final Item NANOFIBER_POLE = createBlockItem(ABlocks.NANOFIBER_POLE,
            new Item.Properties().tab(TAB));
    public static final Item CEILING_LIGHT = createBlockItem(ABlocks.CEILING_LIGHT,
            new Item.Properties().tab(TAB));
    public static final Item WALL_TAPESTRY = createBlockItem(ABlocks.WALL_TAPESTRY,
            new Item.Properties().tab(TAB));

    //ARMOR ITEMS
    public static final Item AVALON_HELMET = createItem(new ArmorItem(ArmorMaterial.DIAMOND, EquipmentSlotType.HEAD, new Item.Properties().tab(TAB)), "avalon_helmet");
    public static final Item AVALON_CHESTPLATE = createItem(new ArmorItem(ArmorMaterial.DIAMOND, EquipmentSlotType.CHEST, new Item.Properties().tab(TAB)), "avalon_chestplate");
    public static final Item AVALON_LEGGINGS = createItem(new ArmorItem(ArmorMaterial.DIAMOND, EquipmentSlotType.LEGS, new Item.Properties().tab(TAB)), "avalon_leggings");
    public static final Item AVALON_BOOTS = createItem(new ArmorItem(ArmorMaterial.DIAMOND, EquipmentSlotType.FEET, new Item.Properties().tab(TAB)), "avalon_boots");


    public static Item createItem(Item item, String id) {
        return createItem(item, AvaliMod.createResource(id));
    }

    public static Item createBlockItem(Block block, Item.Properties props) {
        return createItem(new BlockItem(block, props), Registry.BLOCK.getKey(block));
    }

    public static Item createItem(Item item, ResourceLocation id) {
        if (id != null && !id.equals(new ResourceLocation("minecraft:air"))) {
            item.setRegistryName(id);

            items.add(item);

            return item;
        } else return null;
    }

    public static void init() {
    }
}