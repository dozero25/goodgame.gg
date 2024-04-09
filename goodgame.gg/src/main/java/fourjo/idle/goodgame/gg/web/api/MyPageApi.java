package fourjo.idle.goodgame.gg.web.api;

import fourjo.idle.goodgame.gg.web.dto.CMRespDto;
import fourjo.idle.goodgame.gg.web.dto.board.BoardDTO;
import fourjo.idle.goodgame.gg.web.dto.user.UserDTO;
import fourjo.idle.goodgame.gg.web.service.MyPageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mypage")
@Tag(name ="Mypage Api", description = "Mypage Api 입니다.")
public class MyPageApi {

    @Autowired
    private MyPageService myPageService;

    /*회원탈퇴*/
    @DeleteMapping("/member/Delete/{Userid}")
    public void deleteMemberAllDataByIdAndPw(@PathVariable String id) {
        myPageService.deleteMemberAllDataByIdAndPw(id);
    }

    /*회원정보 수정*/
    /*@PostMapping("member/update")
    public ResponseEntity<CMRespDto<?>> updateMyPageByPrivacy(@RequestBody String id, HttpSession session, Model model){
        //세션에 저장된 유저아이디 가져오기
        String userId = (String) session.getAttribute("userId");
        myPageService.updateMyPageByPrivacy(id);
        model.addAttribute("userid",userId);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered",true));

    }*/
    /*회원정보 수정*/
    @PostMapping("/member/update")
    public ResponseEntity<CMRespDto<?>> updateMyPageByPrivacy(@RequestBody UserDTO userDTO){

        myPageService.updateMyPageByPrivacy(String.valueOf(userDTO));
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered",true));

    }

    /*내가 쓴 글 목록*/
    @PostMapping("/member/searchListById")
    public ResponseEntity<CMRespDto<?>> searchMyWriteListByID(@RequestBody UserDTO userDTO,HttpSession session) {
        // 세션에서 로그인한 유저의 아이디 가져오기
        String loggedInUserId = (String) session.getAttribute("userId");

        // 유저의 아이디로 해당 유저가 쓴 글 목록을 조회
        /*HashMap<String , String> map = new HashMap<>();
        map.put(loggedInUserId,"유저목록 조회");*/

        List<UserDTO> myBoardList = myPageService.getMyBoardList(UserDTO.getUserId());
        UserDTO myPosts = myPageService.searchMyWriteListByID(loggedInUserId);

        return ResponseEntity.ok().body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully retrieved", myPosts));
    }

    /*내가 쓴 댓글 목록*/
    @PostMapping("/searchList")
    public ResponseEntity<CMRespDto<?>> searchMyCommentsListByID(@RequestBody BoardDTO boardDTO, Model model) {
        List<Comment> comments = myPageService.getCommentsByUserId(boardDTO.getUserId());

        if(comments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CMRespDto<>(HttpStatus.NOT_FOUND.value(), "No comments found for the user", false));
        }
        if(comments != null && comments.isEmpty()) {
            model.addAttribute("comments",comments);
        }


        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully retrieved comments", true, List<comments>));
    }



}
