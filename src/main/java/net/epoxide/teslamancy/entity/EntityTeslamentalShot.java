package net.epoxide.teslamancy.entity;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityShulkerBullet;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityTeslamentalShot extends EntityShulkerBullet {
    
    private EntityLivingBase owner;
    
    public EntityTeslamentalShot(World worldIn) {
        
        super(worldIn);
    }
    
    public EntityTeslamentalShot(World worldIn, EntityLivingBase ownerIn, Entity targetIn, EnumFacing.Axis direction) {
        
        super(worldIn, ownerIn, targetIn, direction);
        this.owner = ownerIn;
    }
    
    @Override
    public void onUpdate () {
        
        if (this.isInsideOfMaterial(Material.WATER) || (this.owner != null && this.owner.isDead))
            this.setDead();
            
        super.onUpdate();
    }
    
    @Override
    protected void bulletHit (RayTraceResult result) {
        
        if (result.entityHit == null) {
            
            ((WorldServer) this.worldObj).spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX, this.posY, this.posZ, 2, 0.2D, 0.2D, 0.2D, 0.0D, new int[0]);
            this.playSound(SoundEvents.ENTITY_SHULKER_BULLET_HIT, 1.0F, 1.0F);
        }
        
        else {
            
            final boolean flag = result.entityHit.attackEntityFrom(DamageSource.causeIndirectDamage(this, this.owner).setProjectile(), 4.0F);
            
            if (flag) {
                
                this.applyEnchantments(this.owner, result.entityHit);
                
                if (result.entityHit instanceof EntityLivingBase) {
                    
                    ((EntityLivingBase) result.entityHit).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100));
                    ((EntityLivingBase) result.entityHit).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200));
                    ((EntityLivingBase) result.entityHit).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100));
                }
            }
        }
        
        this.setDead();
    }
}
