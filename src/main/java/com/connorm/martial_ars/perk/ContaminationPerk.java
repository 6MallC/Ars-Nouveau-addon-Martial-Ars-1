package com.connorm.martial_ars.perk;

import com.connorm.martial_ars.MartialArs;
import com.hollingsworth.arsnouveau.api.event.SpellCastEvent;
import com.hollingsworth.arsnouveau.api.perk.IEffectResolvePerk;
import com.hollingsworth.arsnouveau.api.perk.Perk;
import com.hollingsworth.arsnouveau.api.perk.PerkInstance;
import com.hollingsworth.arsnouveau.api.util.PerkUtil;
import com.hollingsworth.arsnouveau.setup.registry.ModPotions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class ContaminationPerk extends Perk implements IEffectResolvePerk {
    public static final ContaminationPerk INSTANCE = new ContaminationPerk(MartialArs.prefix("thread_contamination"));

    public ContaminationPerk(ResourceLocation key) {
        super(key);
    }
    @Override
    public void onSpellCast(SpellCastEvent spellCastEvent, PerkInstance perkInstance) {
        LivingEntity caster = spellCastEvent.context.getUnwrappedCaster();
        int level = PerkUtil.countForPerk(INSTANCE, caster);

        if (caster.hasEffect(MobEffects.POISON)) {
            caster.removeEffect(MobEffects.POISON);
            caster.addEffect(new MobEffectInstance(ModPotions.IMMOLATE_EFFECT, 20 * 5, level - 1));
        }
        if (caster.hasEffect(alexthw.ars_elemental.registry.ModPotions.VENOM)){
            caster.removeEffect(alexthw.ars_elemental.registry.ModPotions.VENOM);
            caster.addEffect(new MobEffectInstance(ModPotions.IMMOLATE_EFFECT, 20 * 5, level + 1));


        }
    }
    @Override
    public String getLangName() {
        return "Contamination";
    }

    @Override
    public String getLangDescription() {
        return "If the user is Poisoned or Envenomed, Upon casting a spell they will gain the Contaminated effect which boosts Poison damage based on the level of the thread.";
    }
}
