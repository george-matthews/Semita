/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world;

import java.util.ArrayList;

public final class Relation {

    public ArrayList<Link> links;
    public Actor subject;

    /**
     * Expiring relations? links have different expiring periods depending on
     * how bad they were?
     *
     * @param subject
     * @param link
     */
    public Relation(Actor subject, Link link) {
        this.subject = subject;
        links = new ArrayList<>();
        addLink(link);
    }

    public void addLink(Link link) {
        Link existingLink = existingLink(link);
        if (existingLink != null) {
            existingLink.increase();
        } else {
            links.add(link);
        }
    }

    public int getDisposition() {
        int disp = 0;
        disp = links.stream().map((link) -> link.effectCurrent).reduce(disp, Integer::sum);
        return disp;
    }

    public Link existingLink(Link link) {
        for (Link e : links) {
            if (link.description.equals(e.description)) {
                return e;
            }
        }
        return null;
    }

    public void printLinks() {
        links.forEach((link) -> {
            System.out.println(link.toString());
        });
    }
}
