package com.lazrproductions.cuffed_example_addon;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lazrproductions.cuffed.items.base.AbstractRestraintItem;
import com.lazrproductions.cuffed.restraints.RestraintAPI;
import com.lazrproductions.cuffed.restraints.base.AbstractRestraint;
import com.lazrproductions.cuffed_example_addon.init.ModCreativeTabs;
import com.lazrproductions.cuffed_example_addon.init.ModItems;
import com.lazrproductions.cuffed_example_addon.init.ModModelLayers;
import com.lazrproductions.cuffed_example_addon.init.ModRestraints;
import com.lazrproductions.cuffed_example_addon.init.ModSounds;

import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.ForgeRegistries.Keys;
import net.minecraftforge.registries.IForgeRegistry;

/*

    This is the main mod class that every forge mod needs to be able to run. For a Cuffed addon there must be some things implement here.


    FIRTLY, you need to register your custom restraints in the constructor for your mod, just like any other registry.

    OPTIONALLY, you can make your restraints dispensable by adding the following to the common setup fml event:

        DispenseItemBehavior dispenseitembehavior = new OptionalDispenseItemBehavior() {
            protected ItemStack execute(@Nonnull BlockSource source, @Nonnull ItemStack stack) {
                this.setSuccess(AbstractRestraintItem.dispenseRestraint(source, stack));
                if(this.isSuccess())
                    stack.shrink(1);
                return stack;
            }
        };
        DispenserBlock.registerBehavior(MyModItems.MY_RESTRAINT_ITEM.get(), dispenseitembehavior);

    
    LASTLY, and most vitally, in version 1.3.2, Cuffed has difficulty finding addon's registries. So to ensure Cuffed finds your registries, 
    add the following snippet to a function with the RegisterEvent event:
        
        IForgeRegistry<?> r = event.getForgeRegistry();
        if(r != null && r.getValues().size() > 0 && r.getValues().toArray()[0] instanceof AbstractRestraint) {
            if(r.getRegistryName().getNamespace().equals(MODID))
                RestraintAPI.Registries.register(r);
        }
    
    This hopefully will be fixed in the future.


    Use feel free to use this example mod as a template for your addon creation needs.
    
 */

@Mod(CuffedExampleAddonMod.MODID)
public class CuffedExampleAddonMod {
    public static final Logger LOGGER = LogManager.getLogger(CuffedExampleAddonMod.MODID);
    public static final String MODID = "cuffed_example_addon";



    public CuffedExampleAddonMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        ModCreativeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModRestraints.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::onRegister);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Running commmon setup for Cuffed Example Addon");

        DispenseItemBehavior dispenseitembehavior = new OptionalDispenseItemBehavior() {
            protected ItemStack execute(@Nonnull BlockSource source, @Nonnull ItemStack stack) {
                this.setSuccess(AbstractRestraintItem.dispenseRestraint(source, stack));
                if(this.isSuccess())
                    stack.shrink(1);
                return stack;
            }
        };
        DispenserBlock.registerBehavior(ModItems.DIAMOND_HANDCUFFS.get(), dispenseitembehavior);
    }

    private void onRegister(RegisterEvent event) {
        if (event.getRegistryKey().equals(Keys.SOUND_EVENTS))
            ModSounds.register(event);

        // Temporary fix for Cuffed not recognizing custom registries
        IForgeRegistry<?> r = event.getForgeRegistry();
        if(r != null && r.getValues().size() > 0 && r.getValues().toArray()[0] instanceof AbstractRestraint) {
            if(r.getRegistryName().getNamespace().equals(MODID))
                RestraintAPI.Registries.register(r);
        }
    }
    
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            // Register the model layers for the custom restraint models.
            ModModelLayers.registerLayers(event);
        }
    }
}