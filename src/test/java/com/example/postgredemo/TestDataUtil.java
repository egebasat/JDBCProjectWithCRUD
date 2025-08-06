package com.example.postgredemo;

import com.example.postgredemo.domain.Author;
import com.example.postgredemo.domain.Book;

public class TestDataUtil {
    private TestDataUtil(){}

    public static Author createTestAuthor() {
        return Author.builder()
                .age(80)
                .name("John Doe")
                .build();
    }
    public static Author createTestAuthor2() {
        return Author.builder()
                .age(76)
                .name("Ege Bagcilar")
                .build();
    }

    public static Book createTestBook(final Author author) {
        return Book.builder()
                .author(author)
                .isbn("1F69")
                .title("1984")
                .build();
    }
    public static Book createTestBook2(final Author author) {
        return Book.builder()
                .author(author)
                .isbn("5T90")
                .title("To Kill The Mockingbird")
                .build();
    }
}
