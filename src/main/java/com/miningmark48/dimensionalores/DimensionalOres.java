package com.miningmark48.dimensionalores;

import com.miningmark48.dimensionalores.handler.ConfigurationHandler;
import com.miningmark48.dimensionalores.init.*;
import com.miningmark48.dimensionalores.proxy.CommonProxy;
import com.miningmark48.dimensionalores.reference.Reference;
import com.miningmark48.mininglib.utility.ModLogger;
import com.miningmark48.mininglib.utility.ModTranslate;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid=Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS, dependencies = Reference.DEPENDENCIES)
public class DimensionalOres {
    @Mod.Instance(Reference.MOD_ID)
    public static DimensionalOres instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());

        ModItems.init();
        ModBlocks.init();
        ModRegistry.init();
        MinecraftForge.EVENT_BUS.register(new ModRegistry());

        ModLogger.info(ModTranslate.toLocal("log.info.preinit"));
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        proxy.registerRenders();

        GameRegistry.registerWorldGenerator(new WorldGen(), 0);

        Recipes.init();

        ModOreDict.registerOreNames();

        ModLogger.info(ModTranslate.toLocal("log.info.init"));

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        ModLogger.info(ModTranslate.toLocal("log.info.postinit"));
    }
}