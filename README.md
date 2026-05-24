# Spring Microsservicos

Projeto de estudo com arquitetura de microsservicos em Java e Spring Boot. A solucao possui dois microsservicos de negocio, um servidor de descoberta Eureka, um API Gateway e uma collection do Insomnia para testar as requisicoes.

## Arquitetura

| Modulo | Descricao | Porta |
| --- | --- | --- |
| `pedidos-sd/pedidos-sd` | Service Discovery com Netflix Eureka Server | `5050` |
| `gateway/gateway` | API Gateway com Spring Cloud Gateway | `5051` |
| `ON_TDS_JAVA_ADVANCED_MICROSSERVICES_PEDIDOS` | Microsservico de pedidos | Aleatoria (`server.port=0`) |
| `ON_TDS_JAVA_ADVANCED_MICROSSERVICES_ENTREGAS` | Microsservico de entregas | Aleatoria (`server.port=0`) |

Os microsservicos de pedidos e entregas registram suas instancias no Eureka. O Gateway usa o service discovery para rotear chamadas pelos nomes dos servicos:

- `http://localhost:5051/pedidos-ms/pedidos`
- `http://localhost:5051/entregas-ms/entregas`

## Tecnologias

- Java 21
- Spring Boot
- Spring Cloud Gateway
- Netflix Eureka
- Spring Data JPA
- Flyway
- MySQL
- OpenFeign
- Maven
- Docker Compose

## Banco de dados MySQL

As aplicacoes de pedidos e entregas usam MySQL local na porta `3306`, usuario `root` e senha `1234`.

Para subir apenas o banco com Docker Compose:

```bash
docker compose up -d
```

Esse comando cria um container MySQL 8.0 e usa um volume Docker chamado `mysql-data`. Os bancos sao criados automaticamente pelas URLs JDBC das aplicacoes:

- `db_pedidos`
- `db_entregas`

As tabelas sao criadas pelo Flyway a partir dos scripts em:

- `ON_TDS_JAVA_ADVANCED_MICROSSERVICES_PEDIDOS/src/main/resources/db/migration`
- `ON_TDS_JAVA_ADVANCED_MICROSSERVICES_ENTREGAS/src/main/resources/db/migration`

Se preferir rodar o MySQL manualmente com Docker:

```bash
docker run --name spring-microsservicos-mysql -e MYSQL_ROOT_PASSWORD=1234 -p 3306:3306 -d mysql:8.0
```

## Como executar

Suba os projetos nesta ordem:

1. MySQL
2. Eureka Server
3. Microsservico de entregas
4. Microsservico de pedidos
5. Gateway

Em terminais separados, execute:

```bash
cd pedidos-sd/pedidos-sd
./mvnw spring-boot:run
```

```bash
cd ON_TDS_JAVA_ADVANCED_MICROSSERVICES_ENTREGAS
./mvnw spring-boot:run
```

```bash
cd ON_TDS_JAVA_ADVANCED_MICROSSERVICES_PEDIDOS
./mvnw spring-boot:run
```

```bash
cd gateway/gateway
./mvnw spring-boot:run
```

No Windows PowerShell, use `.\mvnw.cmd spring-boot:run`.

## Testando a API

A raiz do projeto contem a collection `http-request-spring-microsservicos.yaml`, que pode ser importada no Insomnia.

Principais rotas pelo Gateway:

### Pedidos

- `POST /pedidos-ms/pedidos`
- `GET /pedidos-ms/pedidos`
- `GET /pedidos-ms/pedidos/{numeroPedido}`
- `PUT /pedidos-ms/pedidos`
- `DELETE /pedidos-ms/pedidos/{numeroPedido}`
- `PATCH /pedidos-ms/pedidos/{id}/transporte`
- `GET /pedidos-ms/pedidos/porta`

### Entregas

- `POST /entregas-ms/entregas`
- `GET /entregas-ms/entregas`
- `GET /entregas-ms/entregas/{numeroEntrega}`
- `PUT /entregas-ms/entregas`
- `DELETE /entregas-ms/entregas/{numeroEntrega}`
- `PUT /entregas-ms/entregas/{id}/transporte`
- `GET /entregas-ms/entregas/porta`

## Observacoes

- O Eureka Dashboard fica disponivel em `http://localhost:5050`.
- O Gateway fica disponivel em `http://localhost:5051`.
- Os microsservicos de negocio sobem em portas aleatorias e devem ser acessados preferencialmente pelo Gateway.
