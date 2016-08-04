package net.epoxide.teslamancy.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.world.World;

public class ContainerWand extends Container {
    private final EntityPlayer player;
    private final World world;
    private final IInventory inventory;

    public ContainerWand (EntityPlayer player, World world) {
        this.inventory = new InventoryBasic("wand", false, 7);
        this.player = player;
        this.world = world;
        for (int i = 0; i < 6; i++) {
            double f = (i / 3.0) * Math.PI - 6 * 8;
            this.addSlotToContainer(new Slot(inventory, i, (int) Math.sin(f) - 8, (int) Math.cos(f) - 8));
        }
        this.addSlotToContainer(new Slot(inventory, 6, 0, 0));

        for (int l = 0; l < 3; ++l)
        {
            for (int j1 = 0; j1 < 9; ++j1)
            {
                this.addSlotToContainer(new Slot(player.inventory, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + 80));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1)
        {
            this.addSlotToContainer(new Slot(player.inventory, i1, 8 + i1 * 18, 161 + 80));
        }
    }

    @Override
    public boolean canInteractWith (EntityPlayer playerIn) {
        return true;
    }
}
