package zephyrseb.pokemontoptrumps.comparators;

import java.util.Comparator;

import zephyrseb.pokemontoptrumps.Type;

public class TypeComparator implements Comparator<Type> {
    public int compare(Type t1, Type t2) {
        if (t1 == t2) return 0;
        if (t1 == Type.NORMAL || t2 == Type.NORMAL) {
            if (t1 == Type.NORMAL) return -1;
            return 1;
        }
        if (t1 == Type.FIRE || t2 == Type.FIRE) {
            if (t1 == Type.FIRE) return -1;
            return 1;
        }
        if (t1 == Type.WATER || t2 == Type.WATER) {
            if (t1 == Type.WATER) return -1;
            return 1;
        }
        if (t1 == Type.GRASS || t2 == Type.GRASS) {
            if (t1 == Type.GRASS) return -1;
            return 1;
        }
        if (t1 == Type.ELECTRIC || t2 == Type.ELECTRIC) {
            if (t1 == Type.ELECTRIC) return -1;
            return 1;
        }
        if (t1 == Type.ICE || t2 == Type.ICE) {
            if (t1 == Type.ICE) return -1;
            return 1;
        }
        if (t1 == Type.PSYCHIC || t2 == Type.PSYCHIC) {
            if (t1 == Type.PSYCHIC) return -1;
            return 1;
        }
        if (t1 == Type.FIGHTING || t2 == Type.FIGHTING) {
            if (t1 == Type.FIGHTING) return -1;
            return 1;
        }
        if (t1 == Type.GROUND || t2 == Type.GROUND) {
            if (t1 == Type.GROUND) return -1;
            return 1;
        }
        if (t1 == Type.DARK || t2 == Type.DARK) {
            if (t1 == Type.DARK) return -1;
            return 1;
        }
        if (t1 == Type.STEEL || t2 == Type.STEEL) {
            if (t1 == Type.STEEL) return -1;
            return 1;
        }
        if (t1 == Type.FAIRY || t2 == Type.FAIRY) {
            if (t1 == Type.FAIRY) return -1;
            return 1;
        }
        if (t1 == Type.DRAGON || t2 == Type.DRAGON) {
            if (t1 == Type.DRAGON) return -1;
            return 1;
        }
        if (t1 == Type.STELLAR || t2 == Type.STELLAR) {
            if (t1 == Type.STELLAR) return -1;
            return 1;
        }
        return 0;
    }
}
