package com.example.postgredemo.dao;

import com.example.postgredemo.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    void create(Book book);

    Optional<Book> findBook(String isbn);

    List<Optional<Book>> findManyBooks(String isbn);

    List<Book> findAll();

    void update(String isbn,Book book);

    void delete(String isbn);
}
