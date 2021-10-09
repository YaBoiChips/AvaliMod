package tombchips.avalimod.core;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
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
            return null; //new ItemStack(AItems.ITEM);
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
