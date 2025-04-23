# Voting API

API REST para votação em assembleia

Este projeto é um desafio técnico, descrição:

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias,
por votação. Imagine que você deve criar uma solução backend para gerenciar essas sessões de
votação.

Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de
uma API REST:

- Cadastrar uma nova pauta
- Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default)
- Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta)
- Contabilizar os votos e dar o resultado da votação na pauta

### Tarefa Bônus 1 - Integração com sistemas externos
    Implementado na camada de serviço um método para consumo da API recomendada.

### Tarefa Bônus 2 - Mensageria e filas
    Não implementado, porém a utilização do Kafka é possível.

### Tarefa Bônus 3 - Performance
    Não implementado, JMeter ou Gatling:
    Ferramentas de teste de carga como o Apache JMeter ou o Gatling podem ser usadas para simular um grande número de solicitações simultâneas e avaliar como a API responde sob carga. Isso ajuda a identificar pontos de estrangulamento e otimizar a performance.
    É possível utilizar o Kibana e Elasticsearch API REST em um aplicativo Java Spring Boot para monitorar e melhorar o desempenho.

### Tarefa Bônus 4 - Versionamento da API
    Há diferentes formas de versionamento. Segue na API o versionamento por URL.

## Funcionamento da API

    A lógica utilizada para a criação de sessões de votação.
    
    É necessário criar uma pauta antes de criar uma sessão. Conforme o mapeamento das entidades é possível comprovar isso.
    Então criamos uma pauta e posteriormente criamos uma sessão passando o id da pauta como parâmetro para iniciar uma votação.


# 🗳️ Voting API

RESTful API for managing voting sessions in member assemblies. Developed as a technical challenge, the system allows you to create agendas, open voting sessions, register votes, and retrieve voting results.

---


## 🚀 Technologies Used

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL - Database name = assembleia**
- **Maven**
---


## 📦 How to Run Locally

### Prerequisites

- Java 17 installed  
- Maven installed  
- PostgreSQL running locally  

### Steps

```bash
# Clone the repository
git clone https://github.com/hvmello/votacao.git
cd votacao

# Configure the database in:
# src/main/resources/application.properties

# Build and run the application
mvn spring-boot:run


## Swagger Doc

    localhost:8081/swagger-ui/index.html (8081 por conta da application.properties) 
    Foram utilizadas @Operations para explicar a função dos protocolos HTTP


### Create agenda
POST /pautas
Content-Type: application/json

{
  "nome": "Pauta sobre aumento de contribuição"
}

### Open Session
POST /sessao/1/abrir
Content-Type: application/json

{
  "duracaoEmMinutos": 5
}

### Register Vote
POST /votos
Content-Type: application/json

{
  "cpfAssociado": "12345678900",
  "idPauta": 1,
  "voto": "SIM"
}

### Show Results
GET /pautas/1/resultado

## TESTS

JUNIT 5

##📐 Architecture

The application follows an MVC structure with clear separation of responsibilities:

    controller: Entry point (REST)

    service: Business logic

    repository: Data access

    model: Domain entities


## 👨‍💻 Author

Developed by Heitor Mello

