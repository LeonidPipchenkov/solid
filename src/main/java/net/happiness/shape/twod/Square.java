package net.happiness.shape.twod;

public record Square(int side) implements Shape2D {

    @Override
    public double area() {
        return side * side;
    }

}
