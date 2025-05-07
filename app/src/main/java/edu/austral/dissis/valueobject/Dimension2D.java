package edu.austral.dissis.valueobject;

import java.util.Objects;

public record Dimension2D(double height, double width) {
    @Override
    public String toString() {
        return "(" + height + ", " + width + ")";
    }
}
