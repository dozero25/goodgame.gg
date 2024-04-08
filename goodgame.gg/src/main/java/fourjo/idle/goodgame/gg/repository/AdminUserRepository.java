package fourjo.idle.goodgame.gg.repository;


import fourjo.idle.goodgame.gg.web.dto.admin.AdminUserSearchDTO;
import fourjo.idle.goodgame.gg.web.dto.admin.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminUserRepository {


    public int userUpdateUserNickByUserIndex(UserDto dto);
    public int userDeleteByUserIndex(int userIndex);



    public List<UserDto> userSearchByUserNickAndEmailAndId(AdminUserSearchDTO adminUserSearchDTO);

    public UserDto userSelectOneByUserIndex(int userIndex);

}
