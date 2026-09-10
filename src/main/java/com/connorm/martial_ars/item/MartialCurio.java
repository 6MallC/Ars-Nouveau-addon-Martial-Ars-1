package com.connorm.martial_ars.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class MartialCurio extends Item implements ICurioItem {
    public MartialCurio(Properties pProperties) {
        super(pProperties);
    }
    // no offhand equipping for you!
    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return stack != slotContext.entity().getItemInHand(InteractionHand.OFF_HAND);
    }

}

