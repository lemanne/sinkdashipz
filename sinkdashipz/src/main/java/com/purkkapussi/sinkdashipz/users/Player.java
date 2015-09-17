/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.users;

import com.purkkapussi.sinkdashipz.domain.Ship;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ile
 */
public class Player extends Actor{
    
    private final ArrayList<Ship> ships;
    private String nickName;
    
    public Player(String nick){
        this.nickName = nick;
        this.ships = new ArrayList<Ship>();
    }
    
    public ArrayList<Ship> getShips(){
        return this.ships;
    }
    
    
    
}