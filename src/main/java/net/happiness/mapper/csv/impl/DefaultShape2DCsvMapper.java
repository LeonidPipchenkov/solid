package net.happiness.mapper.csv.impl;

import net.happiness.mapper.csv.Shape2DCsvMapper;
import net.happiness.shape.twod.Circle;
import net.happiness.shape.twod.Rectangle;
import net.happiness.shape.twod.Shape2D;
import net.happiness.shape.twod.Square;
import net.happiness.util.ShapeUtil;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultShape2DCsvMapper implements Shape2DCsvMapper {

    @Override
    public String map(List<Shape2D> shapes) {
        var groupedShapes = groupShapes(shapes);
        return groupedShapes.entrySet().stream()
                .map(entry -> {
                    try {
                        if (Objects.equals(entry.getKey(), Circle.class)) {
                            List<Circle> circles = castToCircles(entry.getValue());
                            return buildCircleCsv(circles);
                        }
                        if (Objects.equals(entry.getKey(), Rectangle.class)) {
                            List<Rectangle> rectangles = castToRectangles(entry.getValue());
                            return buildRectangleCsv(rectangles);
                        }
                        if (Objects.equals(entry.getKey(), Square.class)) {
                            List<Square> squares = castToSquares(entry.getValue());
                            return buildSquareCsv(squares);
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    return null;
                })
                .collect(Collectors.joining("\n"));
    }

    private Map<Class<? extends Shape2D>, List<Shape2D>> groupShapes(List<Shape2D> shapes) {
        return shapes.stream()
                .collect(Collectors.groupingBy(Shape2D::getClass));
    }

    private String buildCircleCsv(List<Circle> circles) throws IOException {
        StringWriter writer = new StringWriter();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("shape", "radius", "area");
            for (Circle circle : circles) {
                csvPrinter.printRecord(
                        circle.getClass().getSimpleName(),
                        circle.radius(),
                        ShapeUtil.roundDouble(circle.area(), 2)
                );
            }
        }
        return writer.toString();
    }

    private String buildRectangleCsv(List<Rectangle> rectangles) throws IOException {
        StringWriter writer = new StringWriter();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("shape", "height", "width", "area");
            for (Rectangle rectangle : rectangles) {
                csvPrinter.printRecord(
                        rectangle.getClass().getSimpleName(),
                        rectangle.height(),
                        rectangle.width(),
                        ShapeUtil.roundDouble(rectangle.area(), 2)
                );
            }
        }
        return writer.toString();
    }

    private String buildSquareCsv(List<Square> squares) throws IOException {
        StringWriter writer = new StringWriter();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("shape", "side", "area");
            for (Square square : squares) {
                csvPrinter.printRecord(
                        square.getClass().getSimpleName(),
                        square.side(),
                        ShapeUtil.roundDouble(square.area(), 2)
                );
            }
        }
        return writer.toString();
    }

    private List<Circle> castToCircles(List<Shape2D> shapes) {
        return shapes.stream()
                .map(shape -> (Circle) shape)
                .toList();
    }

    private List<Rectangle> castToRectangles(List<Shape2D> shapes) {
        return shapes.stream()
                .map(shape -> (Rectangle) shape)
                .toList();
    }

    private List<Square> castToSquares(List<Shape2D> shapes) {
        return shapes.stream()
                .map(shape -> (Square) shape)
                .toList();
    }

}
