package net.happiness;

import net.happiness.extractor.Shape2DExtractor;
import net.happiness.extractor.Shape3DExtractor;
import net.happiness.extractor.impl.DefaultShape2DExtractor;
import net.happiness.extractor.impl.DefaultShape3DExtractor;
import net.happiness.file.impl.DefaultFileService;
import net.happiness.file.FileService;
import net.happiness.mapper.csv.Shape3DCsvMapper;
import net.happiness.mapper.csv.impl.DefaultShape3DCsvMapper;
import net.happiness.mapper.json.impl.DefaultShape2DJsonMapper;
import net.happiness.mapper.json.Shape2DJsonMapper;
import net.happiness.shape.Shape;
import net.happiness.shape.threed.Cube;
import net.happiness.shape.threed.Shape3D;
import net.happiness.shape.threed.Sphere;
import net.happiness.shape.twod.Circle;
import net.happiness.shape.twod.Rectangle;
import net.happiness.shape.twod.Shape2D;
import net.happiness.shape.twod.Square;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Shape> shapes = List.of(
                new Circle(10),
                new Square(15),
                new Rectangle(10, 15),
                new Cube(20),
                new Sphere(25)
        );

        processShapes2D(shapes);
        System.out.println();
        processShapes3D(shapes);
    }

    private static void processShapes2D(List<Shape> shapes) {
        FileService fileService = new DefaultFileService();
        String filename = "JSON";

        Shape2DExtractor extractor = new DefaultShape2DExtractor();
        Shape2DJsonMapper mapper = new DefaultShape2DJsonMapper();

        List<Shape2D> shapes2D = extractor.extract(shapes);
        String json = mapper.map(shapes2D);

        fileService.createFile(filename);
        fileService.writeToFile(filename, json);

        String readJson = fileService.readFromFile(filename);
        System.out.println(readJson);
    }

    private static void processShapes3D(List<Shape> shapes) {
        FileService fileService = new DefaultFileService();
        String filename = "CSV";

        Shape3DExtractor extractor = new DefaultShape3DExtractor();
        Shape3DCsvMapper mapper = new DefaultShape3DCsvMapper();

        List<Shape3D> shapes3D = extractor.extract(shapes);
        String csv = mapper.map(shapes3D);

        fileService.createFile(filename);
        fileService.writeToFile(filename, csv);

        String readCsv = fileService.readFromFile(filename);
        System.out.println(readCsv);
    }

}
