package com.example.postgredemo.repositories;

import com.example.postgredemo.TestDataUtil;
import com.example.postgredemo.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class AuthorDaoImplIntegrationTests {

    private final AuthorRepository underTest;

    @Autowired
    public AuthorDaoImplIntegrationTests(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndCalled(){
    Author author = TestDataUtil.createTestAuthor();
    underTest.save(author);
    Optional<Author> result = underTest.findById(author.getId());
    assertThat(result).isPresent();
    assertThat(result.get()).isEqualTo(author);
    System.out.println(result);
    }
    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndCalled(){
        Author authorA = TestDataUtil.createTestAuthor();
        Author authorB = TestDataUtil.createTestAuthor2();
        underTest.save(authorA);
        underTest.save(authorB);
        Iterable<Author> res = underTest.findAll();
        assertThat(res)
                .hasSize(2)
                .containsExactly(authorA,authorB);
        System.out.println(res);

    }
    @Test
    public void testThatAuthorCanBeUpdated(){
        Author authorA = TestDataUtil.createTestAuthor();
        underTest.save(authorA);
        authorA.setName("UPDATED");
        underTest.save(authorA);
        Optional<Author> res = underTest.findById(authorA.getId());
        assertThat(res).isPresent();
        assertThat(res.get()).isEqualTo(authorA);
    }
//    @Test
//    public void testThatAuthorDeletionIsSuccessful(){
//        Author authorA = TestDataUtil.createTestAuthor();
//        underTest.create(authorA);
//        underTest.delete(authorA.getId());
//        Optional<Author> res = underTest.findOne(authorA.getId());
//        assertThat(res).isEmpty();
//    }

}
