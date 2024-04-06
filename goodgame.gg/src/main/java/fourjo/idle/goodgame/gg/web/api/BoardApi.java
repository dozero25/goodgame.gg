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

    @PostMapping("/delete")
    public ResponseEntity<CMRespDto<?>> deleteBoard(@RequestBody BoardDTO boardDTO,Model model){

        boardService.deleteBoard(boardDTO);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered",true));

    }

    /*@GetMapping("/delete")
    public String boardDelete(@RequestParam Integer id, Model model) {
        boardService.deleteBoard(boardDTO);
        model.addAttribute("message", "글이 삭제되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "message";
    }*/


    @GetMapping("/selectOne")
    public ResponseEntity<CMRespDto<?>> selectOneBoard(@RequestBody int board_index){

        boardService.selectOneBoard(board_index);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered",true));

    }

    @GetMapping("/selectAll")
    public ResponseEntity<CMRespDto<?>> selectAllBoard(@RequestBody int boardDTO){

        boardService.selectAllBoard(boardDTO);

        /*int total_rows = service.getTotalRows();
		log.info("total_rows:" + total_rows);

		int totalPageCount = 1;
		if (total_rows / pageBlock == 0) {
			totalPageCount = 1;
		} else if (total_rows % pageBlock == 0) {
			totalPageCount = total_rows / pageBlock;
		} else {
			totalPageCount = total_rows / pageBlock + 1;
		}
		// 페이지 링크 몇개?
		log.info("totalPageCount:" + totalPageCount);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalPageCount", 10);//테스트용

		model.addAttribute("content", "thymeleaf/member/th_selectAll");
		model.addAttribute("title", "회원목록");
	*/
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered",true));

    }

    @PostMapping("/searchList")
    public ResponseEntity<CMRespDto<?>> searchListBoard(@RequestBody int board_index){

        boardService.searchListBoard(board_index);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered",true));

    }


}
