package com.penguindog.easyhomewand.sound;

import com.penguindog.easyhomewand.EasyHomeWand;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, EasyHomeWand.MOD_ID);

    public static final RegistryObject<SoundEvent> SET_HOME = registerSoundEvent("set_home");
    public static final RegistryObject<SoundEvent> TELEPORT_HOME = registerSoundEvent("teleport_home");
    public static final RegistryObject<SoundEvent> ERROR = registerSoundEvent("teleport_error");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name){
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(EasyHomeWand.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }
}
