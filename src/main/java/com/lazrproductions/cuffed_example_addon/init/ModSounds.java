package com.lazrproductions.cuffed_example_addon.init;

import com.lazrproductions.cuffed_example_addon.CuffedExampleAddonMod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries.Keys;
import net.minecraftforge.registries.RegisterEvent;

/*
    Very basic sound event registry, nothing special to note here :)
 */

public class ModSounds {
    public static final SoundEvent DIAMOND_HANDCUFFS_EQUIP = SoundEvent
        .createVariableRangeEvent(new ResourceLocation(CuffedExampleAddonMod.MODID, "restraint.apply_diamond_handcuffs"));

    public static void register(RegisterEvent event) {
        event.register(Keys.SOUND_EVENTS, x -> {
            x.register(new ResourceLocation(CuffedExampleAddonMod.MODID, "restraint.apply_diamond_handcuffs"), DIAMOND_HANDCUFFS_EQUIP);
        });
    }
}
