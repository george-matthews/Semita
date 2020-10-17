/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world;

import java.util.ArrayList;

/**
 *
 * @author George
 */
public class Location extends Entity {

    int owner;
    public String name;
    public int x, y;
    public String type;

    public Location(String name, int x, int y, String type) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    /**
     * Returns an ArrayList of all characters currently at the location.
     *
     * @return
     */
    public ArrayList<Actor> getCharacters() {
        ArrayList<Actor> characters = new ArrayList<>();
        World.actors.stream().filter((actor) -> (x == actor.x && y == actor.y)).forEachOrdered(characters::add);
        return characters;
    }
}
