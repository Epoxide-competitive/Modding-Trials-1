package net.epoxide.teslamancy.client.render.entity;

import net.epoxide.teslamancy.entity.EntityTeslamental;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelEnderCrystal;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderTeslamental extends RenderLiving<EntityTeslamental> {
    
    private static final ResourceLocation TEXTURE = new ResourceLocation("teslamancy", "textures/entity/teslamental.png");
    private static final ModelBase MODEL = new ModelEnderCrystal(0.0F, false);
    
    public RenderTeslamental(RenderManager renderManagerIn) {
        
        super(renderManagerIn, MODEL, 0.5F);
    }
    
    @Override
    public void doRender (EntityTeslamental entity, double x, double y, double z, float entityYaw, float partialTicks) {
        
        final float f = entity.rotation + partialTicks;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x, (float) y, (float) z);
        
        if (entity.hurtTime > 5)
            GlStateManager.color(0.65f, 0f, 0f);
            
        this.bindTexture(TEXTURE);
        float f1 = MathHelper.sin(f * 0.2F) / 2.0F + 0.5F;
        f1 = f1 * f1 + f1;
        MODEL.render(entity, 0.0F, f * 3.0F, f1 * 0.2F, 0.0F, 0.0F, 0.0625F);
        GlStateManager.popMatrix();
        
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
    
    @Override
    protected ResourceLocation getEntityTexture (EntityTeslamental entity) {
        
        return TEXTURE;
    }
    
    @SideOnly(Side.CLIENT)
    public static class RenderFactoryTeslamental implements IRenderFactory<EntityTeslamental> {
        
        @Override
        public RenderTeslamental createRenderFor (RenderManager manager) {
            
            return new RenderTeslamental(manager);
        }
    }
}