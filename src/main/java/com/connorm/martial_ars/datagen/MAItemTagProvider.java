package com.connorm.martial_ars.datagen;

import com.connorm.martial_ars.MartialArs;
import com.connorm.martial_ars.registry.MartialRegistry;
import com.connorm.martial_ars.util.MartialTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosTags;

import java.util.concurrent.CompletableFuture;

public class MAItemTagProvider extends ItemTagsProvider {
    public MAItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, MartialArs.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        // .add(MartialRegistry..get())
        // MARTIAL TAGS
    tag(MartialTags.Items.Weapon_Handles)
            .add(MartialRegistry.SpearShaft.get())
            .add(MartialRegistry.KunaiHandle.get())
            .add(MartialRegistry.AeroHilt.get())
            .add(MartialRegistry.GeoHilt.get())
            .add(MartialRegistry.AquaHilt.get())
            .add(MartialRegistry.PyroHilt.get())

        ;

    tag(MartialTags.Items.Weapon_Heads)
            .add(MartialRegistry.SpearHead.get())
            .add(MartialRegistry.KunaiBlade.get())
            .add(MartialRegistry.GeoBlade.get())
            .add(MartialRegistry.AeroBlade.get())
            .add(MartialRegistry.AquaBlade.get())
            .add(MartialRegistry.PyroBlade.get())

        ;

    tag(MartialTags.Items.Martial_Weapons)
            .add(MartialRegistry.EnchantersSpear.get())
            .add(MartialRegistry.EnchantersKunai.get())
            .add(MartialRegistry.GeoSword.get())
            .add(MartialRegistry.AeroSword.get())
            .add(MartialRegistry.AquaSword.get())
            .add(MartialRegistry.PyroSword.get())

        ;

    tag(MartialTags.Items.Inlays)
            .add(MartialRegistry.BlankInlay.get())
            .add(MartialRegistry.LuckInlay.get())
            .add(MartialRegistry.DelayInlay.get())
            .add(MartialRegistry.AttackInlay.get())
            .add(MartialRegistry.DiscountInlay.get())

    ;
    // CURIO TAGS
    tag(CuriosTags.RING)
            .add(MartialRegistry.MISTBREAKER.get())

    ;

    tag(CuriosTags.BRACELET)
            .add(MartialRegistry.EARTHBAGEL.get())
            .add(MartialRegistry.AIRBAGEL.get())
            .add(MartialRegistry.FIREBAGEL.get())
            .add(MartialRegistry.WATERBAGEL.get())

    ;

    tag(CuriosTags.HEAD)
            .add(MartialRegistry.MAGESSHADES.get())

    ;

    // MINECRAFT TAGS


    this.tag(Tags.Items.INGOTS)
                .add(MartialRegistry.SourceIngot.get());

    tag(ItemTags.BREAKS_DECORATED_POTS)
            .add(MartialRegistry.EnchantersSpear.get())
            .add(MartialRegistry.EnchantersKunai.get())
            .add(MartialRegistry.GeoSword.get())
            .add(MartialRegistry.AeroSword.get())
            .add(MartialRegistry.AquaSword.get())
            .add(MartialRegistry.PyroSword.get())
            .add(MartialRegistry.ARMAMENT.get())
            .add(MartialRegistry.ABJURANTMACE.get())

    ;

        tag(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .add(MartialRegistry.EnchantersSpear.get())
                .add(MartialRegistry.EnchantersKunai.get())
                .add(MartialRegistry.GeoSword.get())
                .add(MartialRegistry.AeroSword.get())
                .add(MartialRegistry.AquaSword.get())
                .add(MartialRegistry.PyroSword.get())
                .add(MartialRegistry.ARMAMENT.get())

        ;

        tag(ItemTags.FIRE_ASPECT_ENCHANTABLE)
                .add(MartialRegistry.EnchantersSpear.get())
                .add(MartialRegistry.EnchantersKunai.get())
                .add(MartialRegistry.GeoSword.get())
                .add(MartialRegistry.AeroSword.get())
                .add(MartialRegistry.AquaSword.get())
                .add(MartialRegistry.PyroSword.get())
                .add(MartialRegistry.ARMAMENT.get())
                .add(MartialRegistry.ABJURANTMACE.get())

        ;

        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .add(MartialRegistry.EnchantersSpear.get())
                .add(MartialRegistry.EnchantersKunai.get())
                .add(MartialRegistry.GeoSword.get())
                .add(MartialRegistry.AeroSword.get())
                .add(MartialRegistry.AquaSword.get())
                .add(MartialRegistry.PyroSword.get())
                .add(MartialRegistry.ARMAMENT.get())
                .add(MartialRegistry.ABJURANTMACE.get())

        ;

        tag(ItemTags.SWORDS)
            .add(MartialRegistry.EnchantersSpear.get())
            .add(MartialRegistry.EnchantersKunai.get())
            .add(MartialRegistry.GeoSword.get())
            .add(MartialRegistry.AeroSword.get())
            .add(MartialRegistry.AquaSword.get())
            .add(MartialRegistry.PyroSword.get())
            .add(MartialRegistry.ARMAMENT.get())

    ;

    tag(ItemTags.WEAPON_ENCHANTABLE)
            .add(MartialRegistry.EnchantersSpear.get())
            .add(MartialRegistry.EnchantersKunai.get())
            .add(MartialRegistry.GeoSword.get())
            .add(MartialRegistry.AeroSword.get())
            .add(MartialRegistry.AquaSword.get())
            .add(MartialRegistry.PyroSword.get())
            .add(MartialRegistry.ARMAMENT.get())
            .add(MartialRegistry.ABJURANTMACE.get())

    ;

    tag(ItemTags.MACE_ENCHANTABLE)
            .add(MartialRegistry.ABJURANTMACE.get());
    }
}
