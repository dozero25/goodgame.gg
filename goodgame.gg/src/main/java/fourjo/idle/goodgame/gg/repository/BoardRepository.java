package fourjo.idle.goodgame.gg.repository;

import fourjo.idle.goodgame.gg.web.dto.board.BoardDTO;
import fourjo.idle.goodgame.gg.web.dto.board.BoardLikeDTO;
import fourjo.idle.goodgame.gg.web.dto.board.BoardReplyDTO;
import fourjo.idle.goodgame.gg.web.dto.board.BoardSearchDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface BoardRepository {

    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ게시글 작성ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    public int boardInsert(BoardDTO dto);
    public int registerBoardImg(BoardDTO boardDTO);



    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ게시글 수정ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    public int boardUpdateOKByBoardIndex(BoardDTO dto);
    public int boardFileUpdate(BoardDTO boardDTO);
    public BoardDTO loadUpdatePageByBoardIndex(int boardIndex);



    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ게시물 삭제ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    public int boardDeleteByBoardIndex(int boardIndex);




    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 상세보기페이지 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    public BoardDTO boardSelectOneByBoardIndex(int boardIndex);




    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ게시물 검색 및 목록ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    public List<BoardDTO> boardSearchAllBySubjectAndUserIndexAndContent(BoardSearchDTO dto);
    public int boardTotalCount(BoardSearchDTO dto) ;


    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ좋아요 추천ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    public int likeByBoardIndexAndUserIndex(int boardIndex, int userIndex);
    public int likeCountByBoardIndex(int boardIndex);
    public void likeAdd(int boardIndex, int userIndex);
    public void likeUpdate(int boardIndex);
    public void likeCancel(int boardIndex, int userIndex);
    

    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ조회수ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    public int boardViewCount(BoardDTO dto);




    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ댓글 작성ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    public int boardReplyInsertByReplyGroup(BoardReplyDTO dto);
    public int boardReplyInsertByReplySequence(BoardReplyDTO dto);





    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ댓글 수정ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    public BoardReplyDTO boardReplyUpdateSelectOneByReplyIndex(int replyIndex);
    public int boardReplyUpdateByReplyIndex(BoardReplyDTO dto);





    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ댓글 삭제ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    public int boardReplyDeleteByReplyIndex(int replyIndex);
    public int boardReplyDeleteByReplyIndexAndBoardIndex(int replyIndex, int boardIndex);

    


    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ댓글 전체 목록ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    public List<BoardReplyDTO> boardReplySelectAll(int boardIndex);



    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ댓글 작성 보조ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    public int boardReplyGroupCount(int boardIndex);
    public int boardReplySequenceCount(int boardIndex, int replyGroup);

    
    
    
    


}
