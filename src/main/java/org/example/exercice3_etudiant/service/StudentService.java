package org.example.exercice3_etudiant.service;

import org.example.exercice3_etudiant.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {
    private final Map<UUID, Student> students;

    public StudentService() {
        students = new HashMap<>();
        Student student1 = Student.builder()
                .id(UUID.randomUUID())
                .firstname("Mathilde")
                .lastname("Calon")
                .age(32)
                .email("mathilde@gmail.com")
                .build();

        Student student2 = Student.builder()
                .id(UUID.randomUUID())
                .firstname("Adele")
                .lastname("Delval")
                .age(4)
                .email("adele@gmail.com")
                .build();

        students.put(student1.getId(), student1);
        students.put(student2.getId(), student2);
    }

    public List<Student> getStudents() {
        return students.values().stream().toList();
    }
    public Student getStudentById(UUID id) {
        return students.get(id);
    }

    public List<Student> getStudentByLastname(String lastname) {
        List<Student> result = new ArrayList<>();
        students.values().stream().filter(s -> s.getLastname().equals(lastname)).forEach(result::add);
        return result;
    }

    public void addStudent(Student student) {
        student.setId(UUID.randomUUID());
        students.put(student.getId(), student);
    }

    public void updateStudent(UUID id, Student student) {
        students.put(id, student);
    }

    public void deleteStudent(UUID id) {
        students.remove(id);
    }

}
