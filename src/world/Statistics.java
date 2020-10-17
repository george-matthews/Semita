package world;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import factory.ActorFactory;

/**
 *
 * @author George
 */
public class Statistics {

    public static void printGovernment(Government government) {
        for (Position p : government.positions.keySet()) {
            System.out.println("\n==========" + p.description + "==========");
            government.positions.get(p).forEach((actor) -> {
                if (actor.position == p) {
                    System.out.println(actor.fullName + ": " + (actor.patrician ? "Patrician" : "Pleb"));
                }
            });
        }
    }

    public static void printRelations(boolean byActor, ArrayList<Actor> actors) {
        for (Actor actor : actors) {
            System.out.println("==================================================");
            System.out.println("Relations for " + actor.fullName + " (" + actor.getLocation().name + (actor.hasPosition() ? " - " + actor.position.description : "") + ")");
//            System.out.println(actor.toString());
            if (byActor) {
                printRelationsByActor(actor);
            } else {
                printRelationsByLink(actor);
            }
        }
    }

    public static void printRelationsByActor(Actor actor) {
        for (Relation relation : actor.relations) {
            System.out.println(relation.subject.fullName + ":");
            relation.printLinks();
            System.out.println("Total: " + relation.getDisposition());
            System.out.println();
        }
    }

    public static void printRelationsByLink(Actor actor) {
        for (Link link : Link.values()) {
            if (actor.hasLink(link)) {
                System.out.println("\n" + link.description);
            }
            actor.relations.stream().filter((relation) -> (relation.links.contains(link))).forEachOrdered((relation) -> {
                System.out.println(relation.subject.fullName + ": " + relation.getDisposition() + ": " + relation.subject.getLocation().name);
            });
        }
    }

    public static void printGens(ArrayList<Actor> actors) {
        for (String patFamilyName : ActorFactory.patFamilyNames) {
            for (Actor actor : actors) {
                if (actor.familyName.equals(patFamilyName)) {
                    System.out.println("\n==========" + actor.familyName + "==========");
                    System.out.println(actor.fullName+ (actor.position!=null?": "+actor.position.description:""));
                    actor.getRelations(Link.KIN).forEach((kin) -> {
                        System.out.println(kin.fullName+(kin.position!=null?": "+kin.position.description:""));
                    });
                    break;
                }
            }
        }
    }
}
