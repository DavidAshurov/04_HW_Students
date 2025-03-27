package telran.java57.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import telran.java57.model.Student;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, Integer> {

    List<Student> findStudentsByName(String name);

    long countStudentsByNameIn(List<String> names);

    @Query("{'scores.?0': {$gte: ?1}}")
    List<Student> findStudentsByMinScore(String exam, int minScore);
}
