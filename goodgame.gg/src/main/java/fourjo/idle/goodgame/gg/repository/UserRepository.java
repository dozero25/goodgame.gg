package fourjo.idle.goodgame.gg.repository;

import fourjo.idle.goodgame.gg.web.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {

    public int registerUser(UserDto userDto);

}
