package net.happiness.mapper.csv.impl;

import net.happiness.mapper.csv.Shape3DCsvMapper;
import net.happiness.shape.threed.Cube;
import net.happiness.shape.threed.Shape3D;
import net.happiness.shape.threed.Sphere;
import net.happiness.util.ShapeUtil;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultShape3DCsvMapper implements Shape3DCsvMapper {

    @Override
    public String map(List<Shape3D> shapes) {
        var groupedShapes = groupShapes(shapes);
        return groupedShapes.entrySet().stream()
                .map(entry -> {
                    try {
                        if (Objects.equals(entry.getKey(), Cube.class)) {
                            List<Cube> cubes = castToCubes(entry.getValue());
                            return buildCubeCsv(cubes);
                        }
                        if (Objects.equals(entry.getKey(), Sphere.class)) {
                            List<Sphere> spheres = castToSpheres(entry.getValue());
                            return buildSphereCsv(spheres);
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    return null;
                })
                .collect(Collectors.joining("\n"));
    }

    private Map<Class<? extends Shape3D>, List<Shape3D>> groupShapes(List<Shape3D> shapes) {
        return shapes.stream()
                .collect(Collectors.groupingBy(Shape3D::getClass));
    }

    private String buildCubeCsv(List<Cube> cubes) throws IOException {
        StringWriter writer = new StringWriter();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("shape", "edge", "area", "volume");
            for (Cube cube : cubes) {
                csvPrinter.printRecord(
                        cube.getClass().getSimpleName(),
                        cube.edge(),
                        ShapeUtil.roundDouble(cube.area(), 2),
                        ShapeUtil.roundDouble(cube.volume(), 2)
                );
            }
        }
        return writer.toString();
    }

    private String buildSphereCsv(List<Sphere> spheres) throws IOException {
        StringWriter writer = new StringWriter();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("shape", "radius", "area", "volume");
            for (Sphere sphere : spheres) {
                csvPrinter.printRecord(
                        sphere.getClass().getSimpleName(),
                        sphere.radius(),
                        ShapeUtil.roundDouble(sphere.area(), 2),
                        ShapeUtil.roundDouble(sphere.volume(), 2)
                );
            }
        }
        return writer.toString();
    }

    private List<Cube> castToCubes(List<Shape3D> shapes) {
        return shapes.stream()
                .map(shape -> (Cube) shape)
                .toList();
    }

    private List<Sphere> castToSpheres(List<Shape3D> shapes) {
        return shapes.stream()
                .map(shape -> (Sphere) shape)
                .toList();
    }

}
