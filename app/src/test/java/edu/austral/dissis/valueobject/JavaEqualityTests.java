package edu.austral.dissis.valueobject;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JavaEqualityTests {

    @Test
    void primitiveEqualityUsesDoubleEquals() {
        int a = 5;
        int b = 5;
        int c = 10;
        
        assertTrue(a == b);
        assertFalse(a == c);
    }
    
    @Test
    void objectReferenceEqualityWithDoubleEquals() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = obj1;
        
        // == checks if references point to the same object
        assertFalse(obj1 == obj2); // Different objects
        assertTrue(obj1 == obj3);  // Same object reference
    }
    
    @Test
    void objectValueEqualityWithEquals() {
        Dimension2D coord1 = new Dimension2D(5, 10);
        Dimension2D coord2 = new Dimension2D(5, 10);
        Dimension2D coord3 = new Dimension2D(10, 5);
        
        // Different objects with the same values
        assertFalse(coord1 == coord2);     // Not the same reference
        assertTrue(coord1.equals(coord2));  // But equal by value
        
        // Different objects with different values
        assertFalse(coord1 == coord3);
        assertFalse(coord1.equals(coord3));
    }
    
    @Test
    void stringsHaveSpecialBehavior() {
        String str1 = "hello";          // String literal
        String str2 = "hello";          // Same string literal
        String str3 = new String("hello"); // New string object
        String str4 = "hel" + "lo";     // Compile-time constant
        String str5 = "hel";            // String literal
        String str6 = str5 + "lo";      // Runtime concatenation
        
        // String literals are interned by JVM
        assertTrue(str1 == str2);       // Same reference from string pool
        assertFalse(str1 == str3);      // Different reference, new String()
        assertTrue(str1.equals(str3));  // Same value
        
        // Compile-time constants are also interned
        assertTrue(str1 == str4);       // Literals concatenated at compile time
        
        // Runtime concatenation creates new objects
        assertFalse(str1 == str6);      // Different reference
        assertTrue(str1.equals(str6));  // Same value
    }
    
    @Test
    void boxedPrimitivesAreTricky() {
        // Autoboxing - primitives to objects
        Integer int1 = 127;
        Integer int2 = 127;
        Integer int3 = 128;
        Integer int4 = 128;
        
        // Java caches Integer objects for values -128 to 127
        assertTrue(int1 == int2);      // Same cached object for small values
        assertFalse(int3 == int4);     // Different objects for larger values

        // equals always compares values
        assertTrue(int1.equals(int2));
        assertTrue(int3.equals(int4));
        
        // Manual boxing always creates new objects
        Integer int5 = new Integer(127);
        assertFalse(int1 == int5);     // Different objects
        assertTrue(int1.equals(int5)); // Same value
    }
    
    @Test
    void nullEqualityChecks() {
        Object obj = null;
        Object obj2 = null;
        
        // Null checks with == are safe
        assertTrue(obj == null);
        assertTrue(obj == obj2);
        
        // obj.equals(null) would throw NullPointerException
        
        // But null checks in proper equals methods are safe
        Dimension2D coord = new Dimension2D(5, 10);
        assertFalse(coord.equals(null));
    }
    
    @Test
    void equalsVsDoubleEqualsWithValueObjects() {
        // Value objects should have value-based equality
        DataSize size1 = DataSize.ofMegabytes(1);
        DataSize size2 = DataSize.ofMegabytes(1);
        DataSize size3 = DataSize.ofKilobytes(1024);
        
        // Different objects with the same logical value
        assertFalse(size1 == size2);       // Different references
        assertTrue(size1.equals(size2));   // Equal by value
        assertTrue(size1.equals(size3));   // Equal by value (different construction)
        
        // This is why we need equals and hashCode for collections
        List<DataSize> sizes = new ArrayList<>();
        sizes.add(size1);
        assertTrue(sizes.contains(size2)); // Contains uses equals
        assertTrue(sizes.contains(size3)); // Contains uses equals
    }
    
    @Test
    void primitiveVsReferenceTypes() {
        int primitiveInt = 5;
        Integer boxedInt = 5;  // Autoboxed
        
        // == works between primitives and boxed types due to auto-unboxing
        assertTrue(primitiveInt == boxedInt);
        
        // This auto-unboxes boxedInt for the comparison
        // Equivalent to primitiveInt == boxedInt.intValue()
        // Equivalent to primitiveInt == boxedInt.intValue()
    }
    
    @Test
    void sideEffectsOfEqualityOperators() {
        // == never has side effects
        MutableCounter counter1 = new MutableCounter(5);
        MutableCounter counter2 = new MutableCounter(5);
        
        // This doesn't change state
        boolean referenceEqual = (counter1 == counter2);
        
        // But badly implemented equals might have side effects
        boolean valueEqual = counter1.equals(counter2);
        
        assertEquals(5, counter1.getValue());
        assertEquals(5, counter2.getValue());
    }
    
    private static class MutableCounter {
        private int value;
        
        MutableCounter(int value) {
            this.value = value;
        }
        
        int getValue() {
            return value;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MutableCounter that = (MutableCounter) o;
            return value == that.value;
        }
    }
}
