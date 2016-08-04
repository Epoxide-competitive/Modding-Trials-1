package net.epoxide.teslamancy.block;

import net.epoxide.teslamancy.libs.Constants;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

public class BlockInductionPad extends Block {

    public BlockInductionPad () {

        super(Material.IRON);
        this.setRegistryName(new ResourceLocation(Constants.MODID, "inductionPad"));
        this.setUnlocalizedName(Constants.MOD_NAME + ".inductionPad");
    }
}
