//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hunter.plugins.HunterUtils.strategy;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TaskManager {
    private List<com.hunter.plugins.HunterUtils.strategy.AbstractTask> tasks = new LinkedList();

    public TaskManager() {
    }

    public void addTask(com.hunter.plugins.HunterUtils.strategy.AbstractTask task) {
        this.tasks.add(task);
    }

    public void removeTask(com.hunter.plugins.HunterUtils.strategy.AbstractTask task) {
        this.tasks.remove(task);
    }

    public void clearTasks() {
        this.tasks.clear();
    }

    public boolean hasTasks() {
        return !this.tasks.isEmpty();
    }

    public List<com.hunter.plugins.HunterUtils.strategy.AbstractTask> getTasks() {
        return this.tasks;
    }

    public void runTasks() {
        Iterator var1 = this.tasks.iterator();

        while(var1.hasNext()) {
            com.hunter.plugins.HunterUtils.strategy.AbstractTask task = (com.hunter.plugins.HunterUtils.strategy.AbstractTask)var1.next();
            if (task.validate()) {
                task.execute();
                break;
            }
        }

    }
}
