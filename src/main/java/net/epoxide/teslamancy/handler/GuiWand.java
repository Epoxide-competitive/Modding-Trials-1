package net.epoxide.teslamancy.handler;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiWand extends GuiScreen {
    private final EntityPlayer player;
    private final World world;

    public GuiWand (EntityPlayer player, World world) {
        this.player = player;
        this.world = world;
    }

    @Override
    public boolean doesGuiPauseGame () {
        return false;
    }
}
