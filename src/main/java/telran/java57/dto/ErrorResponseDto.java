package telran.java57.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public class ErrorResponseDto {
    String timestamp;
    HttpStatusCode status;
    String error;
    String message;
    String path;
}
