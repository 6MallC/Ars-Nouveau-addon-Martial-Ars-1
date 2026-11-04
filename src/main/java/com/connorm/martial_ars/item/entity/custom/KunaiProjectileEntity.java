package com.connorm.martial_ars.item.entity.custom;

import com.connorm.martial_ars.item.entity.MartialItemEntities;
import com.connorm.martial_ars.registry.MartialRegistry;
import com.hollingsworth.arsnouveau.api.spell.Spell;
import com.hollingsworth.arsnouveau.api.spell.SpellContext;
import com.hollingsworth.arsnouveau.api.spell.SpellResolver;
import com.hollingsworth.arsnouveau.common.util.ANCodecs;
import com.hollingsworth.arsnouveau.setup.registry.DataSerializers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;
import org.jetbrains.annotations.NotNull;

public class KunaiProjectileEntity extends AbstractArrow {
    BlockPos lastPosHit;

    public static final EntityDataAccessor<SpellResolver> SPELL_RESOLVER = SynchedEntityData.defineId(KunaiProjectileEntity.class, DataSerializers.SPELL_RESOLVER.get());


    public KunaiProjectileEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public SpellResolver resolver() {
        return this.entityData.get(SPELL_RESOLVER);
    }

    public void setResolver(SpellResolver resolver) {
        if (resolver == null) {
            this.entityData.set(SPELL_RESOLVER, new SpellResolver(new SpellContext(this.level(), new Spell(), null, null)));
            return;
        }

        if (resolver.spellContext != null) {
            resolver.spellContext.level = this.level();
        }

        this.entityData.set(SPELL_RESOLVER, resolver);
    }

    public KunaiProjectileEntity(LivingEntity shooter, Level level) {
        super(MartialItemEntities.KUNAI.get(), shooter, level, new ItemStack(MartialRegistry.EnchantersKunai.get()), null);
        this.pickup = AbstractArrow.Pickup.DISALLOWED;
    }

    @Override
    public void tick() {
    if (this.isRemoved()){
        return;
        }
    super.tick();
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return null;
    }

    public boolean isGrounded() {
        return inGround;
    }

    protected void attemptRemoval() {
        if (level.isClientSide) {return;}
            this.level.broadcastEntityEvent(this, (byte) 3);
            this.remove(RemovalReason.DISCARDED);

    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("resolver")) {
            setResolver(SpellResolver.rehydratedFromTag(tag.getCompound("resolver"), (ServerLevel) level));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (this.resolver() != null) {
            tag.put("resolver", ANCodecs.encode(SpellResolver.CODEC.codec(), this.resolver()));
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(SPELL_RESOLVER, new SpellResolver(new SpellContext(level, new Spell(), null, null)));
    }

    @Override
    protected void doPostHurtEffects(@NotNull LivingEntity living) {
        super.doPostHurtEffects(living);
        this.playResolve();
    }

    @Override
    protected void onHit(HitResult result) {
        if (this.level().isClientSide) {
            return;
        }

        if (result.getType() == HitResult.Type.ENTITY) {
            EntityHitResult entityHit = (EntityHitResult) result;
            Entity hitEntity = entityHit.getEntity();

            if (hitEntity == null || hitEntity == this || hitEntity == this.getOwner()) {
                return;
            }

            if (resolver() != null) {
                resolver().onResolveEffect(this.level(), result);
            }

            hitEntity.hurt(this.damageSources().thrown(this, this.getOwner()), 5.0F);

            this.playResolve();
            this.attemptRemoval();
            return;
        }

        if (result instanceof BlockHitResult blockHitResult) {
            if (lastPosHit == null || !blockHitResult.getBlockPos().equals(lastPosHit)) {
                if (resolver() != null) {
                    resolver().onResolveEffect(this.level(), result);
                }

                this.onHitBlock(blockHitResult);
                lastPosHit = blockHitResult.getBlockPos().immutable();
            }

            this.playResolve();
            this.attemptRemoval();
        }
    }

    protected void onHitBlock(@NotNull BlockHitResult result) {
        if (this.level().isClientSide) {
            return;
        }

        if (resolver() != null) {
            resolver().onResolveEffect(level, result);
        }

        BlockState blockstate = this.level().getBlockState(result.getBlockPos());
        blockstate.onProjectileHit(this.level(), blockstate, result, this);

        Vec3 vec3 = result.getLocation().subtract(this.getX(), this.getY(), this.getZ());
        this.setDeltaMovement(vec3);
    }

    public void playResolve() {
        this.playSound(this.getDefaultHitGroundSoundEvent(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
    }
}