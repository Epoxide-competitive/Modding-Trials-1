package net.epoxide.teslamancy.item;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPowerArmor extends Item {

    boolean enabled = false;

    @Nullable
    @Override
    public ItemStack onItemUseFinish (ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        enabled = !enabled;
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }

    @Override
    public void onUpdate (ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (enabled) {
            //Use Power
        }
    }
}
