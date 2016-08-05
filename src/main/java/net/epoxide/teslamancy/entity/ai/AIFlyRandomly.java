package net.epoxide.teslamancy.entity.ai;

import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;

public class AIFlyRandomly extends EntityAIBase {
    
    private final EntityLiving parentEntity;
    
    public AIFlyRandomly(EntityLiving ghast) {
        
        this.parentEntity = ghast;
        this.setMutexBits(1);
    }
    
    @Override
    public boolean shouldExecute () {
        
        final EntityMoveHelper entitymovehelper = this.parentEntity.getMoveHelper();
        
        if (!entitymovehelper.isUpdating())
            return true;
            
        else {
            
            final double d0 = entitymovehelper.getX() - this.parentEntity.posX;
            final double d1 = entitymovehelper.getY() - this.parentEntity.posY;
            final double d2 = entitymovehelper.getZ() - this.parentEntity.posZ;
            final double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            return d3 < 1.0D || d3 > 3600.0D;
        }
    }
    
    @Override
    public boolean continueExecuting () {
        
        return false;
    }
    
    @Override
    public void startExecuting () {
        
        final Random random = this.parentEntity.getRNG();
        final double d0 = this.parentEntity.posX + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
        final double d1 = this.parentEntity.posY + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
        final double d2 = this.parentEntity.posZ + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
        this.parentEntity.getMoveHelper().setMoveTo(d0, d1, d2, 1.0D);
    }
}