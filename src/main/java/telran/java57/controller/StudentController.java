package telran.java57.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import telran.java57.dto.*;
import telran.java57.dto.exceptions.StudentNotFoundException;
import telran.java57.service.StudentService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public boolean addStudent(@RequestBody NewStudentDto newStudentDto) {
        return studentService.addStudent(newStudentDto);
    }

    @GetMapping("/student/{id}")
    public StudentDto findStudent(@PathVariable int id) {
        return studentService.findStudent(id);
    }

    @DeleteMapping("/student/{id}")
    public StudentDto deleteStudent(@PathVariable int id) {
        return studentService.removeStudent(id);
    }

    @PutMapping("/student/{id}")
    public NewStudentDto updateStudent(@PathVariable int id,@RequestBody StudentUpdateDto studentUpdateDto) {
        return studentService.updateStudent(id,studentUpdateDto);
    }

    @PutMapping("/score/student/{id}")
    public boolean addScore(@PathVariable int id,@RequestBody ScoreDto scoreDto) {
        return studentService.addScore(id,scoreDto);
    }

    @GetMapping("/students/name/{name}")
    public List<StudentDto> findStudentsByName(@PathVariable String name) {
        return studentService.findStudentsByName(name);
    }

    @PostMapping("/quantity/students")
    public long countStudents(@RequestBody List<String> names) {
        return studentService.countStudents(names);
    }

    @GetMapping("/students/exam/{exam}/minscore/{minScore}")
    public List<StudentDto> findStudentsByMinScore(@PathVariable String exam, @PathVariable int minScore) {
        return studentService.findStudentsByMinScore(exam,minScore);
    }

}
