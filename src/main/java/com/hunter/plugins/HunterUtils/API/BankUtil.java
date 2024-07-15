//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hunter.plugins.HunterUtils.API;

import com.example.EthanApiPlugin.EthanApiPlugin;
import com.example.EthanApiPlugin.Collections.Bank;
import com.example.EthanApiPlugin.Collections.BankInventory;
import com.example.EthanApiPlugin.Collections.query.ItemQuery;
import com.example.Packets.MousePackets;
import com.example.Packets.WidgetPackets;
import java.util.Collection;
import java.util.Iterator;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;

public class BankUtil {
    public BankUtil() {
    }

    public static void depositAll() {
        Widget depositInventory = EthanApiPlugin.getClient().getWidget(WidgetInfo.BANK_DEPOSIT_INVENTORY);
        if (depositInventory != null) {
            MousePackets.queueClickPacket();
            WidgetPackets.queueWidgetAction(depositInventory, new String[]{"Deposit inventory"});
        }

    }

    public static ItemQuery nameContainsNoCase(String name) {
        return Bank.search().filter((widget) -> {
            return widget.getName().toLowerCase().contains(name.toLowerCase());
        });
    }

    public static int getItemAmount(int itemId) {
        return getItemAmount(itemId, false);
    }

    public static int getItemAmount(int itemId, boolean stacked) {
        return stacked ? (Integer)Bank.search().withId(itemId).first().map(Widget::getItemQuantity).orElse(0) : Bank.search().withId(itemId).result().size();
    }

    public static int getItemAmount(String itemName) {
        return nameContainsNoCase(itemName).result().size();
    }

    public static boolean hasItem(int id) {
        return hasItem(id, 1, false);
    }

    public static boolean hasItem(int id, int amount) {
        return getItemAmount(id, false) >= amount;
    }

    public static boolean hasItem(int id, int amount, boolean stacked) {
        return getItemAmount(id, stacked) >= amount;
    }

    public static boolean hasAny(int... ids) {
        int[] var1 = ids;
        int var2 = ids.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            int id = var1[var3];
            if (getItemAmount(id) > 0) {
                return true;
            }
        }

        return false;
    }

    public static boolean containsExcept(Collection<Integer> ids) {
        if (!Bank.isOpen()) {
            return false;
        } else {
            Collection<Widget> inventoryItems = BankInventory.search().result();
            Iterator var2 = inventoryItems.iterator();

            Widget item;
            do {
                if (!var2.hasNext()) {
                    return false;
                }

                item = (Widget)var2.next();
            } while(ids.contains(item.getItemId()));

            return true;
        }
    }
}
