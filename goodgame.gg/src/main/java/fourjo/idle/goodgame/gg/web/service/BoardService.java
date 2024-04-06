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


    public int insertBoard(BoardDTO dto) {
      return boardRepository.insertBoard(dto);
   }

    public int updateBoard(BoardDTO dto) {
        return boardRepository.updateBoard(dto);
    }

    public int deleteBoard(int board_index) {
        return boardRepository.deleteBoard(board_index);
    }


    public BoardDTO selectOneBoard(int board_index) {
        return boardRepository.selectOneBoard(board_index);
    }

    public List<BoardDTO> selectAllBoard() {
        return boardRepository.selectAllBoard();
    }

   public List<BoardDTO> searchBoard(String searchKey, String searchValue) {
       return boardRepository.searchBoard(searchKey, searchValue);

    };

    //public List<BoardDTO> selectAll(int cpage, int pageblock);
    //public int getTotalRows();


}
