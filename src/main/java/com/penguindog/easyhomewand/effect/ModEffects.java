package com.penguindog.easyhomewand.effect;

import com.penguindog.easyhomewand.EasyHomeWand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, EasyHomeWand.MOD_ID);

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }

    public static final RegistryObject<MobEffect> TELEPORTED = MOB_EFFECTS.register("teleported", () -> new TeleportEffect(MobEffectCategory.NEUTRAL, 3124687));
}
