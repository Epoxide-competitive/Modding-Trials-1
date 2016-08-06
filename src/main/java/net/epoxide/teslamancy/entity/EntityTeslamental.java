package net.epoxide.teslamancy.entity;

import net.epoxide.teslamancy.entity.ai.AITeslamentalAttack;
import net.epoxide.teslamancy.libs.Constants;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityTeslamental extends EntityMob {
    
    public static final ResourceLocation LOOT_TABLE = LootTableList.register(new ResourceLocation(Constants.MODID, "entities/teslamental"));
    public int rotation;
    
    public EntityTeslamental(World world) {
        
        super(world);
        this.setSize(1.2F, 1.2F);
        this.isImmuneToFire = true;
        this.experienceValue = 45;
    }
    
    @Override
    protected void initEntityAI () {
        
        this.tasks.addTask(4, new AITeslamentalAttack(this));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
    }
    
    @Override
    protected void applyEntityAttributes () {
        
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(35.0D);
    }
    
    @Override
    public void onLivingUpdate () {
        
        this.rotation++;
        
        if (!this.onGround && this.motionY < 0D)
            this.motionY *= 0.6D;
            
        if (this.isInsideOfMaterial(Material.WATER)) {
            
            this.setDropItemsWhenDead(false);
            this.setDead();
        }
        
        super.onLivingUpdate();
    }
    
    @Override
    public void updateAITasks () {
        
        final EntityLivingBase target = this.getAttackTarget();
        
        if (target != null && target.posY + target.getEyeHeight() > this.posY + this.getEyeHeight()) {
            
            this.motionY += (0.3D - this.motionY) * 0.3D;
            this.isAirBorne = true;
        }
    }
    
    @Override
    public void fall (float distance, float damageMultiplier) {
    
    }
    
    @Override
    protected ResourceLocation getLootTable () {
        
        return LOOT_TABLE;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender (float partialTicks) {
        
        return 15728880;
    }
    
    @Override
    public float getBrightness (float partialTicks) {
        
        return 1.0F;
    }
}