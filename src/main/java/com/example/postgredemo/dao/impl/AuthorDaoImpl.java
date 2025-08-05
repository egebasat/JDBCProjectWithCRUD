//package com.example.postgredemo.dao.impl;
//import com.example.postgredemo.domain.Author;
//import com.example.postgredemo.dao.AuthorDao;
//import lombok.extern.apachecommons.CommonsLog;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Component;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Component
//public class AuthorDaoImpl implements AuthorDao {
//    private final JdbcTemplate jdbcTemplate;
//
//    public AuthorDaoImpl(final JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public void create(Author author) {
//        jdbcTemplate.update("INSERT INTO authors (id,name,age) VALUES (?,?,?)",
//                author.getId(),
//                author.getName(),
//                author.getAge());
//    }
//
//    @Override
//    public Optional<Author> findOne(long id) {
//        List<Author> result = jdbcTemplate.query("SELECT id,name,age FROM authors WHERE id = ? LIMIT 1",new AuthorMapper(),id);
//        return result.stream().findFirst();
//    }
//
//    @Override
//    public List<Optional<Author>> findMany(long id) {
//        List<Author> res = jdbcTemplate.query("SELECT id,name,age FROM authors WHERE id = ?",new AuthorMapper(),id);
//        return res.stream()
//                .map(Optional::ofNullable)
//                .toList();
//    }
//
//    @Override
//    public List<Author> find() {
//        return jdbcTemplate.query("SELECT id,name,age FROM authors",new AuthorMapper());
//    }
//
//    @Override
//    public void update(Author author,long id, String name, int age) {
//        jdbcTemplate.update("UPDATE authors SET id = ?,name = ?,age = ? WHERE id = ?",id,name,age,author.getId());
//    }
//
//    @Override
//    public void delete(long id) {
//        jdbcTemplate.update("DELETE FROM authors WHERE id = ?",id);
//    }
//
//    public static class AuthorMapper implements RowMapper<Author> {
//
//        @Override
//        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
//
//            return Author.builder()
//                    .age(rs.getInt("age"))
//                    .id(rs.getLong("id"))
//                    .name(rs.getString("name"))
//                    .build();
//        }
//    }
//}
