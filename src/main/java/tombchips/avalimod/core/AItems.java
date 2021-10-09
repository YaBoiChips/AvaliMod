package tombchips.avalimod.core;

import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import tombchips.avalimod.AvaliMod;

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

    public static Item AVALI_LOGO = createItem(new Item(new Item.Properties().fireResistant()), "avali_logo");

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
