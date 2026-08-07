package com.connorm.martial_ars.event;

import com.alexthw.sauce.registry.ModRegistry;
import com.alexthw.sauce.registry.SauceTags;
import com.connorm.martial_ars.registry.MartialRegistry;
import com.connorm.martial_ars.util.MartialTags;
import com.hollingsworth.arsnouveau.api.perk.PerkAttributes;
import com.hollingsworth.arsnouveau.api.spell.SpellSchool;
import com.hollingsworth.arsnouveau.api.spell.SpellSchools;
import com.hollingsworth.arsnouveau.api.util.PerkUtil;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
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

    public static void linkSchoolToAttribute(SpellSchool school, Holder<Attribute> powerAttribute, Holder<Attribute> defenseAttribute, Holder<Attribute> discountAttribute) {
        if (powerAttribute != null)
            schoolToEffectAttribute.put(school, powerAttribute);
    }

    @SubscribeEvent
    public static void onPotionAdd(MobEffectEvent.Added event) {
        LivingEntity living = event.getEntity();
        Set<SpellSchool> schools = null;
        if (living.getAttribute(MartialRegistry.ELEMENTAL_POTENCY) != null) {
            schools = new HashSet<>();
            for (Map.Entry<SpellSchool, Holder<Attribute>> entry : schoolToEffectAttribute.entrySet()) {
                SpellSchool school = entry.getKey();
                List<TagKey<MobEffect>> tags = MartialTags.Effects.SCHOOL_TO_EFFECT_TYPES.getOrDefault(school, List.of());
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
                        event.getEffectInstance().duration *= (float) (1 + (potency / 100.0));
                        // reminder to check your math
                    }

                }


        /* LivingEntity target = event.getEntity();
        Entity applier = event.getEffectSource();
        if (target.level().isClientSide)
            return;
        double bonus = 0.0;
        Holder<MobEffect> holder = event.getEffectInstance().getEffect();
        MobEffect effect = holder.value();
        if (effect.isBeneficial()) {
            bonus = PerkUtil.valueOrZero(target, PerkAttributes.WIXIE);
        } else if (applier instanceof LivingEntity living) {
            bonus = PerkUtil.valueOrZero(living, PerkAttributes.WIXIE);
        }

        if (bonus > 0.0) {
            event.getEffectInstance().duration = (int) (event.getEffectInstance().getDuration() * bonus);
        }

            }
*/
            }
        }
    }
}


