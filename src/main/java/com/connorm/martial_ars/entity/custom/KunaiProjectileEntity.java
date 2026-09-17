package com.connorm.martial_ars.entity.custom;

import com.connorm.martial_ars.entity.ModEntities;
import com.connorm.martial_ars.registry.MartialRegistry;
import com.hollingsworth.arsnouveau.api.spell.Spell;
import com.hollingsworth.arsnouveau.api.spell.SpellContext;
import com.hollingsworth.arsnouveau.api.spell.SpellResolver;
import com.hollingsworth.arsnouveau.common.util.ANCodecs;
import com.hollingsworth.arsnouveau.setup.registry.DataSerializers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;
import net.neoforged.neoforge.event.EventHooks;

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
        if (resolver.spellContext != null) {
            resolver.spellContext.level = this.level;
        }
        this.entityData.set(SPELL_RESOLVER, resolver);
    }

    public KunaiProjectileEntity(LivingEntity shooter, Level level) {
        super(ModEntities.KUNAI.get(), shooter, level, new ItemStack(MartialRegistry.EnchantersKunai.get()), null);
        this.pickup = AbstractArrow.Pickup.DISALLOWED;
    }

    @Override
    public void tick() {
        boolean isNoClip = this.isNoPhysics();
        Vec3 vec3 = this.getDeltaMovement();
        if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
            double d0 = vec3.horizontalDistance();
            this.setYRot((float) (Mth.atan2(vec3.x, vec3.z) * (double) 180.0F / (double) (float) Math.PI));
            this.setXRot((float) (Mth.atan2(vec3.y, d0) * (double) 180.0F / (double) (float) Math.PI));
            this.yRotO = this.getYRot();
            this.xRotO = this.getXRot();
        }
        vec3 = this.getDeltaMovement();
        double d5 = vec3.x;
        double d6 = vec3.y;
        double d1 = vec3.z;

        double d7 = this.getX() + d5;
        double d2 = this.getY() + d6;
        double d3 = this.getZ() + d1;
        double d4 = vec3.horizontalDistance();
        if (isNoClip) {
            this.setYRot((float) (Mth.atan2(-d5, -d1) * (double) 180.0F / (double) (float) Math.PI));
        } else {
            this.setYRot((float) (Mth.atan2(d5, d1) * (double) 180.0F / (double) (float) Math.PI));
        }

        this.setXRot((float) (Mth.atan2(d6, d4) * (double) 180.0F / (double) (float) Math.PI));
        this.setXRot(lerpRotation(this.xRotO, this.getXRot()));
        this.setYRot(lerpRotation(this.yRotO, this.getYRot()));
        float f = 0.99F;
        if (this.isInWater()) {
            for (int j = 0; j < 4; ++j) {
                float f1 = 0.25F;
                this.level().addParticle(ParticleTypes.BUBBLE, d7 - d5 * f1, d2 - d6 * f1, d3 - d1 * f1, d5, d6, d1);
            }

            f = this.getWaterInertia();
        }

        this.setDeltaMovement(vec3.scale(f));
        if (!isNoClip) {
            this.applyGravity();
        }
        this.inGroundTime = 0;
        Vec3 vector3d2 = this.position();
        Vec3 vector3d3 = vector3d2.add(vec3);
        HitResult hitresult = this.level.clip(new ClipContext(vector3d2, vector3d3, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
        if (hitresult.getType() != HitResult.Type.MISS) {
            vector3d3 = hitresult.getLocation();
        }
        this.setPos(d7, d2, d3);
        this.checkInsideBlocks();

        while (!this.isRemoved()) {
            EntityHitResult entityraytraceresult = this.findHitEntity(vector3d2, vector3d3);
            if (entityraytraceresult != null) {
                hitresult = entityraytraceresult;
            }

            if (hitresult instanceof EntityHitResult entityHitResult) {
                Entity entity = entityHitResult.getEntity();
                Entity entity1 = this.getOwner();
                if (entity.noPhysics) {
                    hitresult = null;
                    entityraytraceresult = null;
                    break;
                } else if (entity instanceof Player player1 && entity1 instanceof Player player2 && !player2.canHarmPlayer(player1)) {
                    hitresult = null;
                    entityraytraceresult = null;
                    break;
                }
            }
            if (hitresult != null && hitresult.getType() != HitResult.Type.MISS && !isNoClip) {
                if (EventHooks.onProjectileImpact(this, hitresult)) {
                    break;
                }

                ProjectileDeflection projectiledeflection = this.hitTargetOrDeflectSelf(hitresult);
                this.hasImpulse = true;
                if (projectiledeflection != ProjectileDeflection.NONE) {
                    break;
                }
            }
            if (entityraytraceresult == null) {
                break;
            }

            hitresult = null;
        }
        this.setDeltaMovement(vec3.scale(f));
        if (!isNoClip) {
            this.applyGravity();
        }
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
    protected void doPostHurtEffects(LivingEntity living) {
        super.doPostHurtEffects(living);
        this.playResolve();
    }

    @Override
    protected void onHit(HitResult result) {
        HitResult.Type raytraceresult$type = result.getType();
        if (raytraceresult$type == HitResult.Type.ENTITY) {
            if (resolver() != null) {
                resolver().onResolveEffect(level, result);
            }
            this.onHitEntity((EntityHitResult) result);
            attemptRemoval();
        } else if (result instanceof BlockHitResult blockHitResult && !(blockHitResult.getBlockPos().equals(lastPosHit))) {
            this.onHitBlock(blockHitResult);
            lastPosHit = blockHitResult.getBlockPos().immutable();
            attemptRemoval();
        }



   /* @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 5);

        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        if (!this.level().isClientSide) {
            this.discard();
        }
    }*/
/*
    @Override
    protected void onHit(HitResult result) {
        if (this.resolverOn().spell != null)
            this.resolver().spell.(level, result);
        HitResult.Type raytraceresult$type = result.getType();
        if (raytraceresult$type == HitResult.Type.ENTITY) {
            if (resolver().spell != null) {
                resolver().spell.getCastMethod(level, result);
            }
        }
    }
*/

    }

    protected void onHitBlock(BlockHitResult result) {
        if (resolver() != null) {
            resolver().onResolveEffect(level, result);
        }
        BlockState blockstate = this.level.getBlockState(result.getBlockPos());
        blockstate.onProjectileHit(this.level, blockstate, result, this);
        playResolve();

        // 1.21.1 Copy of super.onHitBlock with subtractions left as comments
        Vec3 vec3 = result.getLocation().subtract(this.getX(), this.getY(), this.getZ());
        this.setDeltaMovement(vec3);
    }
    public void playResolve() {
        this.playSound(this.getDefaultHitGroundSoundEvent(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
    }
}