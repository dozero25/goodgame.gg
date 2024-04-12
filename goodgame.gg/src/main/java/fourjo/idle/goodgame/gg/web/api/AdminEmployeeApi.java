package fourjo.idle.goodgame.gg.web.api;

import fourjo.idle.goodgame.gg.web.dto.CMRespDto;
import fourjo.idle.goodgame.gg.web.dto.AdminEmployeeDto;
import fourjo.idle.goodgame.gg.web.service.AdminEmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/emp")
@Tag(name ="Emp Api", description = "Emp Api 입니다.")
public class AdminEmployeeApi {

    @Autowired
    private AdminEmployeeService empService;

    @PostMapping("/insert")
    public ResponseEntity<CMRespDto<?>> empInsert (@RequestBody AdminEmployeeDto adminEmployeeDto, BindingResult bindingResult){
        //empService.insertRole(employeeDto.getEmpIndex());
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully Insert", empService.adminEmployeeInsert(adminEmployeeDto)));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<CMRespDto<?>> empDelete (int empIndex){
        empService.adminEmployeeDelete(empIndex);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully Delete", true));
    }
    @PatchMapping("/update")
    public ResponseEntity<CMRespDto<?>> empUpdate (@RequestBody AdminEmployeeDto adminEmployeeDto){
        empService.adminEmployeeUpdate(adminEmployeeDto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully update", adminEmployeeDto));
    }
    @GetMapping("/search")
    public ResponseEntity<CMRespDto<?>> empSearch (String empId){
        empService.adminEmployeeSearch(empId);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully search", empId));
    }

    @PostMapping("/roleInsert")
    public ResponseEntity<CMRespDto<?>> insertRoleId (int empIndex) {
        empService.adminInsertRole(empIndex);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully search", empIndex));
    }







}