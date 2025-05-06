package edu.austral.dissis.valueobject;

import java.util.Objects;

public final class Dimensions2D {
    private final double height;
    private final double width;
    
    public Dimensions2D(double height, double width) {
        this.height = height;
        this.width = width;
    }
    
    public double getHeight() {
        return height;
    }
    
    public double getWidth() {
        return width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        final Dimensions2D that = (Dimensions2D) o;
        
        return Double.compare(that.height, height) == 0 &&
               Double.compare(that.width, width) == 0;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(height, width);
    }
    
    @Override
    public String toString() {
        return "(" + height + ", " + width + ")";
    }
}
