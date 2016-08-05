package net.epoxide.teslamancy.item;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLoadstone extends Item {
    
    public ItemLoadstone() {
        
        this.setMaxStackSize(1);
    }
    
    @Override
    public void onUpdate (ItemStack stack, World world, Entity entity, int slot, boolean isHeld) {
        
        if (isEnabled(stack) && !entity.isSneaking()) {
            
            final int range = 3;
            final float factor = 0.025f;
            
            final double x = entity.posX;
            final double y = entity.posY + 1.5;
            final double z = entity.posZ;
            
            final List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(x - range, y - range, z - range, x + range, y + range, z + range));
            
            for (final EntityItem item : items)
                item.addVelocity((x - item.posX) * factor, (y - item.posY) * factor, (z - item.posZ) * factor);
        }
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick (ItemStack stack, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        
        if (!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());
            
        stack.getTagCompound().setBoolean("Enabled", !isEnabled(stack));
        return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
    }
    
    public static boolean isEnabled (ItemStack stack) {
        
        return stack != null && stack.hasTagCompound() && stack.getTagCompound().getBoolean("Enabled");
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
        
        tooltip.add(I18n.format("tooltip.teslamancy.loadstone." + isEnabled(stack)));
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems (Item item, CreativeTabs tab, List<ItemStack> subItems) {
        
        final ItemStack stack = new ItemStack(item);
        stack.setTagCompound(new NBTTagCompound());
        stack.getTagCompound().setBoolean("Enabled", true);
        subItems.add(stack);
        subItems.add(new ItemStack(item));
    }
}
