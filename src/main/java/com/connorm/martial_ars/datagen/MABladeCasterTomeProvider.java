package com.connorm.martial_ars.datagen;

import alexthw.ars_elemental.common.glyphs.*;
import com.connorm.martial_ars.component.BladeCasterTomeData;
import com.connorm.martial_ars.registry.MartialRegistry;
import com.hollingsworth.arsnouveau.api.sound.ConfiguredSpellSound;
import com.hollingsworth.arsnouveau.api.spell.Spell;
import com.hollingsworth.arsnouveau.client.particle.ParticleColor;
import com.hollingsworth.arsnouveau.common.crafting.recipes.CasterTomeData;
import com.hollingsworth.arsnouveau.common.datagen.CasterTomeProvider;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAOE;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentDampen;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentSensitive;
import com.hollingsworth.arsnouveau.common.spell.effect.*;
import com.hollingsworth.arsnouveau.common.spell.method.MethodTouch;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

import static com.connorm.martial_ars.MartialArs.prefix;
import static com.connorm.martial_ars.component.ModDataComponents.BLADE_TOME_CASTER;

public class MABladeCasterTomeProvider extends CasterTomeProvider {
    public MABladeCasterTomeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }
    @Override
    public void collectJsons(CachedOutput cache) {
      /*  tomes.add(buildTome(SHAPERS_CTOME,"thefunnytest","Mageblock Minigun",new Spell().add(MethodPantomime.INSTANCE)
                .add(EffectBurst.INSTANCE)
                    .add(AugmentAOE.INSTANCE, 2)
                    .add(AugmentSensitive.INSTANCE)
                .add(EffectDelay.INSTANCE)
                    .add(AugmentRandomize.INSTANCE)
                .add(EffectDelay.INSTANCE)
                    .add(AugmentRandomize.INSTANCE)
                .add(EffectPhantomBlock.INSTANCE)
                    .add(AugmentDurationDown.INSTANCE, 3)
                .add(EffectLeap.INSTANCE)
                    .add(AugmentAmplify.INSTANCE,3)
            ,"The sound of an A10 Warthog Minigun: BVRRRRRRRRRRRRRRRRRRT!", ParticleColor.GRAY));

       */
//      .add(Augment.INSTANCE,)
//      .add(Effect.INSTANCE)
//       tomes.add(buildTome(BLADE_TOME_CASTER,"","",new Spell().add(MethodTouch.INSTANCE)

        tomes.add(buildTome(BLADE_TOME_CASTER,"iceflume","Ice Flume",new Spell().add(MethodTouch.INSTANCE)
                .add(EffectSlipper.INSTANCE)
                .add(EffectKnockback.INSTANCE)
                .add(EffectFreeze.INSTANCE)
                .add(EffectColdSnap.INSTANCE)
                    .add(AugmentAOE.INSTANCE,1)
                    .add(AugmentAmplify.INSTANCE,1)
                .add(EffectKnockback.INSTANCE)
                    .add(AugmentDampen.INSTANCE,1)
            ,"Cuts with this weapon seem to carry the inexorable might of ancient frost...", ParticleColor.BLUE));

        tomes.add(buildTome(BLADE_TOME_CASTER,"yeetsplode","Yeetsplode",new Spell().add(MethodTouch.INSTANCE)
                .add(EffectKnockback.INSTANCE)
                    .add(AugmentAmplify.INSTANCE,4)
                .add(EffectDelay.INSTANCE)
                .add(EffectBurst.INSTANCE)
                    .add(AugmentSensitive.INSTANCE,1)
                .add(EffectWindburst.INSTANCE)
                    .add(AugmentAOE.INSTANCE,1)
            ,"When your personal space bubble needs to also include the upper atmosphere. Just don't use it against Knockback resistant creatures OK?", ParticleColor.DEFAULT));

        tomes.add(buildTome(BLADE_TOME_CASTER,"oopsalleffects","THE Elemental Affliction",new Spell().add(MethodTouch.INSTANCE)
                .add(EffectHex.INSTANCE)
                .add(EffectIgnite.INSTANCE)
                .add(EffectFreeze.INSTANCE)
                .add(EffectEnvenom.INSTANCE)
                .add(EffectEnvenom.INSTANCE)
                .add(EffectWither.INSTANCE)
                .add(EffectSpark.INSTANCE)
                .add(EffectSnare.INSTANCE)
                .add(EffectOxidize.INSTANCE)
                .add(EffectWaterGrave.INSTANCE)
            ,"Original by Lyrellion. Why yes this IS just every debilitating effect at once why do you ask?", ParticleColor.DEFAULT));

        Path output = this.generator.getPackOutput().getOutputFolder();
        for (CasterRecipeWrapper g : tomes) {
            Path path = getRecipePath(output, g.id().getPath());
            saveStable(cache, CasterTomeData.CODEC.encodeStart(JsonOps.INSTANCE, g.toData()).getOrThrow(), path);
        }
    }





    public CasterRecipeWrapper buildTome(DeferredHolder<DataComponentType<?>, DataComponentType<BladeCasterTomeData>> item, String id, String name, Spell spell, String flavorText, ParticleColor particleColor) {
        return new CasterRecipeWrapper(prefix(id + "_tome"),
                name,
                spell.serializeRecipe(),
                MartialRegistry.ARMAMENT.getId(),
                flavorText,
                particleColor.serialize(), ConfiguredSpellSound.DEFAULT);
    }

    protected Path getRecipePath(Path pathIn, String str) {
        return pathIn.resolve("data/martial_ars/recipe/tomes/" + str + ".json");
    }

    @Override
    public @NotNull String getName() {
        return "Martial Ars Caster Tomes Datagen";
    }

}
