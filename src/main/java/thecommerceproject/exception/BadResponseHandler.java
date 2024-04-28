package thecommerceproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import thecommerceproject.dto.response.RestResponse;

@RestControllerAdvice
public class BadResponseHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<RestResponse> validException(
            MethodArgumentNotValidException ex) {

        RestResponse restReponse = new RestResponse(false, // 1
                "유효성 검사 실패 : " + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());

        return new ResponseEntity<>(restReponse, HttpStatus.BAD_REQUEST); // 2
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> myExceptionHandler(RuntimeException e) {
        e.printStackTrace();

        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
