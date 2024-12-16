package net.happiness.extractor;

import net.happiness.shape.Shape;
import net.happiness.shape.twod.Shape2D;

import java.util.List;

public interface Shape2DExtractor {
    List<Shape2D> extract(List<Shape> shapes);
}
