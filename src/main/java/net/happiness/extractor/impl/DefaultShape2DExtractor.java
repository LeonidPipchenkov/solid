package net.happiness.extractor.impl;

import net.happiness.extractor.Shape2DExtractor;
import net.happiness.shape.Shape;
import net.happiness.shape.twod.Shape2D;

import java.util.List;

public class DefaultShape2DExtractor implements Shape2DExtractor {

    @Override
    public List<Shape2D> extract(List<Shape> shapes) {
        return shapes.stream()
                .filter(shape -> shape instanceof Shape2D)
                .map(shape -> (Shape2D) shape)
                .toList();
    }

}
