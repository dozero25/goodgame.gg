package fourjo.idle.goodgame.gg.repository;

import fourjo.idle.goodgame.gg.web.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BoardRepository {
    public int insertBoard(BoardDTO dto);
    public int updateBoard(BoardDTO dto);
    public int deleteBoard(int board_index);
    public BoardDTO selectOneBoard(int board_index);
    public List<BoardDTO> selectAllBoard();
    public List<BoardDTO> searchBoard(String searchKey, String searchValue);

}
