package com.lazrproductions.cuffed_example_addon.item;

import com.lazrproductions.cuffed.items.base.AbstractRestraintItem;

/*
    This is the AbstractRestraintItem class for DiamondHandcuffs item, the item that is used to restraint the player with the DiamondHandcuffsArms restraint.
    
    Your restraint items don't HAVE to extend AbstractRestraintItem, AbstractArmRestraint, or AbstractLegRestraint. This only effects what the item can be enchanted 
    with, how high on the player's hitbox the captor must interact to equip the restraint. This also effects dispenser behavior.
    
 */

public class DiamondHandcuffsItem extends AbstractRestraintItem {
    public DiamondHandcuffsItem(Properties p) {
        super(p);
    }
}
