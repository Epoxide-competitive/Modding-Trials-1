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
    
    /**
     * Registers a block with a default item block.
     *
     * @param block The block to register.
     * @param blockName The name for the block. Should use snake case.
     * @return The block that was registered.
     */
    public static Block registerBlock (Block block, String blockName) {
        
        return registerBlock(block, blockName, new ItemBlock(block));
    }
    
    /**
     * Registers a block along side a custom item block.
     *
     * @param block The block to register.
     * @param blockName The name for the block. Should use snake case.
     * @param itemBlock The item to use for the block.
     * @return The block that was registered.
     */
    public static Block registerBlock (Block block, String blockName, ItemBlock itemBlock) {
        
        block.setRegistryName(blockName);
        block.setUnlocalizedName(Constants.MODID + "." + blockName.replaceAll("_", ""));
        block.setCreativeTab(CreativeTabTeslamancy.INSTANCE);
        GameRegistry.register(block);
        registerItem(itemBlock, blockName);
        
        return block;
    }
    
    /**
     * Registers an item.
     *
     * @param item The item to register.
     * @param itemName The name for the item. Should use snake case.
     * @return The block that was registered.
     */
    public static Item registerItem (Item item, String itemName) {
        
        item.setRegistryName(itemName);
        item.setUnlocalizedName(Constants.MODID + "." + itemName.replaceAll("_", ""));
        item.setCreativeTab(CreativeTabTeslamancy.INSTANCE);
        GameRegistry.register(item);
        
        return item;
    }
    
    /**
     * Registers an inventory model for a block, using a series of strings which represent
     * model names in order of meta.
     *
     * @param block The block to register models for.
     * @param variants Array of variant names.
     */
    public static void registerBlockInvModel (Block block, String[] variants) {
        
        registerItemInvModel(Item.getItemFromBlock(block), variants);
    }
    
    /**
     * Registers an inventory model for a block, using a prefix and a series of strings which
     * represent model names in order of meta.
     *
     * @param block The block to register models for.
     * @param prefix The prefix to use on all models.
     * @param variants Array of variant names.
     */
    public static void registerBlockInvModel (Block block, String prefix, String[] variants) {
        
        registerItemInvModel(Item.getItemFromBlock(block), prefix, variants);
    }
    
    /**
     * Registers an inventory model for a basic block.
     *
     * @param block The block to register models for.
     */
    public static void registerBlockInvModel (Block block) {
        
        registerItemInvModel(Item.getItemFromBlock(block), 0, block.getRegistryName().toString());
    }
    
    /**
     * Registers an inventory model for an item.
     *
     * @param item The item to register a model for.
     * @param meta The meta for the model.
     * @param model The model name.
     */
    public static void registerItemInvModel (Item item, int meta, String model) {
        
        setCustomModelWrapper(item, meta, new ModelResourceLocation(model, "inventory"));
    }
    
    /**
     * Registers an inventory model for an item with multiple meta states.
     *
     * @param item The item to register a model for.
     * @param prefix A prefix for all the variant states.
     * @param variants Array of variants in order of meta value.
     */
    public static void registerItemInvModel (Item item, String prefix, String[] variants) {
        
        for (int meta = 0; meta < variants.length; meta++)
            setCustomModelWrapper(item, meta, new ModelResourceLocation(Constants.MODID + ":" + prefix + "_" + variants[meta], "inventory"));
    }
    
    /**
     * Registers an inventory model for an item with multiple meta states.
     *
     * @param item The item to register a model for.
     * @param variants Array of variants in order of meta value.
     */
    public static void registerItemInvModel (Item item, String[] variants) {
        
        for (int meta = 0; meta < variants.length; meta++)
            setCustomModelWrapper(item, meta, new ModelResourceLocation(Constants.MODID + ":" + variants[meta], "inventory"));
    }
    
    /**
     * Register a basic inventory model for an item.
     *
     * @param item The item to register a model for.
     */
    public static void registerItemInvModel (Item item) {
        
        setCustomModelWrapper(item, 0, new ModelResourceLocation(item.getRegistryName().toString(), "inventory"));
    }
    
    /**
     * A wrapper method for
     * {@link ModelLoader#setCustomModelResourceLocation(Item, int, ModelResourceLocation)}
     * that can be called server side. Doing this until I have time to work out a saner
     * registry method.
     *
     * @param item The item to register a model for.
     * @param meta The meta to register a model at.
     * @param model The model to register.
     */
    private static void setCustomModelWrapper (Item item, int meta, ModelResourceLocation model) {
        
        if (FMLCommonHandler.instance().getSide().isClient())
            ModelLoader.setCustomModelResourceLocation(item, meta, model);
    }
}
