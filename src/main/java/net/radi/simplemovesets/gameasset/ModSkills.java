package net.radi.simplemovesets.gameasset;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

import net.radi.simplemovesets.SimpleMovesets;
import net.radi.simplemovesets.skill.innate.RiposteSkill;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.skill.Skill;

@Mod.EventBusSubscriber(modid = SimpleMovesets.MODID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class ModSkills {
    public static Skill RIPOSTE;

    @SubscribeEvent
    public static void buildSkillEvent(SkillBuildEvent build) {
        SkillBuildEvent.ModRegistryWorker modRegistry = build.createRegistryWorker(SimpleMovesets.MODID);

        WeaponInnateSkill riposte = modRegistry.build("riposte", RiposteSkill::new, WeaponInnateSkill.createWeaponInnateBuilder());
        riposte
                .newProperty();
        RIPOSTE = riposte;
    }

    public static void registerModSkills(RegisterEvent bus) {
    }
}
