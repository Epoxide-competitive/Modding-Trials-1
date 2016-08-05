package net.epoxide.teslamancy.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.MathHelper;

public class AILookAround extends EntityAIBase {
    
    private final EntityLiving parentEntity;
    
    public AILookAround(EntityLiving ghast) {
        
        this.parentEntity = ghast;
        this.setMutexBits(2);
    }
    
    @Override
    public boolean shouldExecute () {
        
        return true;
    }
    
    @Override
    public void updateTask () {
        
        if (this.parentEntity.getAttackTarget() == null) {
            
            this.parentEntity.rotationYaw = -((float) MathHelper.atan2(this.parentEntity.motionX, this.parentEntity.motionZ)) * (180F / (float) Math.PI);
            this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
        }
        
        else {
            
            final EntityLivingBase entitylivingbase = this.parentEntity.getAttackTarget();
            final double d0 = 64.0D;
            
            if (entitylivingbase.getDistanceSqToEntity(this.parentEntity) < 4096.0D) {
                
                final double d1 = entitylivingbase.posX - this.parentEntity.posX;
                final double d2 = entitylivingbase.posZ - this.parentEntity.posZ;
                this.parentEntity.rotationYaw = -((float) MathHelper.atan2(d1, d2)) * (180F / (float) Math.PI);
                this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
            }
        }
    }
}