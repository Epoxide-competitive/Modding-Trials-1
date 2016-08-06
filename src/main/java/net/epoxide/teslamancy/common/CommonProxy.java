package net.epoxide.teslamancy.common;

import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
    public void preInit () {
        MinecraftForge.EVENT_BUS.register(new ForgeCommonEvents());
    }
}
