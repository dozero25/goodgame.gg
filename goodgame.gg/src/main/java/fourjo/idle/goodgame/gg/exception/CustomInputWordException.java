package fourjo.idle.goodgame.gg.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class CustomInputWordException extends RuntimeException{
    private Map<Integer, String> errorMap;
}
