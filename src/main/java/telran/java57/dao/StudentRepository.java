package telran.java57.dao;

import telran.java57.model.Student;

import java.util.Collection;
import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);

    Optional<Student> findById(int id);

    Collection<Student> findAll();

    void deleteById(int id);
}
