package net.epoxide.teslamancy.libs.utils;

import com.mojang.realmsclient.gui.ChatFormatting;

import java.util.List;

import net.darkhax.tesla.api.ITeslaHolder;
import net.darkhax.tesla.api.implementation.BaseTeslaContainer;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.darkhax.tesla.lib.TeslaUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class NewTeslaUtils {
    
    public static boolean isTeslaCapability (Capability<?> capability) {
        
        return capability == TeslaCapabilities.CAPABILITY_CONSUMER || capability == TeslaCapabilities.CAPABILITY_PRODUCER || capability == TeslaCapabilities.CAPABILITY_HOLDER;
    }
    
    public static void createTooltip (ItemStack stack, List<String> tooltip) {
        
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
