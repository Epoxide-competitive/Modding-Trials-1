package net.epoxide.teslamancy.item;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.darkhax.tesla.api.ITeslaHolder;
import net.darkhax.tesla.api.implementation.BaseTeslaContainer;
import net.darkhax.tesla.api.implementation.BaseTeslaContainerProvider;
import net.darkhax.tesla.lib.TeslaUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
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
        
        if (TeslaUtils.isTeslaHolder(stack, EnumFacing.DOWN)) {
            
            final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
            final ITeslaHolder holder = TeslaUtils.getTeslaHolder(stack, EnumFacing.DOWN);
            
            if (GameSettings.isKeyDown(keyBindSneak) && holder instanceof BaseTeslaContainer) {
                
                final BaseTeslaContainer container = (BaseTeslaContainer) holder;
                
                tooltip.add(ChatFormatting.DARK_AQUA + I18n.format("tooltip.teslamancy.powerinfo", Long.toString(container.getStoredPower()), Long.toString(container.getCapacity())));
                tooltip.add(ChatFormatting.DARK_AQUA + I18n.format("tooltip.teslamancy.input", Long.toString(container.getInputRate())));
                tooltip.add(ChatFormatting.DARK_AQUA + I18n.format("tooltip.teslamancy.output", Long.toString(container.getOutputRate())));
            }
            
            else
                tooltip.add(I18n.format("tooltip.teslamancy.showinfo", keyBindSneak.getDisplayName()));
        }
    }
}
