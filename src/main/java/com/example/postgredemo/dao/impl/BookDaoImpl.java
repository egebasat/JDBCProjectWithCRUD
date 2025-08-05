package com.example.postgredemo.dao.impl;

import com.example.postgredemo.dao.BookDao;
import com.example.postgredemo.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Component
public class BookDaoImpl implements BookDao {
    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO books (author_id,isbn,title) VALUES (?,?,?)",
                book.getAuthorID(),
                book.getIsbn(),
                book.getTitle());
    }

    @Override
    public Optional<Book> findBook(String isbn) {
        List<Book> bookList = jdbcTemplate.query("SELECT isbn,title,author_id FROM books WHERE isbn = ?",
                new BookRowMapper(),
                isbn);
        return bookList.stream().findFirst();

    }

    @Override
    public List<Optional<Book>> findManyBooks(String isbn) {
        List<Book> bookList = jdbcTemplate.query("SELECT isbn,title,author_id FROM books WHERE isbn = ?",
                new BookRowMapper(),
                isbn);
        return bookList.stream()
                .map(Optional::ofNullable)
                .toList();

    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT isbn, title, author_id FROM books",new BookRowMapper());
    }

    @Override
    public void update(String isbn, Book book) {
        jdbcTemplate.update("UPDATE books SET title = ?, isbn = ?, author_id = ? WHERE isbn = ?",
                book.getTitle(),isbn,book.getAuthorID(),book.getIsbn());
    }

    @Override
    public void delete(String isbn) {
        jdbcTemplate.update("DELETE FROM books WHERE isbn = ?",isbn);
    }

    public static class BookRowMapper implements RowMapper<Book>{

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder()
                    .title(rs.getString("title"))
                    .isbn(rs.getString("isbn"))
                    .authorID(rs.getLong("author_id"))
                    .build();
        }
    }
}
