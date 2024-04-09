package fourjo.idle.goodgame.gg.repository;

import fourjo.idle.goodgame.gg.entity.EmployeeMst;
import fourjo.idle.goodgame.gg.entity.UserMst;
import fourjo.idle.goodgame.gg.web.dto.user.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {

    public int registerUser(UserDto userDto);

    public String findUserByUserIdForError(String userId);

    public UserMst findUserByUserId(String userId);

    public EmployeeMst findEmpByEmpId(String empId);

}
