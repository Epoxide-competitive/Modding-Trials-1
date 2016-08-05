package net.epoxide.teslamancy.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMaterial extends Item {
    
    public static final String[] VARIANTS = new String[] { "circuit", "resistor", "capacitor", "radiator", "microchip", "plastic" };
    
    public ItemMaterial() {
        
        this.hasSubtypes = true;
    }
    
    public String getVariantForMeta (int meta) {
        
        return meta < 0 ? VARIANTS[0] : meta > VARIANTS.length ? VARIANTS[VARIANTS.length] : VARIANTS[meta];
    }
    
    @Override
    public String getUnlocalizedName (ItemStack stack) {
        
        return "item.teslamancy." + this.getVariantForMeta(stack.getMetadata());
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems (Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        
        for (int meta = 0; meta < VARIANTS.length; meta++)
            subItems.add(new ItemStack(itemIn, 1, meta));
    }
}