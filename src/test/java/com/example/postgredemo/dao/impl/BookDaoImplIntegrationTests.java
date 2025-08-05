package com.example.postgredemo.dao.impl;

import com.example.postgredemo.TestDataUtil;
import com.example.postgredemo.dao.AuthorDao;
import com.example.postgredemo.dao.BookDao;
import com.example.postgredemo.domain.Author;
import com.example.postgredemo.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntegrationTests {
    private BookDaoImpl underTest;
    private AuthorDaoImpl authorTest;

    @Autowired
    public BookDaoImplIntegrationTests(BookDaoImpl underTest,AuthorDaoImpl authorTest){
        this.underTest=underTest;
        this.authorTest=authorTest;
    }

    @Test
    public void testThatBookIsCreatedAndFound(){
        Book book = TestDataUtil.createTestBook();
        Author author = TestDataUtil.createTestAuthor();
        authorTest.create(author);
        underTest.create(book);
        Optional<Book> res = underTest.findBook(book.getIsbn());
        assertThat(res).isPresent();
        assertThat(res.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksAreCreatedAndFound(){
        Book book = TestDataUtil.createTestBook();
        Author author = TestDataUtil.createTestAuthor();
        Book book2 = TestDataUtil.createTestBook2();
        Author author2 = TestDataUtil.createTestAuthor2();
        authorTest.create(author);
        authorTest.create(author2);
        underTest.create(book);
        underTest.create(book2);
        List<Book> res = underTest.findAll();
        assertThat(res).hasSize(2);
        System.out.println(res);
    }
    @Test
    public void testThatBookCanBeUpdated(){
        Book book = TestDataUtil.createTestBook();
        Author author = TestDataUtil.createTestAuthor();
        authorTest.create(author);
        underTest.create(book);
        underTest.update("999D",book);
        Optional<Book> res = underTest.findBook("999D");
        assertThat(res).isPresent();
        System.out.println(res.get());
    }
    @Test
    public void testThatBookDeletionIsSuccessful(){
        Book book = TestDataUtil.createTestBook();
        Author author = TestDataUtil.createTestAuthor();
        authorTest.create(author);
        underTest.create(book);
        underTest.delete(book.getIsbn());
        Optional<Book> res = underTest.findBook(book.getIsbn());
        assertThat(res).isEmpty();
    }




}
