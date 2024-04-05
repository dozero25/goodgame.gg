package fourjo.idle.goodgame.gg.web.service;
import fourjo.idle.goodgame.gg.repository.BoardRepository;
import fourjo.idle.goodgame.gg.web.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BoardService {


    @Autowired
    private BoardRepository boardRepository; // mapper대신 사용함, 왜?


    // public int insertOK(BoardDTO dto) {
    //    return boardRepository.insertBoard(dto);
    //}


    public BoardDTO selectOneBoard(int board_index) {
        return boardRepository.selectOneBoard(board_index);
    }

    public List<BoardDTO> selectAllBoard(BoardDTO dto) {
        return boardRepository.selectAllBoard();
    }

   public List<BoardDTO> searchBoard_title(String searchKey, String searchWord) {
       return boardRepository.searchBoard_title(searchKey, searchWord);

    };

    //public List<BoardDTO> selectAll(int cpage, int pageblock);
    //public int getTotalRows();


}
