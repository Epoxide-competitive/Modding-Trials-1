package net.epoxide.teslamancy.handler;

import static net.epoxide.teslamancy.libs.utils.RegistryHelper.*;

import net.epoxide.teslamancy.block.BlockInductionPad;
import net.epoxide.teslamancy.block.BlockPlasmaGenerator;
import net.epoxide.teslamancy.block.tileentity.TileEntityInductionPad;
import net.epoxide.teslamancy.block.tileentity.TileEntityPlasmaGenerator;
import net.epoxide.teslamancy.item.ItemMaterial;
import net.epoxide.teslamancy.item.ItemWand;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ContentHandler {
    
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
        registerItemInvModel(itemMaterial, "material", ItemMaterial.VARIANTS);
        
        itemWand = registerItem(new ItemWand(), "wand");
    }
}
