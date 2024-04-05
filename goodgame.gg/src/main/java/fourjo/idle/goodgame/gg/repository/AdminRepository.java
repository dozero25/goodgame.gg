package fourjo.idle.goodgame.gg.repository;

import fourjo.idle.goodgame.gg.web.dto.AdminUserSelectAll;
import fourjo.idle.goodgame.gg.web.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminRepository {


    public int AdminUserUpdate(UserDto dto);
    public int AdminUserDelete(int user_index);

    public List<UserDto> AdminUserSelectAll();

    public List<UserDto> AdminUserSearchList(String user_nick);

    public UserDto AdminUserSelectOne(int user_index);

}
