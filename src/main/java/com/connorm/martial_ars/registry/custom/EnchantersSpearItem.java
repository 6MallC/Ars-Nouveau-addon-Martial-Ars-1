package com.connorm.martial_ars.registry.custom;

import com.connorm.martial_ars.item.RangeTool;
import com.hollingsworth.arsnouveau.api.item.ICasterTool;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class EnchantersSpearItem extends SwordItem implements ICasterTool, RangeTool {
    public EnchantersSpearItem(Tier tier, Properties properties) {
        super(tier, properties);
    }
}
