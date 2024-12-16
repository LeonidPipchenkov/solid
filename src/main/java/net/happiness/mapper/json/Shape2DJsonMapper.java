package net.happiness.mapper.json;

import net.happiness.shape.twod.Shape2D;

import java.util.List;

public interface Shape2DJsonMapper {
    String map(List<Shape2D> shapes);
}
