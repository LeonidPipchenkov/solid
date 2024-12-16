package net.happiness.mapper.json;

import net.happiness.shape.threed.Shape3D;

import java.util.List;

public interface Shape3DJsonMapper {
    String map(List<Shape3D> shapes);
}
