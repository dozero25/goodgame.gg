package fourjo.idle.goodgame.gg.repository;

import fourjo.idle.goodgame.gg.web.dto.AdminEmployeeDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminEmployeeRepository {

    public int adminEmployeeInsert(AdminEmployeeDto adminEmployeeDto);

    public int adminEmployeeDelete(int empIndex);

    public int adminEmployeeUpdate(AdminEmployeeDto adminEmployeeDto);

    public AdminEmployeeDto adminEmployeeSearch(String empId);

    public int adminInsertRoleId(int empIndex);

}
