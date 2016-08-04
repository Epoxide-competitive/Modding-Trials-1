package net.epoxide.teslamancy.handler;

import net.epoxide.teslamancy.block.BlockInductionPad;
import net.epoxide.teslamancy.block.BlockPlasmaGenerator;
import net.epoxide.teslamancy.block.tileentity.TileEntityInductionPad;
import net.epoxide.teslamancy.block.tileentity.TileEntityPlasmaGenerator;
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
            return Item.getItemFromBlock(ContentHandler.blockPlasmaGenerator);
        }
    };

    public static Block blockPlasmaGenerator;
    public static Block blockInductionPad;

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

    private static Block registerBlock (Block block, String blockName) {
        block.setRegistryName(new ResourceLocation(Constants.MODID, blockName));
        block.setUnlocalizedName(Constants.MODID + "." + blockName);
        block.setCreativeTab(creativeTabTeslamancy);
        GameRegistry.register(block);

        ItemBlock itemBlock = new ItemBlock(block);
        itemBlock.setRegistryName(new ResourceLocation(Constants.MODID, blockName));
        itemBlock.setUnlocalizedName(Constants.MODID + "." + blockName);
        GameRegistry.register(itemBlock);

        return block;
    }

    private static void initItem () {

    }
}
