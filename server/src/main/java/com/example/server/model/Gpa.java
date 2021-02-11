package com.example.server.model;

import javax.persistence.*;
// cardinality - defines what connects to what -- set theory
// gpa <-> student | one to one
// student -> multiple classes | single class could have multiple students | many to many relation
//
@Entity
@Table(name="students_gpa")
public class Gpa {
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column // 3.5 // 1.0 // x.x
    private Double gpa;

    @OneToOne(mappedBy = "gradePointAverage", cascade = {
            CascadeType.ALL
    })
    private Student student;

    public Gpa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }
}
