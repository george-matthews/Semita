/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author George
 */
public class Government {

    public HashMap<Position, ArrayList<Actor>> positions = new HashMap<>();
    ArrayList<Actor> consuls = new ArrayList<>();
    ArrayList<Actor> tribunes = new ArrayList<>();
    ArrayList<Actor> quaestors = new ArrayList<>();
    ArrayList<Actor> praetors = new ArrayList<>();
    ArrayList<Actor> patAediles = new ArrayList<>();
    ArrayList<Actor> plebAediles = new ArrayList<>();

    ArrayList<ArrayList<Actor>> aediles = new ArrayList<>();

    ArrayList<Assembly> assemblies = new ArrayList<>();

    public Government() {
        positions.put(Position.CONSUL, consuls);
        positions.put(Position.TRIBUNE, tribunes);
        positions.put(Position.QUAESTOR, quaestors);
        positions.put(Position.PRAETOR, praetors);
        positions.put(Position.PATAEDILE, patAediles);
        positions.put(Position.PLEBAEDILE, plebAediles);

        aediles.add(plebAediles);
        aediles.add(patAediles);
    }

    public boolean canPosition(Position position, Actor actor, boolean startup) {
        if (positions.get(position).size() != position.max) { //Is the position available?
            int reqAge = (actor.patrician == true ? position.ageReqPat : position.ageReqPle);
            if (reqAge <= actor.age && position.reqClass(actor)) { //Is the actors the required age for their standing?
                if (startup || position.reqPosition(actor.position)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setPosition(Position position, Actor actor) {
        positions.get(position).add(actor);
        actor.position = position;
    }
}
