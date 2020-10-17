/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import world.World;

import java.util.GregorianCalendar;
import java.util.Random;
import world.Actor;
import world.Government;
import world.Link;
import world.Location;
import world.Position;

/**
 *
 * @author George
 */
public class ActorFactory {

    Government government;

    static String[] firstNames = {"Agrippa", "Decimus", "Hostus", "Marcus",
        "Octavius", "Proculus", "Sertor", "Statius", "Vibius", "Appius", "Faustus", "Lucius",
        "Mettius", "Opitus", "Publius", "Servius", "Tiberius", "Volesus", "Aulus", "Gaius",
        "Mamercus", "Paullus", "Quintus", "Sextus", "Titus", "Vopiscus", "Caeso",
        "Gnaeus", "Manius", "Numerius", "Postumus", "Septimus", "Spurius", "Tullus",
        "Ancus", "Minatus", "Ovius", "Attius", "Minius", "Paccius", "Trebius", "Decius",
        "Nerius", "Pompo", "Vibius", "Herius", "Novius", "Salvius", "Vettius", "Marius",
        "Numa", "Seppius", "Mettius", "Numerius", "Statius"};

     public static String[] patFamilyNames = {"Cornelius", "Claudius", "Valerius", "Fabius",
        "Licinius", "Servilius", "Manilius", "Terentius", "Aemilius", "Junius", "Papirius", "Semronius",
        "Furius", "Marcius", "Postumia", "Caecilius", "Sulpicius", "Julius", "Minucius", "Quintus",
        "Atilius", "Calpurnius", "Fulvius", "Plautius", "Pompeius", "Octavius", "Aurelius", "Pomponius",
        "Aebutius", "Metilius", "Tarquinius", "Geganius", "Minucius", "Romilius", "Aquillius", "Curtius",
        "Potitius", "Volumnius", "Veturius", "Tullius", "Siccius", "Pinarius", "Sergius", "Mucius",
        "Herminius", "Lartius", "Papirius"};

    public static String[] pleFamilyNames = {"Apustia", "Abronia", "Aurelius", "Annicius",
        "Baebius", "Barbatius", "Brutus", "Caecius", "Cincius", "Consentius", "Caetronius", "Decius",
        "Duronius", "Egnatius", "Erucius", "Enniacus", "Fabius", "Gavius", "Geminius", "Galerius",
        "Icillius", "Juventius", "Laelius", "Messius", "Marius", "Neratius", "Novius", "Paccidius",
        "Pedius", "Papius", "Salvius", "Sergius", "Sestius", "Sicinius", "Sulpicius", "Tarpeius",
        "Ulpis", "Villius", "Valerius", "Lacerius"};

    static String[] cognomen = {"Aculeo", "Agricola", "Albinus", "Ambustus",
        "Albus", "Arvina", "Buteo", "Bucco", "Caecus", "Caepio", "Calidus", "Calvinus",
        "Canus", "Celer", "Cicero", "Celsus", "Cilo", "Cincinnatus", "Corvus", "Cordus",
        "Crassus", "Crasipes", "Crispus", "Denter", "Dolabella", "Dursuo", "Flaccus", "Flavus",
        "Florus", "Fronto", "Gurges", "Habitus", "Helva", "Labeo", "Lactuca", "Lentulus",
        "Libo", "Lepidus", "Laterensis", "Longus", "Lupus", "Lurco", "Macer", "Malleolus",
        "Merula", "Murena", "Mus", "Musca", "Nasica", "Matta", "Nepos", "Nero",
        "Nerva", "Niger", "Novellus", "Ocella", "Paullus", "Pavo", "Pera", "Pictor",
        "Plautus", "Pricus", "Pulcher", "Quadratus", "Ralla", "Rufus", "Ruga", "Rutilus",
        "Salinator", "Scaeva", "Scapula", "Semeca", "Silo", "Silanus", "Strabo", "Sura",
        "Taurus", "Trigeminus", "Turdus", "Verres", "Vetus"};

    static Random r = new Random();
    boolean patrician;

    public ActorFactory(Government government) {
        this.government = government;
    }

    public Actor fullRandom() {
        patrician = r.nextBoolean();
        String[] fNameList = patrician ? patFamilyNames : pleFamilyNames;
        String familyName = fNameList[r.nextInt(fNameList.length)];
        return familyDefined(familyName, patrician);
    }

    public Actor locationDefined(int x, int y) {
        patrician = r.nextBoolean();
        String[] fNameList = patrician ? patFamilyNames : pleFamilyNames;
        String familyName = fNameList[r.nextInt(fNameList.length)];
        return locationFamilyDefined(familyName, x, y, patrician);
    }

    public Actor familyDefined(String familyName, boolean isPatrician) {
        patrician = isPatrician;
        Location location = World.locations.get(r.nextInt(World.locations.size()));
        return locationFamilyDefined(familyName, location.x, location.y, patrician);
    }

    public Actor locationFamilyDefined(String familyName, int x, int y, boolean isPatrician) { //No case for just location defined.
        patrician = isPatrician;
        int age = r.nextInt(40) + 20;
        String firstName = firstNames[r.nextInt(firstNames.length)];
        return fullDefined(firstName, familyName, x, y, age);
    }

    public Actor fullDefined(String firstName, String familyName, int x, int y, int age) {
        Actor actor = new Actor(firstName, familyName, x, y, patrician, age);
        return actor;
    }

    public Actor actorWithCognomen(Actor actor, int cogChance) {
        if (r.nextInt(100) <= cogChance) {
            actor.setCognomen(cognomen[r.nextInt(cognomen.length)]);
        }
        return actor;
    }

    /**
     * Fills the actors list with a finite amount of randomly generated actors
     *
     * @param amount
     * @param assignPosition
     */
    public void genActors(int amount, boolean assignPosition) {
        int divider = World.actors.size();
        for (int i = 0; i < amount; i++) {
            Actor actor = actorWithCognomen(fullRandom(), 50);
            
            genDates(actor);
            
            World.addEntity(actor);
            assignSkills(actor);
            if (assignPosition) {
                randomPosition(actor);
            }
        }
        genRelationships(divider);
    }
    
    /**
     * For HTML output
     * @param actor
     */
    public void genDates(Actor actor) {
    	int birthYear = randBetween(100, 30);
    	int deathYear = randBetween(birthYear, 10);
    	
        GregorianCalendar birth = new GregorianCalendar();
        birth.set(birth.YEAR, birthYear);
        birth.set(birth.DAY_OF_YEAR, randBetween(1, birth.getActualMaximum(birth.DAY_OF_YEAR)));
        
        GregorianCalendar death = new GregorianCalendar();
        death.set(death.YEAR, deathYear);
        death.set(death.DAY_OF_YEAR, randBetween(1, death.getActualMaximum(death.DAY_OF_YEAR)));
    	
        actor.birthDate = birth;
        actor.deathDate = death;
    }

    private void genRelationships(int divider) {
        for (int i = divider; i < World.actors.size(); i++) {
            Actor thisActor = World.actors.get(i);
            for (int j = 0; j < World.actors.size(); j++) {
                Actor otherActor = World.actors.get(j);
                if (thisActor != otherActor && !thisActor.hasRelations(otherActor)) {
                    Link bond = null;
                    if (thisActor.familyName.equals(otherActor.familyName)) {
                        thisActor.addRelation(otherActor, Link.KIN, true);
                        switch (r.nextInt(10)) {
                            case 0:
                            case 1:
                            case 2:
                                bond = Link.SIBLING;
                                break;
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                                bond = Link.COUSIN;
                                break;
                        }
                    }
                    switch (r.nextInt(5)) {
                        case 0:
                        case 1:
                            bond = Link.ALLY;
                            break;
                        case 2:
                        case 3:
                            bond = Link.RIVAL;
                            break;
                        case 4:
                            bond = Link.SERVED;
                            break;
                    }

                    if (bond != null) {
                        thisActor.addRelation(otherActor, bond, true);
                    }
                    //Random chance of event relation
                }
            }

        }
    }

    private void assignSkills(Actor actor) {
        actor.setSkills(getSkill(actor), getSkill(actor), getSkill(actor), getSkill(actor));
    }

    private void randomPosition(Actor actor) {
        for (Position position : Position.values()) {
            while (government.canPosition(position, actor, true) && !actor.hasPosition()) {
                government.setPosition(position, actor);
            }
        }
    }

    private int getSkill(Actor actor) {
        return r.nextInt(60) + 20;
    }
    
    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}
