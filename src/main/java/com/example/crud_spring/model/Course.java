package com.example.crud_spring.model;

import com.example.crud_spring.enums.Category;
import com.example.crud_spring.enums.Status;
import com.example.crud_spring.enums.converters.CategoryConverter;
import com.example.crud_spring.enums.converters.StatusConverter;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

// camada de dominio
@Entity
//@table(name = "cursos")
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")
@SQLRestriction("status = 'Ativo'")
public class Course {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @JsonProperty("_id")
        private Long id;

        @NotBlank
        @NotNull
        @Length(min = 5, max = 30)
        @Column(length = 100, nullable = false)
        private String name;

        @NotNull
        @Column(nullable = false)
        @Convert(converter = CategoryConverter.class)
        private Category category;

        @NotNull
        @Column(length = 10, nullable = false)
        @Convert(converter = StatusConverter.class)
        private Status status = Status.ACTIVE;

        @NotNull
        @NotEmpty
        @Valid
        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
        private List<Lesson> lessons = new ArrayList<>();

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Status getStatus() {
                return status;
        }

        public void setStatus(Status status) {
                this.status = status;
        }

        public List<Lesson> getLessons() {
                return lessons;
        }

        public void setLessons(List<Lesson> lessons) {
                this.lessons = lessons;
        }

        public Category getCategory() {
                return category;
        }

        public void setCategory(Category category) {
                this.category = category;
        }

        public @NotBlank @NotNull @Length(min = 5, max = 30) String getName() {
                return name;
        }

        public void setName(@NotBlank @NotNull @Length(min = 5, max = 30) String name) {
                this.name = name;
        }


        @Override
        public String toString() {
                return "Course{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", category=" + category +
                        ", status=" + status +
                        ", lessons=" + lessons +
                        '}';
        }


}
