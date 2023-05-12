package com.gl.studenteventreg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.studenteventreg.entity.Student;
import com.gl.studenteventreg.repository.StudentRepository;
import com.gl.studenteventreg.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {

		this.studentRepository = studentRepository;
	}

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

	@Override
	public Student findById(int id) {
		return null;
	}

	@Override
	public void save(Student theStudent) {

	}

	@Override
	public List<Student> findAll() {
		return null;
	}

}
