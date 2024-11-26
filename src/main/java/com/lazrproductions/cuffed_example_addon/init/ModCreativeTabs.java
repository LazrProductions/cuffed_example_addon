package com.lazrproductions.cuffed_example_addon.init;

import com.lazrproductions.cuffed_example_addon.CuffedExampleAddonMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/*
    Very basic creative tabs registry, nothing special to note here :)
 */

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, CuffedExampleAddonMod.MODID);

    public static final RegistryObject<CreativeModeTab> CUFFED_TAB = CREATIVE_MODE_TABS.register("cuffed_example_addon_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.cuffed_example_addon"))
                    .icon(() -> ModItems.DIAMOND_HANDCUFFS.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.DIAMOND_HANDCUFFS.get());
                        output.accept(ModItems.DIAMOND_HANDCUFFS_KEY.get());
                    }).build());

    public static void register(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);
    }
}
