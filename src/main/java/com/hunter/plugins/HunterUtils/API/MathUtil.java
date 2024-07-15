//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hunter.plugins.HunterUtils.API;

import java.util.concurrent.ThreadLocalRandom;
import net.runelite.api.coords.WorldPoint;

public class MathUtil {
    public MathUtil() {
    }

    public static int random(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static WorldPoint randomizeTile(WorldPoint wp, int rX, int rY) {
        return wp.dx(random(-rX, rX + 1)).dy(random(-rY, rY + 1));
    }

    public static WorldPoint randomizeTile2(WorldPoint wp, int rX, int rY) {
        return wp.dx(random(rX, rX + 1)).dy(random(rY, rY + 1));
    }
}
