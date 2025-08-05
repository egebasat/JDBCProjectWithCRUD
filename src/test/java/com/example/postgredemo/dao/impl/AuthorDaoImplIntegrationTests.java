package com.example.postgredemo.dao.impl;

import com.example.postgredemo.TestDataUtil;
import com.example.postgredemo.domain.Author;
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

public class AuthorDaoImplIntegrationTests {

    private AuthorDaoImpl underTest;

    @Autowired
    public AuthorDaoImplIntegrationTests(AuthorDaoImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndCalled(){
    Author author = TestDataUtil.createTestAuthor();
    underTest.create(author);
    Optional<Author> result = underTest.findOne(author.getId());
    assertThat(result).isPresent();
    assertThat(result.get()).isEqualTo(author);
    System.out.println(result);
    }
    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndCalled(){
        Author authorA = TestDataUtil.createTestAuthor();
        Author authorB = TestDataUtil.createTestAuthor2();
        underTest.create(authorA);
        underTest.create(authorB);
        List<Author> res = underTest.find();
        assertThat(res)
                .hasSize(2)
                .containsExactly(authorA,authorB);
        System.out.println(res);

    }
    @Test
    public void testThatAuthorCanBeUpdated(){
        Author authorA = TestDataUtil.createTestAuthor();
        underTest.create(authorA);
        underTest.update(authorA,49L,"arif abi",32);
        Optional<Author> res = underTest.findOne(49L);
        assertThat(res).isPresent();
    }
    @Test
    public void testThatAuthorDeletionIsSuccessful(){
        Author authorA = TestDataUtil.createTestAuthor();
        underTest.create(authorA);
        underTest.delete(authorA.getId());
        Optional<Author> res = underTest.findOne(authorA.getId());
        assertThat(res).isEmpty();
    }

}
