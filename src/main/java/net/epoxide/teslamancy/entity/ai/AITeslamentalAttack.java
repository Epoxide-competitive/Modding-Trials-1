package net.epoxide.teslamancy.entity.ai;

import net.epoxide.teslamancy.entity.EntityTeslamental;
import net.epoxide.teslamancy.entity.EntityTeslamentalShot;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.EnumFacing.Axis;

public class AITeslamentalAttack extends EntityAIBase {
    
    private final EntityTeslamental teslamental;
    private int attackTime;
    
    public AITeslamentalAttack(EntityTeslamental teslamental) {
        
        this.teslamental = teslamental;
        this.setMutexBits(3);
    }
    
    @Override
    public boolean shouldExecute () {
        
        final EntityLivingBase target = this.teslamental.getAttackTarget();
        return target != null && target.isEntityAlive();
    }
    
    @Override
    public void startExecuting () {
        
        this.attackTime = 0;
    }
    
    @Override
    public void updateTask () {
        
        final EntityLivingBase target = this.teslamental.getAttackTarget();
        
        if (target != null && !target.isDead) {
            
            this.attackTime++;
            
            final double distance = this.teslamental.getDistanceSqToEntity(target);
            
            if (distance < 4.0D && this.attackTime < 50) {
                
                if (this.attackTime <= 0) {
                    
                    this.attackTime += 20;
                    this.teslamental.attackEntityAsMob(target);
                }
                
                this.teslamental.getMoveHelper().setMoveTo(target.posX, target.posY, target.posZ, 1.0D);
            }
            
            else if (distance < 256.0D && this.attackTime > 140) {
                
                for (final Axis axis : Axis.values())
                    this.teslamental.worldObj.spawnEntityInWorld(new EntityTeslamentalShot(this.teslamental.worldObj, this.teslamental, target, axis));
                    
                this.teslamental.getLookHelper().setLookPositionWithEntity(target, 10.0F, 10.0F);
                this.attackTime = 0;
            }
            
            else {
                
                this.teslamental.getNavigator().clearPathEntity();
                this.teslamental.getMoveHelper().setMoveTo(target.posX, target.posY, target.posZ, 1.0D);
            }
        }
        
        super.updateTask();
    }
}