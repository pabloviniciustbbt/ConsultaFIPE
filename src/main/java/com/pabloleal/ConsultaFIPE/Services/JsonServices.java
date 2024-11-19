package com.pabloleal.ConsultaFIPE.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class JsonServices implements IJsonServices {

    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public <T> T jsonConverterClass(String json, Class<T> classC) {
        try {
            return mapper.readValue(json,classC);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> jsonConverterList(String json, Class<T> classC) {
        CollectionType list = mapper.getTypeFactory()
                .constructCollectionType(List.class, classC);
        try {
            return mapper.readValue(json,list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}