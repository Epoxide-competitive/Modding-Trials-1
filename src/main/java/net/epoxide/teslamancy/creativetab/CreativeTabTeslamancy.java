package net.epoxide.teslamancy.creativetab;

import net.epoxide.teslamancy.handler.ContentHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabTeslamancy extends CreativeTabs {
    
    public static final CreativeTabs INSTANCE = new CreativeTabTeslamancy();
    
    public CreativeTabTeslamancy() {
        
        super("teslamancy");
    }
    
    @Override
    public Item getTabIconItem () {
        
        return ContentHandler.itemMaterial;
    }
}
