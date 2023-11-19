package com.namelesslk.efrenderfix;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {

    public final static Map<Item,Float> modelMap = new HashMap<>();
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> SHIELD_ITEM = BUILDER
            .comment("items considered as shield", "example: minecraft:bow")
            .defineListAllowEmpty("shield_items", ArrayList::new, Config::validateItemName);

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> BOW_ITEM = BUILDER
            .comment("items considered as bow", "example: minecraft:bow")
            .defineListAllowEmpty("bow_items", ArrayList::new, Config::validateItemName);

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> CROSSBOW_ITEM = BUILDER
            .comment("items considered as crossbow", "example: minecraft:bow")
            .defineListAllowEmpty("crossbow_items", ArrayList::new, Config::validateItemName);

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> TRIDENT_ITEM = BUILDER
            .comment("items considered as trident", "example: minecraft:bow")
            .defineListAllowEmpty("trident_items", ArrayList::new, Config::validateItemName);

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> KATANA_ITEM = BUILDER
            .comment("items considered as katana", "example: minecraft:bow 0")
            .defineListAllowEmpty("katana_items", ArrayList::new, obj -> true);

    public static final ForgeConfigSpec SPEC = BUILDER.build();

    private static boolean validateItemName(final Object obj)
    {
        return obj instanceof final String itemName && ForgeRegistries.ITEMS.containsKey(new ResourceLocation(itemName));
    }
    public static void load(){
        List<? extends String> katanaitem = Config.KATANA_ITEM.get();
        for(String katana : katanaitem){
            String[] entry = katana.split(" ");
            Item katanaItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(entry[0]));
            modelMap.put(katanaItem, Float.parseFloat(entry[1]));
        }
    }

}
