package com.example.postgredemo.dao.impl;


import com.example.postgredemo.TestDataUtil;
import com.example.postgredemo.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTest {
    @Mock
    private  JdbcTemplate jdbcTemplate;
    @InjectMocks
    public BookDaoImpl underTest;

    @Test
    public void testTheCreationOfBooks(){
        Book book = TestDataUtil.createTestBook();
        underTest.create(book);
        verify(jdbcTemplate).update(eq("INSERT INTO books (authorID,isbn,title) VALUES (?,?,?)"),
                eq(1L),
                eq("1F69"),
                eq("1984"));
    }

    @Test
    public void testFindBookMethod(){
        underTest.findBook("1F69");
        verify(jdbcTemplate).query(eq("SELECT isbn, title, author_id FROM books WHERE isbn = ?"),
                any(RowMapper.class),
                eq("1F69"));
    }
    @Test
    public void testThatFindBooksGeneratesCorrectSql(){
        Book book = TestDataUtil.createTestBook();
        Book book2 = TestDataUtil.createTestBook2();
        underTest.create(book);
        underTest.create(book2);
        underTest.findManyBooks("1F69");
        verify(jdbcTemplate).query(eq("SELECT isbn, title, author_id FROM books WHERE isbn = ?"),
                any(RowMapper.class),
                eq("1F69"));

    }
    @Test
    public void testThatFindAllGeneratesCorrectSql(){
        underTest.findAll();
        verify(jdbcTemplate).query(eq("SELECT isbn, title, author_id FROM books"), ArgumentMatchers.<BookDaoImpl.BookRowMapper>any());
    }


}
