package telran.java57.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Student with this id not found")
public class StudentNotFoundException extends RuntimeException {
}
