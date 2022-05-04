package com.penguindog.easyhomewand;

import com.penguindog.easyhomewand.effect.ModEffects;
import com.penguindog.easyhomewand.item.ModItems;
import com.penguindog.easyhomewand.sound.ModSounds;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EasyHomeWand.MOD_ID)
public class EasyHomeWand
{
    public static final String MOD_ID = "easyhomewand";

    public EasyHomeWand()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModSounds.register(eventBus);
        ModEffects.register(eventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
}
