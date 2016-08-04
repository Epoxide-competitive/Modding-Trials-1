package net.epoxide.teslamancy.libs.utils;

import net.epoxide.teslamancy.creativetab.CreativeTabTeslamancy;
import net.epoxide.teslamancy.libs.Constants;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RegistryHelper {
    
    public static Block registerBlock (Block block, String blockName) {
        
        return registerBlock(block, blockName, new ItemBlock(block));
    }
    
    public static Block registerBlock (Block block, String blockName, ItemBlock itemBlock) {
        
        block.setRegistryName(blockName);
        block.setUnlocalizedName(Constants.MODID + "." + blockName.replace('_', '.'));
        block.setCreativeTab(CreativeTabTeslamancy.INSTANCE);
        GameRegistry.register(block);
        registerItem(itemBlock, blockName);
        
        return block;
    }
    
    public static Item registerItem (Item item, String itemName) {
        
        item.setRegistryName(itemName);
        item.setUnlocalizedName(Constants.MODID + "." + itemName.replace('_', '.'));
        item.setCreativeTab(CreativeTabTeslamancy.INSTANCE);
        GameRegistry.register(item);
        
        return item;
    }
    
    public static void registerBlockInvModel (Block block, String[] variants) {
        
        registerItemInvModel(Item.getItemFromBlock(block), variants);
    }
    
    public static void registerBlockInvModel (Block block, String prefix, String[] variants) {
        
        registerItemInvModel(Item.getItemFromBlock(block), prefix, variants);
    }
    
    public static void registerBlockInvModel (Block block) {
        
        registerItemInvModel(Item.getItemFromBlock(block), 0);
    }
    
    public static void registerBlockInvModel (Block block, int meta) {
        
        registerItemInvModel(Item.getItemFromBlock(block), meta);
    }
    
    public static void registerItemInvModel (Item item, int meta, String model) {
        
        setCustomModelWrapper(item, meta, new ModelResourceLocation(model, "inventory"));
    }
    
    public static void registerItemInvModel (Item item, String prefix, String[] variants) {
        
        for (int meta = 0; meta < variants.length; meta++)
            setCustomModelWrapper(item, meta, new ModelResourceLocation(Constants.MODID + ":" + prefix + "_" + variants[meta], "inventory"));
    }
    
    public static void registerItemInvModel (Item item, String[] variants) {
        
        for (int meta = 0; meta < variants.length; meta++)
            setCustomModelWrapper(item, meta, new ModelResourceLocation(Constants.MODID + ":" + variants[meta], "inventory"));
    }
    
    public static void registerItemInvModel (Item item) {
        
        setCustomModelWrapper(item, 0, new ModelResourceLocation(item.getRegistryName().toString(), "inventory"));
    }
    
    public static void registerItemInvModel (Item item, int meta) {
        
        setCustomModelWrapper(item, meta, new ModelResourceLocation(item.getRegistryName().toString(), "inventory"));
    }
    
    private static void setCustomModelWrapper (Item item, int meta, ModelResourceLocation model) {
        
        if (FMLCommonHandler.instance().getSide().isClient())
            ModelLoader.setCustomModelResourceLocation(item, meta, model);
    }
}
