package fourjo.idle.goodgame.gg.repository;

import fourjo.idle.goodgame.gg.web.dto.board.BoardDTO;
import fourjo.idle.goodgame.gg.web.dto.board.BoardLikeDTO;
import fourjo.idle.goodgame.gg.web.dto.board.BoardReplyDTO;
import fourjo.idle.goodgame.gg.web.dto.board.BoardSearchDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BoardRepository {
    public int boardInsert(BoardDTO dto);
    public int boardUpdateByBoardIndex(BoardDTO dto);
    public int boardDeleteByBoardIndex(int boardIndex);
    public BoardDTO boardSelectOneByBoardIndex(int boardIndex);

    public List<BoardDTO> boardSearchAllBySubjectAndUserIndexAndContent(BoardSearchDTO dto);

  /*  public boolean isLiked(BoardLikeDTO dto); // 특정 유저가 좋아요 했는지 안했는지?*/
    public void likeAdd(BoardLikeDTO dto);
    public void badAdd(BoardLikeDTO dto);
    public void likeBadCancel(BoardLikeDTO dto);

    public int boardViewCount(BoardDTO dto);

    public int boardReplyInsert(BoardReplyDTO dto);


    public int boardReplyUpdateByReplyIndex(BoardReplyDTO dto);

    public int boardReplyDeleteByReplyIndex(int replyIndex);

    List<BoardReplyDTO> boardReplySelectAll(int boardIndex);
}
