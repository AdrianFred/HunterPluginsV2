//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hunter.plugins.HunterUtils.API;

import com.example.EthanApiPlugin.Collections.NPCs;
import com.example.EthanApiPlugin.Collections.query.NPCQuery;
import java.util.Optional;
import net.runelite.api.NPC;

public class NpcUtil {
    public NpcUtil() {
    }

    public static Optional<NPC> getNpc(String name, boolean caseSensitive) {
        return caseSensitive ? NPCs.search().withName(name).nearestToPlayer() : nameContainsNoCase(name).nearestToPlayer();
    }

    public static NPCQuery nameContainsNoCase(String name) {
        return NPCs.search().filter((npcs) -> {
            return npcs.getName() != null && npcs.getName().toLowerCase().contains(name.toLowerCase());
        });
    }
}
