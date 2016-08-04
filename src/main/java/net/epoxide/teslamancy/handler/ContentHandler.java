package net.epoxide.teslamancy.handler;

import net.epoxide.teslamancy.block.BlockInductionPad;
import net.epoxide.teslamancy.block.BlockPlasmaGenerator;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ContentHandler {

    public static Block blockPlasmaGenerator;
    public static Block blockInductionPad;

    public static void init () {

        initBlock();
        initItem();
    }

    private static void initBlock () {
        blockPlasmaGenerator = registerBlock(new BlockPlasmaGenerator());
        blockInductionPad = registerBlock(new BlockInductionPad());
    }

    private static Block registerBlock (Block block) {
        GameRegistry.register(block);
        return block;
    }

    private static void initItem () {

    }
}
