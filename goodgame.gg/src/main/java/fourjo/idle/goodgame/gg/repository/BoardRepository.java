package fourjo.idle.goodgame.gg.repository;

import fourjo.idle.goodgame.gg.web.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardRepository {

    public int insertBoard(BoardDTO boardDTO);

    public int updateBoard(BoardDTO boardDTO);

    public int deleteBoard(BoardDTO boardDTO);

    public BoardDTO selectOneBoard(int board_index);
}
