package tombchips.avalimod.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import tombchips.avalimod.AvaliMod;

public class ATags {

    public static class Items {

        public static final Tags.IOptionalNamedTag<Item> AVALI_TOOLS = avaliTag("items/avali_tools");


        private static Tags.IOptionalNamedTag<Item> avaliTag(String id) {
            return ItemTags.createOptional(new ResourceLocation(AvaliMod.MOD_ID, id));
        }

        private static Tags.IOptionalNamedTag<Item> tag(String id) {
            return ItemTags.createOptional(new ResourceLocation("forge", id));
        }

        public static void init() {}

    }
}
