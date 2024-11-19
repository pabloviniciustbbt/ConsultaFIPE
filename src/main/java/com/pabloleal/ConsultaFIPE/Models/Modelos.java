package com.pabloleal.ConsultaFIPE.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Modelos(List<Dados> modelos) {

    @Override
    public String toString() {
        return "Modelos = " + modelos;
    }
}
