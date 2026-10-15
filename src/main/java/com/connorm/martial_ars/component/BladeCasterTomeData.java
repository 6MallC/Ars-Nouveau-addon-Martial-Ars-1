package com.connorm.martial_ars.component;

import com.hollingsworth.arsnouveau.api.spell.AbstractCaster;
import com.hollingsworth.arsnouveau.api.spell.SpellCaster;
import com.hollingsworth.arsnouveau.api.spell.SpellSlotMap;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public class BladeCasterTomeData extends AbstractCaster<BladeCasterTomeData> {

    public static final MapCodec<BladeCasterTomeData> CODEC = SpellCaster.createCodec(BladeCasterTomeData::new);
    public static final StreamCodec<RegistryFriendlyByteBuf, BladeCasterTomeData> STREAM_CODEC = createStream(BladeCasterTomeData::new);

    public BladeCasterTomeData(Integer slot, String flavorText, Boolean isHidden, String hiddenText, int maxSlots, SpellSlotMap spells) {
        super(slot, flavorText, isHidden, hiddenText, maxSlots, spells);
    }


    @Override
        public MapCodec<BladeCasterTomeData> codec() {
            return CODEC;
        }
    @Override
    public StreamCodec<RegistryFriendlyByteBuf, BladeCasterTomeData> streamCodec() {
        return STREAM_CODEC;
    }

    public BladeCasterTomeData() {
        super();
    }
    public BladeCasterTomeData(Integer slot, String flavorText, Boolean isHidden, String hiddenText, int maxSlots) {
        super(slot, flavorText, isHidden, hiddenText, maxSlots);
    }
    @Override
    public DataComponentType<BladeCasterTomeData> getComponentType() {
        return ModDataComponents.BLADE_TOME_CASTER.get();
    }

    @Override
    protected BladeCasterTomeData build(int slot, String flavorText, Boolean isHidden, String hiddenText, int maxSlots, SpellSlotMap spells) {
        return new BladeCasterTomeData((slot), flavorText, isHidden, hiddenText, maxSlots, spells);
    }
}
