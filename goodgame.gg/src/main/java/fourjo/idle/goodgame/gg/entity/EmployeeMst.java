package fourjo.idle.goodgame.gg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeMst {
    private int empIndex;
    private String empId;
    private String empPw;
    private String empName;
    private String empRegNum;
    private String empEmail;
    private String empPhone;
    private String empGender;
    private List<RoleDtl> roleDtl;
}
