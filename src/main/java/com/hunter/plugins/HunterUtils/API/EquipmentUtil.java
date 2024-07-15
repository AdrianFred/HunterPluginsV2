//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hunter.plugins.HunterUtils.API;

import com.example.EthanApiPlugin.Collections.Equipment;
import com.example.EthanApiPlugin.Collections.EquipmentItemWidget;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class EquipmentUtil {
    public EquipmentUtil() {
    }

    public static Optional<EquipmentItemWidget> getItemInSlot(EquipmentSlot slot) {
        return Equipment.search().filter((item) -> {
            EquipmentItemWidget iw = (EquipmentItemWidget)item;
            return iw.getEquipmentIndex() == slot.getIndex();
        }).first();
    }

    public static boolean hasItem(String name) {
        return Equipment.search().nameContainsNoCase(name).first().isPresent();
    }

    public static boolean hasAnyItems(String... names) {
        String[] var1 = names;
        int var2 = names.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String name = var1[var3];
            if (hasItem(name)) {
                return true;
            }
        }

        return false;
    }

    /** @deprecated */
    @Deprecated
    public static boolean hasItems(String... names) {
        String[] var1 = names;
        int var2 = names.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String name = var1[var3];
            if (!hasItem(name)) {
                return false;
            }
        }

        return true;
    }

    public static boolean hasItems(int... ids) {
        int[] var1 = ids;
        int var2 = ids.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            int id = var1[var3];
            if (!hasItem(id)) {
                return false;
            }
        }

        return true;
    }

    public static boolean hasItems(List<Integer> ids) {
        Iterator var1 = ids.iterator();

        int id;
        do {
            if (!var1.hasNext()) {
                return true;
            }

            id = (Integer)var1.next();
        } while(hasItem(id));

        return false;
    }

    public static boolean hasItem(int id) {
        return Equipment.search().withId(id).first().isPresent();
    }

    public static enum EquipmentSlot {
        HEAD(0),
        CAPE(1),
        NECKLACE(2),
        MAIN_HAND(3),
        TORSO(4),
        OFF_HAND(5),
        AMMO(13),
        LEGS(7),
        HANDS(9),
        FEET(10),
        RING(12);

        private final int index;

        private EquipmentSlot(int index) {
            this.index = index;
        }

        public int getIndex() {
            return this.index;
        }
    }
}
