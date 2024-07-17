package com.example.crud_spring.dto.mapper;


import com.example.crud_spring.dto.CourseDTO;
import com.example.crud_spring.enums.Category;
import com.example.crud_spring.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }
//        getValue da classe Status
        return  new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(), course.getLessons());
    }

    public Course toEntity(CourseDTO courseDTO) {

        if(courseDTO == null) {
            return null;
        }

        Course course = new Course();
        if(courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(converteCategory(courseDTO.category()));
//        course.setCategory(Category.FRONT_END);
//        course.setStatus("Ativo");
        return  course;
    }

    public Category converteCategory(String value) {
        if(value == null) {
            return null;
        }
      return  switch (value) {
            case "Front-end" -> Category.FRONT_END;
            case "Back-end" -> Category.BACK_END;
            default -> throw new IllegalArgumentException("Categoria invalida " + value);
        };
    }
}
