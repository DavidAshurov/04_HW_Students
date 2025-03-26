package telran.java57.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import telran.java57.dao.StudentRepository;
import telran.java57.dto.NewStudentDto;
import telran.java57.dto.ScoreDto;
import telran.java57.dto.StudentDto;
import telran.java57.dto.StudentUpdateDto;
import telran.java57.dto.exceptions.StudentNotFoundException;
import telran.java57.model.Student;

import java.util.List;

@Component
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository studentRepository;

    @Override
    public boolean addStudent(NewStudentDto newStudentDto) {
        if (studentRepository.findById(newStudentDto.getId()).isPresent()) {
            return false;
        }
        Student student = new Student(newStudentDto.getId(), newStudentDto.getName(), newStudentDto.getPassword());
        studentRepository.save(student);
        return true;
    }

    @Override
    public StudentDto findStudent(int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " not found"));
        return new StudentDto(id,student.getName(),student.getScores());
    }

    @Override
    public StudentDto removeStudent(int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " not found"));
        studentRepository.deleteById(id);
        return new StudentDto(id,student.getName(),student.getScores());
    }

    @Override
    public NewStudentDto updateStudent(int id, StudentUpdateDto studentUpdateDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " not found"));
        student.setName(studentUpdateDto.getName());
        student.setPassword(studentUpdateDto.getPassword());
        studentRepository.save(student);
        return new NewStudentDto(id,student.getName(),student.getPassword());
    }

    @Override
    public boolean addScore(int id, ScoreDto scoreDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " not found"));
        student.addScore(scoreDto.getExamName(), scoreDto.getScore());
        studentRepository.save(student);
        return true;
    }

    @Override
    public List<StudentDto> findStudentsByName(String name) {
        return studentRepository.findAll().stream()
                .filter(s -> s.getName().equals(name))
                .map(s -> new StudentDto(s.getId(),s.getName(),s.getScores()))
                .toList();
    }

    @Override
    public long countStudents(List<String> names) {
        return studentRepository.findAll().stream()
                .filter(s -> names.contains(s.getName()))
                .count();
    }

    @Override
    public List<StudentDto> findStudentsByMinScore(String exam, int minScore) {
        return studentRepository.findAll().stream()
                .filter(s -> s.getScores().containsKey(exam) && s.getScores().get(exam) >= minScore)
                .map(s -> new StudentDto(s.getId(),s.getName(),s.getScores()))
                .toList();
    }
}
