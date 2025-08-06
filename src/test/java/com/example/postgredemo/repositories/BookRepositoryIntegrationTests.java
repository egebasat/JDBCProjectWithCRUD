package com.example.postgredemo.repositories;

import com.example.postgredemo.TestDataUtil;
import com.example.postgredemo.domain.Author;
import com.example.postgredemo.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTests {
    private BookRepository underTest;
    private AuthorRepository authorTest;

    @Autowired
    public BookRepositoryIntegrationTests(BookRepository underTest,AuthorRepository authorTest){
        this.underTest=underTest;
        this.authorTest=authorTest;
    }

    @Test
    public void testThatBookIsCreatedAndFound(){
        Author author = TestDataUtil.createTestAuthor();
        Book book = TestDataUtil.createTestBook(author);
        authorTest.save(author);
        underTest.save(book);
        Optional<Book> res = underTest.findById(book.getIsbn());
        assertThat(res).isPresent();
        assertThat(res.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksAreCreatedAndFound(){
        Author author = TestDataUtil.createTestAuthor();
        Book book = TestDataUtil.createTestBook(author);

        Book book2 = TestDataUtil.createTestBook2(author);

        authorTest.save(author);

        underTest.save(book);
        underTest.save(book2);
        Iterable<Book> res = underTest.findAll();
        assertThat(res).hasSize(2);
        System.out.println(res);
    }

    @Test
    public void testThatBookCanBeUpdated(){
        Author author = TestDataUtil.createTestAuthor();
        Book book = TestDataUtil.createTestBook(author);

        authorTest.save(author);
        underTest.save(book);
        book.setIsbn("999D");
        underTest.save(book);
        Optional<Book> res = underTest.findById("999D");
        assertThat(res).isPresent();
        System.out.println(res.get());
    }
    @Test
    public void testThatBookDeletionIsSuccessful(){

        Author author = TestDataUtil.createTestAuthor();
        Book book = TestDataUtil.createTestBook(author);
        authorTest.save(author);
        underTest.save(book);
        underTest.delete(book);
        Optional<Book> res = underTest.findById(book.getIsbn());
        assertThat(res).isEmpty();
    }




}
