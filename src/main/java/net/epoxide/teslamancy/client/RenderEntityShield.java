package net.epoxide.teslamancy.client;

import net.epoxide.teslamancy.item.ItemPowerArmor;
import net.epoxide.teslamancy.libs.Constants;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;

import static java.lang.Math.*;
import static org.lwjgl.opengl.GL11.*;

public class RenderEntityShield {

    private float rotation = 0;
    private Sphere sphere = new Sphere();

    //TODO Move
    @SubscribeEvent
    public void onEntityHit (LivingHurtEvent event) {
        if (!(event.getEntity() instanceof EntityPlayer))
            return;

        EntityPlayer entityPlayer = (EntityPlayer) event.getEntity();
        final int[] count = {0};
        entityPlayer.getHeldEquipment().forEach(itemStack -> {
            if (itemStack != null && itemStack.getItem() instanceof ItemPowerArmor)
                count[0]++;
        });

        if(count[0]>0){
        }
    }

    @SubscribeEvent
    public void onEntityLiving (RenderLivingEvent.Specials.Post event) {
        if (Minecraft.getMinecraft().currentScreen != null)
            return;
        if (!(event.getEntity() instanceof EntityPlayer))
            return;


        EntityPlayer entityPlayer = (EntityPlayer) event.getEntity();
        final int[] count = {0};
        entityPlayer.getHeldEquipment().forEach(itemStack -> {
            if (itemStack != null && itemStack.getItem() instanceof ItemPowerArmor)
                count[0]++;
        });

        if (count[0] > 0) {
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
        }
    }

    private void drawSphere (float radius, int lats, int longs) {
        glBegin(GL11.GL_TRIANGLES);
        for (int i = 0; i < lats; i++) {
            double inc1 = (i / (float) lats) * 2 * PI;
            double inc2 = ((i + 1) / (float) lats) * 2 * PI;
            double X1 = sin(inc1);
            double Y1 = cos(inc1);
            double X2 = sin(inc2);
            double Y2 = cos(inc2);
            for (int j = 0; j < longs; j++) {
                double inc3 = (j / (float) longs) * PI * 2;
                double inc4 = ((j + 1) / (float) longs) * PI * 2;

                // store the upper and lower radius, remember everything is going to be drawn as triangles
                double Radius1 = radius * cos(inc3);
                double Radius2 = radius * cos(inc4);

                double Z1 = radius * sin(inc3);
                double Z2 = radius * sin(inc4);
                glTexCoord2f(j / longs, i / lats);
                glNormal3d(Radius1 * X1, Radius1 * Y1, -Z1);
                glVertex3d(Radius1 * X1, Radius1 * Y1, -Z1);

                glTexCoord2f(0 / longs, 0 / lats);
                glNormal3d(Radius1 * X2, Radius1 * Y2, -Z1);
                glVertex3d(Radius1 * X2, Radius1 * Y2, -Z1);

                glTexCoord2f(0 / longs, 0 / lats);
                glNormal3d(Radius2 * X2, Radius2 * Y2, -Z2);
                glVertex3d(Radius2 * X2, Radius2 * Y2, -Z2);

                glTexCoord2f(j / longs, i / lats);
                glNormal3d(Radius1 * X1, Radius1 * Y1, -Z1);
                glVertex3d(Radius1 * X1, Radius1 * Y1, -Z1);

                glTexCoord2f(0 / longs, 0 / lats);
                glNormal3d(Radius2 * X2, Radius2 * Y2, -Z2);
                glVertex3d(Radius2 * X2, Radius2 * Y2, -Z2);

                glTexCoord2f(j / longs, i / lats);
                glNormal3d(Radius2 * X1, Radius2 * Y1, -Z2);
                glVertex3d(Radius2 * X1, Radius2 * Y1, -Z2);
            }
        }
        glEnd();
    }
}
