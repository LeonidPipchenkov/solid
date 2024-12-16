package net.happiness.extractor.impl;

import net.happiness.extractor.Shape3DExtractor;
import net.happiness.shape.Shape;
import net.happiness.shape.threed.Shape3D;

import java.util.List;

public class DefaultShape3DExtractor implements Shape3DExtractor {

    @Override
    public List<Shape3D> extract(List<Shape> shapes) {
        return shapes.stream()
                .filter(shape -> shape instanceof Shape3D)
                .map(shape -> (Shape3D) shape)
                .toList();
    }

}
