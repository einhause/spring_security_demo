package com.einhause.spring_security.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private static final List<Student> students = List.of(
            new Student(1L, "Eric"),
            new Student(2L, "Amy"),
            new Student(3L, "Kevin")
    );

    @GetMapping(path = "{studentId}")
    public Student getStudentById(@PathVariable("studentId") Long studentId) {
        Student student = students.stream()
                .filter(s -> s.getStudentId().equals(studentId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Student with ID: %d not found!", studentId)));
        return student;
    }
}
