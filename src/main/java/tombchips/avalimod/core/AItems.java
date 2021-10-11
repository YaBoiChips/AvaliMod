package tombchips.avalimod.core;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluids;
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
    public static final Item AVALI_SPAWN_EGG = createItem(new SpawnEggItem(AEntityTypes.AVALI, new Color(190, 205, 255).getRGB(),new Color(100, 105, 255).getRGB(), new Item.Properties().tab(TAB)), "avali_spawn_egg");

    //BLOCK ITEMS
    public static final Item AVALON_TELEPORTER = createBlockItem(ABlocks.AVALON_TELEPORTER,
            new Item.Properties().tab(TAB));
    public static final Item AMMONIA_ICE = createBlockItem(ABlocks.AMMONIA_ICE,
            new Item.Properties().tab(TAB));


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
