package fourjo.idle.goodgame.gg.web.api;

import fourjo.idle.goodgame.gg.web.dto.*;
import fourjo.idle.goodgame.gg.web.dto.board.BoardDTO;
import fourjo.idle.goodgame.gg.web.dto.board.BoardLikeDTO;
import fourjo.idle.goodgame.gg.web.dto.board.BoardReplyDTO;
import fourjo.idle.goodgame.gg.web.dto.board.BoardSearchDTO;
import fourjo.idle.goodgame.gg.web.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board") // js로 보낼때 경로
@Tag(name ="Board Api", description = "Board Api 입니다. Board에 관한 api가 있습니다.")
public class BoardApi {

    @Autowired
    private BoardService boardService;

    //@Operation(summary = "게시글 등록", description = "조건에 맞으면 게시글 등록됩니다.")
    @PostMapping("/insert")
    public ResponseEntity<CMRespDto<?>> insertBoard (BoardDTO dto){
        boardService.boardInsert(dto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

    @PostMapping("/update")
    //@Operation(summary = "게시글 수정", description = "조건에 맞으면 게시글 수정됩니다.")
    public ResponseEntity<CMRespDto<?>> updateBoard (BoardDTO dto){
        boardService.boardUpdateByBoardIndex(dto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

    @PostMapping("/delete")
    //@Operation(summary = "게시글 삭제", description = "해당 boardIndex 조건에 맞으면 게시글 삭제됩니다.")
    public ResponseEntity<CMRespDto<?>> deleteBoard (int boardIndex){
        boardService.boardDeleteByBoardIndex(boardIndex);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }
    @GetMapping("/selectOne")
    //@Operation(summary = "게시글 상세보기", description = "해당 boardIndex 조건에 맞는 게시글을 상세 보기합니다.")
    public ResponseEntity<CMRespDto<?>> selectOneBoard (int boardIndex){
        BoardDTO dto2 = boardService.boardSelectOneByBoardIndex(boardIndex);
        log.info(dto2.toString());
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", dto2));
    }


    @GetMapping("/search")
    //@Operation(summary = "게시글 검색", description = "게시글 전체목록, 제목, 닉네임, 내용, 제목+내용에 해당하는 검색목록을 출력합니다.")
    public ResponseEntity<CMRespDto<?>> searchBoard (BoardSearchDTO dto){
        //리스트 빈거
        List<BoardDTO> dtos = new ArrayList<BoardDTO>();

        dtos = boardService.boardSearchAllBySubjectAndUserIndexAndContent(dto);

        log.info(String.valueOf(dtos.size())); //  모델에 담아야지 > html > 가공(Script)
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", dtos)); //true면 출력이 안됨
    }

    @PostMapping("/like")
    //@Operation(summary = "게시글 좋아요", description = "게시글 좋아요를 완료합니다.")
    public ResponseEntity<CMRespDto<?>> likeAdd (BoardLikeDTO dto){
        boardService.likeAdd(dto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }
    //에러페이지 null이 아니면 오류 페이지를 출력한다.


    @PostMapping("/bad")
    //@Operation(summary = "게시글 싫어요", description = "게시글 싫어요를 완료하거나 취소합니다.")
    public ResponseEntity<CMRespDto<?>> badAdd (BoardLikeDTO dto){
        boardService.badAdd(dto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

    @PostMapping("/cancel")
    //@Operation(summary = "게시글 ", description = "게시글 싫어요를 완료하거나 취소합니다.")
    public ResponseEntity<CMRespDto<?>> likeBadCancel (BoardLikeDTO dto){
        boardService.likeBadCancel(dto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }


    @GetMapping("/visit")
    //@Operation(summary = "게시글 조회수", description = "특정 게시물 인덱스를 입력하면(상세보기) 해당 게시글 조회수 1증가합니다.")
    public ResponseEntity<CMRespDto<?>> boardViewCount (BoardDTO dto){
        boardService.boardViewCount(dto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", dto));
    }


    @PostMapping("/selectOne/reply/insert")
    //@Operation(summary = "게시글 댓글 등록", description = "게시글 댓글이 등록됩니다.")
    public ResponseEntity<CMRespDto<?>> boardReplyInsert(BoardReplyDTO dto){

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", boardService.boardReplyInsert(dto)));
    }


    @PostMapping("/selectOne/reply/update")
    //@Operation(summary = "게시글 댓글", description = "게시글 댓글")
    public ResponseEntity<CMRespDto<?>> boardReplyUpdateByReplyIndex(BoardReplyDTO dto){
        boardService.boardReplyUpdateByReplyIndex(dto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

    @PostMapping("/selectOne/reply/delete")
    //@Operation(summary = "게시글 댓글", description = "게시글 댓글")
    public ResponseEntity<CMRespDto<?>> boardReplyDeleteByReplyIndex(int replyIndex){
        boardService.boardReplyDeleteByReplyIndex(replyIndex);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }
    @GetMapping("/selectOne/reply/selectAll")
    //@Operation(summary = "게시글목록",description = "게시글 상세보기를 하면 조회수가 1씩 증가합니다.")
    public ResponseEntity<CMRespDto<?>> boardReplySelectAll (int boardIndex){

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", boardService.boardReplySelectAll(boardIndex)));
    }
//js에서
   /* @GetMapping("/board/hot")
    //@Operation(summary = "인기 게시물", description = "게시글 좋아요 수가 일정 기준에 충족하는 게시물만 출력합니다.")
    public ResponseEntity<CMRespDto<?>> boardHot (BoardLikeDTO dto){
        boardService.boardHot(dto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }*/

   /* @GetMapping("/board/worst")
    //@Operation(summary = " 주의 게시물", description = "게시글 싫어요 수가 일정 기준을 넘어가는 게시물은 관리자에서 따로 관리합니다.")
    public ResponseEntity<CMRespDto<?>> boardWorst (BoardLikeDTO dto){
        boardService.boardWorst(dto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }*/


}
