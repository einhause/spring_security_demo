package com.einhause.spring_security.student;

import org.springframework.security.access.prepost.PreAuthorize;

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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN_TRAINEE')")
    public List<Student> getAllStudents() {
        // this would request db to return all students in db
        return students;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewStudent(Student student) {
        // logic for user registration would be here:
        // check if user already exists, throw ex if so
        // create Student
        // add to db
    }

    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        // logic for user deletion would be here:
        // check if user doesn't exist, throw ex if so
        // delete student and update db
    }

    @PatchMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable("studentId") Long studentId, @RequestBody Student student) {
        // logic for user update would be here:
        // check if user doesn't exist, throw ex if so
        // update student and update db
    }

}
