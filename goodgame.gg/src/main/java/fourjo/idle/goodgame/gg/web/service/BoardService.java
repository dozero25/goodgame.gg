package fourjo.idle.goodgame.gg.web.service;
import fourjo.idle.goodgame.gg.repository.BoardRepository;
import fourjo.idle.goodgame.gg.web.dto.board.BoardDTO;

import fourjo.idle.goodgame.gg.web.dto.board.BoardLikeDTO;
import fourjo.idle.goodgame.gg.web.dto.board.BoardReplyDTO;
import fourjo.idle.goodgame.gg.web.dto.board.BoardSearchDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Slf4j
public class BoardService {


    @Autowired
    private BoardRepository boardRepository; // mapper대신 사용함, 왜?


    public int boardInsert(BoardDTO dto) {
      return boardRepository.boardInsert(dto);
   }

    public int boardUpdateByBoardIndex(BoardDTO dto) {
        return boardRepository.boardUpdateByBoardIndex(dto);
    }

    public BoardDTO updateBoardByBoardIndex(int boardIndex) {
        return boardRepository.updateBoardByBoardIndex(boardIndex);
    }

    public int boardDeleteByBoardIndex(int boardIndex) {
        return boardRepository.boardDeleteByBoardIndex(boardIndex);
    }


    public BoardDTO boardSelectOneByBoardIndex(int boardIndex) {
        return boardRepository.boardSelectOneByBoardIndex(boardIndex);
    }


   public List<BoardDTO> boardSearchAllBySubjectAndUserIndexAndContent(BoardSearchDTO dto) {

       dto.pageIndex();

       return boardRepository.boardSearchAllBySubjectAndUserIndexAndContent(dto);
    };//완.


    public int boardTotalCount(BoardSearchDTO dto) {
        return boardRepository.boardTotalCount(dto);
    }



    public int searchLikeOrBadByBoardIndexAndUserIndex(int boardIndex, int userIndex){
        return boardRepository.searchLikeOrBadByBoardIndexAndUserIndex(boardIndex, userIndex);
    }

    public int searchLikeCountByBoardIndex(int boardIndex) {
        return boardRepository.searchLikeCountByBoardIndex(boardIndex);
    }


    public void likeAdd(int boardIndex, int userIndex) {
        log.info("likeAdd..."+boardIndex);
        boardRepository.likeAdd(boardIndex, userIndex);
        boardRepository.likeUpdate(boardIndex);
    };

    public void badAdd(int boardIndex, int userIndex) {
        boardRepository.badAdd(boardIndex,userIndex);

    };

    public void likeBadCancel(int boardIndex, int userIndex) {
        boardRepository.likeBadCancel(boardIndex,userIndex);
        boardRepository.likeUpdate(boardIndex);
    };



    public int boardViewCount(BoardDTO dto) {
        return boardRepository.boardViewCount(dto);
    }

    /*public int boardReplyInsert(BoardReplyDTO dto) {
       // 로직 다시 생각해봐야할듯
    *//*    BoardReplyDTO.setReplyGroup(boardRepository.getReplyCount(BoardReplyDTO.getBoardIndex()));
        BoardReplyDTO.setReplyGroup(BoardReplyDTO.getReplyGroup());
        BoardReplyDTO.setReplySequence(BoardReplyDTO.getReplySequence() + 1);
        return boardRepository.boardReplyInsert(dto);

*//*
        return boardRepository.boardReplyInsert(dto);
    } // 지우려했는데 밑에 로직이 틀려짐..*/

    public void boardReplyInsertByReplyGroup(BoardReplyDTO dto) { //이름바꾸기
        dto.setReplyGroup(boardRepository.boardReplyGroupCount(dto.getBoardIndex()));

        dto.setReplySequence(dto.getReplySequence() + 1); //

        boardRepository.boardReplyInsertByReplyGroup(dto);

    }

    public void boardReplyInsertBySequence(BoardReplyDTO dto) {

        dto.setReplyGroup(dto.getReplyGroup());
        dto.setReplySequence(boardRepository.boardReplySequenceCount(dto.getBoardIndex()));

        boardRepository.boardReplyInsertByReplyGroup(dto);

    }

    public int boardReplyUpdateByReplyIndex(BoardReplyDTO dto) {
        return boardRepository.boardReplyUpdateByReplyIndex(dto);
    }

    public int boardReplyDeleteByReplyIndex(int replyIndex) {
        return boardRepository.boardReplyDeleteByReplyIndex(replyIndex);
    }

    public List<BoardReplyDTO> boardReplySelectAll(int boardIndex) {
        return boardRepository.boardReplySelectAll(boardIndex);
    }



}
