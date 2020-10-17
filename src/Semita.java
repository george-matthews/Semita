/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.util.ArrayList;

import world.Actor;
import world.World;

/**
 *
 * @author George
 */
public class Semita {

    public static void main(String[] args) throws IOException {
        ArrayList<Actor> actors = World.initialise(100);
        
        for (Actor actor : actors) {
        	Generator.generate(actor);
        }
		System.out.println("Operation successful! Check the 'Outputs' folder for generated pages.");
    }
}
