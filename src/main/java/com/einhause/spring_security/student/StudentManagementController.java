package com.einhause.spring_security.student;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/management/api/v1/students")
public class StudentManagementController {

    private static final List<Student> students = List.of(
            new Student(1L, "Eric"),
            new Student(2L, "Amy"),
            new Student(3L, "Kevin")
    );

    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }

    @PostMapping
    public void registerNewStudent(Student student) {

    }

    @DeleteMapping(path = "/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {

    }

    @PatchMapping(path = "/{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId, @RequestBody Student student) {

    }

}
