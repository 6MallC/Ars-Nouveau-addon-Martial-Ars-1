package com.connorm.martial_ars.entity.custom;

import com.connorm.martial_ars.entity.ModEntities;
import com.connorm.martial_ars.registry.ModRegistry;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec2;
import org.jetbrains.annotations.NotNull;

public class KunaiProjectileEntity extends AbstractArrow {
    public Vec2 groundedOffset;
    public KunaiProjectileEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public KunaiProjectileEntity(LivingEntity shooter, Level level){
        super(ModEntities.KUNAI.get(), shooter, level, new ItemStack(ModRegistry.EnchantersKunai.get()),null);
        this.pickup = AbstractArrow.Pickup.DISALLOWED;
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return null;
    }
    public boolean isGrounded() {
        return  inGround;
    }
    @Override
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
        if (!this.level().isClientSide){
        this.discard();
        }
    }
}
