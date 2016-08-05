package net.epoxide.teslamancy.libs;

import net.darkhax.tesla.api.implementation.BaseTeslaContainer;
import net.darkhax.tesla.capability.TeslaCapabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

public class ArtificialTeslaProvider implements INBTSerializable<NBTTagCompound>, ICapabilityProvider {
    
    private final ArtificialTeslaContainer container;
    
    public ArtificialTeslaProvider () {
        
        this.container = new ArtificialTeslaContainer();
    }
    
    @Override
    public boolean hasCapability (Capability<?> capability, EnumFacing facing) {
        
        return capability == TeslaCapabilities.CAPABILITY_CONSUMER || capability == TeslaCapabilities.CAPABILITY_PRODUCER || capability == TeslaCapabilities.CAPABILITY_HOLDER;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability (Capability<T> capability, EnumFacing facing) {
        
        if (capability == TeslaCapabilities.CAPABILITY_CONSUMER || capability == TeslaCapabilities.CAPABILITY_PRODUCER || capability == TeslaCapabilities.CAPABILITY_HOLDER)
            return (T) this.container;

        return null;
    }
    
    @Override
    public NBTTagCompound serializeNBT () {
        
        return this.container.serializeNBT();
    }
    
    @Override
    public void deserializeNBT (NBTTagCompound nbt) {
        
        this.container.deserializeNBT(nbt);
    }
    
    public class ArtificialTeslaContainer extends BaseTeslaContainer {
        
        public ArtificialTeslaContainer () {
            
            super(500, 5, 5);
        }
    }
}