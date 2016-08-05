package net.epoxide.teslamancy.client;

import net.epoxide.teslamancy.common.CommonProxy;
import net.epoxide.teslamancy.handler.ContentHandler;
import net.epoxide.teslamancy.libs.Constants;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
    private static ModelResourceLocation fluidLocation = new ModelResourceLocation(Constants.MODID + ":primal_plasma", "fluid");

    @Override
    public void preInit () {
        super.preInit();

        Item fluid = Item.getItemFromBlock(ContentHandler.blockPrimalPlasma);
        ModelBakery.registerItemVariants(fluid);

        ModelLoader.setCustomMeshDefinition(fluid, stack -> fluidLocation);

        ModelLoader.setCustomStateMapper(ContentHandler.blockPrimalPlasma, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation (IBlockState state) {
                return fluidLocation;
            }
        });

        MinecraftForge.EVENT_BUS.register(new RenderEntityShield());
    }
}
