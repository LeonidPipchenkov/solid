package net.happiness.mapper.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import net.happiness.shape.twod.Circle;
import net.happiness.shape.twod.Rectangle;
import net.happiness.shape.twod.Shape2D;
import net.happiness.shape.twod.Square;
import net.happiness.util.ShapeUtil;

import java.io.IOException;

public class Shape2DSerializer extends JsonSerializer<Shape2D> {

    @Override
    public void serialize(Shape2D shape, JsonGenerator generator, SerializerProvider serializer) throws IOException {
        generator.writeStartObject();
        writeShapeName(shape, generator);
        writeSpecificFields(shape, generator);
        writeShapeArea(shape, generator);
        generator.writeEndObject();
    }

    private void writeShapeName(Shape2D shape, JsonGenerator generator) throws IOException {
        generator.writeStringField("shape", shape.getClass().getSimpleName());
    }

    private void writeSpecificFields(Shape2D shape, JsonGenerator generator) throws IOException {
        if (shape instanceof Circle circle) {
            generator.writeNumberField("radius", circle.radius());
        }
        if (shape instanceof Rectangle rectangle) {
            generator.writeNumberField("height", rectangle.height());
            generator.writeNumberField("width", rectangle.width());
        }
        if (shape instanceof Square square) {
            generator.writeNumberField("side", square.side());
        }
    }

    private void writeShapeArea(Shape2D shape, JsonGenerator generator) throws IOException {
        generator.writeNumberField("area", ShapeUtil.roundDouble(shape.area(), 2));
    }

}
