package com.example.postgredemo.domain;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "authors")
public class Author {

    private Integer age;

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
}
