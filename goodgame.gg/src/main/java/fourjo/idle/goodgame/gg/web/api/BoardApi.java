package fourjo.idle.goodgame.gg.web.api;

import fourjo.idle.goodgame.gg.web.dto.BoardDTO;
import fourjo.idle.goodgame.gg.web.dto.CMRespDto;
import fourjo.idle.goodgame.gg.web.service.BoardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
@Tag(name ="Board Api", description = "Board Api 입니다.")
public class BoardApi {

    @Autowired
    private BoardService boardService;

    @PostMapping("/insert")
    public ResponseEntity<CMRespDto<?>> insertBoard(@RequestBody BoardDTO boardDTO){

        boardService.insertBoard(boardDTO);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered",true));

    }

    @PostMapping("/update")
    public ResponseEntity<CMRespDto<?>> updateBoard(@RequestBody BoardDTO boardDTO){

        boardService.updateBoard(boardDTO);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered",true));

    }

    @GetMapping("/delete")
    public ResponseEntity<CMRespDto<?>> deleteBoard(@RequestBody BoardDTO boardDTO,Model model){

        boardService.deleteBoard(boardDTO);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered",true));

    }

    /*@GetMapping("/delete")
    public String boardDelete(Integer id, Board board, Model model) {

        boardService.boardDelete(id);

        model.addAttribute("message", "글이 삭제되었습니다.");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }*/

    @PostMapping("/selectOne")
    public ResponseEntity<CMRespDto<?>> selectOneBoard(@RequestBody int board_index){

        boardService.selectOneBoard(board_index);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered",true));

    }


}
