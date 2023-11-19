package com.namelesslk.efrenderfix.client;

import com.namelesslk.efrenderfix.Config;
import com.namelesslk.efrenderfix.common.Items;
import com.namelesslk.efrenderfix.main.efrenderfix;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import yesman.epicfight.api.client.forgeevent.PatchedRenderersEvent;
import yesman.epicfight.client.renderer.patched.item.RenderBow;
import yesman.epicfight.client.renderer.patched.item.RenderCrossbow;
import yesman.epicfight.client.renderer.patched.item.RenderItemBase;
import yesman.epicfight.client.renderer.patched.item.RenderTrident;

import java.util.List;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid= efrenderfix.MODID, value=Dist.CLIENT, bus=EventBusSubscriber.Bus.MOD)
public class ClientModBusEvent {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void RenderRegistry(final PatchedRenderersEvent.Add event) {
        //bow
        List<? extends String> bowitem = Config.BOW_ITEM.get();
        for(String bow : bowitem){
            Item bowItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(bow));
            event.addItemRenderer(bowItem, new RenderBow());
        }

        //crossbow
        List<? extends String> crossbowitem = Config.CROSSBOW_ITEM.get();
        for(String crossbow : crossbowitem){
            Item crossbowItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(crossbow));
            event.addItemRenderer(crossbowItem, new RenderCrossbow());
        }

        //shield
        List<? extends String> shielditem = Config.SHIELD_ITEM.get();
        for(String shield : shielditem){
            Item shieldItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(shield));
            event.addItemRenderer(shieldItem, new RenderItemBase());
        }

        //trident
        List<? extends String> tridentitem = Config.TRIDENT_ITEM.get();
        for(String trident : tridentitem){
            Item tridentItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(trident));
            event.addItemRenderer(tridentItem, new RenderTrident());
        }


        //sheath
        List<? extends String> katanaitem = Config.KATANA_ITEM.get();
        for(String katana : katanaitem){
            String[] entry = katana.split(" ");
            Item katanaItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(entry[0]));
            event.addItemRenderer(katanaItem, new RenderCustomKatana());
        }
    }

    @SubscribeEvent
    public static void propertyOverrideRegistry(FMLClientSetupEvent event){
        event.enqueueWork(() -> ItemProperties.register(Items.CUSTOM_SHEATH.get(),
                new ResourceLocation(efrenderfix.MODID,"custom"),
                (Stack, World, Entity, i) -> {
                    if (Entity != null) {
                        return Config.modelMap.get(Entity.getItemInHand(InteractionHand.MAIN_HAND).getItem());
                    }
                    return 0;
                }));
    }
}
