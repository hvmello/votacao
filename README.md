# Desafio Técnico votação API

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

#### Tarefa Bônus 4 - Versionamento da API
    Há diferentes formas de versionamento. Segue na API o versionamento por URL.


## Configuração Ambiente

    Java 17
    Postgres 42.6 - Ao rodar a aplicação as tabelas já são criadas por praticidade. Nome do db = assembleia
    Maven 3.9.5
