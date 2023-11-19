package com.namelesslk.efrenderfix.common;

import com.namelesslk.efrenderfix.main.efrenderfix;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Items {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, efrenderfix.MODID);
    public static final RegistryObject<Item> CUSTOM_SHEATH = ITEMS.register("custom_sheath", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
}
