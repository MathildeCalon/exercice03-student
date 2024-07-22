package org.example.exercice3_etudiant.controller;

import org.example.exercice3_etudiant.model.Student;
import org.example.exercice3_etudiant.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String homePage() {return "home";}

    @GetMapping("/list")
    public String listStudents(Model model) {
        model.addAttribute("liste", studentService.getStudents());
        return "studentList";
    }

    @GetMapping("/student/{id}")
    public String getStudent(Model model, @PathVariable UUID id) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "student";
    }


    @GetMapping("/add")
    public String addStudent() {
        return "register";
    }

    @PostMapping("/add")
    public String addStudent(Model model,
                             @RequestParam(value = "firstname") String firstname,
                             @RequestParam(value = "lastname") String lastname,
                             @RequestParam(value = "age") int age,
                             @RequestParam(value = "email") String email) {

        Student student = Student.builder()
                .firstname(firstname)
                .lastname(lastname)
                .age(age)
                .email(email)
                .build();

        studentService.addStudent(student);
        model.addAttribute(student);
        return "student";
    }

    @GetMapping("/result")
    public String result(Model model) {
        return "result";
    }

    @PostMapping("/result")
    public String result(Model model, @ModelAttribute("name") String name) {
        List<Student> result = studentService.getStudentByLastname(name);
        model.addAttribute("result", result);
        return "result";
    }
}
