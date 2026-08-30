package com.connorm.martial_ars.registry;

import com.alexthw.sauce.registry.ModRegistry;
import com.connorm.martial_ars.item.ExampleCosmetic;
import com.connorm.martial_ars.registry.custom.EnchantersSpearItem;
import com.connorm.martial_ars.registry.custom.KunaiItem;
import com.connorm.martial_ars.registry.custom.ModToolTiers;
import com.connorm.martial_ars.registry.custom.MushroomWandItem;
import com.connorm.martial_ars.ritual.RitualRepair;
import com.hollingsworth.arsnouveau.api.perk.PerkAttributes;
import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.api.sound.SpellSound;
import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.api.spell.SpellCaster;
import com.hollingsworth.arsnouveau.common.items.RitualTablet;
import com.hollingsworth.arsnouveau.setup.registry.DataComponentRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

import static com.connorm.martial_ars.MartialArs.MODID;
import static com.connorm.martial_ars.MartialArs.prefix;
import static net.minecraft.core.registries.Registries.MOB_EFFECT;
import static net.minecraft.core.registries.Registries.SOUND_EVENT;

    public class MartialRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(SOUND_EVENT, MODID);

    // ITEMS
    public static final DeferredItem<? extends Item> SourceIngot = (DeferredItem<? extends Item>) ITEMS.register("source_ingot",
            () -> new Item(new Item.Properties()) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.martial_ars.source_ingot.tooltip"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    public static final DeferredItem<Item> SourceMatrix = (DeferredItem<Item>) ITEMS.register("source_matrix",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<MushroomWandItem> MushroomWand = (DeferredItem<MushroomWandItem>) ITEMS.register("mushroom_wand",
            () -> new MushroomWandItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> SpearShaft = (DeferredItem<Item>) ITEMS.register("spear_handle",
            () -> new Item(new Item.Properties().stacksTo(8)));
    public static final DeferredItem<Item> SpearHead = (DeferredItem<Item>) ITEMS.register("spear_head",
            () -> new Item(new Item.Properties().stacksTo(8)));
    public static final DeferredItem<SwordItem> GeoBlade = (DeferredItem<SwordItem>) ITEMS.register("geo.blade",
            () -> new SwordItem(ModToolTiers.Martial, new Item.Properties().stacksTo(8)));
    public static final DeferredItem<SwordItem> GeoHilt = (DeferredItem<SwordItem>) ITEMS.register("geo.hilt",
            () -> new SwordItem(ModToolTiers.Martial, new Item.Properties().stacksTo(8)));
    public static final DeferredItem<SwordItem> AeroBlade = (DeferredItem<SwordItem>) ITEMS.register("aero.blade",
            () -> new SwordItem(ModToolTiers.Martial, new Item.Properties().stacksTo(8)));
    public static final DeferredItem<SwordItem> AeroHilt = (DeferredItem<SwordItem>) ITEMS.register("aero.hilt",
            () -> new SwordItem(ModToolTiers.Martial, new Item.Properties().stacksTo(8)));
    public static final DeferredItem<SwordItem> PyroBlade = (DeferredItem<SwordItem>) ITEMS.register("pyro.blade",
            () -> new SwordItem(ModToolTiers.Martial, new Item.Properties().stacksTo(8)));
    public static final DeferredItem<SwordItem> PyroHilt = (DeferredItem<SwordItem>) ITEMS.register("pyro.hilt",
            () -> new SwordItem(ModToolTiers.Martial, new Item.Properties().stacksTo(8)));
    public static final DeferredItem<SwordItem> AquaBlade = (DeferredItem<SwordItem>) ITEMS.register("aqua.blade",
            () -> new SwordItem(ModToolTiers.Martial, new Item.Properties().stacksTo(8)));
    public static final DeferredItem<SwordItem> AquaHilt = (DeferredItem<SwordItem>) ITEMS.register("aqua.hilt",
            () -> new SwordItem(ModToolTiers.Martial, new Item.Properties().stacksTo(8)));

    public static final DeferredItem<Item> KunaiHandle = (DeferredItem<Item>) ITEMS.register("kunai_handle",
            () -> new Item(new Item.Properties().stacksTo(8)));
    public static final DeferredItem<Item> KunaiBlade = (DeferredItem<Item>) ITEMS.register("kunai_blade",
            () -> new Item(new Item.Properties().stacksTo(8)));

    // Inlays
    public static final DeferredItem<Item> DelayInlay = (DeferredItem<Item>) ITEMS.register("delay_inlay",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LuckInlay = (DeferredItem<Item>) ITEMS.register("luck_inlay",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BlankInlay = (DeferredItem<Item>) ITEMS.register("blank_inlay",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> AttackInlay = (DeferredItem<Item>) ITEMS.register("attack_inlay",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DiscountInlay = (DeferredItem<Item>) ITEMS.register("discount_inlay",
            () -> new Item(new Item.Properties()));

    // TOOLS

    public static final DeferredItem<KunaiItem> EnchantersKunai = (DeferredItem<KunaiItem>) ITEMS.register("enchanters_kunai",
            () -> new KunaiItem(ModToolTiers.Martial, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.Martial, -2, -1f)
                            .withModifierAdded(PerkAttributes.SPELL_DAMAGE_BONUS,
                                    new AttributeModifier(ArsNouveau.prefix("sword_spell_bonus"),
                                            2.0f, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND))
                    .component(DataComponentRegistry.SPELL_CASTER, new SpellCaster())));

    public static final DeferredItem<EnchantersSpearItem> EnchantersSpear = (DeferredItem<EnchantersSpearItem>) ITEMS.register("enchanters_spear",
            () -> new EnchantersSpearItem(ModToolTiers.Martial, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.Martial, 1, -2f)
                            .withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE,
                                    new AttributeModifier(ResourceLocation.fromNamespaceAndPath(MODID, "spear_reach"),
                                            2.0, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND)
                            .withModifierAdded(PerkAttributes.SPELL_DAMAGE_BONUS,
                                    new AttributeModifier(ArsNouveau.prefix("sword_spell_bonus"),
                                            4.0f, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND))
                    .component(DataComponentRegistry.SPELL_CASTER, new SpellCaster())));
    public static final DeferredItem<EnchantersSpearItem> GeoSword = (DeferredItem<EnchantersSpearItem>) ITEMS.register("sword.geomancy",
            () -> new EnchantersSpearItem(ModToolTiers.Martial, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.Martial, 2, -2.4f)
                            .withModifierAdded(com.alexthw.sauce.registry.ModRegistry.EARTH_POWER,
                                    new AttributeModifier(ArsNouveau.prefix("sauce.perk.earth_power"),
                                            8.0f, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND)
                            .withModifierAdded(ModRegistry.MANA_DISCOUNT_EARTH,
                                    new AttributeModifier(ArsNouveau.prefix("sauce.perk.mana_discount.earth"),
                                            .15f, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND))
                    .component(DataComponentRegistry.SPELL_CASTER, new SpellCaster())));
    public static final DeferredItem<EnchantersSpearItem> AeroSword = (DeferredItem<EnchantersSpearItem>) ITEMS.register("sword.aeromancy",
            () -> new EnchantersSpearItem(ModToolTiers.Martial, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.Martial, 2, -2.4f)
                            .withModifierAdded(ModRegistry.AIR_POWER,
                                    new AttributeModifier(ArsNouveau.prefix("sauce.perk.air_power"),
                                            8.0f, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND)
                            .withModifierAdded(ModRegistry.MANA_DISCOUNT_AIR,
                                    new AttributeModifier(ArsNouveau.prefix("sauce.perk.mana_discount.air"),
                                            .15f, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND))
                    .component(DataComponentRegistry.SPELL_CASTER, new SpellCaster())));
    public static final DeferredItem<EnchantersSpearItem> AquaSword = (DeferredItem<EnchantersSpearItem>) ITEMS.register("sword.aquamancy",
            () -> new EnchantersSpearItem(ModToolTiers.Martial, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.Martial, 2, -2.4f)
                            .withModifierAdded(ModRegistry.WATER_POWER,
                                    new AttributeModifier(ArsNouveau.prefix("sauce.perk.aqua_power"),
                                            8.0f, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND)
                            .withModifierAdded(ModRegistry.MANA_DISCOUNT_WATER,
                                    new AttributeModifier(ArsNouveau.prefix("sauce.perk.mana_discount.water"),
                                            .15f, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND))
                    .component(DataComponentRegistry.SPELL_CASTER, new SpellCaster())));
    public static final DeferredItem<EnchantersSpearItem> PyroSword = (DeferredItem<EnchantersSpearItem>) ITEMS.register("sword.pyromancy",
            () -> new EnchantersSpearItem(ModToolTiers.Martial, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.Martial, 2, -2.4f)
                            .withModifierAdded(ModRegistry.FIRE_POWER,
                                    new AttributeModifier(ArsNouveau.prefix("sauce.perk.fire_power"),
                                            8.0f, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND)
                            .withModifierAdded(ModRegistry.MANA_DISCOUNT_FIRE,
                                    new AttributeModifier(ArsNouveau.prefix("sauce.perk.mana_discount.fire"),
                                            .15f, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND))
                    .component(DataComponentRegistry.SPELL_CASTER, new SpellCaster())));

    // CURIOS
    public static final DeferredItem<Item> EARTHBAGEL = (DeferredItem<Item>) ITEMS.register("earth_bagel",
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> FIREBAGEL = (DeferredItem<Item>) ITEMS.register("fire_bagel",
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> AIRBAGEL = (DeferredItem<Item>) ITEMS.register("air_bagel",
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> WATERBAGEL = (DeferredItem<Item>) ITEMS.register("water_bagel",
            () -> new Item(new Item.Properties().stacksTo(1)));


    // ATTRIBUTES

    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(
            BuiltInRegistries.ATTRIBUTE, "martial_ars");

    public static final Holder<Attribute> EARTH_POTENCY = ATTRIBUTES.register("earth_potency", () -> new RangedAttribute(
            // The translation key to use.
            "attributes.martial_ars.earth_potency",
            // The default value.
            0,
            // Min and max values.
                    -1000,
            1000
            ).setSyncable(true));
    public static final Holder<Attribute> AIR_POTENCY = ATTRIBUTES.register("air_potency", () -> new RangedAttribute(
            "attributes.martial_ars.air_potency", 0, -1000, 1000).setSyncable(true));
    public static final Holder<Attribute> WATER_POTENCY = ATTRIBUTES.register("water_potency", () -> new RangedAttribute(
            "attributes.martial_ars.water_potency", 0, -1000, 1000).setSyncable(true));
    public static final Holder<Attribute> FIRE_POTENCY = ATTRIBUTES.register("fire_potency", () -> new RangedAttribute(
            "attributes.martial_ars.fire_potency", 0, -1000, 1000).setSyncable(true));
    public static final Holder<Attribute> ELEMENTAL_POTENCY = ATTRIBUTES.register("elemental_potency", () -> new RangedAttribute(
            "attributes.martial_ars.elemental_potency", 0, -1000, 1000).setSyncable(true));

    @SubscribeEvent
    public static void modifyEntityAttributes(@NotNull EntityAttributeModificationEvent event) {
        event.getTypes().stream() .filter(e -> e == EntityType.PLAYER)
                .forEach(player -> {
                    ATTRIBUTES.getEntries().forEach(
                            v -> {
                                event.add(player, v);
                            });
                });
    }
    // BLOCKS
    public static final DeferredBlock<Block> sourceingot_block = registerBlock("sourceingot_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)));


    public static void registerRegistries(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
        ATTRIBUTES.register(bus);
        SOUNDS.register(bus);

    }

    public static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = (DeferredBlock<T>) BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    // SOUNDS
    public static final DeferredHolder<Item, ? extends Item> EXAMPLE;
    //this is an example of how to register a sound. You also need to add the sound to the sound.json file, referencing your ogg files, and a texture for the button under textures/sounds.
//this example will use one of the existing sounds randomly
    public static DeferredHolder<SoundEvent, SoundEvent> EXAMPLE_FAMILY = SOUNDS.register("example_sound", () -> makeSound("example_sound"));
    public static SpellSound EXAMPLE_SPELL_SOUND = new SpellSound(MartialRegistry.EXAMPLE_FAMILY, Component.literal("Example"), prefix("example_random_sound"));


    static {
        EXAMPLE = ITEMS.register("star_hat", () -> new ExampleCosmetic(new Item.Properties()));
    }

    static SoundEvent makeSound(@NotNull String name) {
        return SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MODID, name));
    }

}