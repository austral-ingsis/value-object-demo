package edu.austral.dissis.valueobject;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Dimensions2DTests {
    @Test
    void shouldBeTheSameCoordinateWhenIsTheSameObject() {
        final Dimensions2D coordinate = new Dimensions2D(5.0, 10.0);
        
        assertEquals(coordinate, coordinate);
    }
    
    @Test
    void shouldBeTheSameCoordinateWhenHasTheSameValue() {
        final Dimensions2D coordinate1 = new Dimensions2D(5.0, 10.0);
        final Dimensions2D coordinate2 = new Dimensions2D(5.0, 10.0);
        
        assertEquals(coordinate1, coordinate2);
    }
    
    @Test
    void shouldNotBeTheSameCoordinateWhenHasDifferentValue() {
        final Dimensions2D coordinate1 = new Dimensions2D(5.0, 10.0);
        final Dimensions2D coordinate2 = new Dimensions2D(10.0, 5.0);
        
        assertNotEquals(coordinate1, coordinate2);
    }

    @Test
    void demonstrateHashCodeConsistency() {
        final Dimensions2D coordinate1 = new Dimensions2D(5.0, 10.0);
        final Dimensions2D coordinate2 = new Dimensions2D(5.0, 10.0);
        
        assertEquals(coordinate1.hashCode(), coordinate2.hashCode());
        
        final Map<Dimensions2D, String> coordinateMap = new HashMap<>();
        coordinateMap.put(coordinate1, "Dimension A");
        
        assertEquals("Dimension A", coordinateMap.get(coordinate2));
    }
    

    @Test
    void demonstrateToString() {
        final Dimensions2D coordinate = new Dimensions2D(5.5, 10.75);
        
        assertEquals("(5.5, 10.75)", coordinate.toString());
    }
}
