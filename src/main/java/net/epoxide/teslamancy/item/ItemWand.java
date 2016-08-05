package net.epoxide.teslamancy.item;

import net.epoxide.teslamancy.Teslamancy;
import net.epoxide.teslamancy.handler.GuiHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemWand extends Item {
    
    @Override
    public ActionResult<ItemStack> onItemRightClick (ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        
        playerIn.openGui(Teslamancy.INSTANCE, GuiHandler.ITEM_WAND, worldIn, 0, 0, 0);
        return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
    }
}
