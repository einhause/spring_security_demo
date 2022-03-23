package com.einhause.spring_security.Student;

public class Student {
    private final Long studentId;
    private final String studentName;

    public Student(Long studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public Long getStudentId() {
        return studentId;
    }
}
