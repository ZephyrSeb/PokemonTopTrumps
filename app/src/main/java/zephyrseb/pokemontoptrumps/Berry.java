package zephyrseb.pokemontoptrumps;

import java.util.Random;

public enum Berry {
    NONE(0, 0, 0),
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
    LANSAT(R.string.berry_name_lansat, R.string.berry_description_lansat, R.drawable.lansat_berry),
    STARF(R.string.berry_name_starf, R.string.berry_description_starf, R.drawable.starf_berry),
    ENIGMA(R.string.berry_name_enigma, R.string.berry_description_enigma, R.drawable.enigma_berry);

    private int name;
    private int description;
    private int image;

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

    public Berry generateBerry() {
        Random rand = new Random();
        Berry[] berries = Berry.values();
        return berries[rand.nextInt(berries.length)];
    }
}
