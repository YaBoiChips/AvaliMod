package tombchips.avalimod.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.common.contianers.SmallCanisterContainer;

import java.util.ArrayList;
import java.util.List;

public class AContainers {
    public static List<MenuType<?>> containers = new ArrayList<>();
    
    public static final MenuType<SmallCanisterContainer> SMALL_CAN = createContainer("small_canister", SmallCanisterContainer::new);
            
            
    public static <T extends AbstractContainerMenu> MenuType<T> createContainer(String id, MenuType.MenuSupplier<T> builder){
        MenuType<T> menuType = new MenuType<>(builder);
        menuType.setRegistryName(new ResourceLocation(AvaliMod.MOD_ID, id));
        containers.add(menuType);
        return menuType;
    }

    public static void init() {
    }
}
