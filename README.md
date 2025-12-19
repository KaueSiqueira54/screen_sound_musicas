# 🎵 Screen Sound Músicas

Projeto Java desenvolvido com **Spring Boot** para gerenciamento de **artistas e músicas**, utilizando persistência com **Spring Data JPA / Hibernate** e interação via **console**.

O objetivo do projeto é praticar conceitos fundamentais de:
- Programação orientada a objetos
- Relacionamentos JPA
- Manipulação de coleções
- Boas práticas com Hibernate
- Estruturação de projetos Spring Boot

---

## 📌 Funcionalidades

- Cadastrar artistas
- Cadastrar músicas associadas a artistas
- Listar artistas e suas músicas
- Remover músicas de um artista
- Menu interativo no console

---

## 🛠️ Tecnologias utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **Banco de dados relacional** (configurável em `application.properties`)
- **Maven**

Referências:
- https://spring.io/projects/spring-boot
- https://spring.io/projects/spring-data-jpa
- https://hibernate.org/orm/

---

## 🧩 Modelagem

### Artista
- Possui várias músicas
- Relacionamento `@OneToMany`
- Cascade configurado para persistir/remover músicas automaticamente

### Música
- Pertence a um único artista
- Relacionamento `@ManyToOne`

Documentação:
- https://jakarta.ee/specifications/persistence/
- https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html

---

## Desenvolvido por Kauê Siqueira - Desafio da formação do Oracle Next Education - G9 - Oracle + Alura


