package net.happiness.mapper.json.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.happiness.mapper.json.Shape3DJsonMapper;
import net.happiness.mapper.json.serializer.Shape3DSerializer;
import net.happiness.shape.threed.Shape3D;

import java.util.List;

public class DefaultShape3DJsonMapper implements Shape3DJsonMapper {

    ObjectMapper mapper;

    public DefaultShape3DJsonMapper() {
        mapper = new ObjectMapper();
        registerShape3DSerializer(mapper);
    }

    @Override
    public String map(List<Shape3D> shapes) {
        try {
            return mapper.writeValueAsString(shapes);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void registerShape3DSerializer(ObjectMapper mapper) {
        SimpleModule module = new SimpleModule();
        module.addSerializer(Shape3D.class, new Shape3DSerializer());
        mapper.registerModule(module);
    }

}
