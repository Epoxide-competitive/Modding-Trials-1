package net.epoxide.teslamancy.libs.utils;

import java.lang.reflect.Field;
import java.util.HashMap;

import net.epoxide.teslamancy.libs.Constants;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.CapabilityDispatcher;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

public class CapabilityHelper {
    
    private static boolean initialized = false;
    private static Field capabilitiesField;
    private static Field capsField;
    private static Field writersField;
    private static Field namesField;
    
    public static void addProvider (ItemStack stack, String key, ICapabilityProvider provider) {
        
        init();
        
        try {
            
            CapabilityDispatcher capabilities = (CapabilityDispatcher) capabilitiesField.get(stack);
            
            if (capabilities == null)
                capabilities = new CapabilityDispatcher(new HashMap<ResourceLocation, ICapabilityProvider>());

            final ICapabilityProvider[] caps = (ICapabilityProvider[]) capsField.get(capabilities);
            final String[] names = (String[]) namesField.get(capabilities);
            
            for (String name : names)
                if (name.equals(key)) {
                    
                    Constants.LOGGER.warn("Attempted to add duplicate providers!");
                    return;
                }

            final ICapabilityProvider[] expandedCaps = new ICapabilityProvider[caps.length + 1];
            expandedCaps[expandedCaps.length - 1] = provider;
            System.arraycopy(caps, 0, expandedCaps, 0, caps.length);
            capsField.set(capabilities, expandedCaps);
            
            if (provider instanceof INBTSerializable) {
                
                final INBTSerializable<NBTBase>[] writers = (INBTSerializable<NBTBase>[]) writersField.get(capabilities);
                final INBTSerializable[] expandedWriters = new INBTSerializable[writers.length + 1];
                expandedWriters[expandedWriters.length - 1] = (INBTSerializable) provider;
                System.arraycopy(writers, 0, expandedWriters, 0, writers.length);
                writersField.set(capabilities, expandedWriters);
                
                final String[] expandedNames = new String[names.length + 1];
                System.arraycopy(names, 0, expandedNames, 0, names.length);
                expandedNames[expandedNames.length - 1] = key;
                namesField.set(capabilities, expandedNames);
            }
            
            capabilitiesField.set(stack, capabilities);
        } catch (IllegalArgumentException | IllegalAccessException exception) {
            
            Constants.LOGGER.warn(exception);
        }
    }
    
    private static void init () {
        
        if (!initialized) {
            
            try {
                
                capabilitiesField = ItemStack.class.getDeclaredField("capabilities");
                capabilitiesField.setAccessible(true);
                
                capsField = CapabilityDispatcher.class.getDeclaredField("caps");
                capsField.setAccessible(true);
                
                writersField = CapabilityDispatcher.class.getDeclaredField("writers");
                writersField.setAccessible(true);
                
                namesField = CapabilityDispatcher.class.getDeclaredField("names");
                namesField.setAccessible(true);
            } catch (NoSuchFieldException | SecurityException exception) {
                
                Constants.LOGGER.warn(exception);
            }
        }
    }
}