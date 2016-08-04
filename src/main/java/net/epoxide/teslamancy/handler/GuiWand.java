package net.epoxide.teslamancy.handler;

import static org.lwjgl.opengl.GL11.GL_COMPILE;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glCallList;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glEndList;
import static org.lwjgl.opengl.GL11.glGenLists;
import static org.lwjgl.opengl.GL11.glNewList;
import static org.lwjgl.opengl.GL11.glVertex2d;

import org.lwjgl.opengl.GL11;

import net.epoxide.teslamancy.common.ContainerWand;
import net.epoxide.teslamancy.libs.Constants;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiWand extends GuiContainer {
    private final EntityPlayer player;
    private final World world;
    
    float pTicks = 0;
    int max = 0;
    private int callList = -1;
    
    public GuiWand(EntityPlayer player, World world) {
        super(new ContainerWand(player, world));
        this.player = player;
        this.world = world;
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer (float partialTicks, int mouseX, int mouseY) {
        
        this.pTicks += partialTicks;
        
        if (this.pTicks > 1.0) {
            this.max++;
            if (this.max > 6)
                this.max = 6;
            this.pTicks = 0;
        }
        
        GlStateManager.pushMatrix();
        
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL_TEXTURE_2D);
        GL11.glDisable(GL_DEPTH_TEST);
        GlStateManager.translate(this.width / 2, this.height / 4 + this.height / 8, 0);
        
        for (int i = 0; i < 6; i++) {
            GlStateManager.pushMatrix();
            GlStateManager.scale(13, 13, 0);
            final double f = i / 3.0 * Math.PI - (6 - this.max) * 8;
            GlStateManager.translate(Math.sin(f) * this.max * 5 / 8, Math.cos(f) * this.max * 5 / 8, 0);
            this.drawHexagon();
            GlStateManager.popMatrix();
            
            this.inventorySlots.getSlot(i).xDisplayPosition = (int) (Math.sin(f) * this.max * 8.0f - 8.0f + this.xSize / 2.0f);
            this.inventorySlots.getSlot(i).yDisplayPosition = (int) (Math.cos(f) * this.max * 8.0f - 8.0f);
            if (!this.inventorySlots.getSlot(i).getHasStack())
                this.inventorySlots.getSlot(i).inventory.setInventorySlotContents(i, new ItemStack(Items.APPLE));
        }
        GlStateManager.pushMatrix();
        GlStateManager.scale(13, 13, 0);
        this.drawHexagon();
        GlStateManager.popMatrix();
        
        this.inventorySlots.getSlot(6).xDisplayPosition = (int) (this.xSize / 2.0f) - 8;
        this.inventorySlots.getSlot(6).yDisplayPosition = (int) (this.ySize / 2.0) - 8;
        if (!this.inventorySlots.getSlot(6).getHasStack())
            this.inventorySlots.getSlot(6).inventory.setInventorySlotContents(6, new ItemStack(Items.APPLE));
            
        GL11.glEnable(GL_DEPTH_TEST);
        GL11.glEnable(GL_TEXTURE_2D);
        
        GlStateManager.popMatrix();
        
        GlStateManager.pushMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Constants.MODID, "textures/gui/playerInv.png"));
        final int i = (this.width - 175) / 2;
        final int j = (this.height - 83) / 2 + 75;
        this.drawTexturedModalRect(i, j, 0, 0, 1, 1, 175, 83);
        GlStateManager.popMatrix();
    }
    
    @Override
    public boolean doesGuiPauseGame () {
        
        return false;
    }
    
    private void drawHexagon () {
        
        if (this.callList == -1) {
            this.callList = glGenLists(1);
            glNewList(this.callList, GL_COMPILE);
            glBegin(GL_POLYGON);
            for (int i = 0; i < 6; ++i) {
                final double f = i / 6.0 * 2.0 * Math.PI;
                glVertex2d(Math.sin(f), Math.cos(f));
            }
            glEnd();
            glEndList();
        }
        else
            glCallList(this.callList);
    }
    
    public void drawTexturedModalRect (double x, double y, double minU, double minV, double maxU, double maxV, double width, double height) {
        
        final Tessellator tessellator = Tessellator.getInstance();
        final VertexBuffer vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos(x, y + height, this.zLevel).tex(minU, maxV).endVertex();
        vertexbuffer.pos(x + width, y + height, this.zLevel).tex(maxU, maxV).endVertex();
        vertexbuffer.pos(x + width, y + 0, this.zLevel).tex(maxU, minV).endVertex();
        vertexbuffer.pos(x + 0, y + 0, this.zLevel).tex(minU, minV).endVertex();
        tessellator.draw();
    }
}
