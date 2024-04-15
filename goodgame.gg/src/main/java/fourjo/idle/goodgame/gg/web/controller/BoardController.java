package fourjo.idle.goodgame.gg.web.controller;

import fourjo.idle.goodgame.gg.web.dto.board.BoardDTO;
import fourjo.idle.goodgame.gg.web.dto.board.BoardSearchDTO;
import fourjo.idle.goodgame.gg.web.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {



    @GetMapping("")
    public String board(){return "board/main";}

    @GetMapping("/selectOne")
    public String boardList(){return "board/selectOne";}

    @GetMapping("/insert")
    public String boardInsert(){return "board/insert";}

   /* @GetMapping("/fileInsert")
    public String fileInsert(){return "board/insert";}*/

    @GetMapping("/update")
    public String boardUpdate(){return "board/update";}



//    @GetMapping("/board") //메인페이지
//    public String board() {return "board/main";}
//    @Autowired
//    private BoardService service;
//    @GetMapping("/board/search") // 글목록
//    public String boardList(Model model, BoardSearchDTO dto) {
//        List<BoardDTO> boardList = service.boardSearchAllBySubjectAndUserIndexAndContent(dto);
//        model.addAttribute("boardList", boardList);
//        return "board/boardSearch";
//    }
//
//    @PostMapping("/board/insert") // 글삽입
//    public String boardInsert(Model model, BoardSearchDTO dto) {
//        List<BoardDTO> boardList = service.boardSearchAllBySubjectAndUserIndexAndContent(dto);
//        model.addAttribute("boardList", boardList);
//        return "board/boardIU";
//    }
//    @PostMapping("/board/update") //글수정
//    public String boardUpdate(Model model, BoardSearchDTO dto) {
//        List<BoardDTO> boardList = service.boardSearchAllBySubjectAndUserIndexAndContent(dto);
//        model.addAttribute("boardList", boardList);
//        return "board/boardIU";
//    }







}
