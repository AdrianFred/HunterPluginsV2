//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hunter.plugins.HunterUtils.strategy;

import com.example.EthanApiPlugin.Collections.NPCs;
import com.example.EthanApiPlugin.Collections.TileObjects;
import com.example.EthanApiPlugin.Collections.query.NPCQuery;
import com.example.EthanApiPlugin.Collections.query.TileObjectQuery;
import com.example.InteractionApi.NPCInteraction;
import com.example.InteractionApi.TileObjectInteraction;
import java.util.Optional;
import java.util.function.Predicate;

import com.hunter.plugins.HunterUtils.strategy.TaskInterface;
import net.runelite.api.NPC;
import net.runelite.api.TileObject;
import net.runelite.client.config.Config;
import net.runelite.client.plugins.Plugin;

public abstract class AbstractTask<T extends Plugin, V extends Config> implements TaskInterface {
    protected T plugin;
    protected V config;

    public AbstractTask(T plugin, V config) {
        this.plugin = plugin;
        this.config = config;
    }

    public abstract boolean validate();

    public abstract void execute();

    public boolean interactObject(TileObject object, String action) {
        return object == null ? false : TileObjectInteraction.interact(object, new String[]{action});
    }

    public boolean interactObject(String objectName, String action, boolean nearest) {
        TileObjectQuery query = TileObjects.search().nameContains(objectName).withAction(action);
        Optional<TileObject> object = Optional.ofNullable(nearest ? (TileObject)query.nearestToPlayer().orElse((TileObject) null) : (TileObject)query.first().orElse((TileObject) null));
        return this.interactObject((TileObject)object.orElse((TileObject) null), action);
    }

    public boolean interactObject(String objectName, String action, Predicate<TileObject> condition) {
        TileObject object = (TileObject)TileObjects.search().nameContains(objectName).withAction(action).filter(condition).first().orElse((TileObject) null);
        return this.interactObject(object, action);
    }

    public boolean interactNpc(NPC npc, String action) {
        return npc == null ? false : NPCInteraction.interact(npc, new String[]{action});
    }

    public boolean interactNpc(String npcName, String action, boolean nearest) {
        NPCQuery query = NPCs.search().nameContains(npcName).withAction(action);
        Optional<NPC> npc = Optional.ofNullable(nearest ? (NPC)query.nearestToPlayer().orElse((NPC) null) : (NPC)query.first().orElse((NPC) null));
        return this.interactNpc((NPC)npc.orElse((NPC) null), action);
    }

    public boolean interactNpc(String npcName, String action, Predicate<NPC> condition) {
        NPC npc = (NPC)NPCs.search().nameContains(npcName).withAction("Attack").filter(condition).first().orElse((NPC) null);
        return this.interactNpc(npc, action);
    }
}
