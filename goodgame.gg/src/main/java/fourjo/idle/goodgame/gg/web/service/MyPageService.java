package fourjo.idle.goodgame.gg.web.service;

import fourjo.idle.goodgame.gg.repository.MyPageRepository;
import fourjo.idle.goodgame.gg.web.dto.mypage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPageService {

    @Autowired
    private MyPageRepository myPageRepository;


    /*0. 더미데이터 생성*/
    public int insertUserData(UserDTO userDTO) {

        return myPageRepository.insertUserData(userDTO);
    }

    /*1. 회원탈퇴*/
    public int deleteUserAllData(int userIndex) {

        return myPageRepository.deleteUserAllData(userIndex);
    }
    public String pwCheck(int userId) {

        return myPageRepository.pwCheck(userId);
    }
    //========================================
    /*2. 회원정보 수정*/
    public int updateMypageInfo(UserDTO userDTO) {

        return myPageRepository.updateMypageInfo(userDTO);
    }

    /*3. 내가 쓴 글 목록*/
    public List<BoardAndLikeDTO> searchMyBoardListByIndex(BoardSearchDTO boardSearchDTO) {

       boardSearchDTO.setIndex();
        return myPageRepository.searchMyBoardListByIndex(boardSearchDTO);
    }

   /* 4. 내가 쓴 댓글 목록*/
    public List<ReplyDTO> searchMyReplyListByIndex(ReplySearchDTO replySearchDTO){

        replySearchDTO.setIndex();
        return myPageRepository.searchMyReplyListByIndex(replySearchDTO);
    }

    /*5. 일단 하나만 불러오자*/
    public UserDTO selectOneData(int userIndex){
        return myPageRepository.selectOneData(userIndex);
    }


    public int totalBoardCount(int userIndex){
        return myPageRepository.totalBoardCount(userIndex);
    }

    public int totalReplyCount(int userIndex){
        return myPageRepository.totalReplyCount(userIndex);
    }

    public int deleteBoardByUserIndexAndBoardIndex(int boardIndex, int userIndex){
        return myPageRepository.deleteBoardByUserIndexAndBoardIndex(boardIndex, userIndex);
    }

    public int deleteBoardReplyByUserIndexAndBoardIndexAndBoardGroup(int boardIndex, int userIndex, int replyGroup, int replySequence){
        return myPageRepository.deleteBoardReplyByUserIndexAndBoardIndexAndBoardGroup(boardIndex, userIndex,replyGroup,replySequence);
    }
}
