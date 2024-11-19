package com.pabloleal.ConsultaFIPE.Main;

import com.pabloleal.ConsultaFIPE.Models.Veiculo;
import com.pabloleal.ConsultaFIPE.Models.Dados;
import com.pabloleal.ConsultaFIPE.Models.Modelos;
import com.pabloleal.ConsultaFIPE.Services.ApiServices;
import com.pabloleal.ConsultaFIPE.Services.JsonServices;

import java.util.*;

public class Main {
    Scanner scan = new Scanner(System.in);
    ApiServices apiServices = new ApiServices();
    JsonServices jsonServices = new JsonServices();
    String endereco = "https://parallelum.com.br/fipe/api/v1/";
    String json;
    String veiculo;
    String opcao;
    String codigoModelo;
    String trechoModelo;
    String codigoVeiculo;
    boolean encontrado;
    List<Veiculo> veiculoList = new ArrayList<>();

    public void exibeMenu() {

        String menu = ("""
                
                ---------------------------------------------
                            Consulta Tabela FIPE
                ---------------------------------------------

                Digite o tipo de veiculo:

                1. Motos
                2. Carros
                3. Caminhoes

                Digite Aqui:\s""");

        System.out.print(menu);

        opcao = scan.nextLine();

        if (opcao.toLowerCase().contains("mo") || opcao.contains("1")) {
            veiculo = "motos/marcas/";
        } else if (opcao.toLowerCase().contains("car") || opcao.contains("2")) {
            veiculo = "carros/marcas/";
        } else if (opcao.toLowerCase().contains("cam") || opcao.contains("3")) {
            veiculo = "caminhoes/marcas/";
        }

        endereco = endereco + veiculo;

        json = apiServices.apiRequest(endereco);

        List<Dados> marcasList = jsonServices.jsonConverterList(json, Dados.class);

        marcasList.forEach(System.out::println);

        System.out.print("\nDigite o codigo do modelo para consultar valores: ");
        codigoModelo = scan.nextLine();

        endereco = endereco + codigoModelo + "/modelos/";

        try {
            json = apiServices.apiRequest(endereco);

            if (json == null) {
                System.exit(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Modelos modelosList = jsonServices.jsonConverterClass(json, Modelos.class);

        modelosList.modelos().forEach(System.out::println);

        do {
            System.out.print("\nDigite um trecho do nome do veículo para consulta:");
            trechoModelo = scan.nextLine();

            List<Dados> encontradosList = modelosList.modelos().stream()
                    .filter(m -> m.nome().toUpperCase().contains(trechoModelo.toUpperCase()))
                    .toList();

            encontrado = !encontradosList.isEmpty();

            if (encontrado) {
                encontradosList.forEach(System.out::println);
            } else {
                System.out.println("\nStatus Code: 404 - Não foi possível localizar a informação solicitada!");
            }

        } while (!encontrado);

        System.out.print("\nDigite o código do veiculo para consultar valores:");
        codigoVeiculo = scan.nextLine();

        endereco = endereco + codigoVeiculo + "/anos/";

        try {
            json = apiServices.apiRequest(endereco);

            if (json == null) {
                System.exit(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        List<Dados> dadosVeiculo = jsonServices.jsonConverterList(json, Dados.class);

        for (Dados dados : dadosVeiculo) {
            String enderecoAno = endereco + dados.codigo();
            json = apiServices.apiRequest(enderecoAno);
            veiculoList.add(jsonServices.jsonConverterClass(json, Veiculo.class));
        }

        veiculoList.stream()
                .sorted(Comparator.comparing(Veiculo::anoModelo))
                .forEach(System.out::println);

    }
}
