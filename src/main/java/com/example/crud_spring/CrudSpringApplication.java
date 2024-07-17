package com.example.crud_spring;

import com.example.crud_spring.enums.Category;
import com.example.crud_spring.model.Course;
import com.example.crud_spring.model.Lesson;
import com.example.crud_spring.repository.CourseRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
//			função lambda do proprio java

			courseRepository.deleteAll();

			Course c = new Course();
			c.setName("Spring");
			c.setCategory(Category.FRONT_END);

			Lesson l = new Lesson();
			l.setName("Introdução");
			l.setYoutubeUrl("https://www.youtube.com");
			l.setCourse(c);
			c.getLessons().add(l);

			Lesson l1 = new Lesson();
			l1.setName("Angular");
			l1.setYoutubeUrl("https://www.youtube.com");
			l1.setCourse(c);
			c.getLessons().add(l1);

			courseRepository.save(c);
		};
	}
}
