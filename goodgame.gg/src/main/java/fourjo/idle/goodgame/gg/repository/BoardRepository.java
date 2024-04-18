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
    public BoardDTO updateBoardByBoardIndex(int boardIndex);
    public int boardDeleteByBoardIndex(int boardIndex);
    public BoardDTO boardSelectOneByBoardIndex(int boardIndex);

    public List<BoardDTO> boardSearchAllBySubjectAndUserIndexAndContent(BoardSearchDTO dto);

    public int boardTotalCount(BoardSearchDTO dto) ;



    /*  public boolean isLiked(BoardLikeDTO dto); // 특정 유저가 좋아요 했는지 안했는지?*/
    public int searchLikeOrBadByBoardIndexAndUserIndex(int boardIndex, int userIndex);

    public int searchLikeCountByBoardIndex(int boardIndex);
    public void likeAdd(int boardIndex, int userIndex);
    public void likeUpdate(int boardIndex);
    public void badAdd(int boardIndex, int userIndex);
    public void likeBadCancel(int boardIndex, int userIndex);

    public int boardViewCount(BoardDTO dto);

   /* public int boardReplyInsert(BoardReplyDTO dto);*/


    public int boardReplyUpdateByReplyIndex(BoardReplyDTO dto);

    public int boardReplyDeleteByReplyIndex(int replyIndex);

    List<BoardReplyDTO> boardReplySelectAll(int boardIndex);

    public int boardReplyGroupCount(int boardIndex);
    public int boardReplySequenceCount(int boardIndex);

    public void boardReplyInsertByReplyGroup(BoardReplyDTO dto);



}
