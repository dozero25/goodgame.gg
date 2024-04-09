package fourjo.idle.goodgame.gg.repository;

import fourjo.idle.goodgame.gg.web.dto.user.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {

    public int registerUser(UserDTO userDto);

}
