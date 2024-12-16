package net.happiness.mapper.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import net.happiness.shape.threed.Cube;
import net.happiness.shape.threed.Shape3D;
import net.happiness.shape.threed.Sphere;
import net.happiness.util.ShapeUtil;

import java.io.IOException;

public class Shape3DSerializer extends JsonSerializer<Shape3D> {

    @Override
    public void serialize(Shape3D shape, JsonGenerator generator, SerializerProvider serializer) throws IOException {
        generator.writeStartObject();
        writeShapeName(shape, generator);
        writeSpecificFields(shape, generator);
        writeShapeArea(shape, generator);
        writeShapeVolume(shape, generator);
        generator.writeEndObject();
    }

    private void writeShapeName(Shape3D shape, JsonGenerator generator) throws IOException {
        generator.writeStringField("shape", shape.getClass().getSimpleName());
    }

    private void writeSpecificFields(Shape3D shape, JsonGenerator generator) throws IOException {
        if (shape instanceof Cube cube) {
            generator.writeNumberField("edge", cube.edge());
        }
        if (shape instanceof Sphere sphere) {
            generator.writeNumberField("radius", sphere.radius());
        }
    }

    private void writeShapeArea(Shape3D shape, JsonGenerator generator) throws IOException {
        generator.writeNumberField("area", ShapeUtil.roundDouble(shape.area(), 2));
    }

    private void writeShapeVolume(Shape3D shape, JsonGenerator generator) throws IOException {
        generator.writeNumberField("volume", ShapeUtil.roundDouble(shape.volume(), 2));
    }

}
