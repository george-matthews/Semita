/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world;

import java.util.ArrayList;
import java.util.Random;

import factory.ActorFactory;

/**
 *
 * @author George
 */
public class World {

    public static ArrayList<Actor> actors;
    public static ArrayList<Location> locations;
    static Random r = new Random();
    static Government government;

    /**
     * Initial setup of locations and actors
     */
    public static ArrayList<Actor> initialise(int actorCount) {
        actors = new ArrayList<>();
        locations = new ArrayList<>();
        government = new Government();

        setupLocations();
        setupActors(actorCount);
        
        

//        Statistics.printGovernment(government);
//        Statistics.printRelations(false, actors);
//        Statistics.printGens(actors);

//        System.out.println("==================================================\nCharacters in Each Location");
//        locations.forEach((location) -> {
//            System.out.println(location.name + "(" + location.type + ")" + " " + location.getCharacters().size());
//        });
//        
        return actors;
    }

    /**
     * World method to construct the pre-scripted actors. Commented out chunk
     * ideal for reading from external sources
     *
     * World relations here too, before genActors
     */
    private static void setupActors(int actorCount) {
        ActorFactory afac = new ActorFactory(government);
//        World.addEntity(new Actor("Gaius", "Scipio", 50, 50, true, 40));
//        World.addEntity(new Actor("Flavius", "Brutus", 50, 50, true, 40));
//        World.addEntity(new Actor("Quintus", "Julius", 50, 50, true, 40));
//        World.addEntity(new Actor("Varus", "Marcellus", "Semita", 50, 50, true, 40));

        //setup relations here
        afac.genActors(actorCount, true);
    }

    /**
     * World existing locations of game.
     */
    private static void setupLocations() {
        //USE LOCATION FACTORY
        Object[][] locationArray = {
            {"Rome", 50, 50, "City"},
        {"Capua", 40, 30, "City"},
        {"Neopolis", 55, 60, "City"}
        };

        for (Object[] l : locationArray) {
            Location location = new Location((String) l[0], (int) l[1], (int) l[2], (String) l[3]);
            World.addEntity(location);
        }
    }

    /**
     * Adds the provided entity into it's corresponding list, then sets it's id.
     *
     * @param entity
     */
    public static void addEntity(Entity entity) {
        if (entity instanceof Actor) {
            actors.add((Actor) entity);
            entity.id = actors.indexOf(entity);
        } else {
            locations.add((Location) entity);
            entity.id = locations.indexOf(entity);
        }
    }
    
    

    Actor mostMartial() {
        Actor returnActor = actors.get(0);
        for(Actor actor : actors){
            if(actor.martial > returnActor.martial) returnActor = actor;
        }
        return returnActor;
    }

    Actor mostRhetoric() {
        Actor returnActor = actors.get(0);
        for(Actor actor : actors){
            if(actor.martial > returnActor.martial) returnActor = actor;
        }
        return returnActor;
    }

    Actor mostSmarts() {
        Actor returnActor = actors.get(0);
        for(Actor actor : actors){
            if(actor.martial > returnActor.martial) returnActor = actor;
        }
        return returnActor;
    }

    Actor mostLabor() {
        Actor returnActor = actors.get(0);
        for(Actor actor : actors){
            if(actor.martial > returnActor.martial) returnActor = actor;
        }
        return returnActor;
    }

}
