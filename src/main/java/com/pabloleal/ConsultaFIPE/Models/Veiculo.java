package com.pabloleal.ConsultaFIPE.Models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculo(@JsonAlias("Modelo") String modelo,
                      @JsonAlias("Marca") String marca,
                      @JsonAlias("AnoModelo") String anoModelo,
                      @JsonAlias("Combustivel") String combustivel,
                      @JsonAlias("Valor") String valor
                      ){

    @Override
    public String toString() {
        return "\nModelo = " + modelo +
                "\nAno de Lancamento = " + anoModelo +
                "\nMarca = " + marca +
                "\nCombustivel = " + combustivel +
                "\nValor = " + valor;
    }
}
