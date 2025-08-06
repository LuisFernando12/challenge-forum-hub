# 🚀 Forum Hub - Challenge ONE

> **API REST para simulação de um fórum de discussões**
> 
> Projeto desenvolvido como parte do programa Oracle Next Education (ONE) da Alura, demonstrando conhecimentos em Java e Spring Framework.

[![Java](https://img.shields.io/badge/Java-21+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0+-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0+-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/)

## 📋 Sobre o Projeto

O **Forum Hub** é uma API REST que simula o backend de um fórum de discussões, permitindo aos usuários criar tópicos, responder discussões e gerenciar conteúdo. Este projeto foi desenvolvido como desafio final do curso de Java e Spring Framework da trilha de formação Oracle Next Education.

### 🎯 Objetivos do Challenge

- Implementar uma API REST completa usando Spring Boot
- Aplicar conceitos de autenticação e autorização com JWT
- Praticar persistência de dados com JPA/Hibernate
- Implementar validações e tratamento de exceções
- Documentar API com Swagger/OpenAPI
- Aplicar boas práticas de desenvolvimento

## ⚡ Features Principais

- 🔐 **Autenticação JWT** - Sistema seguro de login e autorização
- 👥 **Gerenciamento de Usuários** - Cadastro e perfis de usuário
- 💬 **CRUD de Tópicos** - Criar, listar, atualizar e deletar tópicos
- 📝 **Sistema de Respostas** - Responder e interagir com tópicos
- 🏷️ **Categorização** - Organizar tópicos por cursos/categorias
- 📚 **Documentação API** - Swagger UI integrado
- ✅ **Validações** - Validação completa dos dados de entrada

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 21+** - Linguagem principal
- **Spring Boot 3.x** - Framework principal
- **Spring Security** - Autenticação e autorização
- **Spring Data JPA** - Persistência de dados
- **JWT** - Tokens de autenticação
- **Bean Validation** - Validação de dados
- **Maven** - Gerenciamento de dependências

### Banco de Dados
- **MySQL 8.0+** - Banco de dados principal
- **H2** - Banco para testes (opcional)

### Ferramentas
- **Swagger/OpenAPI** - Documentação da API
- **Docker** - Containerização
- **Insominia** - Testes da API

## 🚀 Como Executar

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- MySQL 8.0+
- Docker (opcional)

### 🔧 Configuração Local

1. **Clone o repositório**
   ```bash
   git clone https://github.com/LuisFernando12/challenge-forum-hub.git
   cd challenge-forum-hub
   ```

2. **Configure o banco de dados caso já tenha mysql**
   ```sql
   CREATE DATABASE forum_hub;
   ```

3. **Configure as variáveis de ambiente (OPCIONAL)**
   ```bash
   # Crie um arquivo .env ou configure as variáveis
   export DB_HOST=localhost
   export DB_PORT=3306
   export DB_NAME=forum_hub
   export DB_USERNAME=seu_usuario
   export DB_PASSWORD=sua_senha
   export JWT_SECRET=seu_jwt_secret_muito_seguro
   ```

4. **Execute a aplicação**
   - Utilize um editor/ide de sua preferencia sugestão (Intellij)

### 🐳 Executar com Docker

1. **Criar arquivo docker-compose**
   ```bash
   Siga o arquivo docker_template.yaml para criar o seu.
   ```

2. **Execute com docker-compose**
   ```bash
   docker-compose up -d
   ```

## 📖 Documentação da API

Após executar a aplicação, acesse:

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **API Docs**: `http://localhost:8080/v3/api-docs`

### 🔑 Endpoints Principais

| Método | Endpoint | Descrição | Auth |
|---------|----------|-----------|------|
| `POST` | `/auth/login` | Realizar login | ❌ |
| `POST` | `/auth/register` | Cadastrar usuário | ❌ |
| `GET` | `/topics` | Listar tópicos | ✅ |
| `POST` | `/topics` | Criar tópico | ✅ |
| `GET` | `/topics/{id}` | Buscar tópico | ✅ |
| `PUT` | `/topics/{id}` | Atualizar tópico | ✅ |
| `DELETE` | `/topics/{id}` | Deletar tópico | ✅ |
| `POST` | `/topics/{id}/responses` | Responder tópico | ✅ |

### 📝 Exemplo de Uso

```bash
# Login
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "usuario@email.com",
    "password": "senha123"
  }'

# Criar tópico
curl -X POST http://localhost:8080/topics \
  -H "Authorization: Bearer seu_jwt_token" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Dúvida sobre Spring Security",
    "message": "Como implementar autenticação JWT?",
    "course": "Spring Boot"
  }'
```



## 📦 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/forum/hub/
│   │   ├── config/          # Configurações
│   │   ├── controller/      # Controllers REST
│   │   ├── dto/            # Data Transfer Objects
│   │   ├── entity/         # Entidades JPA
│   │   ├── repository/     # Repositórios
│   │   ├── service/        # Lógica de negócio
│   │   ├── security/       # Configurações de segurança
│   │   └── exception/      # Tratamento de exceções
│   └── resources/
│       ├── application.yml # Configurações
│       └── db/migration/   # Scripts SQL (Flyway)
└── test/                  # Testes automatizados
```


## 👤 Sobre o Desenvolvedor

**Luis Fernando** - Desenvolvedor FullStack

- 💼 Participante do programa Oracle Next Education (ONE)

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)]([https://linkedin.com/in/seu-perfil](https://www.linkedin.com/in/luis-fernando-dev-full-js/))
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/LuisFernando12)

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 🤝 Contribuições

Contribuições são sempre bem-vindas! Para contribuir:

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request


⭐ **Se este projeto te ajudou, considere dar uma estrela no repositório!**

> 💡 **Dica**: Este projeto foi desenvolvido como parte do Challenge ONE da Alura. Para mais projetos similares, visite o [repositório oficial dos challenges](https://github.com/alura-challenges).
