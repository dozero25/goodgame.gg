package fourjo.idle.goodgame.gg.web.service;
import fourjo.idle.goodgame.gg.repository.BoardRepository;
import fourjo.idle.goodgame.gg.web.dto.board.BoardDTO;

import fourjo.idle.goodgame.gg.web.dto.board.BoardLikeDTO;
import fourjo.idle.goodgame.gg.web.dto.board.BoardReplyDTO;
import fourjo.idle.goodgame.gg.web.dto.board.BoardSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BoardService {


    @Autowired
    private BoardRepository boardRepository; // mapper대신 사용함, 왜?


    public int boardInsert(BoardDTO dto) {
      return boardRepository.boardInsert(dto);
   }

    public int boardUpdateByBoardIndex(BoardDTO dto) {
        return boardRepository.boardUpdateByBoardIndex(dto);
    }

    public int boardDeleteByBoardIndex(int boardIndex) {
        return boardRepository.boardDeleteByBoardIndex(boardIndex);
    }


    public BoardDTO boardSelectOneByBoardIndex(int boardIndex) {
        return boardRepository.boardSelectOneByBoardIndex(boardIndex);
    }


   public List<BoardDTO> boardSearchAllBySubjectAndUserIndexAndContent(BoardSearchDTO dto) {
       return boardRepository.boardSearchAllBySubjectAndUserIndexAndContent(dto);
    };



    public void likeAdd(BoardLikeDTO dto) {
        boardRepository.likeAdd(dto);
    };

    public void badAdd(BoardLikeDTO dto) {
        boardRepository.badAdd(dto);
    };

    public void likeBadCancel(BoardLikeDTO dto) {
        boardRepository.likeBadCancel(dto);
    };


    public int boardViewCount(BoardDTO dto) {
        return boardRepository.boardViewCount(dto);
    }

    public int boardReplyInsert(BoardReplyDTO dto) {
       // 로직 다시 생각해봐야할듯
    /*    BoardReplyDTO.setReplyGroup(boardRepository.getReplyCount(BoardReplyDTO.getBoardIndex()));
        BoardReplyDTO.setReplyGroup(BoardReplyDTO.getReplyGroup());
        BoardReplyDTO.setReplySequence(BoardReplyDTO.getReplySequence() + 1);
        return boardRepository.boardReplyInsert(dto);




*/


        return boardRepository.boardReplyInsert(dto);
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
