package fourjo.idle.goodgame.gg.web.service;

import fourjo.idle.goodgame.gg.repository.BoardRepository;
import fourjo.idle.goodgame.gg.web.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public int insertBoard(BoardDTO boardDTO) {
        return boardRepository.insertBoard(boardDTO);
    }

    public int updateBoard(BoardDTO boardDTO) {
        return boardRepository.updateBoard(boardDTO);
    }

    public int deleteBoard(BoardDTO boardDTO) {
        return boardRepository.deleteBoard(boardDTO);
    }

    /*@GetMapping("/delete")
    public String boardDelete(Integer id, Board board, Model model) {

        boardService.boardDelete(id);

        model.addAttribute("message", "글이 삭제되었습니다.");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }*/

    public BoardDTO selectOneBoard(int boardDTO) {
        return boardRepository.selectOneBoard(boardDTO);
    }

    public BoardDTO selectAllBoard(int boardDTO) {
        return boardRepository.selectOneBoard(boardDTO);
    }

    public BoardDTO searchListBoard(int boardDTO) {
        return boardRepository.selectOneBoard(boardDTO);
    }


}
