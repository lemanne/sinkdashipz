package com.purkkapussi.sinkdashipz.domain;

import com.purkkapussi.sinkdashipz.tools.Location;
import java.util.Objects;



public class Hull {
    
    private Location location;
    private int x;
    private int y;
    
    public Hull(Location location){
        this.location = location;
        this.x = location.getX();
        this.y = location.getY();
    }
    
    public Hull(int x, int y){
        this.x = x;
        this.y = y;
        this.location = new Location(x,y);
        
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    
    
    public String toString(){
        return this.location.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.location);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hull other = (Hull) obj;
        return Objects.equals(this.location, other.location);
    }
    
    
    
}