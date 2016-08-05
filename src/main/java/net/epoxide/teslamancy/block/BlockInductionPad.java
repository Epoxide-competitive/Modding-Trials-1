package net.epoxide.teslamancy.block;

import net.epoxide.teslamancy.block.tileentity.TileEntityInductionPad;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockInductionPad extends Block implements ITileEntityProvider {
    
    public BlockInductionPad () {
        
        super(Material.IRON);
    }
    
    @Override
    public TileEntity createNewTileEntity (World worldIn, int meta) {
        
        return new TileEntityInductionPad();
    }
}
