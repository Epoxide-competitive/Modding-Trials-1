package net.epoxide.teslamancy.common;

import net.epoxide.teslamancy.item.ItemPowerShield;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ForgeCommonEvents {

    @SubscribeEvent
    public void onEntityHit (LivingHurtEvent event) {
        if (!(event.getEntity() instanceof EntityPlayer))
            return;

        event.getEntity().getHeldEquipment().forEach(itemStack -> {
            if (itemStack != null && itemStack.getItem() instanceof ItemPowerShield && ItemPowerShield.isEnabled(itemStack)) {
                long power = ItemPowerShield.remainingPower(itemStack);
                if (power >= event.getAmount()) {
                    ItemPowerShield.removePower(itemStack, event.getAmount());
                    event.setCanceled(true);
                }
            }
        });
    }
}
