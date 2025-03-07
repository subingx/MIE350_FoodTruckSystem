package com.example.cms.controller;

import com.example.cms.controller.dto.CourseDto;
import com.example.cms.controller.exceptions.DepartmentNotFoundException;
import com.example.cms.controller.exceptions.ProfessorNotFoundException;
import com.example.cms.model.entity.*;
import com.example.cms.model.repository.*;

import com.example.cms.controller.exceptions.FoodTruckNotFoundException;
import com.example.cms.controller.exceptions.FoodTruckOwnerNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class FoodTruckController {
    @Autowired
    private final FoodTruckRepository foodTruckRepository;

    @Autowired
    private FoodTruckOwnerRepository OwnerRepository;

    public FoodTruckController(FoodTruckRepository repository) {
        this.foodTruckRepository = repository;
    }



    @GetMapping("/departments")
    List<Department> retrieveAllDepartments() {
        return repository.findAll();
    }

    @GetMapping("/departments/{code}")
    Department retrieveDepartment(@PathVariable("code") String departCode) {
        return repository.findById(departCode)
                .orElseThrow(() -> new DepartmentNotFoundException(departCode));
    }

    //
    @Autowired
    private final CourseRepository repository;

    @Autowired
    private ProfessorRepository professorRepository;

    public CourseController(CourseRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/courses")
    List<Course> retrieveAllCourses() {
        return repository.findAll();
    }

    @PostMapping("/courses")
    Course createCourse(@RequestBody CourseDto courseDto) {
        Course newCourse = new Course();
        newCourse.setName(courseDto.getName());
        newCourse.setCode(courseDto.getCode());
        Professor professor = professorRepository.findById(courseDto.getProfessorId()).orElseThrow(
                () -> new ProfessorNotFoundException(courseDto.getProfessorId()));
        newCourse.setProfessor(professor);
        return repository.save(newCourse);
    }


}
