package fourjo.idle.goodgame.gg.web.api;

import fourjo.idle.goodgame.gg.security.PrincipalDetails;
import fourjo.idle.goodgame.gg.web.dto.*;
import fourjo.idle.goodgame.gg.web.dto.board.BoardDTO;

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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board") // js로 보낼때 경로
@Tag(name ="Board Api", description = "Board Api 입니다. Board에 관한 api가 있습니다.")
public class BoardApi {

    @Autowired
    private BoardService boardService;


    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ게시글 작성ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    @Operation(summary = "게시글 등록", description = "조건에 맞으면 게시글 등록됩니다.")
    @PostMapping("/insert")
    public ResponseEntity<CMRespDto<?>> boardInsert (@RequestBody BoardDTO dto){
        boardService.boardInsert(dto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));

    }

    @PostMapping("/fileInsert")
    public ResponseEntity<CMRespDto<?>> boardFileInsert (BoardDTO boardDTO) {

        boardService.registerBoardImg(boardDTO);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }






    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ게시글 수정ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    @PostMapping("/update")
    //@Operation(summary = "게시글 수정", description = "조건에 맞으면 게시글 수정됩니다.")
    public ResponseEntity<CMRespDto<?>> boardUpdateOKByBoardIndex (@RequestBody BoardDTO dto){
        log.info("수정 {}",dto);

        boardService.boardUpdateOKByBoardIndex(dto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true ));
    }
    

    
    //update file upload용 API 예정
    @PostMapping("/fileUpdate")
    public ResponseEntity<CMRespDto<?>> boardFileUpdate (BoardDTO boardDTO) {

        boardService.boardFileUpdate(boardDTO);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }



    @GetMapping("/update/{boardIndex}")
    //@Operation(summary = "게시글 수정용 ", description = "해당 boardIndex 조건에 맞는 게시글을 상세 보기합니다.")
    public ResponseEntity<CMRespDto<?>> loadUpdatePageByBoardIndex (@PathVariable("boardIndex") int boardIndex){
        System.out.println(boardIndex);
        log.info("업데이트용 "+ boardService.loadUpdatePageByBoardIndex(boardIndex));
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", boardService.loadUpdatePageByBoardIndex(boardIndex)));
    }





    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ게시물 삭제ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    @PostMapping("/delete")
    //@Operation(summary = "게시글 삭제", description = "해당 boardIndex 조건에 맞으면 게시글 삭제됩니다.")
    public ResponseEntity<CMRespDto<?>> deleteBoard (int boardIndex){
        boardService.boardDeleteByBoardIndex(boardIndex);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }




    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 상세보기페이지 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    @GetMapping("/selectOne/{boardIndex}")
    //@Operation(summary = "게시글 상세보기", description = "해당 boardIndex 조건에 맞는 게시글을 상세 보기합니다.")
    public ResponseEntity<CMRespDto<?>> selectOneBoard (@PathVariable("boardIndex") int boardIndex){
        log.info("selectOne...{}", boardIndex);
        BoardDTO dto2 = boardService.boardSelectOneByBoardIndex(boardIndex);
        log.info(dto2.toString());
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", dto2));
    }




    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ게시물 검색 및 목록ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    @GetMapping("/search")
    //@Operation(summary = "게시글 검색", description = "게시글 전체목록, 제목, 닉네임, 내용, 제목+내용에 해당하는 검색목록을 출력합니다.")
    public ResponseEntity<CMRespDto<?>> searchBoard (BoardSearchDTO dto){
        //리스트 빈거
        log.info("search...{}", dto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", boardService.boardSearchAllBySubjectAndUserIndexAndContent(dto))); //true면 출력이 안됨
    }

    @GetMapping("/totalCount")
    //@Operation(summary = "게시글 전체 개수", description = "게시글 전체 카운트.")
    public ResponseEntity<CMRespDto<?>> boardTotalCount (BoardSearchDTO dto){

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", boardService.boardTotalCount(dto)));

    }




    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ좋아요 추천ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    @GetMapping("/find/like")
    //@Operation(summary = " 해당 게시글 및 유저 추천수", description = "커뮤니티게시판 boardIndex 조건에 맞는 해당 게시글에 UserIndex 좋아요(추천) 체크 확인용 API")
    public ResponseEntity<CMRespDto<?>> likeByBoardIndexAndUserIndex (int boardIndex, int userIndex ){
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", boardService.likeByBoardIndexAndUserIndex(boardIndex, userIndex)));

    }
    @GetMapping("/like/count")
    //@Operation(summary = "게시글 추천수 count", description = "커뮤니티게시판 boardIndex 조건에 맞는 해당 게시글 추천수 Count API")
    public ResponseEntity<CMRespDto<?>> likeCountByBoardIndex (int boardIndex){
        boardIndex = 10;
        System.out.println(boardService.likeCountByBoardIndex(boardIndex));

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", boardService.likeCountByBoardIndex(boardIndex)));

    }

    @PostMapping("/like")
    //@Operation(summary = "게시글 좋아요", description = "게시글 좋아요를 완료합니다.")
    public ResponseEntity<CMRespDto<?>> likeAdd (int boardIndex, int userIndex){
        log.info(""+boardIndex);

        boardService.likeAdd(boardIndex, userIndex);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

    @PostMapping("/cancel")
    //@Operation(summary = "게시글 좋아요 취소 ", description = "게시글 좋아요 선택을 취소합니다.")
    public ResponseEntity<CMRespDto<?>> likeBadCancel (int boardIndex, int userIndex) {
        boardService.likeCancel(boardIndex,userIndex);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

//    @PostMapping("/bad")
//    //@Operation(summary = "게시글 싫어요", description = "게시글 싫어요를 완료합니다.")
//    public ResponseEntity<CMRespDto<?>> badAdd (int boardIndex, int userIndex){
//        boardService.badAdd(boardIndex, userIndex);
//        return ResponseEntity.ok()
//                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
//    }




    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ조회수ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    @GetMapping("/visit")
    //@Operation(summary = "게시글 조회수", description = "특정 게시물 인덱스를 입력하면(상세보기) 해당 게시글 조회수 1증가합니다.")
    public ResponseEntity<CMRespDto<?>> boardViewCount (BoardDTO dto){
        boardService.boardViewCount(dto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", dto));
    }




    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ댓글 작성ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    @PostMapping("/selectOne/reply/insert")
    //@Operation(summary = "게시글 댓글 등록", description = " 해당 게시글의 댓글을 등록할 수 있습니다.")
    public ResponseEntity<CMRespDto<?>> boardReplyInsertByReplyGroup(@RequestBody BoardReplyDTO dto, @AuthenticationPrincipal PrincipalDetails principalDetails){
       boardService.boardReplyInsertByReplyGroup(dto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

    @PostMapping(value = "/selectOne/reply/insert/{replyGroup}")
    //@Operation(summary = "게시글 대댓글 등록", description = " 해당 게시글의 replyGroup에 따른 대댓글을 등록할 수 있습니다.")
    public ResponseEntity<CMRespDto<?>> boardReplyInsertByReplySequence(@PathVariable(value = "replyGroup") int replyGroup,
                                                                        @RequestBody BoardReplyDTO dto,


                                                                        @AuthenticationPrincipal PrincipalDetails principalDetails){
        boardService.boardReplyInsertBySequence(dto);

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ댓글 수정ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    @PostMapping("/selectOne/reply/update")
    //@Operation(summary = "게시글 댓글수정 ", description = "해당 게시글의 특정 댓글을 수정할 수 있습니다")
    public ResponseEntity<CMRespDto<?>> boardReplyUpdateByReplyIndex(@RequestBody BoardReplyDTO dto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boardService.boardReplyUpdateByReplyIndex(dto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

    @GetMapping("/selectOne/reply/update/{replyIndex}")
    //@Operation(summary = "게시글 댓글수정로드용 ", description = "해당 게시글의 특정 댓글 정보를 가져옵니다.")
    public ResponseEntity<CMRespDto<?>> boardReplyUpdateSelectOneByReplyIndex(@PathVariable("replyIndex") int replyIndex, @AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println(replyIndex);
        log.info("업데이트용 "+ boardService.boardReplyUpdateSelectOneByReplyIndex(replyIndex));
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", boardService.boardReplyUpdateSelectOneByReplyIndex(replyIndex)));
    }





    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ댓글 삭제ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    @PostMapping("/selectOne/replyGroup/delete")
    //@Operation(summary = "게시글 댓글삭제", description = "해당 게시글의 특정 댓글을 삭제할 수 있습니다")
    public ResponseEntity<CMRespDto<?>> boardReplyDeleteByReplyIndexAndBoardIndex(int replyIndex, int boardIndex){
        boardService.boardReplyDeleteByReplyIndexAndBoardIndex(replyIndex,boardIndex);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

    @PostMapping("/selectOne/replySequence/delete")
    //@Operation(summary = "게시글 대댓글삭제", description = "해당 게시글의 특정 대댓글을 삭제할 수 있습니다")
    public ResponseEntity<CMRespDto<?>> boardReplyDeleteByReplyIndex(int replyIndex){
        boardService.boardReplyDeleteByReplyIndex(replyIndex);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }






    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ댓글 전체 목록ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
    @GetMapping("/selectOne/reply/{boardIndex}")
    //@Operation(summary = "댓글목록",description = "게시글 상세보기를 하면 댓글목록을 확인 할 수 있습니다.")
    public ResponseEntity<CMRespDto<?>> boardReplySelectAll (@PathVariable("boardIndex") int boardIndex, @AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println(boardIndex);
        log.info("댓글용1 "+ boardService.loadUpdatePageByBoardIndex(boardIndex));

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", boardService.boardReplySelectAll(boardIndex)));
    }




}
