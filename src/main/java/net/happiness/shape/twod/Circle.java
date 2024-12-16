package net.happiness.shape.twod;

public record Circle(int radius) implements Shape2D {

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

}
