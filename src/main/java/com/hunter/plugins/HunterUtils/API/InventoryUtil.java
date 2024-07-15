//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hunter.plugins.HunterUtils.API;

import com.example.EthanApiPlugin.EthanApiPlugin;
import com.example.EthanApiPlugin.Collections.Inventory;
import com.example.EthanApiPlugin.Collections.query.ItemQuery;
import com.example.Packets.MousePackets;
import com.example.Packets.WidgetPackets;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import net.runelite.api.widgets.Widget;
import net.runelite.client.util.Text;

public class InventoryUtil {
    public InventoryUtil() {
    }

    public static boolean useItemNoCase(String name, String... actions) {
        return (Boolean)nameContainsNoCase(name).first().flatMap((item) -> {
            MousePackets.queueClickPacket();
            WidgetPackets.queueWidgetAction(item, actions);
            return Optional.of(true);
        }).orElse(false);
    }

    public static ItemQuery nameContainsNoCase(String name) {
        return Inventory.search().filter((widget) -> {
            return widget.getName().toLowerCase().contains(name.toLowerCase());
        });
    }

    public static Optional<Widget> getById(int id) {
        return Inventory.search().withId(id).first();
    }

    public static Optional<Widget> getItemNameContains(String name, boolean caseSensitive) {
        return caseSensitive ? Inventory.search().filter((widget) -> {
            return widget.getName().contains(name);
        }).first() : Inventory.search().filter((widget) -> {
            return widget.getName().toLowerCase().contains(name.toLowerCase());
        }).first();
    }

    public static Optional<Widget> getItemNameContains(String name) {
        return getItemNameContains(name, true);
    }

    public static Optional<Widget> getItem(String name, boolean caseSensitive) {
        return caseSensitive ? Inventory.search().filter((widget) -> {
            return widget.getName().equals(name);
        }).first() : Inventory.search().filter((widget) -> {
            return widget.getName().toLowerCase().equals(name.toLowerCase());
        }).first();
    }

    public static Optional<Widget> getItem(String name) {
        return getItem(name, true);
    }

    public static int getItemAmount(String name, boolean stacked) {
        if (stacked) {
            return nameContainsNoCase(name).first().isPresent() ? ((Widget)nameContainsNoCase(name).first().get()).getItemQuantity() : 0;
        } else {
            return nameContainsNoCase(name).result().size();
        }
    }

    public static int getItemAmount(int id) {
        return getItemAmount(id, false);
    }

    public static int getItemAmount(int id, boolean stacked) {
        if (stacked) {
            return getById(id).isPresent() ? ((Widget)getById(id).get()).getItemQuantity() : 0;
        } else {
            return Inventory.search().withId(id).result().size();
        }
    }

    public static boolean hasItem(String name) {
        return getItemAmount(name, false) > 0;
    }

    public static boolean hasItem(String name, boolean stacked) {
        return getItemAmount(name, stacked) > 0;
    }

    public static boolean hasItem(String name, int amount) {
        return getItemAmount(name, false) >= amount;
    }

    public static boolean hasItem(String name, int amount, boolean stacked) {
        return getItemAmount(name, stacked) >= amount;
    }

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

    public static boolean hasAnyItems(Collection<Integer> itemIds) {
        Iterator var1 = itemIds.iterator();

        Integer id;
        do {
            if (!var1.hasNext()) {
                return false;
            }

            id = (Integer)var1.next();
        } while(!hasItem(id));

        return true;
    }

    public static boolean hasItem(int id) {
        return getItemAmount(id) > 0;
    }

    public static List<Widget> getItems() {
        return Inventory.search().result(); // This is a list of widgets
    }

    public static List<String> getItemNames() {
        return Inventory.search().result().stream()
                .map(widget -> Text.removeTags(widget.getName()))
                .collect(Collectors.toList());
    }

    public static List<Integer> getItemIds() {
        return Inventory.search().result().stream()
                .map(Widget::getItemId)
                .collect(Collectors.toList());
    }

    public static Map<String, Integer> getItemNamesAndIds() {
        return Inventory.search().result().stream()
                .collect(Collectors.toMap(widget -> Text.removeTags(widget.getName()), Widget::getItemId));
    }


    public static int emptySlots() {
        return 28 - Inventory.search().result().size();
    }

    public static boolean runePouchContains(int id) {
        Set<Integer> runePouchIds = new HashSet();
        if (EthanApiPlugin.getClient().getVarbitValue(29) != 0) {
            runePouchIds.add(Runes.getRune(EthanApiPlugin.getClient().getVarbitValue(29)).getItemId());
        }

        if (EthanApiPlugin.getClient().getVarbitValue(1622) != 0) {
            runePouchIds.add(Runes.getRune(EthanApiPlugin.getClient().getVarbitValue(1622)).getItemId());
        }

        if (EthanApiPlugin.getClient().getVarbitValue(1623) != 0) {
            runePouchIds.add(Runes.getRune(EthanApiPlugin.getClient().getVarbitValue(1623)).getItemId());
        }

        if (EthanApiPlugin.getClient().getVarbitValue(14285) != 0) {
            runePouchIds.add(Runes.getRune(EthanApiPlugin.getClient().getVarbitValue(14285)).getItemId());
        }

        Iterator var2 = runePouchIds.iterator();

        int runePouchId;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            runePouchId = (Integer)var2.next();
        } while(runePouchId != id);

        return true;
    }

    public static boolean runePouchContains(Collection<Integer> ids) {
        Iterator var1 = ids.iterator();

        int runeId;
        do {
            if (!var1.hasNext()) {
                return true;
            }

            runeId = (Integer)var1.next();
        } while(runePouchContains(runeId));

        return false;
    }

    public static int runePouchQuanitity(int id) {
        Map<Integer, Integer> runePouchSlots = new HashMap();
        if (EthanApiPlugin.getClient().getVarbitValue(29) != 0) {
            runePouchSlots.put(Runes.getRune(EthanApiPlugin.getClient().getVarbitValue(29)).getItemId(), EthanApiPlugin.getClient().getVarbitValue(1624));
        }

        if (EthanApiPlugin.getClient().getVarbitValue(1622) != 0) {
            runePouchSlots.put(Runes.getRune(EthanApiPlugin.getClient().getVarbitValue(1622)).getItemId(), EthanApiPlugin.getClient().getVarbitValue(1625));
        }

        if (EthanApiPlugin.getClient().getVarbitValue(1623) != 0) {
            runePouchSlots.put(Runes.getRune(EthanApiPlugin.getClient().getVarbitValue(1623)).getItemId(), EthanApiPlugin.getClient().getVarbitValue(1626));
        }

        if (EthanApiPlugin.getClient().getVarbitValue(14285) != 0) {
            runePouchSlots.put(Runes.getRune(EthanApiPlugin.getClient().getVarbitValue(14285)).getItemId(), EthanApiPlugin.getClient().getVarbitValue(14286));
        }

        return runePouchSlots.containsKey(id) ? (Integer)runePouchSlots.get(id) : 0;
    }
}
