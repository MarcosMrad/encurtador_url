# 🚀 Encurtador de URL API 🔗

Este projeto é uma API RESTful para encurtamento de URLs, construída com Java, Spring Boot, Spring Data JPA e H2 Database.

## ✨ Funcionalidades

* transforma URLs GIGANTES em links curtinhos.
* ➡️ Redireciona automaticamente para a URL original.
* 🗓️ Links curtos expiram depois de 3 dias.
* ✔️ Valida se a URL original é válida.

## 💻 Tecnologias

* **Java 17**
* **Spring Boot 3.5.0**
    * Spring Web (para a API REST)
    * Spring Data JPA (para salvar os dados)
    * Spring Validation (para checar se a URL é válida)
* **H2 Database**: Um banco de dados que roda na memória.
* **Lombok**: Para escrever menos código repetitivo.
* **Maven**: Para organizar o projeto.

## 🎯 Endpoints da API

Prefixo base: `/api`

### 1. Encurtar URL ✂️

* **Como chamar:** `POST /api/shorten`
* **O que enviar (JSON):**
    ```json
    {
        "originalUrl": "https://github.com/MarcosMrad/"
    }
    ```
* **O que você recebe de volta (JSON):**
    ```json
    {
        "id": 1,
        "originalUrl": "https://github.com/MarcosMrad/encurtador_url",
        "shortUrl": "aB1cD2", // <-- Seu link curto!
        "expirationDate": "2025-06-06T12:00:00.000000" // Quando ele expira
    }
    ```

### 2. Acessar o Link Curto ➡️

* **Como chamar:** `GET /api/s/{linkCurto}`
    * Exemplo: `/api/s/aB1cD2`
* **O que acontece:** Você é redirecionado para a URL original! 슝슝!

### 3. Ver Detalhes do Link 🧐

* **Como chamar:** `GET /api/details/{linkCurto}`
    * Exemplo: `/api/details/aB1cD2`
* **O que você recebe de volta (JSON):** Mesmos detalhes de quando você criou o link.
