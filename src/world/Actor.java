/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Actor extends Entity {

    public ArrayList<Relation> relations;
    String firstName;
    public String familyName;
    public String fullName;
    public String cognomen;
    public Position position;
    public boolean patrician;
    boolean travelling;
    
    public GregorianCalendar birthDate;
    public GregorianCalendar deathDate;

    int x, y, martial, rhetoric, smarts, labor, age;

    public Actor(String firstName, String familyName, int x, int y, boolean isPatrician, int age) {
        travelling = false;
        this.firstName = firstName;
        this.familyName = familyName;
        fullName = firstName + " " + familyName;
        relations = new ArrayList<>();
        position = null;
        patrician = isPatrician;

        this.x = x;
        this.y = y;

        this.age = age;

        martial = 0;
        rhetoric = 0;
        smarts = 0;
        labor = 0;
    }

    public void setSkills(int martial, int rhetoric, int smarts, int labor) {
        this.martial = martial;
        this.rhetoric = rhetoric;
        this.smarts = smarts;
        this.labor = labor;
    }

    public Actor(String firstName, String familyName, String cognomen, int x, int y, boolean isPatrician, int age) {
        this(firstName, familyName, x, y, isPatrician, age);
        this.cognomen = cognomen;
        fullName += (" " + cognomen);
    }

    public void makeAction() {
    }

    public void addRelation(Actor subject, Link link, boolean reflect) {
        Relation existingRelation = actorRelation(subject);
        if (existingRelation != null) {
            existingRelation.addLink(link);
        } else {
            relations.add(new Relation(subject, link));
        }

        if (reflect) {
            subject.addRelation(this, link, false);
        }
    }

    private Relation actorRelation(Actor subject) {
        for (Relation relation : relations) {
            if (subject == relation.subject) {
                return relation;
            }
        }
        return null;
    }

    private Link getLink(Actor subject, Link target) {
        for (Link link : actorRelation(subject).links) {
            if (target == link) {
                return link;
            }
        }
        return null;
    }

    /**
     * Checks if this Actor has any relations with the given Actor
     *
     * @param subject
     * @return
     */
    public boolean hasRelations(Actor subject) {
        return relations.stream().anyMatch((relation) -> (relation.subject == subject));
    }

    /**
     * Checks if the link of the given type exists for this Actor
     *
     * @param link
     * @return
     */
    public boolean hasLink(Link link) {
        return relations.stream().anyMatch((relation) -> (relation.links.contains(link)));
    }

    public ArrayList<Actor> getRelations(Link link) {
        ArrayList<Actor> linkedActors = new ArrayList<>();
        relations.stream().filter((relation) -> (relation.existingLink(link) != null)).forEachOrdered((relation) -> {
            linkedActors.add(relation.subject);
        });
        return linkedActors;
        
    }

    public void setCognomen(String cognomen) {
        this.cognomen = cognomen;
        fullName += (" " + cognomen);
    }

    public Location getLocation() {
        for (Location location : World.locations) {
            if (x == location.x && y == location.y) {
                return location;
            }
        }
        return null;
    }

    public boolean hasPosition() {
        return position != null;
    }
    
    public String getDescriptor() {
    	if (martial >= 60) return "military leader";
    	else if (rhetoric >= 60) return "orator";
    	else if (smarts >= 60) return "philosopher";
    	else if (labor >= 60) return "engineer";
    	else return "politician";
    }
    
//    @Override
//    public String toString(){
//        return (firstName+" of the House of "+familyName)+(cognomen!=null?", known to others as "+cognomen:". "+
//                (position!=null?(firstName+" is only one of "+position.max+" "+position.description+"s"+" that occupy the Roman senate. "
//                +(World.government.mostMartial()==this?"but the Republic's finest military mind.":"")
//                +(World.government.mostRhetoric()==this?"but the Republic's finest speaker.":"")
//                +(World.government.mostSmarts()==this?"but the Republic's quickest mind.":"")
//                +(World.government.mostLabor()==this?"but the Republic's most diligent worker.":"")
//                ):""));
//    }
}
