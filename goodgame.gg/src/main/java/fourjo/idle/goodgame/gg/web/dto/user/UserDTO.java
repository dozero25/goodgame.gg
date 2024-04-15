package fourjo.idle.goodgame.gg.web.dto.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    
    private int userIndex;
    private String userId;
    private String userPw;
    private String userNick;
    private String userGender;
    private LocalDateTime userRegDate;
    private String userEmail;
    private int roleId;

    public UserDTO getMemberId() {
        return null;
    }
    public byte[] getMemberPw() {
        return new byte[0];
    }


    // public String getMemberPw() {
   //     return null;
   // }
   // public UserDTO getMemberId() {
    //    return null;
   // }



}
