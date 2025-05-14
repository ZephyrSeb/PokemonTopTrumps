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
    public static Ability HYDRATE;
    public static Ability GALVANIZE;
    public static Ability IGNITE;
    public static Ability BRIGHTEN;
    public static Ability DARKEN;
    public static Ability SYLVANIZE;
    public static Ability REFRIGERATE;
    public static Ability PIXILATE;
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

    public static void init(Context ctx) {
        HIDDEN_POWER = new Ability(ctx.getResources().getString(R.string.ability_name_hidden_power),ctx.getResources().getString(R.string.ability_description_hidden_power)).addEffect(new Effect((game, player, opponent) -> player.setSupereffectiveHit(true), 0, "ability"));
        TRUANT = new Ability(ctx.getResources().getString(R.string.ability_name_truant),ctx.getResources().getString(R.string.ability_description_truant)).addEffect(new Effect((game, player, opponent) -> player.setSpeed(-1), 0, "ability"));
        TRANSFORM = new Ability(ctx.getResources().getString(R.string.ability_name_transform),ctx.getResources().getString(R.string.ability_description_transform)).addEffect(new Effect((game, player, opponent) -> {
            player.setPlayedCard(opponent.getPlayedCard());
            if (player.getPlayedCard().getAbility() != null) {
                for (Effect e : player.getPlayedCard().getAbility().getEffects()) {
                    game.effectQueueAdd(new EffectAction(e, player, opponent, player.getPlayedCard().getAttribute("spd")));
                }
            }
        }, 3, "ability"));
        TORRENT = new Ability(ctx.getResources().getString(R.string.ability_name_torrent),ctx.getResources().getString(R.string.ability_description_torrent)).addEffect(new Effect((game, player, opponent) -> {
                if (opponent.getPoints() >= 4) {
                    player.setLevel("hp", player.getLevel("hp") + 1);
                    player.setLevel("atk", player.getLevel("atk") + 1);
                    player.setLevel("def", player.getLevel("def") + 1);
                    player.setLevel("spatk", player.getLevel("spatk") + 1);
                    player.setLevel("spdef", player.getLevel("spdef") + 1);
                    player.setLevel("spd", player.getLevel("spd") + 1);
                }
            }, 0, "ability"));
        OVERGROW = new Ability(ctx.getResources().getString(R.string.ability_name_overgrow),ctx.getResources().getString(R.string.ability_description_overgrow)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.getPoints() >= 4) {
                player.setLevel("hp", player.getLevel("hp") + 1);
                player.setLevel("atk", player.getLevel("atk") + 1);
                player.setLevel("def", player.getLevel("def") + 1);
                player.setLevel("spatk", player.getLevel("spatk") + 1);
                player.setLevel("spdef", player.getLevel("spdef") + 1);
                player.setLevel("spd", player.getLevel("spd") + 1);
            }
        }, 0, "ability"));
        BLAZE = new Ability(ctx.getResources().getString(R.string.ability_name_blaze),ctx.getResources().getString(R.string.ability_description_blaze)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.getPoints() >= 4) {
                player.setLevel("hp", player.getLevel("hp") + 1);
                player.setLevel("atk", player.getLevel("atk") + 1);
                player.setLevel("def", player.getLevel("def") + 1);
                player.setLevel("spatk", player.getLevel("spatk") + 1);
                player.setLevel("spdef", player.getLevel("spdef") + 1);
                player.setLevel("spd", player.getLevel("spd") + 1);
            }
        }, 0, "ability"));
        HEART_SWAP = new Ability(ctx.getResources().getString(R.string.ability_name_heart_swap),ctx.getResources().getString(R.string.ability_description_heart_swap)).addEffect(new Effect((game, player, opponent) -> {
            Card temp = player.getPlayedCard();
            player.setPlayedCard(opponent.getPlayedCard());
            opponent.setPlayedCard(temp);
        }, 3, "ability"));
        PICKUP = new Ability(ctx.getResources().getString(R.string.ability_name_pickup),ctx.getResources().getString(R.string.ability_description_pickup)).addEffect(new Effect((game, player, opponent) -> player.addItem(game.generateItem()), 0, "ability"));
        MOLD_BREAKER = new Ability(ctx.getResources().getString(R.string.ability_name_mold_breaker),ctx.getResources().getString(R.string.ability_description_mold_breaker)).addEffect(new Effect((game, player, opponent) -> game.removeEffectFromQueue("ability", opponent), 4, "ability"));
        DOWNLOAD = new Ability(ctx.getResources().getString(R.string.ability_name_download),ctx.getResources().getString(R.string.ability_description_download)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.getPlayedCard().getAttribute("def") < opponent.getPlayedCard().getAttribute("spdef")) player.setLevel("atk", player.getLevel("atk") + 1);
            if (opponent.getPlayedCard().getAttribute("def") >= opponent.getPlayedCard().getAttribute("spdef")) player.setLevel("spatk", player.getLevel("spatk") + 1);
        }, 0, "ability"));
        SUPER_LUCK = new Ability(ctx.getResources().getString(R.string.ability_name_super_luck),ctx.getResources().getString(R.string.ability_description_super_luck)).addEffect(new Effect((game, player, opponent) -> player.setCritRate(player.getCritRate() + 1), 0, "ability"));
        TRACE = new Ability(ctx.getResources().getString(R.string.ability_name_trace),ctx.getResources().getString(R.string.ability_description_trace)).addEffect(new Effect((game, player, opponent) -> {
            player.getPlayedCard().setAbility(opponent.getPlayedCard().getAbility());
            if (player.getPlayedCard().getAbility() != null) {
                for (Effect e : player.getPlayedCard().getAbility().getEffects()) {
                    game.effectQueueAdd(new EffectAction(e, player, opponent, player.getPlayedCard().getAttribute("spd")));
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
        RAIN_DISH = new Ability(ctx.getResources().getString(R.string.ability_name_rain_dish),ctx.getResources().getString(R.string.ability_description_rain_dish)).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.RAIN) {player.setLevel("hp", player.getLevel("hp") + 1);}}, 0, "ability"));
        SWIFT_SWIM = new Ability(ctx.getResources().getString(R.string.ability_name_swift_swim),ctx.getResources().getString(R.string.ability_description_swift_swim)).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.RAIN) {player.setLevel("spd", player.getLevel("spd") + 1);}}, 0, "ability"));
        CHLOROPHYLL = new Ability(ctx.getResources().getString(R.string.ability_name_chlorophyll),ctx.getResources().getString(R.string.ability_description_chlorophyll)).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.SUN) {player.setLevel("spd", player.getLevel("spd") + 1);}}, 0, "ability"));
        FLOWER_GIFT = new Ability(ctx.getResources().getString(R.string.ability_name_flower_gift),ctx.getResources().getString(R.string.ability_description_flower_gift)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getFieldEffect() == FieldEffect.SUN && Objects.equals(player.getPlayedCard().getName(), ctx.getResources().getString(R.string.pokemon_name_cherrim))) {player.setPlayedCard(CardRegistry.CHERRIM_SUNNY);}
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
        ICE_BODY = new Ability(ctx.getResources().getString(R.string.ability_name_ice_body),ctx.getResources().getString(R.string.ability_description_ice_body)).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.SNOW) {player.setLevel("hp", player.getLevel("hp") + 1);}}, 0, "ability"));
        FORECAST = new Ability(ctx.getResources().getString(R.string.ability_name_forecast),ctx.getResources().getString(R.string.ability_description_forecast)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getFieldEffect() == FieldEffect.SUN && Objects.equals(player.getPlayedCard().getName(), ctx.getResources().getString(R.string.pokemon_name_castform))) {player.setPlayedCard(CardRegistry.CASTFORM_SUN);}
            if (game.getFieldEffect() == FieldEffect.RAIN && Objects.equals(player.getPlayedCard().getName(), ctx.getResources().getString(R.string.pokemon_name_castform))) {player.setPlayedCard(CardRegistry.CASTFORM_RAIN);}
            if (game.getFieldEffect() == FieldEffect.SNOW && Objects.equals(player.getPlayedCard().getName(), ctx.getResources().getString(R.string.pokemon_name_castform))) {player.setPlayedCard(CardRegistry.CASTFORM_SNOW);}
        }, 1, "ability"));
        SAND_FORCE = new Ability(ctx.getResources().getString(R.string.ability_name_sand_force),ctx.getResources().getString(R.string.ability_description_sand_force)).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.SANDSTORM) {player.setLevel("atk", player.getLevel("atk") + 1);}}, 0, "ability"));
        LEVITATE = new Ability(ctx.getResources().getString(R.string.ability_name_levitate),ctx.getResources().getString(R.string.ability_description_levitate)).addEffect(new Effect((game, player, opponent) -> game.removeEffectFromQueue("field", player), 4, "ability"));
        CONTRARY = new Ability(ctx.getResources().getString(R.string.ability_name_contrary),ctx.getResources().getString(R.string.ability_description_contrary)).addEffect(new Effect((game, player, opponent) -> {
            player.setLevel("hp", player.getLevel("hp") * -1);
            player.setLevel("atk", player.getLevel("atk") * -1);
            player.setLevel("def", player.getLevel("def") * -1);
            player.setLevel("spatk", player.getLevel("spatk") * -1);
            player.setLevel("spdef", player.getLevel("spdef") * -1);
            player.setLevel("spd", player.getLevel("spd") * -1);
        }, -1, "ability"));
        ANALYTIC = new Ability(ctx.getResources().getString(R.string.ability_name_analytic),ctx.getResources().getString(R.string.ability_description_analytic)).addEffect(new Effect((game, player, opponent) -> {
            if (player.getPlayedCard().getAttribute("spd") < opponent.getPlayedCard().getAttribute("spd")) {
                player.setLevel("atk", player.getLevel("atk") + 1);
                player.setLevel("spatk", player.getLevel("spatk") + 1);
            }
        }, 0, "ability"));
        PAY_DAY = new Ability(ctx.getResources().getString(R.string.ability_name_pay_day),ctx.getResources().getString(R.string.ability_description_pay_day)).addEffect(new Effect((game, player, opponent) -> game.setPrizePot(game.getPrizePot() * 2), 0, "ability"));
        SUBSTITUTE_DOLL = new Ability(ctx.getResources().getString(R.string.ability_name_substitute_doll),ctx.getResources().getString(R.string.ability_description_substitute_doll)).addEffect(new Effect((game, player, opponent) -> game.setPrizePot(0), 0, "ability"));
        DRY_SKIN = new Ability(ctx.getResources().getString(R.string.ability_name_dry_skin),ctx.getResources().getString(R.string.ability_description_dry_skin)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getFieldEffect() == FieldEffect.SUN) player.setLevel("hp", player.getLevel("hp") - 1);
            if (game.getFieldEffect() == FieldEffect.RAIN) player.setLevel("hp", player.getLevel("hp") + 1);
        }, 0, "ability"));
        SOLAR_POWER = new Ability(ctx.getResources().getString(R.string.ability_name_solar_power),ctx.getResources().getString(R.string.ability_description_solar_power)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getFieldEffect() == FieldEffect.SUN) {
                player.setLevel("hp", player.getLevel("hp") - 1);
                player.setLevel("spatk", player.getLevel("spatk") + 1);}
        }, 0, "ability"));
        SAND_RUSH = new Ability(ctx.getResources().getString(R.string.ability_name_sand_rush),ctx.getResources().getString(R.string.ability_description_sand_rush)).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.SANDSTORM) player.setLevel("spd", player.getLevel("spd") + 1);}, 0, "ability"));
        SLUSH_RUSH = new Ability(ctx.getResources().getString(R.string.ability_name_slush_rush),ctx.getResources().getString(R.string.ability_description_slush_rush)).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.SNOW) player.setLevel("spd", player.getLevel("spd") + 1);}, 0, "ability"));
        EARTH_EATER = new Ability(ctx.getResources().getString(R.string.ability_name_earth_eater),ctx.getResources().getString(R.string.ability_description_earth_eater)).addEffect(new Effect((game, player, opponent) -> {if (opponent.getPlayedCard().getType() == Type.GROUND) player.setLevel("hp", player.getLevel("hp") + 1);}, 0, "ability"));
        WATER_ABSORB = new Ability(ctx.getResources().getString(R.string.ability_name_water_absorb),ctx.getResources().getString(R.string.ability_description_water_absorb)).addEffect(new Effect((game, player, opponent) -> {if (opponent.getPlayedCard().getType() == Type.WATER) player.setLevel("hp", player.getLevel("hp") + 1);}, 0, "ability"));
        VOLT_ABSORB = new Ability(ctx.getResources().getString(R.string.ability_name_volt_absorb),ctx.getResources().getString(R.string.ability_description_volt_absorb)).addEffect(new Effect((game, player, opponent) -> {if (opponent.getPlayedCard().getType() == Type.ELECTRIC) player.setLevel("hp", player.getLevel("hp") + 1);}, 0, "ability"));
        LIGHTNING_ROD = new Ability(ctx.getResources().getString(R.string.ability_name_lightning_rod),ctx.getResources().getString(R.string.ability_description_lightning_rod)).addEffect(new Effect((game, player, opponent) -> {if (opponent.getPlayedCard().getType() == Type.ELECTRIC) player.setLevel("spatk", player.getLevel("spatk") + 1);}, 0, "ability"));
        STORM_DRAIN = new Ability(ctx.getResources().getString(R.string.ability_name_storm_drain),ctx.getResources().getString(R.string.ability_description_storm_drain)).addEffect(new Effect((game, player, opponent) -> {if (opponent.getPlayedCard().getType() == Type.WATER) player.setLevel("spatk", player.getLevel("spatk") + 1);}, 0, "ability"));
        SAP_SIPPER = new Ability(ctx.getResources().getString(R.string.ability_name_sap_sipper),ctx.getResources().getString(R.string.ability_description_sap_sipper)).addEffect(new Effect((game, player, opponent) -> {if (opponent.getPlayedCard().getType() == Type.GRASS) player.setLevel("atk", player.getLevel("atk") + 1);}, 0, "ability"));
        WATER_COMPACTION = new Ability(ctx.getResources().getString(R.string.ability_name_water_compaction),ctx.getResources().getString(R.string.ability_description_water_compaction)).addEffect(new Effect((game, player, opponent) -> {if (opponent.getPlayedCard().getType() == Type.WATER) player.setLevel("def", player.getLevel("def") + 1);}, 0, "ability"));
        WATER_BUBBLE = new Ability(ctx.getResources().getString(R.string.ability_name_water_bubble),ctx.getResources().getString(R.string.ability_description_water_bubble)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.getPlayedCard().getType() == Type.FIRE) player.setLevel("def", player.getLevel("def") + 1);
            if (game.getFieldEffect() == FieldEffect.RAIN) player.setLevel("atk", player.getLevel("atk") + 1);
        }, 0, "ability"));
        TECHNICIAN = new Ability(ctx.getResources().getString(R.string.ability_name_technician),ctx.getResources().getString(R.string.ability_description_technician)).addEffect(new Effect((game, player, opponent) -> {
            if (player.getPlayedCard().getAttribute("hp") <= 60) player.setLevel("hp", player.getLevel("hp") + 1);
            if (player.getPlayedCard().getAttribute("atk") <= 60) player.setLevel("atk", player.getLevel("atk") + 1);
            if (player.getPlayedCard().getAttribute("def") <= 60) player.setLevel("def", player.getLevel("def") + 1);
            if (player.getPlayedCard().getAttribute("spatk") <= 60) player.setLevel("spatk", player.getLevel("spatk") + 1);
            if (player.getPlayedCard().getAttribute("spdef") <= 60) player.setLevel("spdef", player.getLevel("spdef") + 1);
            if (player.getPlayedCard().getAttribute("spd") <= 60) player.setLevel("spd", player.getLevel("spd") + 1);
        }, 0, "ability"));
        OPPORTUNIST = new Ability(ctx.getResources().getString(R.string.ability_name_opportunist),ctx.getResources().getString(R.string.ability_description_opportunist)).addEffect(new Effect((game, player, opponent) -> {
            player.setLevel("hp", opponent.getLevel("hp"));
            player.setLevel("atk", opponent.getLevel("atk"));
            player.setLevel("def", opponent.getLevel("def"));
            player.setLevel("spatk", opponent.getLevel("spatk"));
            player.setLevel("spdef", opponent.getLevel("spdef"));
            player.setLevel("spd", opponent.getLevel("spd"));
        }, -1, "ability"));
        SIMPLE = new Ability(ctx.getResources().getString(R.string.ability_name_simple),ctx.getResources().getString(R.string.ability_description_simple)).addEffect(new Effect((game, player, opponent) -> {
            player.setLevel("hp", player.getLevel("hp") * 2);
            player.setLevel("atk", player.getLevel("atk") * 2);
            player.setLevel("def", player.getLevel("def") * 2);
            player.setLevel("spatk", player.getLevel("spatk") * 2);
            player.setLevel("spdef", player.getLevel("spdef") * 2);
            player.setLevel("spd", player.getLevel("spd") * 2);
        }, -1, "ability"));
        CLEAR_BODY = new Ability(ctx.getResources().getString(R.string.ability_name_clear_body),ctx.getResources().getString(R.string.ability_description_clear_body)).addEffect(new Effect((game, player, opponent) -> {
            player.setLevel("hp", Math.max(player.getLevel("hp"), 0));
            player.setLevel("atk", Math.max(player.getLevel("atk"), 0));
            player.setLevel("def", Math.max(player.getLevel("def"), 0));
            player.setLevel("spatk", Math.max(player.getLevel("spatk"), 0));
            player.setLevel("spdef", Math.max(player.getLevel("spdef"), 0));
            player.setLevel("spd", Math.max(player.getLevel("spd"), 0));
        }, -1, "ability"));
        MOODY = new Ability(ctx.getResources().getString(R.string.ability_name_moody),ctx.getResources().getString(R.string.ability_description_moody)).addEffect(new Effect((game, player, opponent) -> {
            Random rand = new Random();
            String[] stats = {"hp", "atk", "def", "spatk", "spdef", "spd"};
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
            String[] stats = {"hp", "atk", "def", "spatk", "spdef", "spd"};
            int i1 = rand.nextInt(6);
            if (i < 3) {player.setLevel(stats[i1], player.getLevel(stats[i1]) - 1);}
            if (i >= 3 && i < 7) {player.setLevel(stats[i1], player.getLevel(stats[i1]) + 1);}
            if (i >= 7) {player.addItem(game.generateItem());}
        }, 0, "ability"));
        GLUTTONY = new Ability(ctx.getResources().getString(R.string.ability_name_gluttony),ctx.getResources().getString(R.string.ability_description_gluttony));
        RIPEN = new Ability(ctx.getResources().getString(R.string.ability_name_ripen),ctx.getResources().getString(R.string.ability_description_ripen));
        LEAF_GUARD = new Ability(ctx.getResources().getString(R.string.ability_name_leaf_guard),ctx.getResources().getString(R.string.ability_description_leaf_guard)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getFieldEffect() == FieldEffect.SUN) {
                player.setLevel("hp", Math.max(player.getLevel("hp"), 0));
                player.setLevel("atk", Math.max(player.getLevel("atk"), 0));
                player.setLevel("def", Math.max(player.getLevel("def"), 0));
                player.setLevel("spatk", Math.max(player.getLevel("spatk"), 0));
                player.setLevel("spdef", Math.max(player.getLevel("spdef"), 0));
                player.setLevel("spd", Math.max(player.getLevel("spd"), 0));
            }
        }, -1, "ability"));
        HYDRATION = new Ability(ctx.getResources().getString(R.string.ability_name_hydration),ctx.getResources().getString(R.string.ability_description_hydration)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getFieldEffect() == FieldEffect.RAIN) {
                player.setLevel("hp", Math.max(player.getLevel("hp"), 0));
                player.setLevel("atk", Math.max(player.getLevel("atk"), 0));
                player.setLevel("def", Math.max(player.getLevel("def"), 0));
                player.setLevel("spatk", Math.max(player.getLevel("spatk"), 0));
                player.setLevel("spdef", Math.max(player.getLevel("spdef"), 0));
                player.setLevel("spd", Math.max(player.getLevel("spd"), 0));
            }
        }, -1, "ability"));
        SCHOOLING = new Ability(ctx.getResources().getString(R.string.ability_name_schooling),ctx.getResources().getString(R.string.ability_description_schooling)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.getPoints() < 5 && Objects.equals(player.getPlayedCard().getName(), ctx.getResources().getString(R.string.pokemon_name_wishiwashi))) {player.setPlayedCard(CardRegistry.WISHIWASHI_SCHOOLING);}
        }, 1, "ability"));
        SHIELDS_DOWN = new Ability(ctx.getResources().getString(R.string.ability_name_shields_down),ctx.getResources().getString(R.string.ability_description_shields_down)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.getPoints() >= 3 && Objects.equals(player.getPlayedCard().getName(), ctx.getResources().getString(R.string.pokemon_name_minior))) {player.setPlayedCard(CardRegistry.MINIOR_CORE);}
        }, 1, "ability"));
        SPEED_BOOST = new Ability(ctx.getResources().getString(R.string.ability_name_speed_boost),ctx.getResources().getString(R.string.ability_description_speed_boost)).addEffect(new Effect((game, player, opponent) -> player.setLevel("spd", player.getLevel("spd") + 1), 0, "ability"));
        STAMINA = new Ability(ctx.getResources().getString(R.string.ability_name_stamina),ctx.getResources().getString(R.string.ability_description_stamina)).addEffect(new Effect((game, player, opponent) -> player.setLevel("def", player.getLevel("def") + 1), 0, "ability"));
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
        }, 0, "ability"));
        SOLID_ROCK = new Ability(ctx.getResources().getString(R.string.ability_name_solid_rock),ctx.getResources().getString(R.string.ability_description_solid_rock)).addEffect(new Effect((game, player, opponent) -> opponent.setCritRate(0), -1, "ability"));
        DEFEATIST = new Ability(ctx.getResources().getString(R.string.ability_name_defeatist),ctx.getResources().getString(R.string.ability_description_defeatist)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.getPoints() >= 3) {
                player.setLevel("atk", player.getLevel("atk") - 1);
                player.setLevel("spatk", player.getLevel("spatk") - 1);
            }
        }, 0, "ability"));
        SLOW_START = new Ability(ctx.getResources().getString(R.string.ability_name_slow_start),ctx.getResources().getString(R.string.ability_description_slow_start)).addEffect(new Effect((game, player, opponent) -> {
            if (player.getPoints() < 3) {
                player.setLevel("atk", player.getLevel("atk") - 1);
                player.setLevel("spd", player.getLevel("spd") - 1);
            }
        }, 0, "ability"));
        SUBSTITUTE = new Ability(ctx.getResources().getString(R.string.ability_name_substitute),ctx.getResources().getString(R.string.ability_description_substitute)).addEffect(new Effect((game, player, opponent) -> player.addCard(CardRegistry.SUBSTITUTE.clone(), 0), 0, "ability"));
        INTIMIDATE = new Ability(ctx.getResources().getString(R.string.ability_name_intimidate),ctx.getResources().getString(R.string.ability_description_intimidate)).addEffect(new Effect((game, player, opponent) -> opponent.setLevel("atk", opponent.getLevel("atk") - 1), 0, "ability"));
        MUMMY = new Ability(ctx.getResources().getString(R.string.ability_name_mummy),ctx.getResources().getString(R.string.ability_description_mummy)).addEffect(new Effect((game, player, opponent) -> {
            opponent.getPlayedCard().setAbility(Ability.MUMMY);
            game.removeEffectFromQueue("ability", opponent);
        }, 4, "ability"));
        KLUTZ = new Ability(ctx.getResources().getString(R.string.ability_name_klutz),ctx.getResources().getString(R.string.ability_description_klutz)).addEffect(new Effect((game, player, opponent) -> game.removeEffectFromQueue("item", player), 4, "ability"));
        FLUFFY = new Ability(ctx.getResources().getString(R.string.ability_name_fluffy),ctx.getResources().getString(R.string.ability_description_fluffy)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.getPlayedCard().getType() == Type.FIGHTING) player.setLevel(game.getChosenAttribute(), player.getLevel(game.getChosenAttribute()) + 1);
            if (opponent.getPlayedCard().getType() == Type.FIRE) player.setLevel(game.getChosenAttribute(), player.getLevel(game.getChosenAttribute()) - 1);
        }, 0, "ability"));
        THICK_FAT = new Ability(ctx.getResources().getString(R.string.ability_name_thick_fat),ctx.getResources().getString(R.string.ability_description_thick_fat)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.getPlayedCard().getType() == Type.ICE) player.setLevel(game.getChosenAttribute(), player.getLevel(game.getChosenAttribute()) + 1);
            if (opponent.getPlayedCard().getType() == Type.FIRE) player.setLevel(game.getChosenAttribute(), player.getLevel(game.getChosenAttribute()) + 1);
        }, 0, "ability"));
        FUR_COAT = new Ability(ctx.getResources().getString(R.string.ability_name_fur_coat),ctx.getResources().getString(R.string.ability_description_fur_coat)).addEffect(new Effect((game, player, opponent) -> {if (opponent.getPlayedCard().getType() == Type.FIGHTING) player.setLevel(game.getChosenAttribute(), player.getLevel(game.getChosenAttribute()) + 1);}, 0, "ability"));
        HEATPROOF = new Ability(ctx.getResources().getString(R.string.ability_name_heatproof),ctx.getResources().getString(R.string.ability_description_heatproof)).addEffect(new Effect((game, player, opponent) -> {if (opponent.getPlayedCard().getType() == Type.FIRE) player.setLevel(game.getChosenAttribute(), player.getLevel(game.getChosenAttribute()) + 1);}, 0, "ability"));
        JUSTIFIED = new Ability(ctx.getResources().getString(R.string.ability_name_justified),ctx.getResources().getString(R.string.ability_description_justified)).addEffect(new Effect((game, player, opponent) -> {if (opponent.getPlayedCard().getType() == Type.DARK) player.setLevel(game.getChosenAttribute(), player.getLevel(game.getChosenAttribute()) + 1);}, 0, "ability"));
        RATTLED = new Ability(ctx.getResources().getString(R.string.ability_name_rattled),ctx.getResources().getString(R.string.ability_description_rattled)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.getPlayedCard().getType() == Type.GRASS || opponent.getPlayedCard().getType() == Type.PSYCHIC || opponent.getPlayedCard().getType() == Type.DARK) player.setLevel("spd", player.getLevel("spd") + 1);
        }, 0, "ability"));
        WEAK_ARMOR = new Ability(ctx.getResources().getString(R.string.ability_name_weak_armor),ctx.getResources().getString(R.string.ability_description_weak_armor)).addEffect(new Effect((game, player, opponent) -> {
            if (opponent.getPlayedCard().getType() == Type.FIGHTING) {
                player.setLevel("spd", player.getLevel("spd") + 1);
                player.setLevel("def", player.getLevel("def") - 1);
            }
        }, 0, "ability"));
        HYDRATE = new Ability(ctx.getResources().getString(R.string.ability_name_hydrate),ctx.getResources().getString(R.string.ability_description_hydrate)).addEffect(new Effect((game, player, opponent) -> {
            for (Card c : player.getDeck()) {
                if (c.getType() == Type.NORMAL) c.setType(Type.WATER);
            }
        }, 0, "ability"));
        GALVANIZE = new Ability(ctx.getResources().getString(R.string.ability_name_galvanize),ctx.getResources().getString(R.string.ability_description_galvanize)).addEffect(new Effect((game, player, opponent) -> {
            for (Card c : player.getDeck()) {
                if (c.getType() == Type.NORMAL) c.setType(Type.ELECTRIC);
            }
        }, 0, "ability"));
        IGNITE = new Ability(ctx.getResources().getString(R.string.ability_name_ignite),ctx.getResources().getString(R.string.ability_description_ignite)).addEffect(new Effect((game, player, opponent) -> {
            for (Card c : player.getDeck()) {
                if (c.getType() == Type.NORMAL) c.setType(Type.FIRE);
            }
        }, 0, "ability"));
        BRIGHTEN = new Ability(ctx.getResources().getString(R.string.ability_name_brighten),ctx.getResources().getString(R.string.ability_description_brighten)).addEffect(new Effect((game, player, opponent) -> {
            for (Card c : player.getDeck()) {
                if (c.getType() == Type.NORMAL) c.setType(Type.PSYCHIC);
            }
        }, 0, "ability"));
        DARKEN = new Ability(ctx.getResources().getString(R.string.ability_name_darken),ctx.getResources().getString(R.string.ability_description_darken)).addEffect(new Effect((game, player, opponent) -> {
            for (Card c : player.getDeck()) {
                if (c.getType() == Type.NORMAL) c.setType(Type.DARK);
            }
        }, 0, "ability"));
        SYLVANIZE = new Ability(ctx.getResources().getString(R.string.ability_name_sylvanize),ctx.getResources().getString(R.string.ability_description_sylvanize)).addEffect(new Effect((game, player, opponent) -> {
            for (Card c : player.getDeck()) {
                if (c.getType() == Type.NORMAL) c.setType(Type.GRASS);
            }
        }, 0, "ability"));
        REFRIGERATE = new Ability(ctx.getResources().getString(R.string.ability_name_refrigerate),ctx.getResources().getString(R.string.ability_description_refrigerate)).addEffect(new Effect((game, player, opponent) -> {
            for (Card c : player.getDeck()) {
                if (c.getType() == Type.NORMAL) c.setType(Type.ICE);
            }
        }, 0, "ability"));
        PIXILATE = new Ability(ctx.getResources().getString(R.string.ability_name_pixilate),ctx.getResources().getString(R.string.ability_description_pixilate)).addEffect(new Effect((game, player, opponent) -> {
            for (Card c : player.getDeck()) {
                if (c.getType() == Type.NORMAL) c.setType(Type.FAIRY);
            }
        }, 0, "ability"));
        CHARGE = new Ability(ctx.getResources().getString(R.string.ability_name_charge),ctx.getResources().getString(R.string.ability_description_charge)).addEffect(new Effect((game, player, opponent) -> player.setCounterCharge(player.getCounterCharge() + 1), 0, "ability"));
        ELECTROMORPHOSIS = new Ability(ctx.getResources().getString(R.string.ability_name_electromorphosis),ctx.getResources().getString(R.string.ability_description_electromorphosis)).addEffect(new Effect((game, player, opponent) -> {
            player.setCounterCharge(player.getCounterCharge() + 1);
            if (player.getPlayedCard().getCharged()) player.setLevel("spdef", player.getLevel("spdef") + 1);
        }, 0, "ability"));
        WIND_POWER = new Ability(ctx.getResources().getString(R.string.ability_name_wind_power),ctx.getResources().getString(R.string.ability_description_wind_power)).addEffect(new Effect((game, player, opponent) -> {
            if (game.getFieldEffect() == FieldEffect.WIND) {
                player.setCounterCharge(player.getCounterCharge() + 1);
                player.setLevel("spd", player.getLevel("spd") + 1);
            }
        }, 0, "ability"));
        MOTOR_DRIVE = new Ability(ctx.getResources().getString(R.string.ability_name_motor_drive),ctx.getResources().getString(R.string.ability_description_motor_drive)).addEffect(new Effect((game, player, opponent) -> {
            if (player.getPlayedCard().getCharged()) {
                player.setLevel("spd", player.getLevel("spd") + 1);
                player.addItem(Item.CELL_BATTERY);
            }
        }, 0, "ability"));
        DANCER = new Ability(ctx.getResources().getString(R.string.ability_name_dancer),ctx.getResources().getString(R.string.ability_description_dancer)).addEffect(new Effect((game, player, opponent) -> {
            if (!Objects.equals(player.getDanceStat(), "")) {
                player.setLevel(player.getDanceStat(), player.getLevel(player.getDanceStat()) + 1);
            }
        }, 0, "ability")).addEffect(new Effect((game, player, opponent) -> {
            player.setDanceStat(game.getChosenAttribute());
            opponent.setDanceStat(game.getChosenAttribute());
        }, -1, "ability"));
        STATIC = new Ability(ctx.getResources().getString(R.string.ability_name_static),ctx.getResources().getString(R.string.ability_description_static)).addEffect(new Effect((game, player, opponent) -> {if (opponent.getPlayedCard().getType() != Type.ELECTRIC) opponent.setStatusCondition(StatusCondition.PARALYZED);}, 0, "ability"));
        FLAME_BODY = new Ability(ctx.getResources().getString(R.string.ability_name_flame_body),ctx.getResources().getString(R.string.ability_description_flame_body)).addEffect(new Effect((game, player, opponent) -> {if (opponent.getPlayedCard().getType() != Type.FIRE) opponent.setStatusCondition(StatusCondition.BURNED);}, 0, "ability"));
        ICE_BURN = new Ability(ctx.getResources().getString(R.string.ability_name_ice_burn),ctx.getResources().getString(R.string.ability_description_ice_burn)).addEffect(new Effect((game, player, opponent) -> {if (opponent.getPlayedCard().getType() != Type.ICE) opponent.setStatusCondition(StatusCondition.FROSTBITTEN);}, 0, "ability"));
        TRI_ATTACK = new Ability(ctx.getResources().getString(R.string.ability_name_tri_attack),ctx.getResources().getString(R.string.ability_description_tri_attack)).addEffect(new Effect((game, player, opponent) -> {
            Random rand = new Random();
            int r = rand.nextInt(3);
            if (r == 0) opponent.setStatusCondition(StatusCondition.PARALYZED);
            if (r == 1) opponent.setStatusCondition(StatusCondition.BURNED);
            if (r == 2) opponent.setStatusCondition(StatusCondition.FROSTBITTEN);
        }, 0, "ability"));
        POISON_POINT = new Ability(ctx.getResources().getString(R.string.ability_name_poison_point),ctx.getResources().getString(R.string.ability_description_poison_point)).addEffect(new Effect((game, player, opponent) -> {if (opponent.getPlayedCard().getType() != Type.STEEL) opponent.setStatusCondition(StatusCondition.POISONED);}, 0, "ability"));
        CONFUSE_RAY = new Ability(ctx.getResources().getString(R.string.ability_name_confuse_ray),ctx.getResources().getString(R.string.ability_description_confuse_ray)).addEffect(new Effect((game, player, opponent) -> opponent.setStatusCondition(StatusCondition.CONFUSED), 0, "ability"));
        HYPNOSIS = new Ability(ctx.getResources().getString(R.string.ability_name_hypnosis),ctx.getResources().getString(R.string.ability_description_hypnosis)).addEffect(new Effect((game, player, opponent) -> opponent.setStatusCondition(StatusCondition.ASLEEP), 0, "ability"));
        FROZEN_BODY = new Ability(ctx.getResources().getString(R.string.ability_name_frozen_body),ctx.getResources().getString(R.string.ability_description_frozen_body)).addEffect(new Effect((game, player, opponent) -> {if (opponent.getPlayedCard().getType() != Type.ICE) opponent.setStatusCondition(StatusCondition.FROZEN);}, 0, "ability"));
        FACADE = new Ability(ctx.getResources().getString(R.string.ability_name_facade),ctx.getResources().getString(R.string.ability_description_facade)).addEffect(new Effect((game, player, opponent) -> {
            if (player.getStatusCondition() != StatusCondition.NONE) {
                player.setLevel("hp", player.getLevel("hp") + 1);
                player.setLevel("atk", player.getLevel("atk") + 1);
                player.setLevel("def", player.getLevel("def") + 1);
                player.setLevel("spatk", player.getLevel("spatk") + 1);
                player.setLevel("spdef", player.getLevel("spdef") + 1);
                player.setLevel("spd", player.getLevel("spd") + 1);
            }
        }, 0, "ability"));
        NATURAL_CURE = new Ability(ctx.getResources().getString(R.string.ability_name_natural_cure),ctx.getResources().getString(R.string.ability_description_natural_cure)).addEffect(new Effect((game, player, opponent) -> player.setStatusCondition(StatusCondition.NONE), 0, "ability"));
        CORROSION = new Ability(ctx.getResources().getString(R.string.ability_name_corrosion),ctx.getResources().getString(R.string.ability_description_corrosion));
        TOXIC_DEBRIS = new Ability(ctx.getResources().getString(R.string.ability_name_toxic_debris),ctx.getResources().getString(R.string.ability_description_toxic_debris));
        BEGINNERS_LUCK = new Ability(ctx.getResources().getString(R.string.ability_name_beginners_luck),ctx.getResources().getString(R.string.ability_description_beginners_luck));
    }
}
