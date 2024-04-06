package fourjo.idle.goodgame.gg.web.api;

import fourjo.idle.goodgame.gg.web.dto.BoardDTO;
import fourjo.idle.goodgame.gg.web.dto.CMRespDto;
import fourjo.idle.goodgame.gg.web.dto.UserDto;
import fourjo.idle.goodgame.gg.web.service.BoardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Board")
@Tag(name ="Board Api", description = "Board Api 입니다.")
public class BoardApi {

    @Autowired
    private BoardService boardService;


    @PostMapping("/insertBoard")
    public ResponseEntity<CMRespDto<?>> insertBoard (BoardDTO dto){
        boardService.insertBoard(dto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

    @PostMapping("/updateBoard")
    public ResponseEntity<CMRespDto<?>> updateBoard (BoardDTO dto){
        boardService.updateBoard(dto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

    @PostMapping("/deleteBoard")
    public ResponseEntity<CMRespDto<?>> deleteBoard (int board_index){
        boardService.deleteBoard(board_index);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }
    @GetMapping("/selectOneBoard")
    public ResponseEntity<CMRespDto<?>> selectOneBoard (int board_index){
        boardService.selectOneBoard(board_index);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

    @GetMapping("/selectAllBoard")
    public ResponseEntity<CMRespDto<?>> selectAllBoard (){
        boardService.selectAllBoard();
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

    @GetMapping("/searchBoard")
    public ResponseEntity<CMRespDto<?>> searchBoard (String searchKey, String searchValue){
        boardService.searchBoard(searchKey, searchValue);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }




}
