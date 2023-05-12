package com.gl.studenteventreg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gl.studenteventreg.entity.Student;

@Service
public interface StudentService {

	List<Student> getAllStudents();

	Student saveStudent(Student ticket);

	Student getStudentById(Long id);

	Student updateStudent(Student ticket);

	void deleteStudentById(Long id);

	Student findById(int id);

	void save(Student theStudent);

	List<Student> findAll();

}
