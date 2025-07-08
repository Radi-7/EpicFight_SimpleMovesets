package net.radi.simplemovesets.item;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.radi.simplemovesets.SimpleMovesets;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SimpleMovesets.MODID);

    public static final RegistryObject<Item> IRON_SABER = ITEMS.register("iron_saber",() -> new SwordItem(Tiers.IRON, 3, -2.6f, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
