/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.tools;

import com.purkkapussi.sinkdashipz.domain.Direction;
import com.purkkapussi.sinkdashipz.domain.Hull;
import com.purkkapussi.sinkdashipz.domain.Ship;
import com.purkkapussi.sinkdashipz.users.Actor;
import java.util.ArrayList;
import java.util.Random;

public class ShipCreator {
    
    private Random rand = new Random();

    /*
     Metodi luo satunnaisen laivan annetulla koolla satunnaiseen sijaintiin
     */
    public Ship createRandomShip(int size) {
        
        Ship ship = new Ship();
        
        Direction direction = Direction.SOUTH;
        if (rand.nextInt(2) == 1) {
            direction = Direction.EAST;
        }
        Location start = createStartPoint(size, direction);
        
        Hull hull = new Hull(start);
        
        while (size > 0) {
            
            
            
            if (direction == direction.SOUTH) {
                start = start.moveSouth();
                shipBuilder(ship,new Hull(start.getX(),start.getY()));
            } else {
                start = start.moveEast();
                shipBuilder(ship, new Hull(start.getX(),start.getY()));
            }
            
           
            
            size--;
        }
        
        return ship;
    }
    
    public void shipBuilder(Ship ship, Hull hull){
        ship.addHull(hull);
    }
    /*
     Metodi luo satunnaisen aloituspisteen joka sopii yhteen laivan koon kanssa.
     */

    public Location createStartPoint(int size, Direction direction) {
        
        int startX = rand.nextInt(9);
        while (direction == Direction.EAST && (startX + size) >= 10) {
            startX = rand.nextInt(9);
        }
        int startY = rand.nextInt(9);
        while (direction == Direction.NORTH && (startY + size) >= 10) {
            startY = rand.nextInt(9);
        }
        return new Location(startX, startY);
    }
    
    public void addShipToActor(Actor actor, Ship ship) throws IllegalArgumentException {
        
        ArrayList<Ship> ships = actor.getShips();
        if (ships.isEmpty()) {
            actor.addShip(ship);
        } else {
            
            for (Ship shipster : ships) {
                if (ship.equals(shipster)) {
                    throw new IllegalArgumentException("Ships overlapping!");
                }
            }
            actor.addShip(ship);
        }
        
    }
    
    public void createRandomFleet(Actor actor, int fleetSize){
        
        for (int i=0; i< fleetSize; i++){
            Ship ship = createRandomShip(fleetSize-i);
            
            try {
                addShipToActor(actor,ship);
                        }
            catch (IllegalArgumentException e){
                i--;
            }
        }
        
        
    }
}