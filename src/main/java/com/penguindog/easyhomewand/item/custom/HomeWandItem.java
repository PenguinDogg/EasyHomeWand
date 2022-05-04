package com.penguindog.easyhomewand.item.custom;

import com.penguindog.easyhomewand.effect.ModEffects;
import com.penguindog.easyhomewand.sound.ModSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class HomeWandItem extends Item {

    public HomeWandItem(Properties pProperties) {
        super(pProperties);
    }

    // Left Click (go-to home)
    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {

        if(stack.hasTag() && !entity.hasEffect(ModEffects.TELEPORTED.get())){
            MobEffectInstance effect = new MobEffectInstance(ModEffects.TELEPORTED.get(), 6000, -1);
            entity.addEffect(effect);
            CompoundTag tag = stack.getTag();
            entity.level.playSound((Player)entity, tag.getDouble("x"),
                    tag.getDouble("y"), tag.getDouble("z"), ModSounds.TELEPORT_HOME.get(),
                    SoundSource.PLAYERS, 1f, 1f);
            entity.setPos(new Vec3(
                    tag.getDouble("x"),
                    tag.getDouble("y"),
                    tag.getDouble("z")
            ));
            entity.setXRot(tag.getFloat("rot_x"));
            entity.setYRot(tag.getFloat("rot_y"));
        } else if(stack.hasTag() && entity.hasEffect(ModEffects.TELEPORTED.get())){
            entity.playSound(ModSounds.ERROR.get(), 0.5f, 0f);
            msg("You are on cooldown", entity);
        } else {
            entity.playSound(ModSounds.ERROR.get(), 0.5f, 0f);
            msg("No home has been set", entity);
        }
        return super.onEntitySwing(stack, entity);
    }

    @Override
    public boolean isFoil(ItemStack pStack){
        return pStack.hasTag();
    }

    private void msg(String msg, LivingEntity entity){
        if(entity.level.isClientSide){
            entity.sendMessage(new TranslatableComponent(msg), entity.getUUID());
        }
    }

    // Right Click (set home)
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        CompoundTag nbtData = new CompoundTag();
        if(pPlayer.isOnGround()){
            nbtData.putDouble("x", pPlayer.position().x);
            nbtData.putDouble("y", pPlayer.position().y);
            nbtData.putDouble("z", pPlayer.position().z);
            nbtData.putFloat("rot_x", pPlayer.getRotationVector().x);
            nbtData.putFloat("rot_y", pPlayer.getRotationVector().y);
            pPlayer.getItemInHand(pUsedHand).setTag(nbtData);
            pLevel.playSound(pPlayer, pPlayer, ModSounds.SET_HOME.get(), SoundSource.PLAYERS, 0.5f, 1f);
            msg("Home has been set at player coordinates", pPlayer);
        } else {
            pLevel.playSound(pPlayer, pPlayer, ModSounds.ERROR.get(), SoundSource.PLAYERS, 0.5f, 1f);
            msg("Must be grounded in order to set home", pPlayer);
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvenced){
        if(pStack.hasTag()){
            pTooltipComponents.add(new TranslatableComponent("Home set"));
        } else {
            pTooltipComponents.add(new TranslatableComponent("Home has not been set"));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvenced);

    }
}
