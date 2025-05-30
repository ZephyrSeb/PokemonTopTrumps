package zephyrseb.pokemontoptrumps;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Ability extends PassiveAbility {
    private final String name;
    private final String description;
    private final List<Effect> effects = new ArrayList<>();
    public Ability(String n, String d) {
        name = n;
        description = d;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public Ability addEffect(Effect e) {
        effects.add(e);
        return this;
    }

    @Override
    public List<Effect> getEffects() {
        return effects;
    }

    public static Ability HIDDEN_POWER;
    public static Ability TRUANT;
    public static Ability TRANSFORM;
    public static Ability TORRENT;
    public static Ability OVERGROW;
    public static Ability BLAZE;
    public static Ability HEART_SWAP;
    public static Ability PICKUP;
    public static Ability MOLD_BREAKER;
    public static Ability DOWNLOAD;
    public static Ability SUPER_LUCK;
    public static Ability TRACE;
    public static Ability DRIZZLE;
    public static Ability DROUGHT;
    public static Ability SNOW_WARNING;
    public static Ability SAND_STREAM;
    public static Ability GRASSY_SURGE;
    public static Ability ELECTRIC_SURGE;
    public static Ability MISTY_SURGE;
    public static Ability PSYCHIC_SURGE;
    public static Ability RAIN_DISH;
    public static Ability CHLOROPHYLL;
    public static Ability SWIFT_SWIM;
    public static Ability FLOWER_GIFT;
    public static Ability SAND_VEIL;
    public static Ability SNOW_CLOAK;
    public static Ability ICE_BODY;
    public static Ability FORECAST;
    public static Ability CLOUD_NINE;
    public static Ability SAND_FORCE;
    public static Ability LEVITATE;
    public static Ability CONTRARY;
    public static Ability ANALYTIC;
    public static Ability DRY_SKIN;
    public static Ability DEFEATIST;
    public static Ability SUBSTITUTE;
    public static Ability EARTH_EATER;
    public static Ability WATER_ABSORB;
    public static Ability VOLT_ABSORB;
    public static Ability LIGHTNING_ROD;
    public static Ability SAP_SIPPER;
    public static Ability SOLID_ROCK;
    public static Ability FLUFFY;
    public static Ability THICK_FAT;
    public static Ability FUR_COAT;
    public static Ability GRASS_PELT;
    public static Ability SURGE_SURFER;
    public static Ability HARVEST;
    public static Ability CHEEK_POUCH;
    public static Ability CUD_CHEW;
    public static Ability GLUTTONY;
    public static Ability RIPEN;
    public static Ability HEATPROOF;
    public static Ability CLEAR_BODY;
    public static Ability HYDRATION;
    public static Ability LEAF_GUARD;
    public static Ability INTIMIDATE;
    public static Ability ROUGH_SKIN;
    public static Ability JUSTIFIED;
    public static Ability KLUTZ;
    public static Ability MUMMY;
    public static Ability MOODY;
    public static Ability OPPORTUNIST;
    public static Ability RATTLED;
    public static Ability SAND_RUSH;
    public static Ability SLUSH_RUSH;
    public static Ability SIMPLE;
    public static Ability SLOW_START;
    public static Ability SOLAR_POWER;
    public static Ability SPEED_BOOST;
    public static Ability STAMINA;
    public static Ability STORM_DRAIN;
    public static Ability TECHNICIAN;
    public static Ability WATER_BUBBLE;
    public static Ability WATER_COMPACTION;
    public static Ability WEAK_ARMOR;
    public static Ability PAY_DAY;
    public static Ability PRESENT;
    public static Ability SUBSTITUTE_DOLL;
    public static Ability SCHOOLING;
    public static Ability SHIELDS_DOWN;
    public static Ability CAMOUFLAGE;
    public static Ability TRICK_ROOM;
    public static Ability CHARGE;
    public static Ability ELECTROMORPHOSIS;
    public static Ability WIND_POWER;
    public static Ability MOTOR_DRIVE;
    public static Ability DANCER;
    public static Ability STATIC;
    public static Ability FLAME_BODY;
    public static Ability POISON_POINT;
    public static Ability FROZEN_BODY;
    public static Ability TRI_ATTACK;
    public static Ability HYPNOSIS;
    public static Ability CONFUSE_RAY;
    public static Ability ICE_BURN;
    public static Ability FACADE;
    public static Ability NATURAL_CURE;
    public static Ability CORROSION;
    public static Ability TOXIC_DEBRIS;
    public static Ability BEGINNERS_LUCK;
    public static Ability TAILWIND;
    public static Ability VICTORY_STAR;
    public static Ability STORM_WINGS;
    public static Ability SOLAR_BLADE;
    public static Ability FLOWER_VEIL;
    public static Ability AURORA_VEIL;
    public static Ability GALE_WINGS;
    public static Ability WIND_RIDER;
    public static Ability AIR_CUTTER;
    public static Ability FLASH_FIRE;
    public static Ability GOOEY;
    public static Ability NORMAL_AURA;
    public static Ability FIRE_AURA;
    public static Ability WATER_AURA;
    public static Ability GRASS_AURA;
    public static Ability ELECTRIC_AURA;
    public static Ability ICE_AURA;
    public static Ability PSYCHIC_AURA;
    public static Ability FIGHTING_AURA;
    public static Ability GROUND_AURA;
    public static Ability STEEL_AURA;
    public static Ability FAIRY_AURA;
    public static Ability DRAGON_AURA;
    public static Ability DARK_AURA;
    public static Ability AURA_SHIFT;
    public static Ability AURA_BREAK;
    public static Ability COMATOSE;
    public static Ability LUNAR_RUSH;
    public static Ability HEX;
    public static Ability VENOSHOCK;
    public static Ability BLOOD_MOON_FRENZY;
    public static Ability SKILL_LINK;
    public static Ability ELECTROBLAST;
    public static Ability CHLOROBLAST;
    public static Ability PROTOSYNTHESIS;
    public static Ability QUARK_DRIVE;
    public static Ability HADRON_ENGINE;
    public static Ability ORICHALCUM_PULSE;
    public static Ability BLEAKWIND_STORM;//tornadus
    public static Ability WILDBOLT_STORM;//thundurus
    public static Ability SANDSEAR_STORM;
    public static Ability SPRINGTIDE_STORM;
    public static Ability DARK_HAZE;
    public static Ability NIGHTMARE;
    public static Ability MERCILESS;
    public static Ability STEALTH_ROCK;
    public static Ability MIST_BALL;
    public static Ability LUSTER_PURGE;
    public static Ability ICY_TOUCH;
    public static Ability ICE_BALL;
    public static Ability BRAIN_FREEZE;
    public static Ability LUNAR_BLESSING;
    public static Ability PERISH_SONG;
    public static Ability NIGHT_SLASH;
    public static Ability MOXIE;
    public static Ability SPECTRAL_THIEF;
    public static Ability SNIPER;
    public static Ability GUILLOTINE;//plus points on crit
    public static Ability STANCE_CHANGE;
    public static Ability FAIRY_LOCK;
    public static Ability STEAM_ERUPTION;//powers up when water type
    public static Ability TRIAGE;
    public static Ability CLANGING_SCALES;
    public static Ability THIEF;
    public static Ability OBSTRUCT;
    public static Ability ICE_SCALES;
    public static Ability ICE_FACE;
    public static Ability WELL_BAKED_BODY;
    public static Ability GUARD_DOG;
    public static Ability THERMAL_EXCHANGE;
    public static Ability GOOD_AS_GOLD;
    public static Ability TOXIC_CHAIN;
    public static Ability BOOMBURST;
    public static Ability CHATTER;
    public static Ability TABLETS_OF_RUIN;
    public static Ability SWORD_OF_RUIN;
    public static Ability VESSEL_OF_RUIN;
    public static Ability BEADS_OF_RUIN;
    public static Ability TERA_SHIFT;
    public static Ability HARDEN;
    public static Ability WONDER_GUARD;
    public static Ability STEAM_ENGINE;
    public static Ability POLTERGEIST;
    public static Ability NO_RETREAT;
    public static Ability RAGE_FIST;
    public static Ability WATER_SPOUT;
    public static Ability MEGA_LAUNCHER;
    public static Ability EMERGENCY_EXIT;
    public static Ability INNARDS_OUT;
    public static Ability GULP_MISSILE;
    public static Ability ZERO_TO_HERO;
    public static Ability COMMANDER;

    public static Ability MEGA_AIRSTREAM;

    public static Ability MEGA_GEYSER;

    public static Ability MEGA_FLARE;

    public static Ability MEGA_HAILSTORM;

    public static Ability MEGA_ROCKFALL;

    public static Ability MEGA_OVERGROWTH;

    public static Ability MEGA_LIGHTNING;

    public static Ability MEGA_MINDSTORM;

    public static Ability MEGA_STARFALL;
    public static Ability MEGA_DARKNESS;
    public static Ability MEGA_KNUCKLE;
    public static Ability MEGA_STEELSPIKE;

    public static void init(Context ctx) {
        HIDDEN_POWER = new Ability(ctx.getResources().getString(R.string.ability_name_hidden_power),ctx.getResources().getString(R.string.ability_description_hidden_power)).addEffect(new Effect((game, player, opponent) -> player.setSupereffectiveHit(true), 0, "ability"));
        TRUANT = new Ability(ctx.getResources().getString(R.string.ability_name_truant),ctx.getResources().getString(R.string.ability_description_truant)).addEffect(new Effect((game, player, opponent) -> player.setSpeed(-1), 0, "ability"));
        TRANSFORM = new Ability(ctx.getResources().getString(R.string.ability_name_transform),ctx.getResources().getString(R.string.ability_description_transform)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.getPlayedCard().getAbility() != Ability.TRANSFORM) {
                player.setPlayedCard(opponent.getPlayedCard());
                if (player.getPlayedCard().getAbility() != null) {
                    for (Effect e : player.getPlayedCard().getAbility().getEffects()) {
                        game.effectQueueAdd(new EffectAction(e, player, opponent, player.getPlayedCard().getAttribute(Attributes.SPD)));
                    }
                }
            }
        }, 3, "ability"));
        TORRENT = new Ability(ctx.getResources().getString(R.string.ability_name_torrent),ctx.getResources().getString(R.string.ability_description_torrent)).addEffect(new Effect((game, player, opponent) -> {
                if (opponent.getPoints() >= 4 && player.checkType(player.getPlayedCard(), Type.WATER)) {
                    player.incrementLevel(Attributes.HP, 1);
                    player.incrementLevel(Attributes.ATK, 1);
                    player.incrementLevel(Attributes.DEF, 1);
                    player.incrementLevel(Attributes.SPATK, 1);
                    player.incrementLevel(Attributes.SPDEF, 1);
                    player.incrementLevel(Attributes.SPD, 1);
                }
            }, 0, "ability"));
        OVERGROW = new Ability(ctx.getResources().getString(R.string.ability_name_overgrow),ctx.getResources().getString(R.string.ability_description_overgrow)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.getPoints() >= 4 && player.checkType(player.getPlayedCard(), Type.GRASS)) {
                player.incrementLevel(Attributes.HP, 1);
                player.incrementLevel(Attributes.ATK, 1);
                player.incrementLevel(Attributes.DEF, 1);
                player.incrementLevel(Attributes.SPATK, 1);
                player.incrementLevel(Attributes.SPDEF, 1);
                player.incrementLevel(Attributes.SPD, 1);
            }
        }, 0, "ability"));
        BLAZE = new Ability(ctx.getResources().getString(R.string.ability_name_blaze),ctx.getResources().getString(R.string.ability_description_blaze)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.getPoints() >= 4 && player.checkType(player.getPlayedCard(), Type.FIRE)) {
                player.incrementLevel(Attributes.HP, 1);
                player.incrementLevel(Attributes.ATK, 1);
                player.incrementLevel(Attributes.DEF, 1);
                player.incrementLevel(Attributes.SPATK, 1);
                player.incrementLevel(Attributes.SPDEF, 1);
                player.incrementLevel(Attributes.SPD, 1);
            }
        }, 0, "ability"));
        HEART_SWAP = new Ability(ctx.getResources().getString(R.string.ability_name_heart_swap),ctx.getResources().getString(R.string.ability_description_heart_swap)).addEffect(new Effect((game, player, opponent) -> {
            Card temp = player.getPlayedCard();
            player.setPlayedCard(opponent.getPlayedCard());
            opponent.setPlayedCard(temp);
        }, -1, "ability"));
        PICKUP = new Ability(ctx.getResources().getString(R.string.ability_name_pickup),ctx.getResources().getString(R.string.ability_description_pickup)).addEffect(new Effect((game, player, opponent) -> player.addItem(game.generateItem()), 0, "ability"));
        MOLD_BREAKER = new Ability(ctx.getResources().getString(R.string.ability_name_mold_breaker),ctx.getResources().getString(R.string.ability_description_mold_breaker)).addEffect(new Effect((game, player, opponent) -> game.removeEffectFromQueue("ability", opponent), 4, "ability"));
        DOWNLOAD = new Ability(ctx.getResources().getString(R.string.ability_name_download),ctx.getResources().getString(R.string.ability_description_download)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.getPlayedCard().getAttribute(Attributes.DEF) < opponent.getPlayedCard().getAttribute(Attributes.SPDEF)) player.incrementLevel(Attributes.ATK, 1);
            if (opponent.getPlayedCard().getAttribute(Attributes.DEF) >= opponent.getPlayedCard().getAttribute(Attributes.SPDEF)) player.incrementLevel(Attributes.SPATK, 1);
        }, 0, "ability"));
        SUPER_LUCK = new Ability(ctx.getResources().getString(R.string.ability_name_super_luck),ctx.getResources().getString(R.string.ability_description_super_luck)).addEffect(new Effect((game, player, opponent) -> player.setCritRate(player.getCritRate() + 1), 0, "ability"));
        TRACE = new Ability(ctx.getResources().getString(R.string.ability_name_trace),ctx.getResources().getString(R.string.ability_description_trace)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.getPlayedCard().getAbility() != Ability.TRACE) {
                player.getPlayedCard().setAbility(opponent.getPlayedCard().getAbility());
                if (player.getPlayedCard().getAbility() != null) {
                    for (Effect e : player.getPlayedCard().getAbility().getEffects()) {
                        game.effectQueueAdd(new EffectAction(e, player, opponent, player.getPlayedCard().getAttribute(Attributes.SPD)));
                    }
                }
            }
        }, 1, "ability"));
        DRIZZLE = new Ability(ctx.getResources().getString(R.string.ability_name_drizzle),ctx.getResources().getString(R.string.ability_description_drizzle)).addEffect(new Effect((game, player, opponent) -> game.setFieldEffect(FieldEffect.RAIN), 0, "ability"));
        DROUGHT = new Ability(ctx.getResources().getString(R.string.ability_name_drought),ctx.getResources().getString(R.string.ability_description_drought)).addEffect(new Effect((game, player, opponent) -> game.setFieldEffect(FieldEffect.SUN), 0, "ability"));
        SNOW_WARNING = new Ability(ctx.getResources().getString(R.string.ability_name_snow_warning),ctx.getResources().getString(R.string.ability_description_snow_warning)).addEffect(new Effect((game, player, opponent) -> game.setFieldEffect(FieldEffect.SNOW), 0, "ability"));
        SAND_STREAM = new Ability(ctx.getResources().getString(R.string.ability_name_sand_stream),ctx.getResources().getString(R.string.ability_description_sand_stream)).addEffect(new Effect((game, player, opponent) -> game.setFieldEffect(FieldEffect.SANDSTORM), 0, "ability"));
        GRASSY_SURGE = new Ability(ctx.getResources().getString(R.string.ability_name_grassy_surge),ctx.getResources().getString(R.string.ability_description_grassy_surge)).addEffect(new Effect((game, player, opponent) -> game.setFieldEffect(FieldEffect.GRASSY_TERRAIN), 0, "ability"));
        ELECTRIC_SURGE = new Ability(ctx.getResources().getString(R.string.ability_name_electric_surge),ctx.getResources().getString(R.string.ability_description_electric_surge)).addEffect(new Effect((game, player, opponent) -> game.setFieldEffect(FieldEffect.ELECTRIC_TERRAIN), 0, "ability"));
        MISTY_SURGE = new Ability(ctx.getResources().getString(R.string.ability_name_misty_surge),ctx.getResources().getString(R.string.ability_description_misty_surge)).addEffect(new Effect((game, player, opponent) -> game.setFieldEffect(FieldEffect.MISTY_TERRAIN), 0, "ability"));
        PSYCHIC_SURGE = new Ability(ctx.getResources().getString(R.string.ability_name_psychic_surge),ctx.getResources().getString(R.string.ability_description_psychic_surge)).addEffect(new Effect((game, player, opponent) -> game.setFieldEffect(FieldEffect.PSYCHIC_TERRAIN), 0, "ability"));
        CLOUD_NINE = new Ability(ctx.getResources().getString(R.string.ability_name_cloud_nine),ctx.getResources().getString(R.string.ability_description_cloud_nine)).addEffect(new Effect((game, player, opponent) -> game.setFieldEffect(FieldEffect.NONE), 0, "ability"));
        TRICK_ROOM = new Ability(ctx.getResources().getString(R.string.ability_name_trick_room),ctx.getResources().getString(R.string.ability_description_trick_room)).addEffect(new Effect((game, player, opponent) -> game.setFieldEffect(FieldEffect.TRICK_ROOM), 0, "ability"));
        TAILWIND = new Ability(ctx.getResources().getString(R.string.ability_name_tailwind),ctx.getResources().getString(R.string.ability_description_tailwind)).addEffect(new Effect((game, player, opponent) -> game.setFieldEffect(FieldEffect.WIND), 0, "ability"));
        DARK_HAZE = new Ability(ctx.getResources().getString(R.string.ability_name_dark_haze),ctx.getResources().getString(R.string.ability_description_dark_haze)).addEffect(new Effect((game, player, opponent) -> game.setFieldEffect(FieldEffect.DARKNESS), 0, "ability"));
        RAIN_DISH = new Ability(ctx.getResources().getString(R.string.ability_name_rain_dish),ctx.getResources().getString(R.string.ability_description_rain_dish)).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.RAIN) {player.incrementLevel(Attributes.HP, 1);}}, 0, "ability"));
        SWIFT_SWIM = new Ability(ctx.getResources().getString(R.string.ability_name_swift_swim),ctx.getResources().getString(R.string.ability_description_swift_swim)).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.RAIN) {player.incrementLevel(Attributes.SPD, 1);}}, 0, "ability"));
        CHLOROPHYLL = new Ability(ctx.getResources().getString(R.string.ability_name_chlorophyll),ctx.getResources().getString(R.string.ability_description_chlorophyll)).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.SUN) {player.incrementLevel(Attributes.SPD, 1);}}, 0, "ability"));
        FLOWER_GIFT = new Ability(ctx.getResources().getString(R.string.ability_name_flower_gift),ctx.getResources().getString(R.string.ability_description_flower_gift)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getFieldEffect() == FieldEffect.SUN && Objects.equals(player.getPlayedCard().getName(), ctx.getResources().getString(R.string.pokemon_name_cherrim))) {player.setPlayedCard(CardRegistry.initCard(ctx, CardRegistry.CHERRIM_SUNNY));}
        }, 1, "ability"));
        SAND_VEIL = new Ability(ctx.getResources().getString(R.string.ability_name_sand_veil),ctx.getResources().getString(R.string.ability_description_sand_veil)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getFieldEffect() == FieldEffect.SANDSTORM) {
                opponent.setCritRate(0);
                opponent.setSupereffectiveMultiplier(1);
            }
        }, -1, "ability"));
        SNOW_CLOAK = new Ability(ctx.getResources().getString(R.string.ability_name_snow_cloak),ctx.getResources().getString(R.string.ability_description_snow_cloak)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getFieldEffect() == FieldEffect.SNOW) {
                opponent.setCritRate(0);
                opponent.setSupereffectiveMultiplier(1);
            }
        }, -1, "ability"));
        ICE_BODY = new Ability(ctx.getResources().getString(R.string.ability_name_ice_body),ctx.getResources().getString(R.string.ability_description_ice_body)).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.SNOW) {player.incrementLevel(Attributes.HP, 1);}}, 0, "ability"));
        FORECAST = new Ability(ctx.getResources().getString(R.string.ability_name_forecast),ctx.getResources().getString(R.string.ability_description_forecast)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getFieldEffect() == FieldEffect.SUN && Objects.equals(player.getPlayedCard().getName(), ctx.getResources().getString(R.string.pokemon_name_castform))) {player.setPlayedCard(CardRegistry.initCard(ctx, CardRegistry.CASTFORM_SUN));}
            if (game.getFieldEffect() == FieldEffect.RAIN && Objects.equals(player.getPlayedCard().getName(), ctx.getResources().getString(R.string.pokemon_name_castform))) {player.setPlayedCard(CardRegistry.initCard(ctx, CardRegistry.CASTFORM_RAIN));}
            if (game.getFieldEffect() == FieldEffect.SNOW && Objects.equals(player.getPlayedCard().getName(), ctx.getResources().getString(R.string.pokemon_name_castform))) {player.setPlayedCard(CardRegistry.initCard(ctx, CardRegistry.CASTFORM_SNOW));}
        }, 1, "ability"));
        SAND_FORCE = new Ability(ctx.getResources().getString(R.string.ability_name_sand_force),ctx.getResources().getString(R.string.ability_description_sand_force)).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.SANDSTORM) {player.incrementLevel(Attributes.ATK, 1);}}, 0, "ability"));
        LEVITATE = new Ability(ctx.getResources().getString(R.string.ability_name_levitate),ctx.getResources().getString(R.string.ability_description_levitate)).addEffect(new Effect((game, player, opponent) -> game.removeEffectFromQueue("field", player), 4, "ability"));
        CONTRARY = new Ability(ctx.getResources().getString(R.string.ability_name_contrary),ctx.getResources().getString(R.string.ability_description_contrary)).addEffect(new Effect((game, player, opponent) -> {
            player.setLevel(Attributes.HP, player.getLevel(Attributes.HP) * -1);
            player.setLevel(Attributes.ATK, player.getLevel(Attributes.ATK) * -1);
            player.setLevel(Attributes.DEF, player.getLevel(Attributes.DEF) * -1);
            player.setLevel(Attributes.SPATK, player.getLevel(Attributes.SPATK) * -1);
            player.setLevel(Attributes.SPDEF, player.getLevel(Attributes.SPDEF) * -1);
            player.setLevel(Attributes.SPD, player.getLevel(Attributes.SPD) * -1);
        }, -1, "ability"));
        ANALYTIC = new Ability(ctx.getResources().getString(R.string.ability_name_analytic),ctx.getResources().getString(R.string.ability_description_analytic)).addEffect(new Effect((game, player, opponent) -> {
            if (player.getPlayedCard().getAttribute(Attributes.SPD) < opponent.getPlayedCard().getAttribute(Attributes.SPD)) {
                player.incrementLevel(Attributes.ATK, 1);
                player.incrementLevel(Attributes.SPATK, 1);
            }
        }, 0, "ability"));
        PAY_DAY = new Ability(ctx.getResources().getString(R.string.ability_name_pay_day),ctx.getResources().getString(R.string.ability_description_pay_day)).addEffect(new Effect((game, player, opponent) -> game.setPrizePot(game.getPrizePot() * 2), 0, "ability"));
        SUBSTITUTE_DOLL = new Ability(ctx.getResources().getString(R.string.ability_name_substitute_doll),ctx.getResources().getString(R.string.ability_description_substitute_doll)).addEffect(new Effect((game, player, opponent) -> game.setPrizePot(0), 0, "ability"));
        DRY_SKIN = new Ability(ctx.getResources().getString(R.string.ability_name_dry_skin),ctx.getResources().getString(R.string.ability_description_dry_skin)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getFieldEffect() == FieldEffect.SUN) player.incrementLevel(Attributes.HP, -1);
            if (game.getFieldEffect() == FieldEffect.RAIN) player.incrementLevel(Attributes.HP, 1);
        }, 0, "ability"));
        SOLAR_POWER = new Ability(ctx.getResources().getString(R.string.ability_name_solar_power),ctx.getResources().getString(R.string.ability_description_solar_power)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getFieldEffect() == FieldEffect.SUN) {
                player.incrementLevel(Attributes.HP, -1);
                player.incrementLevel(Attributes.SPATK, 1);}
        }, 0, "ability"));
        SAND_RUSH = new Ability(ctx.getResources().getString(R.string.ability_name_sand_rush),ctx.getResources().getString(R.string.ability_description_sand_rush)).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.SANDSTORM) player.incrementLevel(Attributes.SPD, 1);}, 0, "ability"));
        SLUSH_RUSH = new Ability(ctx.getResources().getString(R.string.ability_name_slush_rush),ctx.getResources().getString(R.string.ability_description_slush_rush)).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.SNOW) player.incrementLevel(Attributes.SPD, 1);}, 0, "ability"));
        EARTH_EATER = new Ability(ctx.getResources().getString(R.string.ability_name_earth_eater),ctx.getResources().getString(R.string.ability_description_earth_eater)).addEffect(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), Type.GROUND)) player.incrementLevel(Attributes.HP, 1);}, 0, "ability"));
        WATER_ABSORB = new Ability(ctx.getResources().getString(R.string.ability_name_water_absorb),ctx.getResources().getString(R.string.ability_description_water_absorb)).addEffect(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), Type.WATER)) player.incrementLevel(Attributes.HP, 1);}, 0, "ability"));
        VOLT_ABSORB = new Ability(ctx.getResources().getString(R.string.ability_name_volt_absorb),ctx.getResources().getString(R.string.ability_description_volt_absorb)).addEffect(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), Type.ELECTRIC)) player.incrementLevel(Attributes.HP, 1);}, 0, "ability"));
        LIGHTNING_ROD = new Ability(ctx.getResources().getString(R.string.ability_name_lightning_rod),ctx.getResources().getString(R.string.ability_description_lightning_rod)).addEffect(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), Type.ELECTRIC)) player.incrementLevel(Attributes.SPATK, 1);}, 0, "ability"));
        STORM_DRAIN = new Ability(ctx.getResources().getString(R.string.ability_name_storm_drain),ctx.getResources().getString(R.string.ability_description_storm_drain)).addEffect(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), Type.WATER)) player.incrementLevel(Attributes.SPATK, 1);}, 0, "ability"));
        SAP_SIPPER = new Ability(ctx.getResources().getString(R.string.ability_name_sap_sipper),ctx.getResources().getString(R.string.ability_description_sap_sipper)).addEffect(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), Type.GRASS)) player.incrementLevel(Attributes.ATK, 1);}, 0, "ability"));
        WATER_COMPACTION = new Ability(ctx.getResources().getString(R.string.ability_name_water_compaction),ctx.getResources().getString(R.string.ability_description_water_compaction)).addEffect(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), Type.WATER)) player.incrementLevel(Attributes.DEF, 1);}, 0, "ability"));
        WATER_BUBBLE = new Ability(ctx.getResources().getString(R.string.ability_name_water_bubble),ctx.getResources().getString(R.string.ability_description_water_bubble)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.checkType(opponent.getPlayedCard(), Type.FIRE)) player.incrementLevel(Attributes.DEF, 1);
            if (game.getFieldEffect() == FieldEffect.RAIN) player.incrementLevel(Attributes.ATK, 1);
        }, 0, "ability"));
        TECHNICIAN = new Ability(ctx.getResources().getString(R.string.ability_name_technician),ctx.getResources().getString(R.string.ability_description_technician)).addEffect(new Effect((game, player, opponent) -> {
            if (player.getPlayedCard().getAttribute(Attributes.HP) <= 60) player.incrementLevel(Attributes.HP, 1);
            if (player.getPlayedCard().getAttribute(Attributes.ATK) <= 60) player.incrementLevel(Attributes.ATK, 1);
            if (player.getPlayedCard().getAttribute(Attributes.DEF) <= 60) player.incrementLevel(Attributes.DEF, 1);
            if (player.getPlayedCard().getAttribute(Attributes.SPATK) <= 60) player.incrementLevel(Attributes.SPATK, 1);
            if (player.getPlayedCard().getAttribute(Attributes.SPDEF) <= 60) player.incrementLevel(Attributes.SPDEF, 1);
            if (player.getPlayedCard().getAttribute(Attributes.SPD) <= 60) player.incrementLevel(Attributes.SPD, 1);
        }, 0, "ability"));
        OPPORTUNIST = new Ability(ctx.getResources().getString(R.string.ability_name_opportunist),ctx.getResources().getString(R.string.ability_description_opportunist)).addEffect(new Effect((game, player, opponent) -> {
            player.setLevel(Attributes.HP, opponent.getLevel(Attributes.HP));
            player.setLevel(Attributes.ATK, opponent.getLevel(Attributes.ATK));
            player.setLevel(Attributes.DEF, opponent.getLevel(Attributes.DEF));
            player.setLevel(Attributes.SPATK, opponent.getLevel(Attributes.SPATK));
            player.setLevel(Attributes.SPDEF, opponent.getLevel(Attributes.SPDEF));
            player.setLevel(Attributes.SPD, opponent.getLevel(Attributes.SPD));
        }, -1, "ability"));
        SIMPLE = new Ability(ctx.getResources().getString(R.string.ability_name_simple),ctx.getResources().getString(R.string.ability_description_simple)).addEffect(new Effect((game, player, opponent) -> {
            player.setLevel(Attributes.HP, player.getLevel(Attributes.HP) * 2);
            player.setLevel(Attributes.ATK, player.getLevel(Attributes.ATK) * 2);
            player.setLevel(Attributes.DEF, player.getLevel(Attributes.DEF) * 2);
            player.setLevel(Attributes.SPATK, player.getLevel(Attributes.SPATK) * 2);
            player.setLevel(Attributes.SPDEF, player.getLevel(Attributes.SPDEF) * 2);
            player.setLevel(Attributes.SPD, player.getLevel(Attributes.SPD) * 2);
        }, -1, "ability"));
        CLEAR_BODY = new Ability(ctx.getResources().getString(R.string.ability_name_clear_body),ctx.getResources().getString(R.string.ability_description_clear_body)).addEffect(new Effect((game, player, opponent) -> {
            player.setLevel(Attributes.HP, Math.max(player.getLevel(Attributes.HP), 0));
            player.setLevel(Attributes.ATK, Math.max(player.getLevel(Attributes.ATK), 0));
            player.setLevel(Attributes.DEF, Math.max(player.getLevel(Attributes.DEF), 0));
            player.setLevel(Attributes.SPATK, Math.max(player.getLevel(Attributes.SPATK), 0));
            player.setLevel(Attributes.SPDEF, Math.max(player.getLevel(Attributes.SPDEF), 0));
            player.setLevel(Attributes.SPD, Math.max(player.getLevel(Attributes.SPD), 0));
        }, -1, "ability"));
        MOODY = new Ability(ctx.getResources().getString(R.string.ability_name_moody),ctx.getResources().getString(R.string.ability_description_moody)).addEffect(new Effect((game, player, opponent) -> {
            Random rand = new Random();
            Attributes[] stats = {Attributes.HP, Attributes.ATK, Attributes.DEF, Attributes.SPATK, Attributes.SPDEF, Attributes.SPD};
            int i1 = rand.nextInt(6);
            int i2 = rand.nextInt(6);
            player.setLevel(stats[i1], player.getLevel(stats[i1]) + 1);
            player.setLevel(stats[i2], player.getLevel(stats[i2]) - 1);
        }, 0, "ability"));
        HARVEST = new Ability(ctx.getResources().getString(R.string.ability_name_harvest),ctx.getResources().getString(R.string.ability_description_harvest)).addEffect(new Effect((game, player, opponent) -> player.addItem(game.generateBerry()), 0, "ability"));
        CUD_CHEW = new Ability(ctx.getResources().getString(R.string.ability_name_cud_chew),ctx.getResources().getString(R.string.ability_description_cud_chew)).addEffect(new Effect((game, player, opponent) -> {
            if (player.getPlayedItem() != null) {
                if (player.getPlayedItem().isBerry()) player.addItem(player.getPlayedItem());}
        }, 0, "ability"));
        PRESENT = new Ability(ctx.getResources().getString(R.string.ability_name_present),ctx.getResources().getString(R.string.ability_description_present)).addEffect(new Effect((game, player, opponent) -> {
            Random rand = new Random();
            int i = rand.nextInt(10);
            Attributes[] stats = {Attributes.HP, Attributes.ATK, Attributes.DEF, Attributes.SPATK, Attributes.SPDEF, Attributes.SPD};
            int i1 = rand.nextInt(6);
            if (i < 3) {player.setLevel(stats[i1], player.getLevel(stats[i1]) - 1);}
            if (i >= 3 && i < 7) {player.setLevel(stats[i1], player.getLevel(stats[i1]) + 1);}
            if (i >= 7) {player.addItem(game.generateItem());}
        }, 0, "ability"));
        GLUTTONY = new Ability(ctx.getResources().getString(R.string.ability_name_gluttony),ctx.getResources().getString(R.string.ability_description_gluttony));
        RIPEN = new Ability(ctx.getResources().getString(R.string.ability_name_ripen),ctx.getResources().getString(R.string.ability_description_ripen));
        LEAF_GUARD = new Ability(ctx.getResources().getString(R.string.ability_name_leaf_guard),ctx.getResources().getString(R.string.ability_description_leaf_guard)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getFieldEffect() == FieldEffect.SUN) {
                player.setLevel(Attributes.HP, Math.max(player.getLevel(Attributes.HP), 0));
                player.setLevel(Attributes.ATK, Math.max(player.getLevel(Attributes.ATK), 0));
                player.setLevel(Attributes.DEF, Math.max(player.getLevel(Attributes.DEF), 0));
                player.setLevel(Attributes.SPATK, Math.max(player.getLevel(Attributes.SPATK), 0));
                player.setLevel(Attributes.SPDEF, Math.max(player.getLevel(Attributes.SPDEF), 0));
                player.setLevel(Attributes.SPD, Math.max(player.getLevel(Attributes.SPD), 0));
            }
        }, -1, "ability"));
        HYDRATION = new Ability(ctx.getResources().getString(R.string.ability_name_hydration),ctx.getResources().getString(R.string.ability_description_hydration)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getFieldEffect() == FieldEffect.RAIN) {
                player.setLevel(Attributes.HP, Math.max(player.getLevel(Attributes.HP), 0));
                player.setLevel(Attributes.ATK, Math.max(player.getLevel(Attributes.ATK), 0));
                player.setLevel(Attributes.DEF, Math.max(player.getLevel(Attributes.DEF), 0));
                player.setLevel(Attributes.SPATK, Math.max(player.getLevel(Attributes.SPATK), 0));
                player.setLevel(Attributes.SPDEF, Math.max(player.getLevel(Attributes.SPDEF), 0));
                player.setLevel(Attributes.SPD, Math.max(player.getLevel(Attributes.SPD), 0));
            }
        }, -1, "ability"));
        SCHOOLING = new Ability(ctx.getResources().getString(R.string.ability_name_schooling),ctx.getResources().getString(R.string.ability_description_schooling)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.getPoints() < 5 && Objects.equals(player.getPlayedCard().getName(), ctx.getResources().getString(R.string.pokemon_name_wishiwashi))) {player.setPlayedCard(CardRegistry.initCard(ctx, CardRegistry.WISHIWASHI_SCHOOLING));}
        }, 1, "ability"));
        SHIELDS_DOWN = new Ability(ctx.getResources().getString(R.string.ability_name_shields_down),ctx.getResources().getString(R.string.ability_description_shields_down)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.getPoints() >= 3 && Objects.equals(player.getPlayedCard().getName(), ctx.getResources().getString(R.string.pokemon_name_minior))) {player.setPlayedCard(CardRegistry.initCard(ctx, CardRegistry.MINIOR_CORE));}
        }, 1, "ability"));
        SPEED_BOOST = new Ability(ctx.getResources().getString(R.string.ability_name_speed_boost),ctx.getResources().getString(R.string.ability_description_speed_boost)).addEffect(new Effect((game, player, opponent) -> player.incrementLevel(Attributes.SPD, 1), 0, "ability"));
        STAMINA = new Ability(ctx.getResources().getString(R.string.ability_name_stamina),ctx.getResources().getString(R.string.ability_description_stamina)).addEffect(new Effect((game, player, opponent) -> player.incrementLevel(Attributes.DEF, 1), 0, "ability"));
        CAMOUFLAGE = new Ability(ctx.getResources().getString(R.string.ability_name_camouflage),ctx.getResources().getString(R.string.ability_description_camouflage)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getFieldEffect() == FieldEffect.RAIN) player.getPlayedCard().setType(Type.WATER);
            if (game.getFieldEffect() == FieldEffect.SUN) player.getPlayedCard().setType(Type.FIRE);
            if (game.getFieldEffect() == FieldEffect.SNOW) player.getPlayedCard().setType(Type.ICE);
            if (game.getFieldEffect() == FieldEffect.SANDSTORM) player.getPlayedCard().setType(Type.GROUND);
            if (game.getFieldEffect() == FieldEffect.GRASSY_TERRAIN) player.getPlayedCard().setType(Type.GRASS);
            if (game.getFieldEffect() == FieldEffect.ELECTRIC_TERRAIN) player.getPlayedCard().setType(Type.ELECTRIC);
            if (game.getFieldEffect() == FieldEffect.MISTY_TERRAIN) player.getPlayedCard().setType(Type.FAIRY);
            if (game.getFieldEffect() == FieldEffect.PSYCHIC_TERRAIN) player.getPlayedCard().setType(Type.PSYCHIC);
            if (game.getFieldEffect() == FieldEffect.NONE) player.getPlayedCard().setType(Type.NORMAL);
            if (game.getFieldEffect() == FieldEffect.WIND) player.getPlayedCard().setType(Type.NORMAL);
            if (game.getFieldEffect() == FieldEffect.DARKNESS) player.getPlayedCard().setType(Type.DARK);
        }, 0, "ability"));
        SOLID_ROCK = new Ability(ctx.getResources().getString(R.string.ability_name_solid_rock),ctx.getResources().getString(R.string.ability_description_solid_rock)).addEffect(new Effect((game, player, opponent) -> opponent.setCritRate(0), -1, "ability"));
        DEFEATIST = new Ability(ctx.getResources().getString(R.string.ability_name_defeatist),ctx.getResources().getString(R.string.ability_description_defeatist)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.getPoints() >= 3) {
                player.incrementLevel(Attributes.ATK, -1);
                player.incrementLevel(Attributes.SPATK, -1);
            }
        }, 0, "ability"));
        SLOW_START = new Ability(ctx.getResources().getString(R.string.ability_name_slow_start),ctx.getResources().getString(R.string.ability_description_slow_start)).addEffect(new Effect((game, player, opponent) -> {
            if (!game.checkAttribute(Attributes.HP)) player.incrementLevel(Attributes.HP, -1);
            if (!game.checkAttribute(Attributes.ATK)) player.incrementLevel(Attributes.ATK, -1);
            if (!game.checkAttribute(Attributes.DEF)) player.incrementLevel(Attributes.DEF, -1);
            if (!game.checkAttribute(Attributes.SPATK)) player.incrementLevel(Attributes.SPATK, -1);
            if (!game.checkAttribute(Attributes.SPDEF)) player.incrementLevel(Attributes.SPDEF, -1);
            if (!game.checkAttribute(Attributes.SPD)) player.incrementLevel(Attributes.SPD, -1);
        }, 0, "ability"));
        SUBSTITUTE = new Ability(ctx.getResources().getString(R.string.ability_name_substitute),ctx.getResources().getString(R.string.ability_description_substitute)).addEffect(new Effect((game, player, opponent) -> player.addCard(CardRegistry.initCard(ctx, CardRegistry.SUBSTITUTE), 0), 0, "ability"));
        INTIMIDATE = new Ability(ctx.getResources().getString(R.string.ability_name_intimidate),ctx.getResources().getString(R.string.ability_description_intimidate)).addEffect(new Effect((game, player, opponent) -> opponent.setLevel(Attributes.ATK, opponent.getLevel(Attributes.ATK) - 1), 0, "ability"));
        MUMMY = new Ability(ctx.getResources().getString(R.string.ability_name_mummy),ctx.getResources().getString(R.string.ability_description_mummy)).addEffect(new Effect((game, player, opponent) -> {
            opponent.getPlayedCard().setAbility(Ability.MUMMY);
            game.removeEffectFromQueue("ability", opponent);
        }, 4, "ability"));
        KLUTZ = new Ability(ctx.getResources().getString(R.string.ability_name_klutz),ctx.getResources().getString(R.string.ability_description_klutz)).addEffect(new Effect((game, player, opponent) -> game.removeEffectFromQueue("item", player), 4, "ability"));
        FLUFFY = new Ability(ctx.getResources().getString(R.string.ability_name_fluffy),ctx.getResources().getString(R.string.ability_description_fluffy)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.checkType(opponent.getPlayedCard(), Type.FIGHTING)) player.setLevel(game.getChosenAttribute(), player.getLevel(game.getChosenAttribute()) + 1);
            if (opponent.checkType(opponent.getPlayedCard(), Type.FIRE)) player.setLevel(game.getChosenAttribute(), player.getLevel(game.getChosenAttribute()) - 1);
        }, 0, "ability"));
        THICK_FAT = new Ability(ctx.getResources().getString(R.string.ability_name_thick_fat),ctx.getResources().getString(R.string.ability_description_thick_fat)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.checkType(opponent.getPlayedCard(), Type.ICE)) player.setLevel(game.getChosenAttribute(), player.getLevel(game.getChosenAttribute()) + 1);
            if (opponent.checkType(opponent.getPlayedCard(), Type.FIRE)) player.setLevel(game.getChosenAttribute(), player.getLevel(game.getChosenAttribute()) + 1);
        }, 0, "ability"));
        FUR_COAT = new Ability(ctx.getResources().getString(R.string.ability_name_fur_coat),ctx.getResources().getString(R.string.ability_description_fur_coat)).addEffect(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), Type.FIGHTING)) player.setLevel(game.getChosenAttribute(), player.getLevel(game.getChosenAttribute()) + 1);}, 0, "ability"));
        HEATPROOF = new Ability(ctx.getResources().getString(R.string.ability_name_heatproof),ctx.getResources().getString(R.string.ability_description_heatproof)).addEffect(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), Type.FIRE)) player.setLevel(game.getChosenAttribute(), player.getLevel(game.getChosenAttribute()) + 1);}, 0, "ability"));
        JUSTIFIED = new Ability(ctx.getResources().getString(R.string.ability_name_justified),ctx.getResources().getString(R.string.ability_description_justified)).addEffect(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), Type.DARK)) player.setLevel(game.getChosenAttribute(), player.getLevel(game.getChosenAttribute()) + 1);}, 0, "ability"));
        RATTLED = new Ability(ctx.getResources().getString(R.string.ability_name_rattled),ctx.getResources().getString(R.string.ability_description_rattled)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.checkType(opponent.getPlayedCard(), Type.GRASS) || opponent.checkType(opponent.getPlayedCard(), Type.PSYCHIC) || opponent.checkType(opponent.getPlayedCard(), Type.DARK)) player.incrementLevel(Attributes.SPD, 1);
        }, 0, "ability"));
        WEAK_ARMOR = new Ability(ctx.getResources().getString(R.string.ability_name_weak_armor),ctx.getResources().getString(R.string.ability_description_weak_armor)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.checkType(opponent.getPlayedCard(), Type.FIGHTING)) {
                player.incrementLevel(Attributes.SPD, 1);
                player.incrementLevel(Attributes.DEF, -1);
            }
        }, 0, "ability"));
        NORMAL_AURA = new Ability(ctx.getResources().getString(R.string.ability_name_normal_aura),ctx.getResources().getString(R.string.ability_description_normal_aura)).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.NORMAL), 0, "ability"));
        FIRE_AURA = new Ability(ctx.getResources().getString(R.string.ability_name_fire_aura),ctx.getResources().getString(R.string.ability_description_fire_aura)).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.FIRE), 0, "ability"));
        WATER_AURA = new Ability(ctx.getResources().getString(R.string.ability_name_water_aura),ctx.getResources().getString(R.string.ability_description_water_aura)).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.WATER), 0, "ability"));
        GRASS_AURA = new Ability(ctx.getResources().getString(R.string.ability_name_grass_aura),ctx.getResources().getString(R.string.ability_description_grass_aura)).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.GRASS), 0, "ability"));
        ELECTRIC_AURA = new Ability(ctx.getResources().getString(R.string.ability_name_electric_aura),ctx.getResources().getString(R.string.ability_description_electric_aura)).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.ELECTRIC), 0, "ability"));
        ICE_AURA = new Ability(ctx.getResources().getString(R.string.ability_name_ice_aura),ctx.getResources().getString(R.string.ability_description_ice_aura)).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.ICE), 0, "ability"));
        PSYCHIC_AURA = new Ability(ctx.getResources().getString(R.string.ability_name_psychic_aura),ctx.getResources().getString(R.string.ability_description_psychic_aura)).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.PSYCHIC), 0, "ability"));
        DARK_AURA = new Ability(ctx.getResources().getString(R.string.ability_name_dark_aura),ctx.getResources().getString(R.string.ability_description_dark_aura)).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.DARK), 0, "ability"));
        FAIRY_AURA = new Ability(ctx.getResources().getString(R.string.ability_name_fairy_aura),ctx.getResources().getString(R.string.ability_description_fairy_aura)).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.FAIRY), 0, "ability"));
        FIGHTING_AURA = new Ability(ctx.getResources().getString(R.string.ability_name_fighting_aura),ctx.getResources().getString(R.string.ability_description_fighting_aura)).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.FIGHTING), 0, "ability"));
        GROUND_AURA = new Ability(ctx.getResources().getString(R.string.ability_name_ground_aura),ctx.getResources().getString(R.string.ability_description_ground_aura)).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.GROUND), 0, "ability"));
        STEEL_AURA = new Ability(ctx.getResources().getString(R.string.ability_name_steel_aura),ctx.getResources().getString(R.string.ability_description_steel_aura)).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.STEEL), 0, "ability"));
        DRAGON_AURA = new Ability(ctx.getResources().getString(R.string.ability_name_dragon_aura),ctx.getResources().getString(R.string.ability_description_dragon_aura)).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.DRAGON), 0, "ability"));

        CHARGE = new Ability(ctx.getResources().getString(R.string.ability_name_charge),ctx.getResources().getString(R.string.ability_description_charge)).addEffect(new Effect((game, player, opponent) -> player.setCharge(true), 0, "ability"));
        ELECTROMORPHOSIS = new Ability(ctx.getResources().getString(R.string.ability_name_electromorphosis),ctx.getResources().getString(R.string.ability_description_electromorphosis)).addEffect(new Effect((game, player, opponent) -> {
            if (player.getCharge()) {
                player.incrementLevel(Attributes.SPDEF, 1);
                player.setUsedCharge(true);
            }
            else {
                player.setCharge(true);
            }
        }, 0, "ability"));
        WIND_POWER = new Ability(ctx.getResources().getString(R.string.ability_name_wind_power),ctx.getResources().getString(R.string.ability_description_wind_power)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getFieldEffect() == FieldEffect.WIND) {
                player.setCharge(true);
                player.incrementLevel(Attributes.SPD, 1);
            }
        }, 0, "ability"));
        MOTOR_DRIVE = new Ability(ctx.getResources().getString(R.string.ability_name_motor_drive),ctx.getResources().getString(R.string.ability_description_motor_drive)).addEffect(new Effect((game, player, opponent) -> {
            if (player.getCharge()) {
                player.incrementLevel(Attributes.SPD, 1);
                player.setUsedCharge(true);
            }
        }, 0, "ability"));
        DANCER = new Ability(ctx.getResources().getString(R.string.ability_name_dancer),ctx.getResources().getString(R.string.ability_description_dancer)).addEffect(new Effect((game, player, opponent) -> {
            if (!Objects.equals(player.getDanceStat(), null)) {
                player.setLevel(player.getDanceStat(), player.getLevel(player.getDanceStat()) + 1);
            }
        }, 0, "ability")).addEffect(new Effect((game, player, opponent) -> {
            player.setDanceStat(game.getChosenAttribute());
            opponent.setDanceStat(game.getChosenAttribute());
        }, -1, "ability"));
        STATIC = new Ability(ctx.getResources().getString(R.string.ability_name_static),ctx.getResources().getString(R.string.ability_description_static)).addEffect(new Effect((game, player, opponent) -> {if (!opponent.checkType(opponent.getPlayedCard(), Type.ELECTRIC)) opponent.setStatusCondition(StatusCondition.PARALYZED);}, 0, "ability"));
        FLAME_BODY = new Ability(ctx.getResources().getString(R.string.ability_name_flame_body),ctx.getResources().getString(R.string.ability_description_flame_body)).addEffect(new Effect((game, player, opponent) -> {if (!opponent.checkType(opponent.getPlayedCard(), Type.FIRE)) opponent.setStatusCondition(StatusCondition.BURNED);}, 0, "ability"));
        ICE_BURN = new Ability(ctx.getResources().getString(R.string.ability_name_ice_burn),ctx.getResources().getString(R.string.ability_description_ice_burn)).addEffect(new Effect((game, player, opponent) -> {if (!opponent.checkType(opponent.getPlayedCard(), Type.ICE)) opponent.setStatusCondition(StatusCondition.FROSTBITTEN);}, 0, "ability"));
        TRI_ATTACK = new Ability(ctx.getResources().getString(R.string.ability_name_tri_attack),ctx.getResources().getString(R.string.ability_description_tri_attack)).addEffect(new Effect((game, player, opponent) -> {
            Random rand = new Random();
            int r = rand.nextInt(3);
            if (r == 0) opponent.setStatusCondition(StatusCondition.PARALYZED);
            if (r == 1) opponent.setStatusCondition(StatusCondition.BURNED);
            if (r == 2) opponent.setStatusCondition(StatusCondition.FROSTBITTEN);
        }, 0, "ability"));
        POISON_POINT = new Ability(ctx.getResources().getString(R.string.ability_name_poison_point),ctx.getResources().getString(R.string.ability_description_poison_point)).addEffect(new Effect((game, player, opponent) -> {if (!opponent.checkType(opponent.getPlayedCard(), Type.STEEL)) opponent.setStatusCondition(StatusCondition.POISONED);}, 0, "ability"));
        CONFUSE_RAY = new Ability(ctx.getResources().getString(R.string.ability_name_confuse_ray),ctx.getResources().getString(R.string.ability_description_confuse_ray)).addEffect(new Effect((game, player, opponent) -> opponent.setStatusCondition(StatusCondition.CONFUSED), 0, "ability"));
        HYPNOSIS = new Ability(ctx.getResources().getString(R.string.ability_name_hypnosis),ctx.getResources().getString(R.string.ability_description_hypnosis)).addEffect(new Effect((game, player, opponent) -> opponent.setStatusCondition(StatusCondition.ASLEEP), 0, "ability"));
        FROZEN_BODY = new Ability(ctx.getResources().getString(R.string.ability_name_frozen_body),ctx.getResources().getString(R.string.ability_description_frozen_body)).addEffect(new Effect((game, player, opponent) -> {if (!opponent.checkType(opponent.getPlayedCard(), Type.ICE)) opponent.setStatusCondition(StatusCondition.FROZEN);}, 0, "ability"));
        FACADE = new Ability(ctx.getResources().getString(R.string.ability_name_facade),ctx.getResources().getString(R.string.ability_description_facade)).addEffect(new Effect((game, player, opponent) -> {
            if (player.getStatusCondition() != StatusCondition.NONE) {
                player.incrementLevel(Attributes.HP, 1);
                player.incrementLevel(Attributes.ATK, 1);
                player.incrementLevel(Attributes.DEF, 1);
                player.incrementLevel(Attributes.SPATK, 1);
                player.incrementLevel(Attributes.SPDEF, 1);
                player.incrementLevel(Attributes.SPD, 1);
            }
        }, 0, "ability"));
        NATURAL_CURE = new Ability(ctx.getResources().getString(R.string.ability_name_natural_cure),ctx.getResources().getString(R.string.ability_description_natural_cure)).addEffect(new Effect((game, player, opponent) -> player.setStatusCondition(StatusCondition.NONE), 0, "ability"));
        CORROSION = new Ability(ctx.getResources().getString(R.string.ability_name_corrosion),ctx.getResources().getString(R.string.ability_description_corrosion)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.getStatusCondition() == StatusCondition.POISONED) {
                player.setStatusLevel(1);
            }
            else {
                opponent.setStatusCondition(StatusCondition.POISONED);
            }
        }, 0, "ability"));
        TOXIC_DEBRIS = new Ability(ctx.getResources().getString(R.string.ability_name_toxic_debris),ctx.getResources().getString(R.string.ability_description_toxic_debris)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.getStatusCondition() == StatusCondition.POISONED) {
                player.incrementBreakPoints(3);
            }
        }, 0, "ability"));
        BEGINNERS_LUCK = new Ability(ctx.getResources().getString(R.string.ability_name_beginners_luck),ctx.getResources().getString(R.string.ability_description_beginners_luck)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.getPlayedCard().hasMegaEvolved()) {
                player.setAutoWin(true);
            }
        }, 0, "ability"));
        VICTORY_STAR = new Ability(ctx.getResources().getString(R.string.ability_name_victory_star),ctx.getResources().getString(R.string.ability_description_victory_star)).addEffect(new Effect((game, player, opponent) -> game.removeEffectFromQueue("break", player), 2, "ability"));
        STORM_WINGS = new Ability(ctx.getResources().getString(R.string.ability_name_storm_wings),ctx.getResources().getString(R.string.ability_description_storm_wings)).addEffect(new Effect((game, player, opponent) -> {
            player.addDelayedEffect(new Effect((game1, player1, opponent1) -> {
                if (game1.getFieldEffect() == FieldEffect.RAIN || game1.getFieldEffect() == FieldEffect.ELECTRIC_TERRAIN || game1.getFieldEffect() == FieldEffect.WIND) {
                    player1.setLevel(Attributes.SPD, player1.getLevel(Attributes.SPD) + 1);
                }
            }, 0, "ability"), 3);
        }, 0, "ability"));
        GRASS_PELT = new Ability(ctx.getResources().getString(R.string.ability_name_grass_pelt),ctx.getResources().getString(R.string.ability_description_grass_pelt)).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.GRASSY_TERRAIN) player.incrementLevel(Attributes.DEF, 1);}, 0, "ability"));
        SURGE_SURFER = new Ability(ctx.getResources().getString(R.string.ability_name_surge_surfer),ctx.getResources().getString(R.string.ability_description_surge_surfer)).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.ELECTRIC_TERRAIN) player.incrementLevel(Attributes.SPD, 1);}, 0, "ability"));
        CHEEK_POUCH = new Ability(ctx.getResources().getString(R.string.ability_name_cheek_pouch),ctx.getResources().getString(R.string.ability_description_cheek_pouch));
        ROUGH_SKIN = new Ability(ctx.getResources().getString(R.string.ability_name_rough_skin),ctx.getResources().getString(R.string.ability_description_rough_skin)).addEffect(new Effect((game, player, opponent) -> opponent.incrementBreakPoints(1), 0, "ability"));
        SOLAR_BLADE = new Ability(ctx.getResources().getString(R.string.ability_name_solar_blade),ctx.getResources().getString(R.string.ability_description_solar_blade)).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.SUN) player.incrementLevel(Attributes.ATK, 1);}, 0, "ability"));
        FLOWER_VEIL = new Ability(ctx.getResources().getString(R.string.ability_name_flower_veil),ctx.getResources().getString(R.string.ability_description_flower_veil)).addEffect(new Effect((game, player, opponent) -> {
            player.addDelayedEffect(new Effect((game1, player1, opponent1) -> {
                if (game1.getFieldEffect() == FieldEffect.SUN) {
                    player1.setLevel(Attributes.HP, Math.max(player1.getLevel(Attributes.HP), 0));
                    player1.setLevel(Attributes.ATK, Math.max(player1.getLevel(Attributes.ATK), 0));
                    player1.setLevel(Attributes.DEF, Math.max(player1.getLevel(Attributes.DEF), 0));
                    player1.setLevel(Attributes.SPATK, Math.max(player1.getLevel(Attributes.SPATK), 0));
                    player1.setLevel(Attributes.SPDEF, Math.max(player1.getLevel(Attributes.SPDEF), 0));
                    player1.setLevel(Attributes.SPD, Math.max(player1.getLevel(Attributes.SPD), 0));
                }
            }, 0, "ability"), 3);
        }, 0, "ability"));
        AURORA_VEIL = new Ability(ctx.getResources().getString(R.string.ability_name_aurora_veil),ctx.getResources().getString(R.string.ability_description_aurora_veil)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getFieldEffect() == FieldEffect.SNOW) {
                player.addDelayedEffect(new Effect((game1, player1, opponent1) -> {
                    player.incrementLevel(Attributes.DEF, 1);
                    player.incrementLevel(Attributes.SPDEF, 1);
                }, 0, "ability"), 5);
            }
        }, 0, "ability"));
        GALE_WINGS = new Ability(ctx.getResources().getString(R.string.ability_name_gale_wings),ctx.getResources().getString(R.string.ability_description_gale_wings)).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.WIND) player.setSpeed(1);}, 0, "ability"));
        WIND_RIDER = new Ability(ctx.getResources().getString(R.string.ability_name_wind_rider),ctx.getResources().getString(R.string.ability_description_wind_rider)).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.WIND) player.incrementLevel(Attributes.SPD, 1);}, 0, "ability"));
        AIR_CUTTER = new Ability(ctx.getResources().getString(R.string.ability_name_air_cutter),ctx.getResources().getString(R.string.ability_description_air_cutter)).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.WIND) player.incrementCritRate(2);}, 0, "ability"));
        FLASH_FIRE = new Ability(ctx.getResources().getString(R.string.ability_name_flash_fire),ctx.getResources().getString(R.string.ability_description_flash_fire)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.checkType(opponent.getPlayedCard(), Type.FIRE) || player.getCurrentAura() == Aura.FIRE || player.getStatusCondition() == StatusCondition.BURNED) {
                if (player.getStatusCondition() == StatusCondition.BURNED) game.removeEffectFromQueue("status", player);
                player.incrementLevel(Attributes.ATK, 1);
            }
        }, 1, "ability"));
        GOOEY = new Ability(ctx.getResources().getString(R.string.ability_name_gooey),ctx.getResources().getString(R.string.ability_description_gooey)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getFieldEffect() == FieldEffect.RAIN) {
                player.addItem(player.getPlayedItem());
                opponent.incrementLevel(Attributes.SPD, -1);
            }
        }, 0, "ability"));
        AURA_SHIFT = new Ability(ctx.getResources().getString(R.string.ability_name_aura_shift),ctx.getResources().getString(R.string.ability_description_aura_shift)).addEffect(new Effect((game, player, opponent) -> {
            player.setCurrentAura(Aura.ELECTRIC);
            player.addDelayedEffect(new Effect((game1, player1, opponent1) -> {
                if (player1.getCurrentAura() == Aura.ELECTRIC) player1.setCurrentAura(Aura.DARK);
                else player1.setCurrentAura(Aura.ELECTRIC);
            }, 0, "ability"), 3);
        }, 0, "ability"));
        AURA_BREAK = new Ability(ctx.getResources().getString(R.string.ability_name_aura_break),ctx.getResources().getString(R.string.ability_description_aura_break)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.getCurrentAura() != Aura.NONE) {
                opponent.setCurrentAura(Aura.NONE);
                opponent.incrementBreakPoints(5);
            }
        }, 0, "ability"));
        COMATOSE = new Ability(ctx.getResources().getString(R.string.ability_name_comatose),ctx.getResources().getString(R.string.ability_description_comatose)).addEffect(new Effect((game, player, opponent) -> player.setStatusCondition(StatusCondition.ASLEEP), 0, "ability"));
        LUNAR_RUSH = new Ability(ctx.getResources().getString(R.string.ability_name_lunar_rush),ctx.getResources().getString(R.string.ability_description_lunar_rush)).addEffect(new Effect((game, player, opponent) -> {if (player.getStatusCondition() == StatusCondition.ASLEEP) player.incrementLevel(Attributes.SPD, 2);}, 0, "ability"));
        HEX = new Ability(ctx.getResources().getString(R.string.ability_name_hex),ctx.getResources().getString(R.string.ability_description_hex)).addEffect(new Effect((game, player, opponent) -> {if (opponent.getStatusCondition() != StatusCondition.NONE) opponent.incrementBreakPoints(2);}, 0, "ability"));
        VENOSHOCK = new Ability(ctx.getResources().getString(R.string.ability_name_venoshock),ctx.getResources().getString(R.string.ability_description_venoshock)).addEffect(new Effect((game, player, opponent) -> {if (opponent.getStatusCondition() == StatusCondition.POISONED) opponent.incrementBreakPoints(3);}, 0, "ability"));
        BLOOD_MOON_FRENZY = new Ability(ctx.getResources().getString(R.string.ability_name_blood_moon_frenzy),ctx.getResources().getString(R.string.ability_description_blood_moon_frenzy)).addEffect(new Effect((game, player, opponent) -> {
            if (player.getStatusCondition() == StatusCondition.ASLEEP) {
                opponent.setBreakPoints(4);
                player.setBreakPoints(2);
                player.setStatusCondition(StatusCondition.NONE);
            }
        }, 0, "ability"));
        SKILL_LINK = new Ability(ctx.getResources().getString(R.string.ability_name_skill_link),ctx.getResources().getString(R.string.ability_description_skill_link)).addEffect(new Effect((game, player, opponent) -> {if (game.getChosenAttribute() == game.getPreviousAttribute()) player.incrementLevel(game.getChosenAttribute(), 1);}, 0, "ability"));
        ELECTROBLAST = new Ability(ctx.getResources().getString(R.string.ability_name_electroblast),ctx.getResources().getString(R.string.ability_description_electroblast)).addEffect(new Effect((game, player, opponent) -> {
            if (player.getCharge()) {
                player.setUsedCharge(true);
                game.setFieldEffect(FieldEffect.ELECTRIC_TERRAIN);
                player.incrementLevel(Attributes.SPD, 1);
            }
        }, 0, "ability"));
        CHLOROBLAST = new Ability(ctx.getResources().getString(R.string.ability_name_chloroblast),ctx.getResources().getString(R.string.ability_description_chloroblast)).addEffect(new Effect((game, player, opponent) -> {
            if (player.getCharge()) {
                player.setUsedCharge(true);
                game.setFieldEffect(FieldEffect.GRASSY_TERRAIN);
                player.incrementLevel(Attributes.HP, 1);
            }
        }, 0, "ability"));
        PROTOSYNTHESIS = new Ability(ctx.getResources().getString(R.string.ability_name_protosynthesis),ctx.getResources().getString(R.string.ability_description_protosynthesis)).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.SUN) player.incrementLevel(player.getPlayedCard().getHighestStat(), 1);}, 0, "ability"));
        QUARK_DRIVE = new Ability(ctx.getResources().getString(R.string.ability_name_quark_drive),ctx.getResources().getString(R.string.ability_description_quark_drive)).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.ELECTRIC_TERRAIN) player.incrementLevel(player.getPlayedCard().getHighestStat(), 1);}, 0, "ability"));
        ORICHALCUM_PULSE = new Ability(ctx.getResources().getString(R.string.ability_name_orichalcum_pulse),ctx.getResources().getString(R.string.ability_description_orichalcum_pulse)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getFieldEffect() == FieldEffect.SUN) player.incrementLevel(Attributes.ATK, 1);
            game.setFieldEffect(FieldEffect.SUN);
        }, 0, "ability"));
        HADRON_ENGINE = new Ability(ctx.getResources().getString(R.string.ability_name_hadron_engine),ctx.getResources().getString(R.string.ability_description_hadron_engine)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getFieldEffect() == FieldEffect.ELECTRIC_TERRAIN) player.incrementLevel(Attributes.SPATK, 1);
            game.setFieldEffect(FieldEffect.ELECTRIC_TERRAIN);
        }, 0, "ability"));
        BLEAKWIND_STORM = new Ability(ctx.getResources().getString(R.string.ability_name_bleakwind_storm),ctx.getResources().getString(R.string.ability_description_bleakwind_storm)).addEffect(new Effect((game, player, opponent) -> {
            opponent.incrementLevel(Attributes.SPATK, -1);
            if (game.getFieldEffect() == FieldEffect.WIND) {opponent.setStatusCondition(StatusCondition.FROSTBITTEN);}
        }, 0, "ability"));
        WILDBOLT_STORM = new Ability(ctx.getResources().getString(R.string.ability_name_wildbolt_storm),ctx.getResources().getString(R.string.ability_description_wildbolt_storm)).addEffect(new Effect((game, player, opponent) -> {
            opponent.incrementLevel(Attributes.SPD, -1);
            if (game.getFieldEffect() == FieldEffect.RAIN) {opponent.setStatusCondition(StatusCondition.PARALYZED);}
        }, 0, "ability"));
        SANDSEAR_STORM = new Ability(ctx.getResources().getString(R.string.ability_name_sandsear_storm),ctx.getResources().getString(R.string.ability_description_sandsear_storm)).addEffect(new Effect((game, player, opponent) -> {
            opponent.incrementLevel(Attributes.ATK, -1);
            if (game.getFieldEffect() == FieldEffect.SANDSTORM) {opponent.setStatusCondition(StatusCondition.BURNED);}
        }, 0, "ability"));
        SPRINGTIDE_STORM = new Ability(ctx.getResources().getString(R.string.ability_name_springtide_storm),ctx.getResources().getString(R.string.ability_description_springtide_storm)).addEffect(new Effect((game, player, opponent) -> {
            opponent.incrementLevel(Attributes.SPDEF, -1);
            if (game.getFieldEffect() == FieldEffect.MISTY_TERRAIN) {
                opponent.addDelayedEffect(new Effect((game1, player1, opponent1) -> {player1.incrementLevel(Attributes.SPDEF, -1);}, 0, "ability"), 3);
            }
        }, 0, "ability"));
        NIGHTMARE = new Ability(ctx.getResources().getString(R.string.ability_name_nightmare),ctx.getResources().getString(R.string.ability_description_nightmare)).addEffect(new Effect((game, player, opponent) -> {
            opponent.addDelayedEffect(new Effect((game1, player1, opponent1) -> {
                if (player1.getStatusCondition() == StatusCondition.ASLEEP) {
                    player1.incrementBreakPoints(2);
                }
            }, 0, "ability"), 3);
        }, 0, "ability"));
        MERCILESS = new Ability(ctx.getResources().getString(R.string.ability_name_merciless),ctx.getResources().getString(R.string.ability_description_merciless)).addEffect(new Effect((game, player, opponent) -> {if (opponent.getStatusCondition() == StatusCondition.POISONED) player.setCritRate(4);}, 0, "ability"));
        STEALTH_ROCK = new Ability(ctx.getResources().getString(R.string.ability_name_stealth_rock),ctx.getResources().getString(R.string.ability_description_stealth_rock)).addEffect(new Effect((game, player, opponent) -> {
            opponent.addDelayedEffect(new Effect((game1, player1, opponent1) -> {
                if (player1.getPlayedCard().getWeakness() == Type.GROUND) player1.incrementBreakPoints(2);
                else player1.incrementBreakPoints(1);
            }, 0, "ability"), 3);
        }, 0, "ability"));
        MIST_BALL = new Ability(ctx.getResources().getString(R.string.ability_name_mist_ball),ctx.getResources().getString(R.string.ability_description_mist_ball)).addEffect(new Effect((game, player, opponent) -> opponent.incrementLevel(Attributes.SPATK, -1), 0, "ability"));
        LUSTER_PURGE = new Ability(ctx.getResources().getString(R.string.ability_name_luster_purge),ctx.getResources().getString(R.string.ability_description_luster_purge)).addEffect(new Effect((game, player, opponent) -> opponent.incrementLevel(Attributes.SPDEF, -1), 0, "ability"));
        ICY_TOUCH = new Ability(ctx.getResources().getString(R.string.ability_name_icy_touch),ctx.getResources().getString(R.string.ability_description_icy_touch)).addEffect(new Effect((game, player, opponent) -> {if (!opponent.checkType(opponent.getPlayedCard(), Type.ICE)) opponent.setStatusCondition(StatusCondition.FROZEN);}, 0, "ability"));
        ICE_BALL = new Ability(ctx.getResources().getString(R.string.ability_name_ice_ball),ctx.getResources().getString(R.string.ability_description_ice_ball)).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.SNOW) player.incrementLevel(Attributes.DEF, 1);}, 0, "ability"));
        BRAIN_FREEZE = new Ability(ctx.getResources().getString(R.string.ability_name_brain_freeze),ctx.getResources().getString(R.string.ability_description_brain_freeze)).addEffect(new Effect((game, player, opponent) -> {if (!opponent.checkType(opponent.getPlayedCard(), Type.ICE)) opponent.setStatusCondition(StatusCondition.FROSTBITTEN);}, 0, "ability"));
        LUNAR_BLESSING = new Ability(ctx.getResources().getString(R.string.ability_name_lunar_blessing),ctx.getResources().getString(R.string.ability_description_lunar_blessing)).addEffect(new Effect((game, player, opponent) -> {if (player.getStatusCondition() == StatusCondition.ASLEEP) {player.setStatusCondition(StatusCondition.NONE); player.incrementLevel(Attributes.HP, 1); player.setBreakPoints(0);}}, 0, "ability"));
        PERISH_SONG = new Ability(ctx.getResources().getString(R.string.ability_name_perish_song),ctx.getResources().getString(R.string.ability_description_perish_song)).addEffect(new Effect((game, player, opponent) -> {}, 0, "ability"));
        NIGHT_SLASH = new Ability(ctx.getResources().getString(R.string.ability_name_night_slash),ctx.getResources().getString(R.string.ability_description_night_slash)).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.DARKNESS) player.incrementCritRate(2);}, 0, "ability"));
        SPECTRAL_THIEF = new Ability(ctx.getResources().getString(R.string.ability_name_spectral_thief),ctx.getResources().getString(R.string.ability_description_spectral_thief)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getFieldEffect() == FieldEffect.DARKNESS) {
                player.setLevel(Attributes.HP, opponent.getLevel(Attributes.HP));
                player.setLevel(Attributes.ATK, opponent.getLevel(Attributes.ATK));
                player.setLevel(Attributes.DEF, opponent.getLevel(Attributes.DEF));
                player.setLevel(Attributes.SPATK, opponent.getLevel(Attributes.SPATK));
                player.setLevel(Attributes.SPDEF, opponent.getLevel(Attributes.SPDEF));
                player.setLevel(Attributes.SPD, opponent.getLevel(Attributes.SPD));
                opponent.setLevel(Attributes.HP, 0);
                opponent.setLevel(Attributes.ATK, 0);
                opponent.setLevel(Attributes.DEF, 0);
                opponent.setLevel(Attributes.SPATK, 0);
                opponent.setLevel(Attributes.SPDEF, 0);
                opponent.setLevel(Attributes.SPD, 0);
                opponent.incrementBreakPoints(Math.max(player.getLevel(Attributes.HP) + player.getLevel(Attributes.ATK) + player.getLevel(Attributes.DEF) + player.getLevel(Attributes.SPATK) + player.getLevel(Attributes.SPDEF) + player.getLevel(Attributes.SPD), 0));
            }
        }, 0, "ability"));
        SNIPER = new Ability(ctx.getResources().getString(R.string.ability_name_sniper),ctx.getResources().getString(R.string.ability_description_sniper)).addEffect(new Effect((game, player, opponent) -> player.setCritMultiplier(player.getCritMultiplier() * 1.5d), 0, "ability"));
        GUILLOTINE = new Ability(ctx.getResources().getString(R.string.ability_name_guillotine),ctx.getResources().getString(R.string.ability_description_guillotine)).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.DARKNESS) player.setCritPointMultiplier(2d);}, 0, "ability"));
        STANCE_CHANGE = new Ability(ctx.getResources().getString(R.string.ability_name_stance_change),ctx.getResources().getString(R.string.ability_description_stance_change)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getActivePlayer() == player && Objects.equals(player.getPlayedCard().getName(), ctx.getResources().getString(R.string.pokemon_name_aegislash))) {
                player.setPlayedCard(CardRegistry.initCard(ctx, CardRegistry.AEGISLASH_BLADE_FORME));
            }
        }, 0, "ability"));
        FAIRY_LOCK = new Ability(ctx.getResources().getString(R.string.ability_name_fairy_lock),ctx.getResources().getString(R.string.ability_description_fairy_lock)).addEffect(new Effect((game, player, opponent) -> {}, 0, "ability"));
        STEAM_ERUPTION = new Ability(ctx.getResources().getString(R.string.ability_name_steam_eruption),ctx.getResources().getString(R.string.ability_description_steam_eruption)).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.WATER)) {opponent.setStatusCondition(StatusCondition.BURNED); game.setFieldEffect(FieldEffect.RAIN); player.incrementLevel(Attributes.SPATK, 1);}}, 0, "ability"));
        TRIAGE = new Ability(ctx.getResources().getString(R.string.ability_name_triage),ctx.getResources().getString(R.string.ability_description_triage)).addEffect(new Effect((game, player, opponent) -> {}, 0, "ability"));
        CLANGING_SCALES = new Ability(ctx.getResources().getString(R.string.ability_name_clanging_scales),ctx.getResources().getString(R.string.ability_description_clanging_scales)).addEffect(new Effect((game, player, opponent) -> {}, 0, "ability"));
        THIEF = new Ability(ctx.getResources().getString(R.string.ability_name_thief),ctx.getResources().getString(R.string.ability_description_thief)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getFieldEffect() == FieldEffect.DARKNESS) {
                List<EffectAction> effects = game.getEffectsFromQueue("item", opponent);
                game.removeEffectFromQueue("item", opponent);
                for (EffectAction effect : effects) {
                    effect.setPlayer(player);
                    game.effectQueueAdd(effect);
                }
            }
        }, 0, "ability"));
        OBSTRUCT = new Ability(ctx.getResources().getString(R.string.ability_name_obstruct),ctx.getResources().getString(R.string.ability_description_obstruct)).addEffect(new Effect((game, player, opponent) -> {}, 0, "ability"));
        ICE_SCALES = new Ability(ctx.getResources().getString(R.string.ability_name_ice_scales),ctx.getResources().getString(R.string.ability_description_ice_scales)).addEffect(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), Type.PSYCHIC)) player.incrementLevel(game.getChosenAttribute(), 1);}, 0, "ability"));
        ICE_FACE = new Ability(ctx.getResources().getString(R.string.ability_name_ice_face),ctx.getResources().getString(R.string.ability_description_ice_face)).addEffect(new Effect((game, player, opponent) -> {}, 0, "ability"));
        WELL_BAKED_BODY = new Ability(ctx.getResources().getString(R.string.ability_name_well_baked_body),ctx.getResources().getString(R.string.ability_description_well_baked_body)).addEffect(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), Type.FIRE)) player.incrementLevel(Attributes.DEF, 1);}, 0, "ability"));
        GUARD_DOG = new Ability(ctx.getResources().getString(R.string.ability_name_guard_dog),ctx.getResources().getString(R.string.ability_description_guard_dog)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getFieldEffect() == FieldEffect.DARKNESS) {
                player.setLevel(Attributes.HP, Math.max(player.getLevel(Attributes.HP), player.getLevel(Attributes.HP) * -1));
                player.setLevel(Attributes.ATK, Math.max(player.getLevel(Attributes.ATK), player.getLevel(Attributes.ATK) * -1));
                player.setLevel(Attributes.DEF, Math.max(player.getLevel(Attributes.DEF), player.getLevel(Attributes.DEF) * -1));
                player.setLevel(Attributes.SPATK, Math.max(player.getLevel(Attributes.SPATK), player.getLevel(Attributes.SPATK) * -1));
                player.setLevel(Attributes.SPDEF, Math.max(player.getLevel(Attributes.SPDEF), player.getLevel(Attributes.SPDEF) * -1));
                player.setLevel(Attributes.SPD, Math.max(player.getLevel(Attributes.SPD), player.getLevel(Attributes.SPD) * -1));
            }
        }, -1, "ability"));
        THERMAL_EXCHANGE = new Ability(ctx.getResources().getString(R.string.ability_name_thermal_exchange),ctx.getResources().getString(R.string.ability_description_thermal_exchange)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getFieldEffect() == FieldEffect.SNOW) {
                player.incrementLevel(Attributes.DEF, 1);
            }
            if (game.getFieldEffect() == FieldEffect.SUN || opponent.checkType(opponent.getPlayedCard(), Type.FIRE)) {
                player.incrementLevel(Attributes.ATK, 1);
            }
        }, 0, "ability"));
        GOOD_AS_GOLD = new Ability(ctx.getResources().getString(R.string.ability_name_good_as_gold),ctx.getResources().getString(R.string.ability_description_good_as_gold)).addEffect(new Effect((game, player, opponent) -> {
            player.setStatusCondition(StatusCondition.NONE);
            player.setBreakPoints(0);
            player.setLevel(Attributes.HP, Math.max(player.getLevel(Attributes.HP), 0));
            player.setLevel(Attributes.ATK, Math.max(player.getLevel(Attributes.ATK), 0));
            player.setLevel(Attributes.DEF, Math.max(player.getLevel(Attributes.DEF), 0));
            player.setLevel(Attributes.SPATK, Math.max(player.getLevel(Attributes.SPATK), 0));
            player.setLevel(Attributes.SPDEF, Math.max(player.getLevel(Attributes.SPDEF), 0));
            player.setLevel(Attributes.SPD, Math.max(player.getLevel(Attributes.SPD), 0));
        }, 0, "ability"));
        TOXIC_CHAIN = new Ability(ctx.getResources().getString(R.string.ability_name_toxic_chain),ctx.getResources().getString(R.string.ability_description_toxic_chain)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.getStatusCondition() == StatusCondition.POISONED) {
                opponent.setStatusLevel(1);
            }
            else {
                opponent.setStatusCondition(StatusCondition.POISONED);
            }
        }, 0, "ability"));
        BOOMBURST = new Ability(ctx.getResources().getString(R.string.ability_name_boomburst),ctx.getResources().getString(R.string.ability_description_boomburst)).addEffect(new Effect((game, player, opponent) -> opponent.incrementBreakPoints(2), 0, "ability"));
        CHATTER = new Ability(ctx.getResources().getString(R.string.ability_name_chatter),ctx.getResources().getString(R.string.ability_description_chatter)).addEffect(new Effect((game, player, opponent) -> opponent.incrementBreakPoints(1), 0, "ability"));
        TABLETS_OF_RUIN = new Ability(ctx.getResources().getString(R.string.ability_name_tablets_of_ruin),ctx.getResources().getString(R.string.ability_description_tablets_of_ruin)).addEffect(new Effect((game, player, opponent) -> opponent.incrementLevel(Attributes.ATK, -1), 0, "ability"));
        SWORD_OF_RUIN = new Ability(ctx.getResources().getString(R.string.ability_name_sword_of_ruin),ctx.getResources().getString(R.string.ability_description_sword_of_ruin)).addEffect(new Effect((game, player, opponent) -> opponent.incrementLevel(Attributes.DEF, -1), 0, "ability"));
        VESSEL_OF_RUIN = new Ability(ctx.getResources().getString(R.string.ability_name_vessel_of_ruin),ctx.getResources().getString(R.string.ability_description_vessel_of_ruin)).addEffect(new Effect((game, player, opponent) -> opponent.incrementLevel(Attributes.SPATK, -1), 0, "ability"));
        BEADS_OF_RUIN = new Ability(ctx.getResources().getString(R.string.ability_name_beads_of_ruin),ctx.getResources().getString(R.string.ability_description_beads_of_ruin)).addEffect(new Effect((game, player, opponent) -> opponent.incrementLevel(Attributes.SPDEF, -1), 0, "ability"));
        TERA_SHIFT = new Ability(ctx.getResources().getString(R.string.ability_name_tera_shift),ctx.getResources().getString(R.string.ability_description_tera_shift)).addEffect(new Effect((game, player, opponent) -> {}, 0, "ability"));
        HARDEN = new Ability(ctx.getResources().getString(R.string.ability_name_harden),ctx.getResources().getString(R.string.ability_description_harden)).addEffect(new Effect((game, player, opponent) -> player.incrementLevel(Attributes.DEF, 1), 0, "ability"));

        MEGA_AIRSTREAM = new Ability(ctx.getResources().getString(R.string.ability_name_mega_airstream),ctx.getResources().getString(R.string.ability_description_mega_airstream)).addEffect(new Effect((game, player, opponent) -> {if (game.getMode() != "classic") game.setFieldEffect(FieldEffect.WIND);}, 0, "ability"));
        MEGA_DARKNESS = new Ability(ctx.getResources().getString(R.string.ability_name_mega_darkness),ctx.getResources().getString(R.string.ability_description_mega_darkness)).addEffect(new Effect((game, player, opponent) -> {if (game.getMode() != "classic") game.setFieldEffect(FieldEffect.DARKNESS);}, 0, "ability"));
        MEGA_FLARE = new Ability(ctx.getResources().getString(R.string.ability_name_mega_flare),ctx.getResources().getString(R.string.ability_description_mega_flare)).addEffect(new Effect((game, player, opponent) -> {if (game.getMode() != "classic") game.setFieldEffect(FieldEffect.SUN);}, 0, "ability"));
        MEGA_HAILSTORM = new Ability(ctx.getResources().getString(R.string.ability_name_mega_hailstorm),ctx.getResources().getString(R.string.ability_description_mega_hailstorm)).addEffect(new Effect((game, player, opponent) -> {if (game.getMode() != "classic") game.setFieldEffect(FieldEffect.SNOW);}, 0, "ability"));
        MEGA_GEYSER = new Ability(ctx.getResources().getString(R.string.ability_name_mega_geyser),ctx.getResources().getString(R.string.ability_description_mega_geyser)).addEffect(new Effect((game, player, opponent) -> {if (game.getMode() != "classic") game.setFieldEffect(FieldEffect.RAIN);}, 0, "ability"));
        MEGA_LIGHTNING = new Ability(ctx.getResources().getString(R.string.ability_name_mega_lightning),ctx.getResources().getString(R.string.ability_description_mega_lightning)).addEffect(new Effect((game, player, opponent) -> {if (game.getMode() != "classic") game.setFieldEffect(FieldEffect.ELECTRIC_TERRAIN);}, 0, "ability"));
        MEGA_MINDSTORM = new Ability(ctx.getResources().getString(R.string.ability_name_mega_mindstorm),ctx.getResources().getString(R.string.ability_description_mega_mindstorm)).addEffect(new Effect((game, player, opponent) -> {if (game.getMode() != "classic") game.setFieldEffect(FieldEffect.PSYCHIC_TERRAIN);}, 0, "ability"));
        MEGA_OVERGROWTH = new Ability(ctx.getResources().getString(R.string.ability_name_mega_overgrowth),ctx.getResources().getString(R.string.ability_description_mega_overgrowth)).addEffect(new Effect((game, player, opponent) -> {if (game.getMode() != "classic") game.setFieldEffect(FieldEffect.GRASSY_TERRAIN);}, 0, "ability"));
        MEGA_ROCKFALL = new Ability(ctx.getResources().getString(R.string.ability_name_mega_rockfall),ctx.getResources().getString(R.string.ability_description_mega_rockfall)).addEffect(new Effect((game, player, opponent) -> {if (game.getMode() != "classic") game.setFieldEffect(FieldEffect.SANDSTORM);}, 0, "ability"));
        MEGA_STARFALL = new Ability(ctx.getResources().getString(R.string.ability_name_mega_starfall),ctx.getResources().getString(R.string.ability_description_mega_starfall)).addEffect(new Effect((game, player, opponent) -> {if (game.getMode() != "classic") game.setFieldEffect(FieldEffect.MISTY_TERRAIN);}, 0, "ability"));
        MEGA_KNUCKLE = new Ability(ctx.getResources().getString(R.string.ability_name_mega_knuckle),ctx.getResources().getString(R.string.ability_description_mega_knuckle)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getMode() != "classic") {
                player.addDelayedEffect(new Effect((game1, player1, opponent1) -> {
                    if (player1.checkType(player1.getPlayedCard(), Type.FIGHTING)) {
                        player1.incrementLevel(Attributes.ATK, 1);
                    }
                }, 0, "ability"), 3);
            }
        }, 0, "ability"));
        MEGA_STEELSPIKE = new Ability(ctx.getResources().getString(R.string.ability_name_mega_steelspike),ctx.getResources().getString(R.string.ability_description_mega_steelspike)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getMode() != "classic") {
                player.addDelayedEffect(new Effect((game1, player1, opponent1) -> {
                    if (player1.checkType(player1.getPlayedCard(), Type.STEEL)) {
                        player1.incrementLevel(Attributes.DEF, 1);
                    }
                }, 0, "ability"), 3);
            }
        }, 0, "ability"));
    }
}
