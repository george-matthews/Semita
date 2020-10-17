/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world;

/**
 *
 * @author George
 */
public enum Position {
    CONSUL(2, "Consul", 40, 42, 0),
    PRAETOR(8, "Praetor", 39, 39, 0),
    QUAESTOR(20, "Quaestor", 28, 30, 0),
    PLEBAEDILE(2, "Plebeian Aedile", 36, 36, 0),
    PATAEDILE(2, "Curule Aedile", 36, 36, 0),
    TRIBUNE(10, "Plebeian Tribune", 40, 42, 0);
    //MILTRIBUNE

    public int max, ageReqPat, ageReqPle, milReq;
    public String description;

    Position(int max, String description, int ageReqPat, int ageReqPle, int milReq) {
        this.max = max;
        this.description = description;
        this.ageReqPat = ageReqPat;
        this.ageReqPle = ageReqPle;
        this.milReq = milReq;
    }

    public boolean reqClass(Actor actor) {
        if (this == PLEBAEDILE || this == TRIBUNE) {
            return (!actor.patrician);
        }
        return true;
    }
    
    public boolean reqPosition(Position position){
        switch(this){
            case CONSUL: return position == PRAETOR; 
            case PRAETOR: return (position == PRAETOR) || (position == PLEBAEDILE) || (position == PATAEDILE);
            case QUAESTOR: return true;
            case PATAEDILE: case PLEBAEDILE: return position == QUAESTOR;
            case TRIBUNE: return true;
            default: return false;
        }
    }
}
