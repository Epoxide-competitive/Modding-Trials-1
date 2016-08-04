package net.epoxide.teslamancy.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemMaterial extends Item {

    private static final String[] VARIANTS = new String[] {"circuit", "resistor", "capacitor", "radiator"};
    
    public ItemMaterial() {
       
        this.hasSubtypes = true;
    }
    
    public String getVariantForMeta(int meta) {
        
        return (meta < 0) ? VARIANTS[0] : (meta > VARIANTS.length) ? VARIANTS[VARIANTS.length] : VARIANTS[meta];
    }
    
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        
        return "item." + getVariantForMeta(stack.getMetadata()) + ".name";
    }
}