package com.lazrproductions.cuffed_example_addon.init;

import com.lazrproductions.cuffed_example_addon.CuffedExampleAddonMod;
import com.lazrproductions.cuffed_example_addon.restraints.client.model.DiamondHandcuffsArmsModel;
import com.lazrproductions.cuffed_example_addon.restraints.client.model.DiamondHanduffsLegsModel;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;

/*

    This class is used to store and register the model layers used by your restraints, this can be done in the model classes but I find it easier to keep everything 
    in one place and to register them all at once.

    This is exactly the same as registering ModelLayerLocations for entities.

 */

public class ModModelLayers {
    // Restraint Model Layer Locations
    public static final ModelLayerLocation DIAMOND_HANDCUFFS_ARMS_LAYER = new ModelLayerLocation(new ResourceLocation(CuffedExampleAddonMod.MODID, "diamond_handcuffs_arms_layer"), "main");
    public static final ModelLayerLocation DIAMOND_HANDCUFFS_LEGS_LAYER = new ModelLayerLocation(new ResourceLocation(CuffedExampleAddonMod.MODID, "diamond_handcuffs_legs_layer"), "main");
    
    
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(DIAMOND_HANDCUFFS_ARMS_LAYER, DiamondHandcuffsArmsModel::createArmorLayer);
        event.registerLayerDefinition(DIAMOND_HANDCUFFS_LEGS_LAYER, DiamondHanduffsLegsModel::createArmorLayer);
    }
}
