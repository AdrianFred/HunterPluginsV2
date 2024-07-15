//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hunter.plugins.HunterUtils.API;

import com.example.EthanApiPlugin.Collections.Inventory;
import com.example.EthanApiPlugin.Collections.NPCs;
import com.example.EthanApiPlugin.Collections.query.NPCQuery;
import com.google.inject.Inject;
import java.util.List;
import net.runelite.api.Client;
import net.runelite.api.Skill;
import net.runelite.api.coords.WorldArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerUtil {
    private static final Logger log = LoggerFactory.getLogger(PlayerUtil.class);
    @Inject
    private Client client;

    public PlayerUtil() {
    }

    public boolean isAutoRetaliating() {
        return this.client.getVarpValue(172) == 0;
    }

    public boolean inArea(WorldArea area) {
        return area.contains(this.client.getLocalPlayer().getWorldLocation());
    }

    public boolean inRegion(int region) {
        return this.client.getLocalPlayer().getWorldLocation().getRegionID() == region;
    }

    public boolean inRegion(int... regions) {
        int[] var2 = regions;
        int var3 = regions.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            int region = var2[var4];
            if (this.inRegion(region)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasItem(String name) {
        return Inventory.getItemAmount(name) > 0;
    }

    public boolean hasItem(int id) {
        return Inventory.getItemAmount(id) > 0;
    }

    public int runEnergy() {
        return this.client.getEnergy() / 100;
    }

    public int specEnergy() {
        return this.client.getVarpValue(300) / 10;
    }

    public int hp() {
        return this.client.getBoostedSkillLevel(Skill.HITPOINTS);
    }

    public boolean isStaminaActive() {
        return this.client.getVarbitValue(25) == 1;
    }

    public boolean isRunning() {
        return this.client.getVarpValue(173) == 0;
    }

    public boolean inMulti() {
        return this.client.getVarbitValue(4605) == 1;
    }

    public boolean isInteracting() {
        return this.client.getLocalPlayer().isInteracting();
    }

    public boolean isBeingInteracted() {
        return NPCs.search().interactingWithLocal().first().isPresent();
    }

    public boolean isBeingInteracted(String name) {
        return NPCs.search().filter((npc) -> {
            return npc.getName() != null && npc.getName().equalsIgnoreCase(name);
        }).interactingWithLocal().first().isPresent();
    }

    public NPCQuery getBeingInteracted(String name) {
        return NPCs.search().filter((npc) -> {
            return npc.getName() != null && npc.getName().equalsIgnoreCase(name);
        }).interactingWithLocal();
    }

    public NPCQuery getBeingInteracted(List<String> names) {
        return NPCs.search().filter((npc) -> {
            return npc.getName() != null && names.contains(npc.getName());
        }).interactingWithLocal();
    }

    public int getTaskCount() {
        return this.client.getVarpValue(394);
    }
}
