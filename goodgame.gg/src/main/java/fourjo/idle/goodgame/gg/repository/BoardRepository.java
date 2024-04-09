package fourjo.idle.goodgame.gg.repository;

import fourjo.idle.goodgame.gg.web.dto.board.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardRepository {

    public int insertBoard(BoardDTO boardDTO);

    public int updateBoard(BoardDTO boardDTO);

    public int deleteBoard(BoardDTO boardDTO);

    public List<BoardDTO> selectAllBoard(BoardDTO boardDTO);

    public BoardDTO selectOneBoard(BoardDTO boardDTO);

    public BoardDTO searchListBoard(int board_index);

    public List<BoardDTO> searchListID(Map<String, String> map);
    public List<BoardDTO> searchListNAME(Map<String, String> map);

    public List<BoardDTO> selectAllPageBlock(Map<String, Integer> map);

    public List<BoardDTO> searchListID_PAGE(Map<String, Object> map);
    public List<BoardDTO> searchListNAME_PAGE(Map<String, Object> map);

    public int getTotalRows();

    public int search_total_rows_id(Map<String, String> map);

    public int search_total_rows_name(Map<String, String> map);



}
