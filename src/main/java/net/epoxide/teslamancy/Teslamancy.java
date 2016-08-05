package net.epoxide.teslamancy;

import net.epoxide.teslamancy.common.CommonProxy;
import net.epoxide.teslamancy.handler.ContentHandler;
import net.epoxide.teslamancy.handler.ForgeEventHandler;
import net.epoxide.teslamancy.handler.GuiHandler;
import net.epoxide.teslamancy.libs.Constants;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = Constants.MODID, name = Constants.MOD_NAME, version = Constants.VERSION)
public class Teslamancy {
    
    @Mod.Instance
    public static Teslamancy INSTANCE;

    @SidedProxy(clientSide = "net.epoxide.teslamancy.client.ClientProxy", serverSide = "net.epoxide.teslamancy.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent event) {

        ContentHandler.init();
        proxy.preInit();
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new GuiHandler());
        MinecraftForge.EVENT_BUS.register(new ForgeEventHandler());
    }
    
    @Mod.EventHandler
    public void init (FMLInitializationEvent event) {

    }
}
