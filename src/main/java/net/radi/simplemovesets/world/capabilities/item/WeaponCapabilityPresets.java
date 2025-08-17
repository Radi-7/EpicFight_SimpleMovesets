package net.radi.simplemovesets.world.capabilities.item;


import java.util.Map;
import java.util.function.Function;

import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.radi.simplemovesets.SimpleMovesets;
import net.radi.simplemovesets.gameasset.ModAnimations;
import net.radi.simplemovesets.gameasset.ModSkills;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.CapabilityItem.Styles;
import yesman.epicfight.world.capabilities.item.WeaponCapability;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

@Mod.EventBusSubscriber(modid = SimpleMovesets.MODID , bus = Mod.EventBusSubscriber.Bus.MOD)
public class WeaponCapabilityPresets {
    public static final Function<Item, CapabilityItem.Builder> SABER = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder()
                .category(ModWeaponCategories.SABER) // Updated to use custom category
                .styleProvider((playerpatch) -> Styles.TWO_HAND)
                .collider(ColliderPreset.LONGSWORD)
                .canBePlacedOffhand(false)
                .newStyleCombo(Styles.TWO_HAND, ModAnimations.SABER_AUTO1, ModAnimations.SABER_AUTO2, ModAnimations.SABER_AUTO3, ModAnimations.SABER_DASH, Animations.LONGSWORD_AIR_SLASH)
                .innateSkill(Styles.TWO_HAND, (itemstack) -> ModSkills.RIPOSTE)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.BLOCK, Animations.UCHIGATANA_GUARD)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.IDLE, ModAnimations.SABER_IDLE)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.WALK, ModAnimations.SABER_WALK)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.RUN, ModAnimations.SABER_RUN)
                .newStyleCombo(Styles.MOUNT, Animations.SWORD_MOUNT_ATTACK);

        if (item instanceof TieredItem tieredItem) {
            builder.hitSound(tieredItem.getTier() == Tiers.WOOD ? EpicFightSounds.BLUNT_HIT.get() : EpicFightSounds.BLADE_HIT.get());
            builder.hitParticle(tieredItem.getTier() == Tiers.WOOD ? EpicFightParticles.HIT_BLUNT.get() : EpicFightParticles.HIT_BLADE.get());
        }

        return builder;
    };

    @SubscribeEvent
    public static void registerMovesets(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(SimpleMovesets.MODID,"saber"), SABER);
    }
}