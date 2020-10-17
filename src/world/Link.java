/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world;

public enum Link {
    INSULTED(-10, 100, "Insulted"),
    PRAISED(10, 50, "Praised"),
    KIN(10, "Kin"),
    SIBLING(30, "Sibling"),
    CHILD(40, "Child"),
    ALLY(20, "Ally"),
    COUSIN(20, "Cousin"),
    RIVAL(-20, "Rival"),
    FOE(-40, "Bitter Foe"),
    SERVED(10, "Served Together");

    int effectBase;
    int effectCurrent;
    public String description;
    int expireTime;

    Link(int effect, int expireTime, String description) {
        effectBase = effect;
        effectCurrent = effect;
        this.description = description;
        this.expireTime = expireTime;
    }

    Link(int effect, String description) {
        this(effect, -1, description);
    }

    public void increase() {
        effectCurrent += effectBase;
    }

    @Override
    public String toString() {
        return description + ": " + (effectCurrent >= 0 ? "+" : "")
                + effectCurrent + (expireTime < 0 ? ": " + expireTime + " days left." : "");
    }
}
