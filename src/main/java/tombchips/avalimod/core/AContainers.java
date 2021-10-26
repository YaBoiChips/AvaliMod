package tombchips.avalimod.core;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.extensions.IForgeContainerType;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.common.contianers.SmallCanisterContainer;

import java.util.ArrayList;
import java.util.List;

public class AContainers {
    public static List<ContainerType<?>> containers = new ArrayList<>();
    
    public static final ContainerType<SmallCanisterContainer> SMALL_CAN = createContainer("small_canister", IForgeContainerType.create(SmallCanisterContainer::new));
            
            
    public static <E extends Container, T extends ContainerType<E>> T createContainer(String id, T container){
        container.setRegistryName(new ResourceLocation(AvaliMod.MOD_ID, id));
        containers.add(container);
        return container;
    }

    public static void init() {
    }
}
