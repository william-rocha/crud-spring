package com.example.crud_spring.model;

import com.example.crud_spring.enums.Category;
import com.example.crud_spring.enums.Status;
import com.example.crud_spring.enums.converters.CategoryConverter;
import com.example.crud_spring.enums.converters.StatusConverter;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

// camada de dominio
@Data
@Entity
//@table(name = "cursos")
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")
@SQLRestriction("status = 'Ativo'")
public class Course {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @JsonProperty("_id")
//        @JsonIgnore
        private Long id;

//        como no banco foi 100
        @NotBlank
        @NotNull
        @Length(min = 5, max = 30)
        @Column(length = 100, nullable = false)
        private String name;

        @NotNull
//        @Length(max = 10)
//        @Pattern(regexp = "Back-end|Front-end")
        @Column(nullable = false)
//        ordinal pega o index
//        @Enumerated(EnumType.ORDINAL)
        @Convert(converter = CategoryConverter.class)
        private Category category;

        @NotNull
        @Column(length = 10, nullable = false)
        @Convert(converter = StatusConverter.class)
        private Status status = Status.ACTIVE;


}
