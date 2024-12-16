package net.happiness.extractor;

import net.happiness.shape.Shape;
import net.happiness.shape.threed.Shape3D;

import java.util.List;

public interface Shape3DExtractor {
    List<Shape3D> extract(List<Shape> shapes);
}
