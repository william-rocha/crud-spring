package com.example.crud_spring.service;

import com.example.crud_spring.dto.CourseDTO;
import com.example.crud_spring.dto.mapper.CourseMapper;
import com.example.crud_spring.exception.RecordNotFoundException;
import com.example.crud_spring.model.Course;
import com.example.crud_spring.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> list() {
        //                .map(course -> courseMapper.toDTO(course))
        return courseRepository.findAll().stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    };

    public CourseDTO findById(@NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO create(@Valid @NotNull CourseDTO course) {
        return  courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));
    }

    public CourseDTO update(@Valid @NotNull @Positive Long id, @Valid @NotNull CourseDTO courseDTO) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    Course course = courseMapper.toEntity(courseDTO);
            recordFound.setName(courseDTO.name());
            recordFound.setCategory(this.courseMapper.converteCategory(courseDTO.category()));
            recordFound.getLessons().clear();
            course.getLessons().forEach(recordFound.getLessons()::add);

            return courseMapper.toDTO(courseRepository.save(recordFound));
        }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {

        courseRepository.delete(courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));

//         courseRepository.findById(id)
//                .map(recordFound -> {
//                    courseRepository.deleteById(id);
//                    return true;
//                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

}
