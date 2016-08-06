package net.epoxide.teslamancy.item;

import java.util.List;

import net.darkhax.tesla.api.implementation.BaseTeslaContainer;
import net.darkhax.tesla.api.implementation.BaseTeslaContainerProvider;
import net.darkhax.tesla.capability.TeslaCapabilities;

import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPowerShield extends Item {

    public ItemPowerShield () {

        this.setMaxStackSize(1);
    }

    @Override
    public void onUpdate (ItemStack stack, World world, Entity entity, int slot, boolean isHeld) {

        if (isEnabled(stack)) {
            //TODO remove power every so often
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick (ItemStack stack, World worldIn, EntityPlayer playerIn, EnumHand hand) {

        if (!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());

        stack.getTagCompound().setBoolean("Enabled", !isEnabled(stack));
        return new ActionResult<>(EnumActionResult.PASS, stack);
    }

    public static boolean isEnabled (ItemStack stack) {

        return stack != null && stack.hasTagCompound() && stack.getTagCompound().getBoolean("Enabled");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {

        final BaseTeslaContainer container = (BaseTeslaContainer) stack.getCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN);

        tooltip.add(I18n.format("tooltip.teslamancy.powershield.remaining", container.getStoredPower(), container.getCapacity()));
        tooltip.add(I18n.format("tooltip.teslamancy.boolean." + isEnabled(stack)));
    }

    public static long remainingPower (ItemStack itemStack) {
        final BaseTeslaContainer container = (BaseTeslaContainer) itemStack.getCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN);

        return container.getStoredPower();
    }

    public static void removePower (ItemStack itemStack, float amount) {
        final BaseTeslaContainer container = (BaseTeslaContainer) itemStack.getCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN);

        container.takePower((long) (amount * 10), false);
    }

    @Override
    public ICapabilityProvider initCapabilities (ItemStack stack, NBTTagCompound nbt) {

        return new BaseTeslaContainerProvider(new BaseTeslaContainer());
    }
}
