package net.epoxide.teslamancy.entity;

import net.epoxide.teslamancy.entity.ai.AIFlyRandomly;
import net.epoxide.teslamancy.entity.ai.AILookAround;
import net.epoxide.teslamancy.entity.ai.FlyingMovementHelper;
import net.epoxide.teslamancy.libs.Constants;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityTeslamental extends EntityFlying implements IMob {
    
    public static final ResourceLocation LOOT_TABLE = LootTableList.register(new ResourceLocation(Constants.MODID, "entities/teslamental"));
    public int rotation;
    
    public EntityTeslamental(World world) {
        
        super(world);
        this.setSize(1F, 1F);
        this.isImmuneToFire = true;
        this.experienceValue = 35;
        this.moveHelper = new FlyingMovementHelper(this);
        this.rotation = this.rand.nextInt(100000);
    }
    
    @Override
    protected void initEntityAI () {
        
        this.tasks.addTask(5, new AIFlyRandomly(this));
        this.tasks.addTask(7, new AILookAround(this));
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
    }
    
    @Override
    public void onUpdate () {
        
        super.onUpdate();
        
        ++this.rotation;
        
        if (!this.worldObj.isRemote && this.worldObj.getDifficulty() == EnumDifficulty.PEACEFUL)
            this.setDead();
    }
    
    @Override
    protected ResourceLocation getLootTable () {
        
        return LOOT_TABLE;
    }
}