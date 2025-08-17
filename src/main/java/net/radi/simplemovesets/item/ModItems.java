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

    public static final RegistryObject<Item> WOODEN_SABER = ITEMS.register("wooden_saber",() -> new SwordItem(Tiers.WOOD, 3, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> STONE_SABER = ITEMS.register("stone_saber",() -> new SwordItem(Tiers.STONE, 3, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> IRON_SABER = ITEMS.register("iron_saber",() -> new SwordItem(Tiers.IRON, 3, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_SABER = ITEMS.register("golden_saber",() -> new SwordItem(Tiers.GOLD, 3, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_SABER = ITEMS.register("diamond_saber",() -> new SwordItem(Tiers.DIAMOND, 3, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_SABER = ITEMS.register("netherite_saber",() -> new SwordItem(Tiers.NETHERITE, 3, -2.6f, new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
