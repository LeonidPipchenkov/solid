package net.happiness.mapper.csv;

import net.happiness.shape.threed.Shape3D;

import java.util.List;

public interface Shape3DCsvMapper {
    String map(List<Shape3D> shapes);
}
