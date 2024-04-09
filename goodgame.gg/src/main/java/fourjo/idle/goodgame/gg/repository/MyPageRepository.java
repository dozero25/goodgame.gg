package fourjo.idle.goodgame.gg.repository;

import fourjo.idle.goodgame.gg.web.dto.board.BoardDTO;
import fourjo.idle.goodgame.gg.web.dto.user.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MyPageRepository {

    /*회원탈퇴*/
    public void deleteMemberAllDataByIdAndPw(UserDTO userDTO);

    /*회원정보 수정*/
    /*1. 개인정보 확인*/
    /*public int selectMyPageByPrivacy(int userid);*/
    /*2. 수정*/
    /*principle(js) 이용*/
    public int updateMyPageByPrivacy(String userDTO);

    /*내가 쓴 글 목록 보기*/
    public List<BoardDTO> searchMyWriteListByID(Map<String, String> map);
    public List<BoardDTO> searchMyCommentsListByID(Map<String, String> map);



}
