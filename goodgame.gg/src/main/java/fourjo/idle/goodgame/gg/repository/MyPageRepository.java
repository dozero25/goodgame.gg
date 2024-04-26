package fourjo.idle.goodgame.gg.repository;

import fourjo.idle.goodgame.gg.web.dto.board.BoardDTO;
import fourjo.idle.goodgame.gg.web.dto.user.ReplyDTO;
import fourjo.idle.goodgame.gg.web.dto.user.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageRepository {

    /*0. 더미데이터 생성*/
    public int insertUserData(UserDTO userDTO);

    /*1. 회원탈퇴*/
    public int deleteUserAllData(int userIndex);
    public String pwCheck(int userIndex);

    //========================================
    /*2. 회원정보 수정*/
    public int updateMypageInfo(UserDTO userDTO);

    /*3. 내가 쓴 글 목록*/
    public List<BoardDTO> searchMyPostListByIndex(int userIndex);

    /*4. 내가 쓴 댓글 목록*/
    public List<ReplyDTO> searchMyReplyListByIndex(int userIndex);

    /*5. 일단 하나만 불러오자*/
    public UserDTO selectOneData(int userIndex);

    /*6.*/
    public List<BoardDTO> selectAllData(int userIndex,BoardDTO boardDTO);



}
