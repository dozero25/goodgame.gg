package fourjo.idle.goodgame.gg.web.controller;

import fourjo.idle.goodgame.gg.exception.CustomInputWordException;
import fourjo.idle.goodgame.gg.web.api.BadWordFilterApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/badwords")
@Tag(name ="BadWords", description = "비속어 필터링 기능입니다.")
public class BadWordFilterController {
    private final BadWordFilterApi badWordFilterApi;

    @Autowired
    public BadWordFilterController(BadWordFilterApi badWordFilterApi) {
        this.badWordFilterApi = badWordFilterApi;
    }

    @PostMapping
    @ApiOperation("AddBadWord")
    public ResponseEntity<String> addBadWord(@RequestBody String word) {
        boolean added = badWordFilterApi.addBadWord(word);
        if (added) {
            return ResponseEntity.ok("비속어가 추가되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("이미 추가된 비속어입니다.");
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation("RemoveBadWord")
    public ResponseEntity<String> removeBadWord(@PathVariable int id) {
        boolean removed = badWordFilterApi.removeBadWord(id);
        if (removed) {
            return ResponseEntity.ok("비속어가 제거되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("존재하지 않는 인덱스입니다.");
        }
    }

    @PostMapping("/filter")
    @ApiOperation("FilterBadWords")
    public ResponseEntity<Object> filterBadWords(@RequestBody String input) {
        try {
            badWordFilterApi.containsBadWord(input);
            return ResponseEntity.ok("OK");
        } catch (CustomInputWordException e) {
            Map<Integer, String> errorMap = e.getErrorMap();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
        }
    }

}