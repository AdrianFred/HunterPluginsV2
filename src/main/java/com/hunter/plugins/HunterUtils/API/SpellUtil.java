//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hunter.plugins.HunterUtils.API;

import com.example.PacketUtils.WidgetInfoExtended;
import net.runelite.api.Client;
import net.runelite.api.widgets.Widget;

public class SpellUtil {
    public SpellUtil() {
    }

    public static WidgetInfoExtended parseStringForWidgetInfoExtended(String input) {
        WidgetInfoExtended[] var1 = WidgetInfoExtended.values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            WidgetInfoExtended value = var1[var3];
            if (value.name().equalsIgnoreCase("SPELL_" + input.replace(" ", "_"))) {
                return value;
            }
        }

        return null;
    }

    public static Widget getSpellWidget(Client client, String input) {
        return client.getWidget(parseStringForWidgetInfoExtended(input).getPackedId());
    }
}
