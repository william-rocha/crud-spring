package com.example.crud_spring.controller;

import com.example.crud_spring.dto.CourseDTO;
import com.example.crud_spring.dto.CoursePageDTO;
import com.example.crud_spring.dto.mapper.CourseMapper;
import com.example.crud_spring.model.Course;
import com.example.crud_spring.repository.CourseRepository;
import com.example.crud_spring.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public CoursePageDTO list(@RequestParam(defaultValue = "0") @PositiveOrZero int page, @RequestParam(defaultValue = "10") @Positive @Max(100) int size) {
        return courseService.list(page, size);
    };

    @GetMapping("/{id}")
    public CourseDTO findById(@PathVariable @NotNull @Positive Long id) {
        return courseService.findById(id);

    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CourseDTO create(@RequestBody @Valid CourseDTO course) {
        return courseService.create(course);
    }

    @PutMapping("/{id}")
    public CourseDTO update(@PathVariable @NotNull @Positive Long id,
                            @RequestBody @Valid @NotNull CourseDTO course) {
        return courseService.update(id, course);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        courseService.delete(id);
    }
}
