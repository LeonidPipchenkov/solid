package net.happiness.shape.threed;

public record Sphere(int radius) implements Shape3D {

    @Override
    public double area() {
        return 4 * Math.PI * radius * radius;
    }

    @Override
    public double volume() {
        return 4 * Math.PI * radius * radius * radius / 3;
    }

}
