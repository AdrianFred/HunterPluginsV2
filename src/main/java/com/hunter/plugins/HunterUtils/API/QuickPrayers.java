//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hunter.plugins.HunterUtils.API;

import java.util.ArrayList;
import java.util.List;
import net.runelite.api.Prayer;

public enum QuickPrayers {
    THICK_SKIN(Prayer.THICK_SKIN, 1),
    BURST_OF_STRENGTH(Prayer.BURST_OF_STRENGTH, 2),
    CLARITY_OF_THOUGHT(Prayer.CLARITY_OF_THOUGHT, 4),
    SHARP_EYE(Prayer.SHARP_EYE, 262144),
    MYSTIC_WILL(Prayer.MYSTIC_WILL, 524288),
    ROCK_SKIN(Prayer.ROCK_SKIN, 8),
    SUPERHUMAN_STRENGTH(Prayer.SUPERHUMAN_STRENGTH, 16),
    IMPROVED_REFLEXES(Prayer.IMPROVED_REFLEXES, 32),
    RAPID_RESTORE(Prayer.RAPID_RESTORE, 64),
    RAPID_HEAL(Prayer.RAPID_HEAL, 128),
    PROTECT_ITEM(Prayer.PROTECT_ITEM, 256),
    HAWK_EYE(Prayer.HAWK_EYE, 1048576),
    MYSTIC_LORE(Prayer.MYSTIC_LORE, 2097152),
    STEEL_SKIN(Prayer.STEEL_SKIN, 512),
    ULTIMATE_STRENGTH(Prayer.ULTIMATE_STRENGTH, 1024),
    INCREDIBLE_REFLEXES(Prayer.INCREDIBLE_REFLEXES, 2048),
    PROTECT_FROM_MAGIC(Prayer.PROTECT_FROM_MAGIC, 4096),
    PROTECT_FROM_MISSILES(Prayer.PROTECT_FROM_MISSILES, 8192),
    PROTECT_FROM_MELEE(Prayer.PROTECT_FROM_MELEE, 16384),
    EAGLE_EYE(Prayer.EAGLE_EYE, 4194304),
    MYSTIC_MIGHT(Prayer.MYSTIC_MIGHT, 8388608),
    RETRIBUTION(Prayer.RETRIBUTION, 32768),
    REDEMPTION(Prayer.REDEMPTION, 65536),
    SMITE(Prayer.SMITE, 131072),
    PRESERVE(Prayer.PRESERVE, 268435456),
    CHIVALRY(Prayer.CHIVALRY, 33554432),
    PIETY(Prayer.PIETY, 67108864),
    RIGOUR(Prayer.RIGOUR, 16777216),
    AUGURY(Prayer.AUGURY, 134217728);

    private final Prayer prayer;
    private final int bitValue;

    private QuickPrayers(Prayer prayer, int bitValue) {
        this.prayer = prayer;
        this.bitValue = bitValue;
    }

    public static List<Prayer> getSelectedPrayers(int varbitValue) {
        List<Prayer> selectedPrayers = new ArrayList();
        QuickPrayers[] var2 = values();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            QuickPrayers pwb = var2[var4];
            if ((varbitValue & pwb.getBitValue()) != 0) {
                selectedPrayers.add(pwb.getPrayer());
            }
        }

        return selectedPrayers;
    }

    public Prayer getPrayer() {
        return this.prayer;
    }

    public int getBitValue() {
        return this.bitValue;
    }
}
