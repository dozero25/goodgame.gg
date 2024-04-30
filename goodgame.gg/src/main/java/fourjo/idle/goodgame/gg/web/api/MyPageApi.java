package fourjo.idle.goodgame.gg.web.api;

import ch.qos.logback.core.boolex.EvaluationException;
import fourjo.idle.goodgame.gg.security.PrincipalDetails;
import fourjo.idle.goodgame.gg.web.dto.CMRespDto;
import fourjo.idle.goodgame.gg.web.dto.mypage.*;
import fourjo.idle.goodgame.gg.web.service.MyPageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mypage")
@Tag(name = "mypage Api", description = "mypage Api 입니다.")
public class MyPageApi {

    @Autowired
    private MyPageService myPageService;

    /*0. 더미데이터 생성*/
    @PostMapping("/insert")
    public ResponseEntity<CMRespDto<?>> insertUserData(UserDTO userDTO) {

        myPageService.insertUserData(userDTO);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));

    }

    /*1. 회원탈퇴*/
    /*@DeleteMapping("/delete")
    public ResponseEntity<CMRespDto<?>> deleteUserAllData(@RequestBody UserDTO userDTO,Model model){

        myPageService.deleteUserAllData(userDTO);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered",true));

    }*/
    //=========================================================================================
    //1. 본인인증을 윈한 비밀번호 1회 확인 (로그인에 의해 본인인증은 따로 필요없음.)
    //2. 회원탈퇴 시 주의사항 고지
    //index 페이지는 탈퇴페이지에 대한 설정 변수 소스와 "회원탈퇴 시 주의사항","탈퇴사유", "패스워드 인증"에 대한 HTML 소스를 포함
    //process 페이지는 이용자가 입력한 탈퇴사유 저장, 이용자 ID/패스워드 확인, 개인정보 삭제에 대한 소스를 포함하고 있다

    @DeleteMapping(value = "/delete/{userIndex}")
    public ResponseEntity<CMRespDto<?>> deleteUserAllData(@PathVariable("userIndex") int userIndex) {
        myPageService.deleteUserAllData(userIndex);
       System.out.println(userIndex);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully deleted", true));
    }

    //비밀번호 유효성검사
    @GetMapping("/delete/pwCheck")
    public ResponseEntity<CMRespDto<?>> pwCheck(UserDTO userDTO) throws EvaluationException {
        System.out.println(userDTO+"인덱스 비번");

        // 사용자의 ID를 기반으로 암호화된 비밀번호를 가져옴
        // myPageService에서 pwCheck 메서드를 호출하여 사용자의 인덱스를 기반으로
        // 데이터베이스에서 암호화된 비밀번호를 가져옴.
        String memberPw = myPageService.pwCheck(userDTO.getUserIndex());
        System.out.println(memberPw+"DB 비번");

        // 입력된 비밀번호와 데이터베이스에 저장된 해싱된 비밀번호를 비교
        // BCrypt 클래스의 checkpw 메서드를 사용하여 사용자가 입력한 비밀번호(userDTO.getUserPw())와
        // 데이터베이스에 저장된 해싱된 비밀번호(memberPw)를
        // 비교하여 일치 여부를 확인.
        boolean isPasswordMatch = BCrypt.checkpw(userDTO.getUserPw(), memberPw);
        System.out.println(isPasswordMatch+"비교 후");

        // 비밀번호가 일치하지 않으면 문장출력
        // 변수 선언
        String check; //비밀번호 일치 여부를 저장할 변수 선언
        if (isPasswordMatch == true) { // 비밀번호가 일치하는 경우
            check = "인증이 완료되었습니다.";
        } else { // 비밀번호가 일치하지 않는 경우
            check = "비밀번호가 일치하지 않습니다.";
        }

        // 비밀번호가 일치하면 삭제페이지로 이동
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", isPasswordMatch));
        //텍스트 처리해서 js로 처리
        //제공되는 텍스트를 직접 input으로 입력해서 텍스트 입력값을 js에서 비교하여 처리

    }

    /*

    //======================================================================================
       try {
            myPageService.deleteUserAllDataByIdAndPw(userId);
            return "Member deleted successfully";
        } catch (Exception e) {
            // 예외 처리: 회원 삭제에 실패한 경우
            return "Error deleting member: " + e.getMessage();
        }
    }*/

    /*2. 회원정보 수정*/
    //@PatchMapping("/member/update/{userIndex}")
    @PatchMapping("/update/{userIndex}")
    public ResponseEntity<CMRespDto<?>> updateMypageInfo(@PathVariable("userIndex") int userIndex, @RequestBody UserDTO userDTO) {
        System.out.println(userDTO);
        userDTO.setUserIndex(userIndex);
        myPageService.updateMypageInfo(userDTO);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", userDTO));
    }

    /*3. 내가 쓴 글 목록*/
    @GetMapping("/searchList/board")
    public ResponseEntity<CMRespDto<?>> searchMyBoardListByIndex(BoardSearchDTO boardSearchDTO, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println(boardSearchDTO);
        List<BoardAndLikeDTO> boardDTO =  myPageService.searchMyBoardListByIndex(boardSearchDTO);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", boardDTO));

    }

    /*4. 내가 쓴 댓글 목록*/
    @GetMapping("/searchList/reply")
    public ResponseEntity<CMRespDto<?>> searchMyReplyListByIndex(ReplySearchDTO replySearchDTO, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        List<ReplyDTO> replyDTO = myPageService.searchMyReplyListByIndex(replySearchDTO);
        System.out.println("replyDTO:"+replyDTO);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", replyDTO));

    }

    /*5. 일단 하나만 불러오자*/
    @GetMapping("/selectOne/{userIndex}")
    public ResponseEntity<CMRespDto<?>> selectOneData(@PathVariable("userIndex") int userIndex ) {
        System.out.println("selectOne>>>userIndex:"+userIndex);

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", myPageService.selectOneData(userIndex)));

    }


    /*6*/
    @GetMapping("/getTotalBoardCount")
    public ResponseEntity<CMRespDto<?>> totalBoardCount(int userIndex, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("selectAllData>>>userIndex:"+userIndex);

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", myPageService.totalBoardCount(userIndex)));

    }
    @GetMapping("/getTotalReplyCount")
    public ResponseEntity<CMRespDto<?>> totalReplyCount(int userIndex, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("selectAllData>>>userIndex:"+userIndex);

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", myPageService.totalReplyCount(userIndex)));

    }

    @DeleteMapping("/delete/board/info/{boardIndex}")
    public ResponseEntity<CMRespDto<?>> deleteBoardByUserIndexAndBoardIndex(@PathVariable("boardIndex") int boardIndex, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", myPageService.deleteBoardByUserIndexAndBoardIndex(boardIndex, principalDetails.getUser().getUserIndex())));

    }

    @DeleteMapping("/delete/board/reply/info/{boardIndex}/{replyGroup}/{replySequence}")
    public ResponseEntity<CMRespDto<?>> deleteBoardReplyByUserIndexAndBoardIndexAndBoardGroup(@PathVariable("boardIndex") int boardIndex,@PathVariable("replyGroup") int replyGroup,@PathVariable("replySequence") int replySequence, @AuthenticationPrincipal PrincipalDetails principalDetails ) {
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", myPageService.deleteBoardReplyByUserIndexAndBoardIndexAndBoardGroup(boardIndex, principalDetails.getUser().getUserIndex(), replyGroup, replySequence)));

    }



}
