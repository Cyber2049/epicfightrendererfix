package com.namelesslk.efrenderfix.main;

import com.namelesslk.efrenderfix.Config;
import com.namelesslk.efrenderfix.common.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(efrenderfix.MODID)
public class efrenderfix {
    public static final String MODID = "efrenderfix";


    public efrenderfix(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        Items.ITEMS.register(bus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        Config.load();
    }
}
