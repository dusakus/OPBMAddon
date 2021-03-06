package com.haito.opbmaddon;

import com.haito.opbmaddon.handler.ConfigHandler;
import com.haito.opbmaddon.init.ModBlocks;
import com.haito.opbmaddon.init.ModItems;
import com.haito.opbmaddon.init.ModTileEntity;
import com.haito.opbmaddon.init.Recipes;
import com.haito.opbmaddon.network.NetworkHandler;
import com.haito.opbmaddon.proxy.IProxy;
import com.haito.opbmaddon.refference.MainRef;
import com.haito.opbmaddon.tileEntity.TEUnholyPylon;
import com.haito.opbmaddon.utility.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;


@Mod(modid = MainRef.MOD_ID, name = MainRef.MOD_NAME, version = MainRef.VERSION, guiFactory = MainRef.GUI_FACTORY)
public class Main {

    @Mod.Instance(MainRef.MOD_ID)
    public static Main mainInstance;

    @SidedProxy(serverSide = MainRef.SERVER_PROXY,clientSide = MainRef.CLIENT_PROXY,modId = MainRef.MOD_ID)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e){
        ConfigHandler.init(e.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigHandler());

        ModItems.init();
        ModBlocks.init();
        NetworkHandler.init();

        LogHelper.info("Pre-Initialization has succeded! Yep, no joking it's almost ready :>");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e){
        LogHelper.info("Mod has loaded correctly... Probably.");
        Recipes.init();
        ModTileEntity.init();
        proxy.initRenderStuff();
        GameRegistry.registerTileEntity(TEUnholyPylon.class,"sampleTile");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e){

    }
}
