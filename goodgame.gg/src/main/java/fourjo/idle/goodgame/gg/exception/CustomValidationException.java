package fourjo.idle.goodgame.gg.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class CustomValidationException extends RuntimeException{
    private Map<String, String> errorMap;

    //오류 처리 종류 정하고
}
