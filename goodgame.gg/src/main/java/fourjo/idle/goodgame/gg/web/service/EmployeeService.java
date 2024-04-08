package fourjo.idle.goodgame.gg.web.service;

import fourjo.idle.goodgame.gg.repository.EmployeeRepository;
import fourjo.idle.goodgame.gg.web.dto.EmployeeDto;
import fourjo.idle.goodgame.gg.web.dto.RoleDtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public int employeeInsert(EmployeeDto employeeDto){
        return employeeRepository.employeeInsert(employeeDto);
    }
    public int employeeDelete(int empDto){
        return employeeRepository.employeeDelete(empDto);
    }
    public int employeeUpdate(EmployeeDto employeeDto){
        return employeeRepository.employeeUpdate(employeeDto);
    }
    public EmployeeDto employeeSearch(String empId){
        return employeeRepository.employeeSearch(empId);
    }
    public void insertRole(int empIndex){
       employeeRepository.insertRoleId(empIndex);
    }


}
