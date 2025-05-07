package edu.austral.dissis.valueobject;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Dimension2DTests {
    @Test
    void shouldBeTheSameCoordinateWhenIsTheSameObject() {
        final Dimension2D coordinate = new Dimension2D(5.0, 10.0);

        assertEquals(coordinate, coordinate);
    }
    
    @Test
    void shouldBeTheSameCoordinateWhenHasTheSameValue() {
        final Dimension2D coordinate1 = new Dimension2D(5.0, 10.0);
        final Dimension2D coordinate2 = new Dimension2D(5.0, 10.0);
        
        assertEquals(coordinate1, coordinate2);
    }
    
    @Test
    void shouldNotBeTheSameCoordinateWhenHasDifferentValue() {
        final Dimension2D coordinate1 = new Dimension2D(5.0, 10.0);
        final Dimension2D coordinate2 = new Dimension2D(10.0, 5.0);
        
        assertNotEquals(coordinate1, coordinate2);
    }

    @Test
    void demonstrateHashCodeConsistency() {
        final Dimension2D coordinate1 = new Dimension2D(5.0, 10.0);
        final Dimension2D coordinate2 = new Dimension2D(5.0, 10.0);
        
        assertEquals(coordinate1.hashCode(), coordinate2.hashCode());
        
        final Map<Dimension2D, String> coordinateMap = new HashMap<>();
        coordinateMap.put(coordinate1, "Dimension A");
        
        assertEquals("Dimension A", coordinateMap.get(coordinate2));
    }
    

    @Test
    void demonstrateToString() {
        final Dimension2D coordinate = new Dimension2D(5.5, 10.75);
        
        assertEquals("(5.5, 10.75)", coordinate.toString());
    }
}
