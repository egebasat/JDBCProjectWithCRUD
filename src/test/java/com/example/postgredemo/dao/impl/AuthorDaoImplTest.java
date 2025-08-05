package com.example.postgredemo.dao.impl;


import com.example.postgredemo.TestDataUtil;
import com.example.postgredemo.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    public AuthorDaoImpl underTest;

    @Test
    public void testThatAuthorGeneratesCorrectSql(){
        Author author = TestDataUtil.createTestAuthor();
        underTest.create(author);
        verify(jdbcTemplate).update(eq("INSERT INTO authors (id,name,age) VALUES (?,?,?)"),
                eq(1L),
                eq("John Doe"),
                eq(80));
    }

    @Test
    public void testFindOneFunction(){
        underTest.findOne(1L);
        verify(jdbcTemplate).query(eq("SELECT id,name,age FROM authors WHERE id = ? LIMIT 1"), any(RowMapper.class),eq(1L));
    }
    @Test
    public void testThatFindManyGeneratesCorrectSql(){
        Author author1 = TestDataUtil.createTestAuthor();
        Author author2 = TestDataUtil.createTestAuthor2();
        underTest.create(author1);
        underTest.create(author2);
        underTest.findMany(1L);
        verify(jdbcTemplate).query(eq("SELECT id,name,age FROM authors WHERE id = ?"),any(RowMapper.class),eq(1L));
    }
    @Test
    public void testThatFindAllGeneratesCorrectSql(){
        Author author1 = TestDataUtil.createTestAuthor();
        Author author2 = TestDataUtil.createTestAuthor2();
        underTest.create(author1);
        underTest.create(author2);
        underTest.find();
        verify(jdbcTemplate).query(eq("SELECT id,name,age FROM authors"),any(RowMapper.class));
    }



}
