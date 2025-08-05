package com.example.postgredemo.dao;

import com.example.postgredemo.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> findOne(long id);

    List<Optional<Author>> findMany(long id);

    List<Author> find();

    void update(Author author, long id, String name, int age);

    void delete(long id);
}

