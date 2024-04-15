package fourjo.idle.goodgame.gg.web.api;

import fourjo.idle.goodgame.gg.web.dto.board.BoardDTO;
import fourjo.idle.goodgame.gg.web.dto.CMRespDto;
import fourjo.idle.goodgame.gg.web.service.BoardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    }


    @PostMapping("/selectOne")
    public ResponseEntity<CMRespDto<?>> selectOneBoard(@RequestBody BoardDTO boardDTO){

        boardService.selectOneBoard(boardDTO);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered",true));

    }*/

    @GetMapping("/selectAll")
    public ResponseEntity<CMRespDto<?>> selectAllBoard(@RequestBody BoardDTO boardDTO){

        boardService.selectAllBoard(boardDTO);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered",true));

    }

   /* @GetMapping("/selectAllPageBlock")
    public ResponseEntity<CMRespDto<?>> selectAllPageBlock(@RequestBody int board_index,
                                                           @RequestParam(defaultValue = "1") int cpage,
                                                           @RequestParam(defaultValue = "5") int pageBlock,
                                                           Model model) {
        boardService.selectAllPageBlock(cpage, pageBlock);

        int total_rows = boardService.getTotalRows();

        int totalPageCount = 1;
        if (total_rows / pageBlock == 0) {
            totalPageCount = 1;
        } else if (total_rows % pageBlock == 0) {
            totalPageCount = total_rows / pageBlock;
        } else {
            totalPageCount = total_rows / pageBlock + 1;
        }

        model.addAttribute("totalPageCount", totalPageCount);

        model.addAttribute("content", "selectAll");
        model.addAttribute("title", "회원목록");
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

    @PostMapping("/searchList")
    public ResponseEntity<CMRespDto<?>> searchListBoard(@RequestBody int board_index){

        boardService.searchListBoard(board_index);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered",true));

    }

    @GetMapping("/Paging")
    public ResponseEntity<CMRespDto<?>> Paging(@RequestBody int board_index, Model model){

        boardService.Paging(board_index);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered",true));

    }*/

    //================================================================

    /*//시작페이지
    @GetMapping("/")
    public String index() {
        System.out.println("Home.index");
        return "index";
    }
    //게시글 작성화면
    @GetMapping("/save")//save: 주소
    public String save() {//save: 자바 메서드 이름
        return "save";//save: 리턴할 화면의 이름
    }
    //게시글 작성 데이터 전송
    @PostMapping("/save")//위와 메서드 이름은 같지만 파라미터가 다르기 때문에 중복 가능
    //save.html에서 넘어온 데이터를 여기서 받는다.
    public String save(BoardDTO boardDTO) {//DTO로 객체를 받겠다.
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "index";
    }
    //게시글 목록 출력
    @GetMapping("/list")
    public String findAll(Model model) {//DB에서 조회한 데이터를 화면으로 가져가야하므로 model객체 사용
        //model : 데이터를 화면으로 가져갈 수 있도록 전달해주는 객체 / 가져갈 게 있다 하면 model 사용
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "list";
    }*/

}
