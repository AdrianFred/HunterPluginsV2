//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hunter.plugins.HunterUtils.API;

import com.google.common.collect.ImmutableMap;
import java.awt.image.BufferedImage;
import java.util.Map;

public enum Runes {
    AIR(1, 556),
    WATER(2, 555),
    EARTH(3, 557),
    FIRE(4, 554),
    MIND(5, 558),
    CHAOS(6, 562),
    DEATH(7, 560),
    BLOOD(8, 565),
    COSMIC(9, 564),
    NATURE(10, 561),
    LAW(11, 563),
    BODY(12, 559),
    SOUL(13, 566),
    ASTRAL(14, 9075),
    MIST(15, 4695),
    MUD(16, 4698),
    DUST(17, 4696),
    LAVA(18, 4699),
    STEAM(19, 4694),
    SMOKE(20, 4697),
    WRATH(21, 21880);

    private static final Map<Integer, Runes> runes;
    private final int id;
    private final int itemId;
    private BufferedImage image;

    private Runes(int id, int itemId) {
        this.id = id;
        this.itemId = itemId;
    }

    public static Runes getRune(int varbit) {
        return (Runes)runes.get(varbit);
    }

    public String getName() {
        String name = this.name();
        String var10000 = name.substring(0, 1);
        name = var10000 + name.substring(1).toLowerCase();
        return name;
    }

    public int getId() {
        return this.id;
    }

    public int getItemId() {
        return this.itemId;
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    static {
        ImmutableMap.Builder<Integer, Runes> builder = new ImmutableMap.Builder();
        Runes[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Runes rune = var1[var3];
            builder.put(rune.getId(), rune);
        }

        runes = builder.build();
    }
}
