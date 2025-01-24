package manga.controller.error;

import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;

/*
 * This is a Global Error Handler, it will catch an error falling under the "No Such Element" category.
 * For future updates it could also catch Illegal Argument and Constraint Violation Exceptions.
 */

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Map<String, String> handleNoSuchElementException(
			NoSuchElementException e){
		log.error("Exception: {}", e.toString());
		return Map.of("message", e.toString());
	}
}
