package net.epoxide.teslamancy.client.render.entity;

import net.epoxide.teslamancy.entity.EntityTeslamentalShot;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderShulkerBullet;
import net.minecraft.entity.projectile.EntityShulkerBullet;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderTeslamentalShot extends RenderShulkerBullet {
    
    private static final ResourceLocation TEXTURE = new ResourceLocation("teslamancy", "textures/entity/teslamental_spark.png");
    
    public RenderTeslamentalShot(RenderManager manager) {
        
        super(manager);
    }
    
    @Override
    protected ResourceLocation getEntityTexture (EntityShulkerBullet entity) {
        
        return TEXTURE;
    }
    
    @SideOnly(Side.CLIENT)
    public static class RenderFactoryTeslamentalShot implements IRenderFactory<EntityTeslamentalShot> {
        
        @Override
        public RenderTeslamentalShot createRenderFor (RenderManager manager) {
            
            return new RenderTeslamentalShot(manager);
        }
    }
}
