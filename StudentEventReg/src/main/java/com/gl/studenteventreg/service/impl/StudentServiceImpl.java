package com.gl.studenteventreg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.studenteventreg.entity.Student;
import com.gl.studenteventreg.repository.StudentRepository;
import com.gl.studenteventreg.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	public StudentServiceImpl(StudentRepository studentRepository) {

		this.studentRepository = studentRepository;
	}

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);

	}

	@Override
	public Student getStudentById(Long id) {
		return studentRepository.findById(id).get();

	}

	@Override
	public Student updateStudent(Student student) {
		return studentRepository.save(student);

	}

	@Override
	public void deleteStudentById(Long id) {
		studentRepository.deleteById(id);

	}

}
