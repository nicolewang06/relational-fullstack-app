package com.example.server.service;

import com.example.server.model.Student;
import com.example.server.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;//field or instance variable

    @Override
    public Iterable<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
        //gives back optional (if ID doesnt exist) .get() gets the info/object
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public HttpStatus deleteStudent(Long id) {
        studentRepository.deleteById(id);
        return HttpStatus.I_AM_A_TEAPOT;
    }
}
