//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hunter.plugins.HunterUtils.strategy;

import java.util.function.Predicate;
import net.runelite.api.NPC;
import net.runelite.api.TileObject;

public interface TaskInterface {
    boolean validate();

    void execute();

    boolean interactObject(TileObject var1, String var2);

    boolean interactObject(String var1, String var2, boolean var3);

    boolean interactObject(String var1, String var2, Predicate<TileObject> var3);

    boolean interactNpc(NPC var1, String var2);

    boolean interactNpc(String var1, String var2, boolean var3);

    boolean interactNpc(String var1, String var2, Predicate<NPC> var3);
}
