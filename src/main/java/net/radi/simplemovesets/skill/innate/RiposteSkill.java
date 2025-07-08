package net.radi.simplemovesets.skill.innate;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import net.radi.simplemovesets.gameasset.ModAnimations;

import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.DodgeAnimation;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.effect.EpicFightMobEffects;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener.EventType;

public class RiposteSkill extends WeaponInnateSkill {
    private static final UUID EVENT_UUID = UUID.fromString("1f6aea85-2194-4761-af8e-1a5c99c4f414");
    public final AssetAccessor<? extends DodgeAnimation> first;
    public final AssetAccessor<? extends AttackAnimation> second;

    public RiposteSkill(SkillBuilder<? extends WeaponInnateSkill> builder) {
        super(builder);
        this.first = Animations.BIPED_STEP_BACKWARD;
        this.second = Animations.RUSHING_TEMPO1;
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);
        container.getExecutor().getEventListener().addEventListener(EventType.DODGE_SUCCESS_EVENT, EVENT_UUID, (event) -> {
            event.getPlayerPatch().reserveAnimation(this.second);
            ((ServerPlayer)container.getExecutor().getOriginal()).addEffect(new MobEffectInstance((MobEffect) EpicFightMobEffects.STUN_IMMUNITY.get(), 38, 0, true, false, false));
        });
    }

    @Override
    public void onRemoved(SkillContainer container) {
        container.getExecutor().getEventListener().removeListener(EventType.DODGE_SUCCESS_EVENT, EVENT_UUID);
    }

    @Override
    public void executeOnServer(SkillContainer container, FriendlyByteBuf args) {
        container.getExecutor().playAnimationSynchronized(this.first, 0);
    }

    @Override
    public WeaponInnateSkill registerPropertiesToAnimation() {
        return this;
    }
}