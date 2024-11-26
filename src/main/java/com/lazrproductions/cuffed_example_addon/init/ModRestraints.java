package com.lazrproductions.cuffed_example_addon.init;

import com.lazrproductions.cuffed.restraints.base.AbstractRestraint;

import com.lazrproductions.cuffed_example_addon.CuffedExampleAddonMod;
import com.lazrproductions.cuffed_example_addon.restraints.custom.DiamondHandcuffsArmsRestraint;
import com.lazrproductions.cuffed_example_addon.restraints.custom.DiamondHandcuffsLegsRestraint;
import com.lazrproductions.cuffed_example_addon.restraints.custom.ExampleHeadRestraint;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

/*
    This is the registry class that is used to register custom restraints. Your mod must have one of these to register your restraints.
    It works just like any other mod registry, except the register function has some fluff to it to make it work.

    1. CREATE THE REGISTRY
        First you need to create the DefferedRegister for your mod, the one here is called RESTRAINTS. this will create a registry for AbstractRestraints for the path:
            "yourmodid:restraints"

    2. REGISTER RESTRAINTS
        Second you should create your static references to your restraint RegistryObjects like this:

            public static final RegistryObject<AbstractRestraint> MY_RESTRAINT = RESTRAINTS.register("my_restraint" , MyRestraintClass::new);

        This uses that empty constructor in your restraint class to create a registry instance of the restraint to be copied when it is applied to players.

    3. REGISTER THE REGISTRY
        Finally you add the register function to this class to pack the registry for Cuffed to handle it:
        
            public static void register(final IEventBus modEventBus) {
                RESTRAINTS.makeRegistry(RegistryBuilder::new);
                RESTRAINTS.register(modEventBus);
            }
        
        Then call the register function in your mod's main class:
            
            public MyMod() {
                MyRestraintsClass.register(bus);
            }
 */

public class ModRestraints {
    // this isn't required, it just can help a bit when developing in case things go awry.
    private static boolean isInitialized = false;

    // Create your custom registry to hold your restraint classes
    public static final DeferredRegister<AbstractRestraint> RESTRAINTS = DeferredRegister.create(new ResourceLocation(CuffedExampleAddonMod.MODID, "restraints"), CuffedExampleAddonMod.MODID);

    // Register your restraints to the register
    public static final RegistryObject<AbstractRestraint> DIAMOND_HANDCUFFS_ARMS = RESTRAINTS.register("diamond_handcuffs_arms", DiamondHandcuffsArmsRestraint::new);
    public static final RegistryObject<AbstractRestraint> DIAMOND_HANDCUFFS_LEGS = RESTRAINTS.register("diamond_handcuffs_legs", DiamondHandcuffsLegsRestraint::new);
    public static final RegistryObject<AbstractRestraint> EXAMPLE_HEAD_RESTRAINT = RESTRAINTS.register("example_head_restraint", ExampleHeadRestraint::new);

    // Register the custom registry to the forge event bus.
    public static void register(final IEventBus modEventBus) {
        if (isInitialized)
            throw new IllegalStateException("Restraints already initialized"); // Not required, just useful for development.

        RESTRAINTS.makeRegistry(RegistryBuilder::new);
        RESTRAINTS.register(modEventBus);

        // Not required, just useful for development.
        isInitialized = true;
        CuffedExampleAddonMod.LOGGER.info("Registered restraints");
    }
}
