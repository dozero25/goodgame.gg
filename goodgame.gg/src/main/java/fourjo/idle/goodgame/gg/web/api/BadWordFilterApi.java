package fourjo.idle.goodgame.gg.web.api;

import fourjo.idle.goodgame.gg.exception.CustomInputWordException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class BadWordFilterApi {
    private Map<Integer, String> badWords = new HashMap<>();
    private AtomicInteger nextIndex = new AtomicInteger(1);

    public boolean addBadWord(String word) {
        if (badWords.containsValue(word)) {
            return false; // 이미 추가된 비속어
        }
        int id = nextIndex.getAndIncrement();
        badWords.put(id, word);
        return true;
    }

    public boolean removeBadWord(int id) {
        if (!badWords.containsKey(id)) {
            return false; // 존재하지 않는 인덱스
        }
        badWords.remove(id);
        return true;
    }

    public boolean containsBadWord(String input) throws CustomInputWordException {
        Map<Integer, String> errorMap = new HashMap<>();
        for (Map.Entry<Integer, String> entry : badWords.entrySet()) {
            if (input.contains(entry.getValue())) {
                errorMap.put(entry.getKey(), entry.getValue());
            }
        }
        if (!errorMap.isEmpty()) {
            throw new CustomInputWordException(errorMap);
        }
        return false;
    }
}