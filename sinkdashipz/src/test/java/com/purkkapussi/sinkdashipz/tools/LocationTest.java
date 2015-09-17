
package com.purkkapussi.sinkdashipz.tools;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Test;


public class LocationTest {
    
    public Location location1 = new Location(1, 1);
    
    @Test
    public void locationEqualsSameLocation(){
        
        Location location2 = new Location(1,1);
        
        assertEquals(location1, location2);
        
    }
    
    @Test
    public void differentLocationsNotEqual(){
        
        Location location2 = new Location(2,1);
        
        assertThat(location1, not(equalTo(location2)));
    }
    
    @Test
    public void newDifLocationDontMatch(){
        
        assertThat(new Location(2,2), not(equalTo(new Location(2,3))));
    }
    
    @Test
    public void newLocationsMatch(){
        
        assertEquals(new Location(2,2), new Location(2,2));
    }
    
}