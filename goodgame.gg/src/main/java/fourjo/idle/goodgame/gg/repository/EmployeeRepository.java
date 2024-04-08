package fourjo.idle.goodgame.gg.repository;

import fourjo.idle.goodgame.gg.web.dto.EmployeeDto;
import fourjo.idle.goodgame.gg.web.dto.RoleDtl;
import fourjo.idle.goodgame.gg.web.dto.RoleMst;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeRepository {

    public int employeeInsert(EmployeeDto employeeDto);

    public int employeeDelete(int empIndex);

    public int employeeUpdate(EmployeeDto employeeDto);

    public EmployeeDto employeeSearch(String empId);

    public int insertRoleId(int empIndex);

}
