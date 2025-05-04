

---

# 📘 Java Test Project – Spring Boot + JUnit + Mockito

Este projeto é um pequeno exemplo de autenticação em Spring Boot com foco no **estudo de testes automatizados**, utilizando **JUnit** e **Mockito**.

> O objetivo é te ajudar a compreender como testar camadas de serviço e controller com mock de dependências reais.

---

## ✅ Funcionalidade

* Endpoint de login com autenticação de e-mail e senha
* Geração de token JWT ao fazer login com sucesso
* Testes automatizados da API de autenticação

---

## 🧪 Tecnologias de Testes Usadas

### 🔹 JUnit 5

O **JUnit** é um framework de testes para aplicações Java. Ele permite escrever testes unitários e de integração para verificar o comportamento de métodos e classes.

* Usado para estruturar os testes
* Permite validar retornos esperados
* Garante que alterações no código não quebrem funcionalidades já implementadas

Exemplo:

```java
@Test
public void testLogin_InvalidCredentials() throws Exception {
    ...
    mockMvc.perform(...).andExpect(status().isUnauthorized());
}
```

---

### 🔹 Mockito

**Mockito** é uma biblioteca de mocking (simulação). Com ela, você pode criar objetos falsos de serviços e repositórios, permitindo testar uma unidade de código sem depender de implementações reais.

* Simula chamadas a dependências
* Retorna valores predefinidos
* Ajuda a isolar a classe testada

Exemplo:

```java
when(userService.authenticateUser("email", "senha")).thenReturn(null);
```

---

## 🧪 Teste de Exemplo: `AuthControllerTest`

O teste `AuthControllerTest` cobre o endpoint `/auth/login`, validando:

* ✅ Login bem-sucedido com retorno de token
* ❌ Login com credenciais inválidas (esperado erro 401)

---

## ▶️ Como rodar o projeto

### ✅ Requisitos

* Java 17+
* Maven
* IDE como IntelliJ ou VS Code
* Banco de dados não é necessário (serviços e repositórios são mockados nos testes)

### 📦 Passos

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/java-test-auth.git
cd java-test-auth
```

2. Execute os testes com Maven:

```bash
./mvnw test
```

ou:

```bash
mvn test
```

3. Ou execute via IDE:

   * Clique com o botão direito sobre a classe `AuthControllerTest` e escolha **Run 'AuthControllerTest'**

---

## 📁 Estrutura de Arquivos

* `controller/AuthController.java` – Responsável pelo endpoint `/auth/login`
* `service/UserService.java` – Autenticação de usuários
* `service/JwtTokenService.java` – Geração e verificação de token JWT
* `dto/LoginRequest.java` e `LoginResponse.java` – Estrutura dos dados de entrada e saída
* `entity/User.java` – Entidade de usuário
* `AuthControllerTest.java` – Classe de testes com foco no endpoint `/auth/login`

---

## 💡 Dica

> Ao estudar, tente criar novos testes:

* Usuário com senha vazia
* E-mail inválido
* Token expirado
* Testes para `UserService`

---

## 📚 Referências

* [Documentação oficial do JUnit 5](https://junit.org/junit5/)
* [Documentação oficial do Mockito](https://site.mockito.org/)
* [Spring Boot Testing Docs](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing)

---

