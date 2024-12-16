package net.happiness.shape.twod;

public record Rectangle(int height, int width) implements Shape2D {

    @Override
    public double area() {
        return height * width;
    }

}
