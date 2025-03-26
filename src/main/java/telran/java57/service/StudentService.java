package telran.java57.service;

import telran.java57.dto.NewStudentDto;
import telran.java57.dto.ScoreDto;
import telran.java57.dto.StudentDto;
import telran.java57.dto.StudentUpdateDto;

import java.util.List;

public interface StudentService {
    boolean addStudent(NewStudentDto newStudentDto);

    StudentDto findStudent(int id);

    StudentDto removeStudent(int id);

    NewStudentDto updateStudent(int id, StudentUpdateDto studentUpdateDto);

    boolean addScore(int id, ScoreDto scoreDto);

    List<StudentDto> findStudentsByName(String name);

    long countStudents(List<String> names);

    List<StudentDto> findStudentsByMinScore(String exam, int minScore);
}
