package net.epoxide.teslamancy;

import net.epoxide.teslamancy.handler.ContentHandler;
import net.epoxide.teslamancy.handler.GuiHandler;
import net.epoxide.teslamancy.libs.Constants;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = Constants.MODID, name = Constants.MOD_NAME, version = Constants.VERSION)
public class Teslamancy {

    @Mod.Instance
    public static Teslamancy INSTANCE;

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent event) {

        ContentHandler.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new GuiHandler());
    }

    @Mod.EventHandler
    public void init (FMLInitializationEvent event) {

    }
}
