package net.epoxide.teslamancy.handler;

import static net.epoxide.teslamancy.libs.utils.RegistryHelper.registerBlock;
import static net.epoxide.teslamancy.libs.utils.RegistryHelper.registerItem;
import static net.epoxide.teslamancy.libs.utils.RegistryHelper.registerItemInvModel;

import net.epoxide.teslamancy.block.BlockInductionPad;
import net.epoxide.teslamancy.block.BlockPlasmaGenerator;
import net.epoxide.teslamancy.block.BlockPrimalPlasma;
import net.epoxide.teslamancy.block.tileentity.TileEntityInductionPad;
import net.epoxide.teslamancy.block.tileentity.TileEntityPlasmaGenerator;
import net.epoxide.teslamancy.fluid.FluidPrimalPlasma;
import net.epoxide.teslamancy.item.ItemLoadstone;
import net.epoxide.teslamancy.item.ItemMaterial;
import net.epoxide.teslamancy.item.ItemPowerCell;
import net.epoxide.teslamancy.item.ItemWand;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ContentHandler {
    
    public static Block blockPlasmaGenerator;
    public static Block blockInductionPad;
    public static Block blockPrimalPlasma;
    
    public static Item itemMaterial;
    public static Item itemWand;
    public static Item itemPowercell;
    public static Item itemLoadstone;
    
    public static void init () {
        
        FluidRegistry.registerFluid(FluidPrimalPlasma.INSTANCE);
        
        initBlock();
        initItem();
    }
    
    private static void initBlock () {
        
        blockPlasmaGenerator = registerBlock(new BlockPlasmaGenerator(), "plasma_generator");
        GameRegistry.registerTileEntity(TileEntityPlasmaGenerator.class, "plasma_generator");
        
        blockInductionPad = registerBlock(new BlockInductionPad(), "induction_pad");
        GameRegistry.registerTileEntity(TileEntityInductionPad.class, "induction_pad");
        
        blockPrimalPlasma = registerBlock(new BlockPrimalPlasma(), "primal_plasma");
    }
    
    private static void initItem () {
        
        itemMaterial = registerItem(new ItemMaterial(), "material");
        registerItemInvModel(itemMaterial, "material", ItemMaterial.VARIANTS);
        
        itemWand = registerItem(new ItemWand(), "wand");
        
        itemPowercell = registerItem(new ItemPowerCell(), "powercell");
        registerItemInvModel(itemPowercell);
        
        itemLoadstone = registerItem(new ItemLoadstone(), "loadstone");
        registerItemInvModel(itemLoadstone);
    }
}
