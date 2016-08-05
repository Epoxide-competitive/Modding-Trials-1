package net.epoxide.teslamancy.item;

import java.util.List;

import net.epoxide.teslamancy.libs.utils.NewTeslaUtils;

import net.darkhax.tesla.api.implementation.BaseTeslaContainer;
import net.darkhax.tesla.api.implementation.BaseTeslaContainerProvider;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPowerCell extends Item {
    
    @Override
    public ICapabilityProvider initCapabilities (ItemStack stack, NBTTagCompound nbt) {
        
        return new BaseTeslaContainerProvider(new BaseTeslaContainer());
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
        
        NewTeslaUtils.createTooltip(stack, tooltip);
    }
}
