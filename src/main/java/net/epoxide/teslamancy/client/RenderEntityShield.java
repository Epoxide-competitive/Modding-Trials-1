package net.epoxide.teslamancy.client;

import net.epoxide.teslamancy.item.ItemPowerShield;
import net.epoxide.teslamancy.libs.Constants;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.lwjgl.util.glu.Sphere;

import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;

public class RenderEntityShield {

    private float rotation = 0;
    private Sphere sphere = new Sphere();

    @SubscribeEvent
    public void onEntityLiving (RenderLivingEvent.Specials.Post event) {
        if (Minecraft.getMinecraft().currentScreen != null)
            return;

        if (!(event.getEntity() instanceof EntityPlayer))
            return;

        EntityPlayer entityPlayer = (EntityPlayer) event.getEntity();
        entityPlayer.getHeldEquipment().forEach(itemStack -> {
            if (itemStack != null && itemStack.getItem() instanceof ItemPowerShield && ItemPowerShield.isEnabled(itemStack)) {
                sphere.setTextureFlag(true);
                rotation += Minecraft.getMinecraft().getRenderPartialTicks();

                if (rotation > 360)
                    rotation -= 360;

                GlStateManager.pushMatrix();

                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
                event.getRenderer().bindTexture(new ResourceLocation(Constants.MODID, "textures/renders/shield.png"));
                GlStateManager.translate((float) event.getX(), (float) event.getY() + 0.5f, (float) event.getZ());

                sphere.draw(2, 45, 45);
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
                return;
            }
        });
    }
}
