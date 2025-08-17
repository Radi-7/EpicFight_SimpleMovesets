package net.radi.simplemovesets.gameasset;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

import net.radi.simplemovesets.SimpleMovesets;
import net.radi.simplemovesets.skill.weaponinnate.RiposteSkill;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.world.damagesource.ExtraDamageInstance;

import java.util.Set;

@Mod.EventBusSubscriber(modid = SimpleMovesets.MODID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class ModSkills {
    public static Skill RIPOSTE;

    @SubscribeEvent
    public static void buildSkillEvent(SkillBuildEvent build) {
        SkillBuildEvent.ModRegistryWorker modRegistry = build.createRegistryWorker(SimpleMovesets.MODID);

        WeaponInnateSkill riposte = modRegistry.build("riposte", RiposteSkill::new, WeaponInnateSkill.createWeaponInnateBuilder());
        riposte
                .newProperty()
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.6F))
                .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()));

        RIPOSTE = riposte;
    }

    public static void registerModSkills(RegisterEvent bus) {
    }
}
