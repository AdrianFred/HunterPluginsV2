//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hunter.plugins.HunterUtils.API;

import com.example.EthanApiPlugin.Collections.TileObjects;
import com.example.EthanApiPlugin.Collections.query.TileObjectQuery;
import java.util.Arrays;
import java.util.Optional;
import net.runelite.api.ObjectComposition;
import net.runelite.api.TileObject;

public class ObjectUtil {
    public ObjectUtil() {
    }

    public static Optional<TileObject> getNearestBank() {
        return TileObjects.search().filter((tileObject) -> {
            ObjectComposition comp = TileObjectQuery.getObjectComposition(tileObject);
            if (comp == null) {
                return false;
            } else {
                return comp.getName().toLowerCase().contains("bank") && Arrays.stream(comp.getActions()).anyMatch((action) -> {
                    return action.toLowerCase().contains("bank") || action.toLowerCase().contains("use") || action.contains("open");
                });
            }
        }).nearestToPlayer();
    }

    public static Optional<TileObject> getNearest(String name, boolean caseSensitive) {
        return caseSensitive ? TileObjects.search().withName(name).nearestToPlayer() : withNameNoCase(name).nearestToPlayer();
    }

    public static Optional<TileObject> getNearest(int id) {
        return TileObjects.search().withId(id).nearestToPlayer();
    }

    public static Optional<TileObject> getNearestNameContains(String name) {
        return nameContainsNoCase(name).nearestToPlayer();
    }

    public static TileObjectQuery withNameNoCase(String name) {
        return TileObjects.search().filter((tileObject) -> {
            ObjectComposition comp = TileObjectQuery.getObjectComposition(tileObject);
            return comp == null ? false : comp.getName().toLowerCase().equals(name.toLowerCase());
        });
    }

    public static TileObjectQuery nameContainsNoCase(String name) {
        return TileObjects.search().filter((tileObject) -> {
            ObjectComposition comp = TileObjectQuery.getObjectComposition(tileObject);
            return comp == null ? false : comp.getName().toLowerCase().contains(name.toLowerCase());
        });
    }
}
