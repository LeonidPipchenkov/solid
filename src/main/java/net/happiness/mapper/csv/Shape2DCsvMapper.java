package net.happiness.mapper.csv;

import net.happiness.shape.twod.Shape2D;

import java.util.List;

public interface Shape2DCsvMapper {
    String map(List<Shape2D> shapes);
}
