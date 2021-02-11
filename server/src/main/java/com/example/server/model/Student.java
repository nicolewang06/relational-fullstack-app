package com.example.server.model;

import javax.persistence.*;

@Entity
@Table(name="school_students")
public class Student {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private Integer age;

    @Column
    private Integer grade;

    @Column
    private String schoolName;

    @OneToOne(cascade = CascadeType.ALL)// if we do 1 thing for this, do for all
    @JoinColumn(name = "students_gpa_id")
    private Gpa gradePointAverage;

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Gpa getGradePointAverage() {
        return gradePointAverage;
    }

    public void setGradePointAverage(Gpa gradePointAverage) {
        this.gradePointAverage = gradePointAverage;
    }
}
