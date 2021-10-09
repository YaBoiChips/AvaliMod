package tombchips.avalimod.core;

import net.minecraft.block.Block;
import tombchips.avalimod.AvaliMod;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ABlocks {

    public static List<Block> blocks = new ArrayList<>();




    static @Nonnull
    <T extends Block> T registerBlock(String id, @Nonnull T block) {
        block.setRegistryName(AvaliMod.createResource(id));

        blocks.add(block);

        return block;
    }

    public static void init() {
    }
}
