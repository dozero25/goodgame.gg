package fourjo.idle.goodgame.gg.web.service;

import fourjo.idle.goodgame.gg.exception.CustomInputWordException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BadWordFilterService {
    private Map<Integer, String> badWords = new HashMap<>();

    public void addBadWord(int id, String word) {
        badWords.put(id, word);
    }
    public void removeBadWord(int id) {
        badWords.remove(id);
    }
    public void containsBadWord(String input) throws CustomInputWordException {
        Map<Integer, String> errorMap = new HashMap<>();
        for (Map.Entry<Integer, String> entry : badWords.entrySet()) {
            if (input.contains(entry.getValue())) {
                errorMap.put(entry.getKey(), entry.getValue());
            }
        }
        if (!errorMap.isEmpty()) {
            throw new CustomInputWordException(errorMap);
        }
    }

}