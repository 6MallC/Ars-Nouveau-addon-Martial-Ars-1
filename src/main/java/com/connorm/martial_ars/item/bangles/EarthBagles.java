package com.connorm.martial_ars.item.bangles;

import com.alexthw.sauce.registry.ModRegistry;
import com.connorm.martial_ars.item.MartialCurio;
import com.connorm.martial_ars.registry.MartialRegistry;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public class EarthBagles extends MartialCurio {
    public EarthBagles(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation uuid, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> map = HashMultimap.create();
        map.put(MartialRegistry.EARTH_POTENCY,new AttributeModifier(uuid,-50,AttributeModifier.Operation.ADD_VALUE));
        map.put(ModRegistry.EARTH_RESISTANCE, new AttributeModifier(uuid,15,AttributeModifier.Operation.ADD_VALUE));
        map.put(Attributes.ARMOR, new AttributeModifier(uuid, 4,AttributeModifier.Operation.ADD_VALUE));
        map.put(ModRegistry.MANA_DISCOUNT_ELEMENTAL, new AttributeModifier(uuid, .10,AttributeModifier.Operation.ADD_VALUE));
        map.put(MartialRegistry.AIR_POTENCY,new AttributeModifier(uuid,25,AttributeModifier.Operation.ADD_VALUE));
        return map;
    }
}
