package net.epoxide.teslamancy.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidPrimalPlasma extends Fluid {
    
    public static final FluidPrimalPlasma INSTANCE = new FluidPrimalPlasma();
    
    public FluidPrimalPlasma() {
        super("primal_plasma", new ResourceLocation("blocks/water_still"), new ResourceLocation("blocks/water_flow"));
    }
    
    @Override
    public int getColor () {
        
        return 0xFFFF0000;
    }
}
