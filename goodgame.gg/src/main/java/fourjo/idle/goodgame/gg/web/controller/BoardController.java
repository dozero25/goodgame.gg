package fourjo.idle.goodgame.gg.web.controller;

import fourjo.idle.goodgame.gg.web.dto.board.BoardDTO;
import fourjo.idle.goodgame.gg.web.dto.board.BoardSearchDTO;
import fourjo.idle.goodgame.gg.web.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

   @GetMapping("/update")
    public String boardUpdate(){return "board/update";}
   







}
