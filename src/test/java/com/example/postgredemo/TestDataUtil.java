package com.example.postgredemo;

import com.example.postgredemo.domain.Author;
import com.example.postgredemo.domain.Book;

public class TestDataUtil {
    private TestDataUtil(){}

    public static Author createTestAuthor() {
        return Author.builder()
                .age(80)
                .id(1L)
                .name("John Doe")
                .build();
    }
    public static Author createTestAuthor2() {
        return Author.builder()
                .age(76)
                .id(5L)
                .name("Ege Bagcilar")
                .build();
    }

    public static Book createTestBook() {
        return Book.builder()
                .authorID(1L)
                .isbn("1F69")
                .title("1984")
                .build();
    }
    public static Book createTestBook2() {
        return Book.builder()
                .authorID(5L)
                .isbn("5T90")
                .title("To Kill The Mockingbird")
                .build();
    }
}
