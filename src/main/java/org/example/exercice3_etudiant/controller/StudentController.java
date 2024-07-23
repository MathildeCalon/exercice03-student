package org.example.exercice3_etudiant.controller;

import jakarta.validation.Valid;
import org.example.exercice3_etudiant.model.Student;
import org.example.exercice3_etudiant.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        return "register";
    }

    @PostMapping("/add")
    public String addStudent(Model model, @Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "register";
        } else {
            studentService.addStudent(student);
            model.addAttribute(student);
            return "student";
        }
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

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable UUID id) {
        studentService.deleteStudent(id);
        return "redirect:/list";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable UUID id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, @PathVariable("id") UUID id) {
        if(bindingResult.hasErrors()) {
            return "update";
        } else {
            student.setId(id);
            studentService.updateStudent(student.getId(), student);
            return "redirect:/list";
        }
    }

}
