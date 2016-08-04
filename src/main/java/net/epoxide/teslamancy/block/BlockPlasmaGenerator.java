package net.epoxide.teslamancy.block;

import net.epoxide.teslamancy.libs.Constants;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

public class BlockPlasmaGenerator extends Block {

    public BlockPlasmaGenerator () {

        super(Material.IRON);
        this.setRegistryName(new ResourceLocation(Constants.MODID, "plasmaGenerator"));
        this.setUnlocalizedName(Constants.MOD_NAME + ".plasmaGenerator");
    }
}
