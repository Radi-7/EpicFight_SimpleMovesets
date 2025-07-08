package net.radi.simplemovesets.gameasset;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;


import net.radi.simplemovesets.SimpleMovesets;
import yesman.epicfight.api.animation.AnimationClip;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.AnimationManager.AnimationRegistryEvent;
import yesman.epicfight.api.animation.AnimationManager.AnimationAccessor;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty.*;
import yesman.epicfight.api.animation.property.MoveCoordFunctions;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.utils.HitEntityList;
import yesman.epicfight.api.utils.TimePairList;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.damagesource.EpicFightDamageType;
import yesman.epicfight.world.damagesource.StunType;
import yesman.epicfight.world.effect.EpicFightMobEffects;

import javax.annotation.Nullable;

public class ModAnimations {
    public static AnimationAccessor<BasicAttackAnimation> SABER_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> SABER_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> SABER_AUTO3;
    public static AnimationAccessor<DashAttackAnimation> SABER_DASH;
    public static AnimationAccessor<StaticAnimation> SABER_IDLE;
    public static AnimationAccessor<MovementAnimation> SABER_WALK;
    public static AnimationAccessor<MovementAnimation> SABER_RUN;

    @SubscribeEvent
    public static void registerAnimations(AnimationRegistryEvent event) {
        event.newBuilder(SimpleMovesets.MODID, ModAnimations::build);
    }

    public static void build(AnimationManager.AnimationBuilder builder) {
        SABER_IDLE = builder.nextAccessor("biped/living/saber_idle", (accessor) -> new StaticAnimation(true, accessor, Armatures.BIPED));
        SABER_WALK = builder.nextAccessor("biped/living/saber_walk", (accessor) -> new MovementAnimation(true, accessor, Armatures.BIPED));
        SABER_RUN = builder.nextAccessor("biped/living/saber_run", (accessor) -> new MovementAnimation(true, accessor, Armatures.BIPED));
        SABER_AUTO1 = builder.nextAccessor("biped/combat/saber_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.3F, 0.7F, 0.7F, null, Armatures.BIPED.get().toolR, accessor, Armatures.BIPED)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));
        SABER_AUTO2 = builder.nextAccessor("biped/combat/saber_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.0F, 0.1F, 0.5F, 0.7F, null, Armatures.BIPED.get().toolR, accessor, Armatures.BIPED)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));
        SABER_AUTO3 = builder.nextAccessor("biped/combat/saber_auto3", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.0F, 0.15F, 0.4F, 0.7F, null, Armatures.BIPED.get().toolR, accessor, Armatures.BIPED)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));
        SABER_DASH = builder.nextAccessor("biped/combat/saber_dash", (accessor) ->
                new DashAttackAnimation(0.2F, 0.3F, 0.35F, 0.8F, 0.8F, null, Armatures.BIPED.get().toolR, accessor, Armatures.BIPED)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.4F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, false));
    }
}
