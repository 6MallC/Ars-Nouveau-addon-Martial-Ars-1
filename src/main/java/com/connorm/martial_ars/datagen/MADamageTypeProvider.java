package com.connorm.martial_ars.datagen;

import com.connorm.martial_ars.MartialArs;
import com.connorm.martial_ars.registry.MartialRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class MADamageTypeProvider extends DatapackBuiltinEntriesProvider {
    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DAMAGE_TYPE, MADamageTypeProvider::bootstrap);

    public MADamageTypeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(MartialArs.MODID));
    }

    public static void bootstrap(BootstrapContext<DamageType> ctx) {
        ctx.register(MartialRegistry.EVILFALL, new DamageType("player", 0.1F));
        ctx.register(MartialRegistry.REFRACTION, new DamageType("player", 0.1F));
    }

    public static class MADamageTagsProvider extends DamageTypeTagsProvider {

        public MADamageTagsProvider(PackOutput pPackOutput, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
            super(pPackOutput, provider, MartialArs.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.@NotNull Provider pProvider) {
            tag(DamageTypeTags.BYPASSES_ARMOR)
                    .addOptional(MartialRegistry.EVILFALL.location())
                    .addOptional(MartialRegistry.REFRACTION.location())
            ;

            tag(DamageTypeTags.BYPASSES_COOLDOWN)
                    .addOptional(MartialRegistry.REFRACTION.location())
            ;

            tag(Tags.DamageTypes.IS_MAGIC)
                    .addOptional(MartialRegistry.EVILFALL.location())
                    .addOptional(MartialRegistry.REFRACTION.location())
            ;

            tag(DamageTypeTags.NO_KNOCKBACK)
                    .addOptional(MartialRegistry.EVILFALL.location())
                    .addOptional(MartialRegistry.REFRACTION.location())
            ;

        }
    }
}