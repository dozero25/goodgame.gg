package fourjo.idle.goodgame.gg.web.service;
import fourjo.idle.goodgame.gg.repository.BoardRepository;
import fourjo.idle.goodgame.gg.web.dto.board.BoardDTO;

import fourjo.idle.goodgame.gg.web.dto.board.BoardLikeDTO;
import fourjo.idle.goodgame.gg.web.dto.board.BoardReplyDTO;
import fourjo.idle.goodgame.gg.web.dto.board.BoardSearchDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;


@Service
@Slf4j
public class BoardService {



    @Autowired
    private BoardRepository boardRepository; // mapper대신 사용함, 왜?

    @Value("${file.path}")
    private String filePath;

    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ게시글 작성ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    public int boardInsert(BoardDTO dto) {
      return boardRepository.boardInsert(dto);
   } //그냥 인서트

    public int registerBoardImg(BoardDTO boardDTO){
        log.info("boardDTO"+boardDTO);

        log.info("filePath"+filePath);
        String boardUploadName = boardDTO.getFile().getOriginalFilename(); // insert할때도 파일이름, 사이즈는 board에 들어감
        log.info("getOriginalFilename:"+boardUploadName);

        long boardUploadSize = boardDTO.getFile().getSize();
        log.info("boardUploadSize:"+boardUploadSize);
        String boardUploadLocation = UUID.randomUUID().toString().replaceAll("-", "") + boardUploadName;
        log.info("boardUploadLocation:"+boardUploadLocation);



        File uploadFile = new File(filePath, boardUploadLocation);

        try {
            boardDTO.getFile().transferTo(uploadFile);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        boardDTO.setBoardUploadName(boardUploadName);
        boardDTO.setBoardUploadSize(boardUploadSize);
        boardDTO.setBoardUploadLocation(boardUploadLocation);

        return boardRepository.registerBoardImg(boardDTO);

    } //이미지 업로드



    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ게시글 수정ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    public int boardUpdateOKByBoardIndex(BoardDTO boardDTO) {

        String boardUploadName = boardDTO.getFile().getOriginalFilename(); // insert할때도 파일이름, 사이즈는 board에 들어감
        log.info("getOriginalFilename:"+boardUploadName);

        long boardUploadSize = boardDTO.getFile().getSize();
        log.info("boardUploadSize:"+boardUploadSize);
        String boardUploadLocation = UUID.randomUUID().toString().replaceAll("-", "") + boardUploadName;
        log.info("boardUploadLocation:"+boardUploadLocation);



        File uploadFile = new File(filePath, boardUploadLocation);

        try {
            boardDTO.getFile().transferTo(uploadFile);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        boardDTO.setBoardUploadName(boardUploadName);
        boardDTO.setBoardUploadSize(boardUploadSize);
        boardDTO.setBoardUploadLocation(boardUploadLocation);


        return boardRepository.boardUpdateOKByBoardIndex(boardDTO);
    }

    public BoardDTO loadUpdatePageByBoardIndex(int boardIndex) {
        return boardRepository.loadUpdatePageByBoardIndex(boardIndex);
    }



    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ게시물 삭제ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    public int boardDeleteByBoardIndex(int boardIndex) {
        return boardRepository.boardDeleteByBoardIndex(boardIndex);
    }


    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 상세보기페이지 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    public BoardDTO boardSelectOneByBoardIndex(int boardIndex) {
        return boardRepository.boardSelectOneByBoardIndex(boardIndex);
    }


    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ게시물 검색 및 목록ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    public List<BoardDTO> boardSearchAllBySubjectAndUserIndexAndContent(BoardSearchDTO dto) {

       dto.pageIndex();

       return boardRepository.boardSearchAllBySubjectAndUserIndexAndContent(dto);
    }

    public int boardTotalCount(BoardSearchDTO dto) {
        return boardRepository.boardTotalCount(dto);
    }


    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ좋아요 추천ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    public int likeByBoardIndexAndUserIndex(int boardIndex, int userIndex){
        return boardRepository.likeByBoardIndexAndUserIndex(boardIndex, userIndex);
    }

    public int likeCountByBoardIndex(int boardIndex) {
        return boardRepository.likeCountByBoardIndex(boardIndex);
    }

    public void likeAdd(int boardIndex, int userIndex) {
        log.info("likeAdd..."+boardIndex);
        boardRepository.likeAdd(boardIndex, userIndex);
        boardRepository.likeUpdate(boardIndex);
    };

    public void likeBadCancel(int boardIndex, int userIndex) {
        boardRepository.likeBadCancel(boardIndex,userIndex);
        boardRepository.likeUpdate(boardIndex);
    };

//    public void badAdd(int boardIndex, int userIndex) {
//        boardRepository.badAdd(boardIndex,userIndex);
//
//    };


    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ조회수ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    public int boardViewCount(BoardDTO dto) {
        return boardRepository.boardViewCount(dto);
    }


    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ댓글 작성ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    public void boardReplyInsertByReplyGroup(BoardReplyDTO dto) { //이름바꾸기
        dto.setReplyGroup(boardRepository.boardReplyGroupCount(dto.getBoardIndex()));

        dto.setReplyGroup(dto.getReplyGroup());
        dto.setReplySequence(dto.getReplySequence()+1);

        boardRepository.boardReplyInsertByReplyGroup(dto);

    }

    public void boardReplyInsertBySequence(BoardReplyDTO dto) {
        dto.setReplySequence(boardRepository.boardReplySequenceCount(dto.getBoardIndex(), dto.getReplyGroup()));
        dto.setReplyGroup(dto.getReplyGroup());
        dto.setReplySequence(dto.getReplySequence());

        boardRepository.boardReplyInsertByReplyGroup(dto);


    }



    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ댓글 수정ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    public BoardReplyDTO boardReplyUpdateSelectOneByReplyIndex(int replyIndex) {
        return boardRepository.boardReplyUpdateSelectOneByReplyIndex(replyIndex);
    }

    public int boardReplyUpdateByReplyIndex(BoardReplyDTO dto) {
        return boardRepository.boardReplyUpdateByReplyIndex(dto);
    }


    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ댓글 삭제ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    public int boardReplyDeleteByReplyIndex(int replyIndex) {
        return boardRepository.boardReplyDeleteByReplyIndex(replyIndex);
    }

    public int boardReplyDeleteByReplyIndexAndBoardIndex(int replyIndex, int boardIndex) {
        return boardRepository.boardReplyDeleteByReplyIndexAndBoardIndex(replyIndex, boardIndex);
    }


    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ댓글 전체 목록ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    public List<BoardReplyDTO> boardReplySelectAll(int boardIndex) {
        return boardRepository.boardReplySelectAll(boardIndex);
    }

 


}
