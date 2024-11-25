# ConsultaFIPE

O projeto ConsultaFIPE é uma aplicação Java desenvolvida para consultar 
informações da Tabela FIPE utilizando uma [API criada pelo Deivid Fortuna](https://github.com/deividfortuna/fipe). 
A principal funcionalidade é a obtenção de dados detalhados de um 
veículo conforme as opções solicitadas pelo usuário, realizando requisições HTTP e 
retornando as informações de forma estruturada.

## Funcionalidades

- Consulta marcas de veículos (motos, carros e caminhões) por tipo.
- Lista modelos disponíveis para cada marca.
- Permite pesquisa por trecho do nome do modelo.
- Exibe detalhes como ano de lançamento, combustível e valor médio do veículo.

## Tecnologias Utilizadas

- **Java**
- **Spring Boot 3.3.5**
- **Maven 4.0**
- **HTTPClient**
- **Jackson**
- **FIPE API**

## Diagrama de Classes

```mermaid
classDiagram
    class ConsultaFipeApplication {
        +main(String[] args)
    }

    class Main {
        -Scanner scan
        -ApiServices apiServices
        -JsonServices jsonServices
        -String endereco
        -String json
        -String veiculo
        -String opcao
        -String codigoModelo
        -String trechoModelo
        -String codigoVeiculo
        -boolean encontrado
        -List~Veiculo~ veiculoList
        +exibeMenu()
    }

    class ApiServices {
        +String apiRequest(String adress)
    }

    class JsonServices {
        +T jsonConverterClass(String json, Class~T~ classC)
        +List~T~ jsonConverterList(String json, Class~T~ classC)
    }

    class Dados {
        -String codigo
        -String nome
    }

    class Modelos {
        -List~Dados~ modelos
    }

    class Veiculo {
        -String modelo
        -String marca
        -String anoModelo
        -String combustivel
        -String valor
    }

    ConsultaFipeApplication --> Main
    Main --> ApiServices
    Main --> JsonServices
    Main --> Dados
    Main --> Modelos
    Main --> Veiculo
````

## Contribuições:

Sinta-se à vontade para contribuir com o projeto! Se você tem sugestões de melhorias, correções de bugs ou novas funcionalidades, abra um Pull Request e ajude a tornar o ConsultaFIPE ainda melhor.

Divirta-se explorando o ConsultaFIPE e descobrindo novas informações sobre veículos!🚗