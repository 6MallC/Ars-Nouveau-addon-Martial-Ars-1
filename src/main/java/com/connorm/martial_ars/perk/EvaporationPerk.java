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
import net.minecraft.world.entity.LivingEntity;

public class EvaporationPerk extends Perk implements IEffectResolvePerk {
    public static final EvaporationPerk INSTANCE = new EvaporationPerk(MartialArs.prefix("thread_evaporation"));

    public EvaporationPerk(ResourceLocation key) {
        super(key);
    }
    @Override
    public void onSpellCast(SpellCastEvent spellCastEvent, PerkInstance perkInstance) {
        LivingEntity caster = spellCastEvent.context.getUnwrappedCaster();
        int level = PerkUtil.countForPerk(INSTANCE, caster);


            if (caster.hasEffect(ModPotions.SOAKED_EFFECT)){
                caster.removeEffect(ModPotions.SOAKED_EFFECT);
                caster.addEffect(new MobEffectInstance(ModPotions.IMMOLATE_EFFECT, 20 * 5, level - 1));

            }

    }
    @Override
    public String getLangName() {
        return "Evaporation";
    }

    @Override
    public String getLangDescription() {
        return "If the user is Soaked, Upon casting a spell they will gain the Evaporate effect which boosts Water damage based on the level of the thread.";
    }
}
