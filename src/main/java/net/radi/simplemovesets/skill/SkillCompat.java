package net.radi.simplemovesets.skill;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;


import net.radi.simplemovesets.SimpleMovesets;
import net.radi.simplemovesets.item.ModItems;
import net.radi.simplemovesets.world.capabilities.item.ModWeaponCategories;
import yesman.epicfight.api.client.forgeevent.WeaponCategoryIconRegisterEvent;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.api.forgeevent.SkillBuildEvent.ModRegistryWorker.SkillCreateEvent;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.skill.guard.ParryingSkill;
import yesman.epicfight.skill.identity.MeteorSlamSkill;
import yesman.epicfight.skill.passive.EmergencyEscapeSkill;
import yesman.epicfight.skill.passive.SwordmasterSkill;

import java.util.List;

@Mod.EventBusSubscriber(modid = SimpleMovesets.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SkillCompat {
    public static void forceGuard(SkillBuildEvent bus) {
    }
    @SubscribeEvent
    public static void onGuardSkillCreate(SkillCreateEvent<GuardSkill.Builder> event) {
        System.out.println("[RapierCompatSkills] Skill being builded: " + event.getRegistryName());
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","guard"))) {
            GuardSkill.Builder builder = event.getSkillBuilder();
            builder.addGuardMotion(ModWeaponCategories.SABER, (item, player) -> {
                return Animations.LONGSWORD_GUARD_HIT;
            }).addGuardBreakMotion(ModWeaponCategories.SABER, (item, player) -> {
                return Animations.BIPED_COMMON_NEUTRALIZED;
            });
        }
    }

    @SubscribeEvent
    public static void onParrySkillCreate(SkillCreateEvent<ParryingSkill.Builder> event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","parrying"))) {
            GuardSkill.Builder builder = event.getSkillBuilder();
            builder.addGuardMotion(ModWeaponCategories.SABER, (item, player) -> {
                return Animations.LONGSWORD_GUARD_HIT;
            }).addGuardBreakMotion(ModWeaponCategories.SABER, (item, player) -> {
                return Animations.BIPED_COMMON_NEUTRALIZED;
            }).addAdvancedGuardMotion(ModWeaponCategories.SABER, (item, player) -> {
                return List.of(Animations.LONGSWORD_GUARD_ACTIVE_HIT1, Animations.LONGSWORD_GUARD_ACTIVE_HIT2);
            });
        }
    }

    @SubscribeEvent
    public static void onScapeSkillCreate(SkillCreateEvent<EmergencyEscapeSkill.Builder> event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","emergency_escape"))) {
            EmergencyEscapeSkill.Builder builder = event.getSkillBuilder();
            builder.addAvailableWeaponCategory(ModWeaponCategories.SABER);
        }
    }

    @SubscribeEvent
    public static void onMeteorSkillCreate(SkillCreateEvent<MeteorSlamSkill.Builder> event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","meteor_slam"))) {
            MeteorSlamSkill.Builder builder = event.getSkillBuilder();
            builder.addSlamMotion(ModWeaponCategories.SABER, (item, player) -> Animations.METEOR_SLAM);
        }
    }

    @SubscribeEvent
    public static void onSwordSkillCreate(SkillCreateEvent<SwordmasterSkill.Builder> event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","swordmaster"))) {
            SwordmasterSkill.Builder builder = event.getSkillBuilder();
            builder.addAvailableWeaponCategory(ModWeaponCategories.SABER);
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onIconCreate(WeaponCategoryIconRegisterEvent icon){
        icon.registerCategory(ModWeaponCategories.SABER, new ItemStack(ModItems.IRON_SABER.get()));
    }
}