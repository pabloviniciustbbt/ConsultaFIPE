package com.pabloleal.ConsultaFIPE.Services;

import java.util.List;

public interface IJsonServices {

    <T> T jsonConverterClass(String json, Class<T> classC);

    <T> List<T> jsonConverterList(String json, Class<T> classC);
}
