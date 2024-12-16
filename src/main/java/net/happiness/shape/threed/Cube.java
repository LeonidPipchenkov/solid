package net.happiness.shape.threed;

public record Cube(int edge) implements Shape3D {

    @Override
    public double area() {
        return 6 * edge * edge;
    }

    @Override
    public double volume() {
        return edge * edge * edge;
    }

}
