package com.connorm.martial_ars.datagen;

import com.hollingsworth.arsnouveau.api.sound.ConfiguredSpellSound;
import com.hollingsworth.arsnouveau.api.spell.Spell;
import com.hollingsworth.arsnouveau.client.particle.ParticleColor;
import com.hollingsworth.arsnouveau.common.crafting.recipes.CasterTomeData;
import com.hollingsworth.arsnouveau.common.datagen.CasterTomeProvider;
import com.hollingsworth.arsnouveau.common.items.CasterTome;
import com.hollingsworth.arsnouveau.common.spell.augment.*;
import com.hollingsworth.arsnouveau.common.spell.effect.EffectBurst;
import com.hollingsworth.arsnouveau.common.spell.effect.EffectDelay;
import com.hollingsworth.arsnouveau.common.spell.effect.EffectLeap;
import com.hollingsworth.arsnouveau.common.spell.effect.EffectPhantomBlock;
import com.hollingsworth.arsnouveau.common.spell.method.MethodPantomime;
import com.mojang.serialization.JsonOps;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

import static alexthw.ars_elemental.registry.ModItems.SHAPERS_CTOME;
import static com.connorm.martial_ars.MartialArs.prefix;

public class MACasterTomeProvider extends CasterTomeProvider {
    public MACasterTomeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }
    @Override
    public void collectJsons(CachedOutput cache) {
        tomes.add(buildTome(SHAPERS_CTOME,"thefunnytest","Mageblock Minigun",new Spell().add(MethodPantomime.INSTANCE)
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




        Path output = this.generator.getPackOutput().getOutputFolder();
        for (CasterRecipeWrapper g : tomes) {
            Path path = getRecipePath(output, g.id().getPath());
            saveStable(cache, CasterTomeData.CODEC.encodeStart(JsonOps.INSTANCE, g.toData()).getOrThrow(), path);
        }
    }

    public CasterRecipeWrapper buildTome(DeferredHolder<Item, ? extends CasterTome> item, String id, String name, Spell spell, String flavorText, ParticleColor particleColor) {
        return new CasterRecipeWrapper(prefix(id + "_tome"),
                name,
                spell.serializeRecipe(),
                item.getId(),
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
