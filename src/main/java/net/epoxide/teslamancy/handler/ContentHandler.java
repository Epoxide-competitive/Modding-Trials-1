package net.epoxide.teslamancy.handler;

import net.epoxide.teslamancy.block.BlockInductionPad;
import net.epoxide.teslamancy.block.BlockPlasmaGenerator;
import net.epoxide.teslamancy.block.tileentity.TileEntityInductionPad;
import net.epoxide.teslamancy.block.tileentity.TileEntityPlasmaGenerator;
import net.epoxide.teslamancy.item.ItemMaterial;
import net.epoxide.teslamancy.item.ItemWand;
import net.epoxide.teslamancy.libs.Constants;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ContentHandler {

    public static CreativeTabs creativeTabTeslamancy = new CreativeTabs("teslamancy") {
        @Override
        public Item getTabIconItem () {
            return ContentHandler.itemWand;
        }
    };

    public static Block blockPlasmaGenerator;
    public static Block blockInductionPad;

    public static Item itemMaterial;
    public static Item itemWand;

    public static void init () {

        initBlock();
        initItem();
    }

    private static void initBlock () {

        blockPlasmaGenerator = registerBlock(new BlockPlasmaGenerator(), "plasmaGenerator");
        GameRegistry.registerTileEntity(TileEntityPlasmaGenerator.class, "plasmaGenerator");

        blockInductionPad = registerBlock(new BlockInductionPad(), "inductionPad");
        GameRegistry.registerTileEntity(TileEntityInductionPad.class, "inductionPad");
    }
    
    private static void initItem () {

        itemMaterial = registerItem(new ItemMaterial(), "material");
        itemWand = registerItem(new ItemWand(), "wand");
    }

    private static Block registerBlock (Block block, String blockName) {
        
        return registerBlock(block, blockName, new ItemBlock(block));
    }
    
    private static Block registerBlock (Block block, String blockName, ItemBlock itemBlock) {

        block.setRegistryName(blockName);
        block.setUnlocalizedName(Constants.MODID + "." + blockName);
        block.setCreativeTab(creativeTabTeslamancy);       
        GameRegistry.register(block);
        registerItem(itemBlock, blockName);
        
        return block;
    }

    private static Item registerItem (Item item, String itemName) {

        item.setRegistryName(itemName);
        item.setUnlocalizedName(Constants.MODID + "." + itemName);
        item.setCreativeTab(creativeTabTeslamancy);
        GameRegistry.register(item);

        return item;
    }
}
