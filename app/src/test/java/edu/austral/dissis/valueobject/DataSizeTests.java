package edu.austral.dissis.valueobject;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DataSizeTests {
    @Test
    void shouldBeTheSameSizeWhenIsTheSameObject() {
        final DataSize size = DataSize.ofMegabytes(100);
        
        assertEquals(size, size);
    }
    
    @Test
    void shouldBeTheSameSizeWhenHasTheSameByteValue() {
        final DataSize size1 = DataSize.ofBytes(1024);
        final DataSize size2 = DataSize.ofKilobytes(1);
        
        assertEquals(size1, size2);
    }
    
    @Test
    void shouldNotBeTheSameSizeWhenHasDifferentValue() {
        final DataSize size1 = DataSize.ofMegabytes(1);
        final DataSize size2 = DataSize.ofMegabytes(2);
        
        assertNotEquals(size1, size2);
    }
    
    @Test
    void demonstrateImmutability() {
        final DataSize original = DataSize.ofMegabytes(10);
        final DataSize modified = original.add(DataSize.ofMegabytes(5));
        
        assertNotEquals(original, modified);
        assertEquals(10 * 1024 * 1024, original.toBytes());
        assertEquals(15 * 1024 * 1024, modified.toBytes());
    }
    
    @Test
    void demonstrateHashCodeConsistency() {
        final DataSize size1 = DataSize.ofKilobytes(1024);
        final DataSize size2 = DataSize.ofMegabytes(1);
        
        assertEquals(size1.hashCode(), size2.hashCode());
        
        final Map<DataSize, String> sizeMap = new HashMap<>();
        sizeMap.put(size1, "One MB");
        
        assertEquals("One MB", sizeMap.get(size2));
    }
    
    @Test
    void demonstrateUnitConversions() {
        final DataSize oneGB = DataSize.ofGigabytes(1);
        
        assertEquals(1, oneGB.toGigabytes());
        assertEquals(1024, oneGB.toMegabytes());
        assertEquals(1024 * 1024, oneGB.toKilobytes());
        assertEquals(1024 * 1024 * 1024, oneGB.toBytes());
        
        assertEquals(1024, oneGB.to(SizeUnit.MEGABYTES));
    }
    
    @Test
    void demonstrateArithmeticOperations() {
        final DataSize size1 = DataSize.ofMegabytes(10);
        final DataSize size2 = DataSize.ofMegabytes(5);
        
        final DataSize sum = size1.add(size2);
        assertEquals(15 * 1024 * 1024, sum.toBytes());
        
        final DataSize difference = size1.subtract(size2);
        assertEquals(5 * 1024 * 1024, difference.toBytes());
        
        final DataSize multiplied = size1.multiply(3);
        assertEquals(30 * 1024 * 1024, multiplied.toBytes());
    }
    
    @Test
    void demonstrateComparisons() {
        final DataSize small = DataSize.ofMegabytes(100);
        final DataSize medium = DataSize.ofMegabytes(500);
        final DataSize large = DataSize.ofGigabytes(1);
        
        assertTrue(small.isSmallerThan(medium));
        assertTrue(medium.isSmallerThan(large));
        assertTrue(large.isLargerThan(medium));
        assertTrue(medium.isLargerThan(small));
        
        assertTrue(small.isSmallerThanOrEqualTo(small));
        assertTrue(medium.isLargerThanOrEqualTo(medium));
    }
    
    @Test
    void demonstrateZeroConstant() {
        final DataSize zero = DataSize.ZERO;
        
        assertEquals(0, zero.toBytes());
        assertEquals(DataSize.ofBytes(0), zero);
    }
    
    @Test
    void demonstrateFactoryMethods() {
        final DataSize size1 = DataSize.ofBytes(1024);
        final DataSize size2 = DataSize.ofKilobytes(1);
        final DataSize size3 = DataSize.of(1, SizeUnit.KILOBYTES);
        
        assertEquals(size1, size2);
        assertEquals(size2, size3);
    }
    
    @Test
    void demonstrateToString() {
        assertEquals("100 B", DataSize.ofBytes(100).toString());
        assertEquals("10 KB", DataSize.ofKilobytes(10).toString());
        assertEquals("5 MB", DataSize.ofMegabytes(5).toString());
        assertEquals("2 GB", DataSize.ofGigabytes(2).toString());
        assertEquals("1 TB", DataSize.ofTerabytes(1).toString());
    }
}
