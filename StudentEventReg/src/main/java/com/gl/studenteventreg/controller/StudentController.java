package com.gl.studenteventreg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gl.studenteventreg.entity.Student;
import com.gl.studenteventreg.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping("/list")
	public String liststudents(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "view";
	}

	@GetMapping("/print")
	public String printStudents(Model model) {
		model.addAttribute("students", studentService.getAllStudents());

		return "print";
	}

	@GetMapping("/access-denied")
	public String showAccessDenied() {
		return "access-denied";

	}

	@GetMapping("/")
	public String login() {
		return "redirect:/student/list";

	}

	@GetMapping("/logout")
	public String logout() {
		return "redirect:/login";

	}

	@GetMapping("/new")
	public String createstudentForm(Model model) {

		// create student object to hold student form data
		Student student = new Student();

		model.addAttribute("student", student);

		return "insert";

	} // pass

	@GetMapping("/edit/{id}")
	public String editstudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		return "edit_student";
	}

	@PostMapping("/save")
	public String saveStudent(@ModelAttribute("student") Student student) {

		studentService.saveStudent(student);
		return "redirect:/student/list";
	}

	@PostMapping("/save/{id}")
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

		return "redirect:/student/list";
	}

	@GetMapping("/delete/{id}")
	public String deletestudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/student/list";
	}

}
