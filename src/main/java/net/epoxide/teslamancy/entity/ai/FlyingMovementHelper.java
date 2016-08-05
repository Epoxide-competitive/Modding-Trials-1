package net.epoxide.teslamancy.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;

public class FlyingMovementHelper extends EntityMoveHelper {
    
    private final EntityLiving parentEntity;
    private int courseChangeCooldown;
    
    public FlyingMovementHelper(EntityLiving entity) {
        
        super(entity);
        this.parentEntity = entity;
    }
    
    @Override
    public void onUpdateMoveHelper () {
        
        if (this.action == EntityMoveHelper.Action.MOVE_TO) {
            
            final double d0 = this.posX - this.parentEntity.posX;
            final double d1 = this.posY - this.parentEntity.posY;
            final double d2 = this.posZ - this.parentEntity.posZ;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            
            if (this.courseChangeCooldown-- <= 0) {
                
                this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(5) + 2;
                d3 = MathHelper.sqrt_double(d3);
                
                if (this.isNotColliding(this.posX, this.posY, this.posZ, d3)) {
                    
                    this.parentEntity.motionX += d0 / d3 * 0.1D;
                    this.parentEntity.motionY += d1 / d3 * 0.1D;
                    this.parentEntity.motionZ += d2 / d3 * 0.1D;
                }
                else
                    this.action = EntityMoveHelper.Action.WAIT;
            }
        }
    }
    
    private boolean isNotColliding (double x, double y, double z, double offset) {
        
        final double d0 = (x - this.parentEntity.posX) / offset;
        final double d1 = (y - this.parentEntity.posY) / offset;
        final double d2 = (z - this.parentEntity.posZ) / offset;
        AxisAlignedBB axisalignedbb = this.parentEntity.getEntityBoundingBox();
        
        for (int i = 1; i < offset; ++i) {
            
            axisalignedbb = axisalignedbb.offset(d0, d1, d2);
            
            if (!this.parentEntity.worldObj.getCollisionBoxes(this.parentEntity, axisalignedbb).isEmpty())
                return false;
        }
        
        return true;
    }
}