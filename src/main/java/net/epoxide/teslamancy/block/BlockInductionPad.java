package net.epoxide.teslamancy.block;

import net.epoxide.teslamancy.block.tileentity.TileEntityInductionPad;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockInductionPad extends BlockContainer {

    public BlockInductionPad () {

        super(Material.IRON);
    }

    @Override
    public TileEntity createNewTileEntity (World worldIn, int meta) {
        return new TileEntityInductionPad();
    }
}
