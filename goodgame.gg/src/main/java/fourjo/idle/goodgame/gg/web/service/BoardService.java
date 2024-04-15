package fourjo.idle.goodgame.gg.web.service;

import fourjo.idle.goodgame.gg.repository.BoardRepository;
import fourjo.idle.goodgame.gg.web.dto.board.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public int insertBoard(BoardDTO boardDTO) {

        int result = boardRepository.insertBoard(boardDTO);
        if(result == 1){
            return result;
        }
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

        boardRepository.boardDelete();

        model.addAttribute("message", "글이 삭제되었습니다.");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }*/

    public List<BoardDTO> selectAllBoard(BoardDTO boardDTO) {

        return boardRepository.selectAllBoard(boardDTO);
    }
    public List<BoardDTO> selectAllPageBlock(int cpage,int pageBlock) {
        int startRow = (cpage - 1) * pageBlock + 1;

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("startRow", startRow-1);
        map.put("pageBlock", pageBlock);

        return boardRepository.selectAllPageBlock(map);
    }

    /*public int getTotalRows() {
        return boardRepository.getTotalRows();
    }

    public BoardDTO selectOneBoard(BoardDTO boardDTO) {

        return boardRepository.selectOneBoard(boardDTO);
    }*/

    /*public BoardDTO searchListBoard(int boardDTO) {

        return boardRepository.selectOneBoard();
    }*/

    /* =================================================*/

    /*public void save(BoardDTO boardDTO) {
        boardRepository.save(boardDTO);
        //boardRepository의 save메서드를 호출해서 boardDTO를 넘긴다.
    }

    public List<BoardDTO> findAll() {
        return boardRepository.findAll();
    }*/

}
