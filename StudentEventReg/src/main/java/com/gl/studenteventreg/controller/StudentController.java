package com.gl.studenteventreg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gl.studenteventreg.entity.Student;
import com.gl.studenteventreg.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	@GetMapping("/")
	public String showHome() {
		return "home";
	}

	@GetMapping("/systems")
	public String showSystems() {
		return "systems";
	}

	@GetMapping("/leaders")
	public String showLeaders() {
		return "leaders";
	}

	@GetMapping("/view")
	public String liststudents(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "view";
	}

	public String showView() {

		return "view";
	}

	@GetMapping("/delete")
	public String deleteStudents(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "delete";
	}

	@GetMapping("/insert")
	public String showInsert() {
		return "insert";
	}

	@GetMapping("/print")
	public String printStudents(Model model) {
		model.addAttribute("students", studentService.getAllStudents());

		return "print";
	}

	// add request mapping for /access-denied
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		return "access-denied";

	}

	@GetMapping("/new")
	public String createstudentForm(Model model) {

		// create student object to hold student form data
		Student student = new Student();

		model.addAttribute("student", student);

		return "insert";

	}

	@GetMapping("/students/edit/{id}")
	public String editstudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		return "edit_student";
	}

	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {

		studentService.saveStudent(student);
		return "redirect:/";
	}

	@PostMapping("/students/{id}")
	public String updatestudent(@PathVariable Long id, @ModelAttribute("student") Student student, Model model) {

		// get student from database by id

		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setId(id);
		existingStudent.setFname(student.getFname());
		existingStudent.setLname(student.getLname());
		existingStudent.setCourse(student.getCourse());
		existingStudent.setCountry(student.getCountry());

		// save updated student object
		studentService.updateStudent(existingStudent);

		return "redirect:/";
	}

	@GetMapping("/students/{id}")
	public String deletestudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/delete";
	}

}
