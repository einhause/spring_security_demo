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
        System.out.println("get all");
        return students;
    }

    @PostMapping
    public void registerNewStudent(Student student) {
        System.out.println(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        System.out.println(studentId);
    }

    @PatchMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId, @RequestBody Student student) {
        System.out.println(studentId);
        System.out.println(student);
    }

}
