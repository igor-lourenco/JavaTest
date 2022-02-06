# Frete - SigaBem
Teste de seleção para vaga de Java
<br/><br/>

## **Tecnologias utilizadas:**
- Spring Boot (2.6.3)
- Java 11
- WebClient
- Maven
- JPA
- JUnit
- Mockito
- Hibernate
- Lombok
- Model Mapper
- Swagger

---

## **Como Acessar?**

- Link:
   - http://localhost:8080/frete

##

- Swagger: 
   - http://localhost:8080/swagger-ui/index.html
    
---

## **Exemplo de inserção (POST):**

```json
{
    "peso": 12.00,
    "cepOrigem": "88020231",
    "cepDestino": "89010000",
    "nomeDestinatario": "Nome"
}
```
## Padrão camadas
![Padrao_Camadas](https://github.com/igor-lourenco/projeto-spring-react-vendas/blob/main/frontend/src/assets/img/padrao_camadas.png)

---

_Testes unitários das camadas de Repositório, Service, DTO e Controller_

_Banco H2_

_Tratamento personalizado de exceções_

_Validações dos campos e mensagens retornadas_

---


