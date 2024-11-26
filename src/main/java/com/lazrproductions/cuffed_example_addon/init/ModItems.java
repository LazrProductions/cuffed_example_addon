package com.lazrproductions.cuffed_example_addon.init;

import com.lazrproductions.cuffed.items.base.AbstractRestraintKeyItem;

import com.lazrproductions.cuffed_example_addon.CuffedExampleAddonMod;
import com.lazrproductions.cuffed_example_addon.item.DiamondHandcuffsItem;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/*
        Very basic item registry, nothing special to note here :)
 */

public class ModItems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
                        CuffedExampleAddonMod.MODID);

        public static final RegistryObject<Item> DIAMOND_HANDCUFFS_KEY = ITEMS.register("diamond_handcuffs_key",
                        () -> new AbstractRestraintKeyItem(new Item.Properties().stacksTo(1)));

        public static final RegistryObject<Item> DIAMOND_HANDCUFFS = ITEMS.register("diamond_handcuffs",
                        () -> new DiamondHandcuffsItem(new Item.Properties().stacksTo(1)
                                                                            .durability(40)
                                                                            .defaultDurability(40)));

        public static void register(IEventBus bus) {
                ITEMS.register(bus);
        }
}
