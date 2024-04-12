package fourjo.idle.goodgame.gg.web.advice;

import fourjo.idle.goodgame.gg.exception.CustomInputWordException;
import fourjo.idle.goodgame.gg.exception.CustomValidationException;
import fourjo.idle.goodgame.gg.web.dto.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<?> validationError(CustomValidationException e){
        return ResponseEntity.badRequest()
                .body(new CMRespDto<>(HttpStatus.BAD_REQUEST.value(), "Validation Error", e.getErrorMap()));
    }

    @ExceptionHandler(CustomInputWordException.class)
    public ResponseEntity<?> inputWordError(CustomInputWordException e){
        return ResponseEntity.badRequest()
                .body(new CMRespDto<>(HttpStatus.BAD_REQUEST.value(), "InputWord Error", e.getErrorMap()));
    }


}
