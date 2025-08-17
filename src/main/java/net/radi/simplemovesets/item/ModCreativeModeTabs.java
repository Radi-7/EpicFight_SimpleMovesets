package net.radi.simplemovesets.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.radi.simplemovesets.SimpleMovesets;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SimpleMovesets.MODID);

    public static final RegistryObject<CreativeModeTab> SIMPLE_MOVESETS_TAB = CREATIVE_MODE_TABS.register("simple_movesets",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.IRON_SABER.get()))
                    .title(Component.translatable("simplemovesets.creative_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.WOODEN_SABER.get());
                        output.accept(ModItems.STONE_SABER.get());
                        output.accept(ModItems.IRON_SABER.get());
                        output.accept(ModItems.GOLDEN_SABER.get());
                        output.accept(ModItems.DIAMOND_SABER.get());
                        output.accept(ModItems.NETHERITE_SABER.get());

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
