package fourjo.idle.goodgame.gg.repository;

import fourjo.idle.goodgame.gg.web.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BoardRepository {

    //public int insertBoard(BoardDTO dto);



    public BoardDTO selectOneBoard(int board_index);


    public List<BoardDTO> selectAllBoard();


    public List<BoardDTO> searchBoard_title(String searchKey, String searchWord);
    //public List<BoardDTO> searchBoard_writer();
    //public List<BoardDTO> searchBoard_content();
}
