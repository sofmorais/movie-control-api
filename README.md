# API de controle de filmes

## Descrição
Essa aplicação é uma API RESTFul desenvolvida para controle de filmes seguindo a arquitetura de microsserviços e o padrão MVC para facilitar a manutenção.
A API permite realizar operações como: cadastrar e consultar filmes e espectadores, registrar filmes assistidos por um espectador, consultar a quantidade de filmes assistidos por um espectador e total de visualizações de um filme, registrar filmes e espectadores como indisponíveis ou inativos.

O banco de dados utilizado foi o [PostgreSQL](https://www.postgresql.org/).
A documentação dos endpoints foi feita utilizando o Swagger que permite visualizar e testar através de uma interface web.
O Postman foi usado pra criar uma coleção com exemplos de requisições e parâmetros necessários.

Além disso, foram implementados testes unitários para garantir a funcionalidade e a integridade da aplicação.
Como melhoria futura, serão implementados os testes de integração e o Docker para containerização.

## Como testar

Para testar a API, você pode utilizar ferramentas como o Postman ou o Swagger UI, pois aplicação ainda não conta com uma interface visual para realizar as requisições.

- Faça o download do Java 17;
- Clone o repositório;
- Inicie o terminal na pasta do projeto e digite `mvn spring-boot:run`;
- OU vá até classe principal `MovieControlApplication` e rode a aplicação se estiver usando uma IDE como IntelliJ IDEA ou Eclipse;
- Acesse o [Swagger](http://localhost:8080/swagger-ui/index.html) para visualizar todos os endpoints disponíveis e testar a API
- OU
- Abra o [Postman](https://documenter.getpostman.com/view/21857150/2sAYHxp4yF) e selecione `Run in Postman`;
- Importe a coleção de requisições e tilize os exemplos de requisições para testar os endpoints.

## Tipos de requisição

A URL base é http://localhost:8080/api/v1 e o final é modificado de acordo com a requisição que será feita.

- ##### Endpoints filme: 
- `Cadastrar um filme`: /movies, método POST
- `Retornar filme por ID`: /movies/{movieId}, método GET
- `Listar todos os filmes cadastrados`: /movies, método GET
- `Listar filmes disponíveis`: /movies/availables, método GET
- `Listar filmes indisponíveis`: /movies/unavailables, método GET
- `Registrar um filme como indisponível` : /movies/{movieId}, método PUT

- ##### Endpoints espectador
- `Cadastrar um espectador`: /viewer, método POST
- `Retornar espectador por ID`: /viewer/{viewerId}, método GET
- `Listar espectadores cadastrados`: /viewer, método GET
- `Listar espectadores ativos`: /viewer/actives, método GET
- `Listar espectadores inativos`: /viewer/inactives, método GET
- `Registrar um espectador como inativo` : /viewer/{viewerId}, método PUT

- ##### Endpoints registro de visualizações
- `Registrar filme assistido por um espectador`: /movie-view-record, método POST
- `Listar espectadores de um filme`: /movie-view-record/{movieId}/viewers, método GET
- `Listar filmes assistidos por um espectador`: /movie-view-record/{viewerId}/movies, método GET
- `Retornar total de visualizações de um filme`: /movie-view-record/movie/{movieId}/total-views, método GET
- `Retornar quantos filmes um espectador assistiu`: /movie-view-record/viewer/{viewerId}/total-movies-watched, método GET

## Documentação

### Swagger UI
Disponível com a aplicação em execução:
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Postman
Pode ser acessada no [link](https://documenter.getpostman.com/view/21857150/2sAYHxp4yF)

## Organização dos arquivos

| Pasta/arquivo                     | Descrição                                                                                                                                                         |
|-----------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| /controllers                      | Pasta com os controladores que lidam com as requisições HTTP, chamam os serviços necessários e retornam as respostas.                                             |
| /dtos                             | Pasta com os objetos de transferência de dados. Os DTOs são utilizados para transferir dados entre a camada de controle e a camada de serviço.                    |
| /models                           | Pasta com as classes que representam as entidades do banco de dados. Essas classes são mapeadas para as tabelas do banco.                                         |
| /repositories                     | Pasta com as classes responsáveis pela interação com o banco de dados, usando repositórios para encapsular a lógica de acesso a dados.                            |
| /resources/db/init                | Pasta com arquivo pra inicialização do banco de dados.                                                                                                            |
| /resources/db/migration           | Pasta com os scripts de migração do banco de dados, utilizados para versionar o banco e alterar o schema do banco (criar novas tabelas, adicionar colunas, etc.). |
| /resources/application.properties | Arquivo de propriedades de configuração da aplicação, contendo configurações sobre o banco de dados, portas, Swagger, etc.                                        |
| /test/unit                        | Pasta contendo os testes unitários da aplicação.                                                                                                                  |
 

## Tecnologias utilizadas

#### Desenvolvimento
Java 17
Spring Boot
Lombok

#### Banco de dados
PostgreSQL
Flyway 

#### Teste de endpoints e documentação
Springdoc/Swagger
Postman

#### Testes unitários
jUnit 5
Mockito

#### Gerenciamento de dependências e build
Maven: Ferramenta de build e gerenciamento de dependências utilizada no projeto
