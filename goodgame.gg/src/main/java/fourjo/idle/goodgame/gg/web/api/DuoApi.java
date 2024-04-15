package fourjo.idle.goodgame.gg.web.api;

import fourjo.idle.goodgame.gg.web.dto.CMRespDto;
import fourjo.idle.goodgame.gg.web.dto.duo.DuoDto;
import fourjo.idle.goodgame.gg.web.dto.duo.DuoSearchDto;
import fourjo.idle.goodgame.gg.web.service.DuoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/duo")
@Tag(name = "DuoApi", description = "듀오찾기 관리 및 출력하는 Api 입니다.")
public class DuoApi {

    @Autowired
    private DuoService duoService;



    @PostMapping("/insert")
    public ResponseEntity<CMRespDto<?>> duoInsert(DuoDto duoDto) {


        duoService.duoInsert(duoDto);

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }
    @GetMapping("/search")
    public ResponseEntity<CMRespDto<?>> duoSearchByQueAndTierAndPosition(DuoSearchDto duoSearchDto) {



        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", duoService.duoSearchByQueAndTierAndPosition(duoSearchDto)));
    }
    @GetMapping("/totalCount")
    public ResponseEntity<CMRespDto<?>> getDuoTotalCount(DuoSearchDto duoSearchDto) {



        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", duoService.getDuoTotalCount(duoSearchDto)));
    }

}