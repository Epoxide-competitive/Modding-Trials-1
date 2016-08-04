package net.epoxide.teslamancy.block;

import net.epoxide.teslamancy.block.tileentity.TileEntityPlasmaGenerator;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPlasmaGenerator extends BlockContainer {

    public BlockPlasmaGenerator () {

        super(Material.IRON);
    }


    @Override
    public TileEntity createNewTileEntity (World worldIn, int meta) {
        return new TileEntityPlasmaGenerator();
    }
}
