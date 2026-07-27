package com.connorm.martial_ars.datagen;

import com.connorm.martial_ars.util.MartialTags;
import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.setup.registry.ModPotions;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
 public class PotionEffectTagProvider extends IntrinsicHolderTagsProvider<MobEffect>  {

        public PotionEffectTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(pOutput, Registries.MOB_EFFECT, pProvider, ef -> BuiltInRegistries.MOB_EFFECT.getResourceKey(ef).get(), ArsNouveau.MODID, existingFileHelper);
        }

     @Override
     protected void addTags(HolderLookup.Provider provider) {
         this.tag(MartialTags.Effects.EARTH_EFFECTS).add(
                 ModPotions.SNARE_EFFECT.value(),
                 MobEffects.POISON.value(),
                 MobEffects.HUNGER.value());
         this.tag(MartialTags.Effects.EARTH_EFFECTS).add(
                 alexthw.ars_elemental.registry.ModPotions.VENOM.getKey());

         this.tag(MartialTags.Effects.AIR_EFFECTS).add(
                ModPotions.SHOCKED_EFFECT.value(),
                ModPotions.GRAVITY_EFFECT.get(),
                MobEffects.LEVITATION.value(),
                ModPotions.GLIDE_EFFECT.get()
                 );
         this.tag(MartialTags.Effects.AIR_EFFECTS).add(
                 alexthw.ars_elemental.registry.ModPotions.LIGHTNING_LURE.value(),
                 alexthw.ars_elemental.registry.ModPotions.RUST.value()
                 );
         this.tag(MartialTags.Effects.AQUA_EFFECTS).add(
                 ModPotions.SOAKED_EFFECT.value(),
                 ModPotions.FREEZING_EFFECT.get()
         );
         this.tag(MartialTags.Effects.AQUA_EFFECTS).add(
                 alexthw.ars_elemental.registry.ModPotions.MANA_BUBBLE.getKey(),
                 alexthw.ars_elemental.registry.ModPotions.ICE_SLIDE.getKey(),
                 alexthw.ars_elemental.registry.ModPotions.MIST.getKey(),
                 alexthw.ars_elemental.registry.ModPotions.FROZEN.getKey()
         );
         this.tag(MartialTags.Effects.FIRE_EFFECTS).add(
                 ModPotions.BLAST_EFFECT.value()
         );
         this.tag(MartialTags.Effects.FIRE_EFFECTS).add(
                 alexthw.ars_elemental.registry.ModPotions.MAGIC_FIRE.value()

         );

     }
 }

