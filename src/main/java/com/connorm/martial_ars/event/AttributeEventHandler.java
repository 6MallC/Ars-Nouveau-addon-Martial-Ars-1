package com.connorm.martial_ars.event;
import com.connorm.martial_ars.registry.MartialRegistry;
import com.connorm.martial_ars.util.MartialTags;
import com.hollingsworth.arsnouveau.api.spell.SpellSchool;
import com.hollingsworth.arsnouveau.api.spell.SpellSchools;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class AttributeEventHandler {

    // credit to AlexThw and SauceLib as most of the raw code for the potency attributes was taken from there
    public static final Map<SpellSchool, Holder<Attribute>> schoolToEffectAttribute = new ConcurrentHashMap<>();

    public static void linkSchoolToAttribute(SpellSchool school, Holder<Attribute> potencyAttribute) {
        if (potencyAttribute != null)
            schoolToEffectAttribute.put(school, potencyAttribute);
    }

    static {
        linkSchoolToAttribute(SpellSchools.ELEMENTAL_EARTH, MartialRegistry.EARTH_POTENCY);
        linkSchoolToAttribute(SpellSchools.ELEMENTAL_WATER, MartialRegistry.WATER_POTENCY);
        linkSchoolToAttribute(SpellSchools.ELEMENTAL_AIR, MartialRegistry.AIR_POTENCY);
        linkSchoolToAttribute(SpellSchools.ELEMENTAL_FIRE, MartialRegistry.FIRE_POTENCY);
        linkSchoolToAttribute(SpellSchools.ELEMENTAL, MartialRegistry.ELEMENTAL_POTENCY);
    }


   @SubscribeEvent
   public static void onPotionAdd(MobEffectEvent.Added event) {

       LivingEntity living = event.getEntity();
       Set<SpellSchool> schools;
       if (living.getAttribute(MartialRegistry.ELEMENTAL_POTENCY) != null) {
           schools = new HashSet<>();
           for (Map.Entry<SpellSchool, Holder<Attribute>> entry : schoolToEffectAttribute.entrySet()) {
               SpellSchool school = entry.getKey();
               List<TagKey<MobEffect>> tags = MartialTags.Effects.SCHOOL_TO_EFFECT_TYPES.getOrDefault(school, List.of());
               if (tags.stream().anyMatch(tag -> event.getEffectInstance().getEffect().is(tag))) {
                   schools.add(school);
                   if (school != SpellSchools.ELEMENTAL)
                       schools.addAll(school.getSubSchools());
               }
           }
           for (SpellSchool school : schools) {
               Holder<Attribute> attribute = schoolToEffectAttribute.get(school);
               if (attribute != null) {
                   AttributeInstance attrInstance = living.getAttribute(attribute);
                   if (attrInstance != null) {
                       double potency = attrInstance.getValue();
                       if (potency != 0) {
                           MobEffectInstance instance = event.getEffectInstance();

                           if (instance.getDuration() != 0) {
                               instance.duration *= (1.0 + (potency / 100.0));
                               // reminder to check your math
                           }
                           return;
                       }
                   }
               }
           }
       }
   }
}