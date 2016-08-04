package net.epoxide.teslamancy;

import net.epoxide.teslamancy.handler.ContentHandler;
import net.epoxide.teslamancy.libs.Constants;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Constants.MODID, name = Constants.MOD_NAME, version = Constants.VERSION)
public class Teslamancy {

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){

        ContentHandler.init();
    }
}
