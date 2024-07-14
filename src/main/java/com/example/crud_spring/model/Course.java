package com.example.crud_spring.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
//@table(name = "cursos")
public class Course {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column(length = 200, nullable = false)
        private String name;

        @Column(length = 10, nullable = false)
        private String category;

}
