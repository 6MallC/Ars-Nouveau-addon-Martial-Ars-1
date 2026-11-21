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

public class ElectrocutionPerk extends Perk implements IEffectResolvePerk {
    public static final ElectrocutionPerk INSTANCE = new ElectrocutionPerk(MartialArs.prefix("thread_electrocution"));

    public ElectrocutionPerk(ResourceLocation key) {
        super(key);
    }
    @Override
    public void onSpellCast(SpellCastEvent spellCastEvent, PerkInstance perkInstance) {
        LivingEntity caster = spellCastEvent.context.getUnwrappedCaster();
        int level = PerkUtil.countForPerk(INSTANCE, caster);


        if (caster.hasEffect(ModPotions.SHOCKED_EFFECT)){
            caster.removeEffect(ModPotions.SHOCKED_EFFECT);
            caster.addEffect(new MobEffectInstance(ModPotions.IMMOLATE_EFFECT, 20 * 5, level - 1));

        }

    }
    @Override
    public String getLangName() {
        return "Electrocution";
    }

    @Override
    public String getLangDescription() {
        return "If the user is Shocked, Upon casting a spell they will gain the Electrocute effect which boosts Lightning damage based on the level of the thread.";
    }
}
