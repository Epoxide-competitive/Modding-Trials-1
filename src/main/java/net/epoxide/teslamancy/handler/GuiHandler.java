package net.epoxide.teslamancy.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    private static final int PLASMA_GENERATOR = 0;
    private static final int INDUCTION_PAD = 1;

    @Override
    public Object getServerGuiElement (int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID){
            case PLASMA_GENERATOR:
                break;
            case INDUCTION_PAD:
                break;
        }
        return null;
    }

    @Override
    public Object getClientGuiElement (int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID){
            case PLASMA_GENERATOR:
                break;
            case INDUCTION_PAD:
                break;
        }
        return null;
    }
}
