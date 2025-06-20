package zephyrseb.pokemontoptrumps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Berry {
    NONE(0, 0, R.drawable.icon_blank),
    CHERI(R.string.berry_name_cheri, R.string.berry_description_cheri, R.drawable.cheri_berry),
    CHESTO(R.string.berry_name_chesto, R.string.berry_description_chesto, R.drawable.chesto_berry),
    RAWST(R.string.berry_name_rawst, R.string.berry_description_rawst, R.drawable.rawst_berry),
    ASPEAR(R.string.berry_name_aspear, R.string.berry_description_aspear, R.drawable.aspear_berry),
    PECHA(R.string.berry_name_pecha, R.string.berry_description_pecha, R.drawable.pecha_berry),
    PERSIM(R.string.berry_name_persim, R.string.berry_description_persim, R.drawable.persim_berry),
    LUM(R.string.berry_name_lum, R.string.berry_description_lum, R.drawable.lum_berry),
    ORAN(R.string.berry_name_oran, R.string.berry_description_oran, R.drawable.oran_berry),
    CHILAN(R.string.berry_name_chilan, R.string.berry_description_chilan, R.drawable.chilan_berry),
    OCCA(R.string.berry_name_occa, R.string.berry_description_occa, R.drawable.occa_berry),
    PASSHO(R.string.berry_name_passho, R.string.berry_description_passho, R.drawable.passho_berry),
    RINDO(R.string.berry_name_rindo, R.string.berry_description_rindo, R.drawable.rindo_berry),
    WACAN(R.string.berry_name_wacan, R.string.berry_description_wacan, R.drawable.wacan_berry),
    YACHE(R.string.berry_name_yache, R.string.berry_description_yache, R.drawable.yache_berry),
    PAYAPA(R.string.berry_name_payapa, R.string.berry_description_payapa, R.drawable.payapa_berry),
    COLBUR(R.string.berry_name_colbur, R.string.berry_description_colbur, R.drawable.colbur_berry),
    CHOPLE(R.string.berry_name_chople, R.string.berry_description_chople, R.drawable.chople_berry),
    SHUCA(R.string.berry_name_shuca, R.string.berry_description_shuca, R.drawable.shuca_berry),
    BABIRI(R.string.berry_name_babiri, R.string.berry_description_babiri, R.drawable.babiri_berry),
    HABAN(R.string.berry_name_haban, R.string.berry_description_haban, R.drawable.haban_berry),
    ROSELI(R.string.berry_name_roseli, R.string.berry_description_roseli, R.drawable.roseli_berry),
    SITRUS(R.string.berry_name_sitrus, R.string.berry_description_sitrus, R.drawable.sitrus_berry),
    LIECHI(R.string.berry_name_liechi, R.string.berry_description_liechi, R.drawable.liechi_berry),
    GANLON(R.string.berry_name_ganlon, R.string.berry_description_ganlon, R.drawable.ganlon_berry),
    SALAC(R.string.berry_name_salac, R.string.berry_description_salac, R.drawable.salac_berry),
    PETAYA(R.string.berry_name_petaya, R.string.berry_description_petaya, R.drawable.petaya_berry),
    APICOT(R.string.berry_name_apicot, R.string.berry_description_apicot, R.drawable.apicot_berry),
    MICLE(R.string.berry_name_micle, R.string.berry_description_micle, R.drawable.micle_berry),
    CUSTAP(R.string.berry_name_custap, R.string.berry_description_custap, R.drawable.custap_berry),
    LANSAT(R.string.berry_name_lansat, R.string.berry_description_lansat, R.drawable.lansat_berry),
    STARF(R.string.berry_name_starf, R.string.berry_description_starf, R.drawable.starf_berry),
    ENIGMA(R.string.berry_name_enigma, R.string.berry_description_enigma, R.drawable.enigma_berry);

    private final int name;
    private final int description;
    private final int image;

    Berry(int name, int description, int image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public int getName() {
        return name;
    }

    public int getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    public static Berry generateBerry() {
        Random rand = new Random();
        Berry[] berries = Berry.values();
        return berries[rand.nextInt(berries.length)];
    }

    public static List<Effect> generateEffect(Berry b) {
        List<Effect> effects = new ArrayList<>();
        switch (b) {
            case CHERI:
                effects.add(new Effect((game, player, opponent) -> {if (player.getStatusCondition() == StatusCondition.PARALYZED) {player.removeStatusCondition(); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case CHESTO:
                effects.add(new Effect((game, player, opponent) -> {if (player.getStatusCondition() == StatusCondition.ASLEEP) {player.removeStatusCondition(); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case RAWST:
                effects.add(new Effect((game, player, opponent) -> {if (player.getStatusCondition() == StatusCondition.BURNED) {player.removeStatusCondition(); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case ASPEAR:
                effects.add(new Effect((game, player, opponent) -> {if (player.getStatusCondition() == StatusCondition.FROZEN || player.getStatusCondition() == StatusCondition.FROSTBITTEN) {player.removeStatusCondition(); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case PECHA:
                effects.add(new Effect((game, player, opponent) -> {if (player.getStatusCondition() == StatusCondition.POISONED) {player.removeStatusCondition(); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case PERSIM:
                effects.add(new Effect((game, player, opponent) -> {if (player.getStatusCondition() == StatusCondition.CONFUSED) {player.removeStatusCondition(); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case LUM:
                effects.add(new Effect((game, player, opponent) -> {if (player.getStatusCondition() != StatusCondition.NONE) {player.removeStatusCondition(); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case ORAN:
                effects.add(new Effect((game, player, opponent) -> {if (player.getBreakPoints() > 0) {player.incrementBreakPoints(-3); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case CHILAN:
                effects.add(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), Type.NORMAL)) {player.incrementLevel(game.getChosenAttribute(), 1); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case OCCA:
                effects.add(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), Type.FIRE)) {player.incrementLevel(game.getChosenAttribute(), 1); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case PASSHO:
                effects.add(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), Type.WATER)) {player.incrementLevel(game.getChosenAttribute(), 1); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case RINDO:
                effects.add(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), Type.GRASS)) {player.incrementLevel(game.getChosenAttribute(), 1); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case WACAN:
                effects.add(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), Type.ELECTRIC)) {player.incrementLevel(game.getChosenAttribute(), 1); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case YACHE:
                effects.add(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), Type.ICE)) {player.incrementLevel(game.getChosenAttribute(), 1); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case PAYAPA:
                effects.add(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), Type.PSYCHIC)) {player.incrementLevel(game.getChosenAttribute(), 1); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case COLBUR:
                effects.add(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), Type.DARK)) {player.incrementLevel(game.getChosenAttribute(), 1); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case CHOPLE:
                effects.add(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), Type.FIGHTING)) {player.incrementLevel(game.getChosenAttribute(), 1); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case SHUCA:
                effects.add(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), Type.GROUND)) {player.incrementLevel(game.getChosenAttribute(), 1); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case BABIRI:
                effects.add(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), Type.STEEL)) {player.incrementLevel(game.getChosenAttribute(), 1); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case HABAN:
                effects.add(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), Type.DRAGON)) {player.incrementLevel(game.getChosenAttribute(), 1); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case ROSELI:
                effects.add(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), Type.FAIRY)) {player.incrementLevel(game.getChosenAttribute(), 1); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case SITRUS:
                effects.add(new Effect((game, player, opponent) -> {if (player.getBreakPoints() >= 5) {player.incrementBreakPoints(-3); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case LIECHI:
                effects.add(new Effect((game, player, opponent) -> {if (game.getChosenAttribute() == Attributes.ATK) {player.incrementLevel(Attributes.ATK, 1); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case GANLON:
                effects.add(new Effect((game, player, opponent) -> {if (game.getChosenAttribute() == Attributes.DEF) {player.incrementLevel(Attributes.DEF, 1); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case SALAC:
                effects.add(new Effect((game, player, opponent) -> {if (game.getChosenAttribute() == Attributes.SPATK) {player.incrementLevel(Attributes.SPATK, 1); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case PETAYA:
                effects.add(new Effect((game, player, opponent) -> {if (game.getChosenAttribute() == Attributes.SPDEF) {player.incrementLevel(Attributes.SPDEF, 1); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case APICOT:
                effects.add(new Effect((game, player, opponent) -> {if (game.getChosenAttribute() == Attributes.SPD) {player.incrementLevel(Attributes.SPD, 1); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case MICLE:
                effects.add(new Effect((game, player, opponent) -> {if (game.getChosenAttribute() == Attributes.ACC) {player.incrementLevel(Attributes.SPD, 1); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case CUSTAP:
                effects.add(new Effect((game, player, opponent) -> {if (game.getChosenAttribute() == Attributes.EV) {player.incrementLevel(Attributes.SPD, 1); player.removeBerry(b);}}, 0, "berry", false));
                break;
            case LANSAT:
                effects.add(new Effect((game, player, opponent) -> {player.incrementCritRate(1); player.removeBerry(b);}, 0, "berry", false));
                break;
            case STARF:
                effects.add(new Effect((game, player, opponent) -> {
                    Random rand = new Random();
                    Attributes[] a = {Attributes.HP, Attributes.ATK, Attributes.DEF, Attributes.SPATK, Attributes.SPDEF, Attributes.SPD};
                    player.incrementLevel(a[rand.nextInt(6)], 1);
                    player.removeBerry(b);
                }, 0, "berry", false));
                break;
            case ENIGMA:
                effects.add(new Effect((game, player, opponent) -> {
                    Card card = player.getPlayedCard();
                    List<Integer> a = new ArrayList<>();
                    a.add(card.getAttribute(Attributes.HP));
                    a.add(card.getAttribute(Attributes.ATK));
                    a.add(card.getAttribute(Attributes.DEF));
                    a.add(card.getAttribute(Attributes.SPATK));
                    a.add(card.getAttribute(Attributes.SPDEF));
                    a.add(card.getAttribute(Attributes.SPD));
                    Collections.shuffle(a);
                    card.setAttribute(Attributes.HP, a.get(0));
                    card.setAttribute(Attributes.ATK, a.get(1));
                    card.setAttribute(Attributes.DEF, a.get(2));
                    card.setAttribute(Attributes.SPATK, a.get(3));
                    card.setAttribute(Attributes.SPDEF, a.get(4));
                    card.setAttribute(Attributes.SPD, a.get(5));
                    player.setPlayedCard(card);
                    player.removeBerry(b);}, 0, "berry", false));
                break;
        }
        return effects;
    }
}
