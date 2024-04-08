package fourjo.idle.goodgame.gg.web.service;

import fourjo.idle.goodgame.gg.repository.AdminEmployeeRepository;
import fourjo.idle.goodgame.gg.web.dto.AdminEmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminEmployeeService {
    @Autowired
    private AdminEmployeeRepository adminEmployeeRepository;
    public int adminEmployeeInsert(AdminEmployeeDto adminEmployeeDto){
        return adminEmployeeRepository.adminEmployeeInsert(adminEmployeeDto);
    }
    public int adminEmployeeDelete(int empDto){
        return adminEmployeeRepository.adminEmployeeDelete(empDto);
    }
    public int adminEmployeeUpdate(AdminEmployeeDto adminEmployeeDto){
        return adminEmployeeRepository.adminEmployeeUpdate(adminEmployeeDto);
    }
    public AdminEmployeeDto adminEmployeeSearch(String empId){
        return adminEmployeeRepository.adminEmployeeSearch(empId);
    }
    public void adminInsertRole(int empIndex){
       adminEmployeeRepository.adminInsertRoleId(empIndex);
    }


}
