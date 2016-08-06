package net.epoxide.teslamancy.block;

import javax.annotation.Nullable;

import net.epoxide.teslamancy.block.tileentity.TileEntityInductionPad;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockInductionPad extends Block implements ITileEntityProvider {
    
    public BlockInductionPad () {
        
        super(Material.IRON);
        this.setLightOpacity(0);
    }

    @Override
    public boolean onBlockActivated (World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {

        TileEntityInductionPad tileEntity = (TileEntityInductionPad) worldIn.getTileEntity(pos);
        if (tileEntity != null) {
            if (heldItem == null) {

            }
            else {
                if (!tileEntity.hasItemStack()) {
                    tileEntity.setItemStack(new ItemStack(heldItem.getItem(), 1, heldItem.getItemDamage()));
                    heldItem.stackSize--;
                }
                else {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity (World worldIn, int meta) {
        
        return new TileEntityInductionPad();
    }

    @Override
    public boolean isOpaqueCube (IBlockState state) {
        return true;
    }

    @Override
    public BlockRenderLayer getBlockLayer () {
        return BlockRenderLayer.TRANSLUCENT;
    }
}
