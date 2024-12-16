package net.happiness.mapper.json.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.happiness.mapper.json.Shape2DJsonMapper;
import net.happiness.mapper.json.serializer.Shape2DSerializer;
import net.happiness.shape.twod.Shape2D;

import java.util.List;

public class DefaultShape2DJsonMapper implements Shape2DJsonMapper {

    ObjectMapper mapper;

    public DefaultShape2DJsonMapper() {
        mapper = new ObjectMapper();
        registerShape2DSerializer(mapper);
    }

    @Override
    public String map(List<Shape2D> shapes) {
        try {
            return mapper.writeValueAsString(shapes);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void registerShape2DSerializer(ObjectMapper mapper) {
        SimpleModule module = new SimpleModule();
        module.addSerializer(Shape2D.class, new Shape2DSerializer());
        mapper.registerModule(module);
    }

}
