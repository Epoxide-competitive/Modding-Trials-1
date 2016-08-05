package net.epoxide.teslamancy.block;

import net.epoxide.teslamancy.fluid.FluidPrimalPlasma;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;

public class BlockPrimalPlasma extends BlockFluidClassic {

    public BlockPrimalPlasma () {

        super(FluidPrimalPlasma.INSTANCE, Material.WATER);
    }

}
