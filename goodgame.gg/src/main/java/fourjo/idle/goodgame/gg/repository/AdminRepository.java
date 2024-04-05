package fourjo.idle.goodgame.gg.repository;

import fourjo.idle.goodgame.gg.web.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminRepository {


    public int userUpdate(UserDto userDto);
    public int userDelete(UserDto userDto);


}
