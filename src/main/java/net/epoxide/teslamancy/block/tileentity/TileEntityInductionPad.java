package net.epoxide.teslamancy.block.tileentity;

import net.darkhax.tesla.api.ITeslaProducer;
import net.darkhax.tesla.api.implementation.BaseTeslaContainer;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.darkhax.tesla.lib.TeslaUtils;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;

public class TileEntityInductionPad extends TileEntity implements ITickable {

    private ItemStack itemStack;

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability (Capability<T> capability, EnumFacing facing) {

        if (capability == TeslaCapabilities.CAPABILITY_PRODUCER)
            return (T) (ITeslaProducer) (power, simulated) -> power;

        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability (Capability<?> capability, EnumFacing facing) {

        if (capability == TeslaCapabilities.CAPABILITY_PRODUCER)
            return true;

        return super.hasCapability(capability, facing);
    }

    @Override
    public void update () {

        if (hasItemStack()) {
            final BaseTeslaContainer container = (BaseTeslaContainer) itemStack.getCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN);
            container.givePower(container.getInputRate(), false);
        }
    }

    public boolean hasItemStack () {
        return getItemStack() != null;
    }

    public ItemStack getItemStack () {
        return itemStack;
    }

    public void setItemStack (ItemStack itemStack) {
        this.itemStack = itemStack;
    }
}
