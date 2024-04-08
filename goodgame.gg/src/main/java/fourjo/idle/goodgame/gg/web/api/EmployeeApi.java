package fourjo.idle.goodgame.gg.web.api;

import fourjo.idle.goodgame.gg.web.dto.CMRespDto;
import fourjo.idle.goodgame.gg.web.dto.EmployeeDto;
import fourjo.idle.goodgame.gg.web.service.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/emp")
@Tag(name ="Emp Api", description = "Emp Api 입니다.")
public class EmployeeApi {

    @Autowired
    private EmployeeService empService;

    @PostMapping("/insert")
    public ResponseEntity<CMRespDto<?>> empInsert (@RequestBody EmployeeDto employeeDto, BindingResult bindingResult){
        //empService.insertRole(employeeDto.getEmpIndex());
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully Insert", empService.employeeInsert(employeeDto)));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<CMRespDto<?>> empDelete (int empIndex){
        empService.employeeDelete(empIndex);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully Delete", true));
    }
    @PatchMapping("/update")
    public ResponseEntity<CMRespDto<?>> empUpdate (@RequestBody EmployeeDto employeeDto){
        empService.employeeUpdate(employeeDto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully update", employeeDto));
    }
    @GetMapping("/search")
    public ResponseEntity<CMRespDto<?>> empSearch (String empId){
        empService.employeeSearch(empId);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully search", empId));
    }

    @PostMapping("/roleInsert")
    public ResponseEntity<CMRespDto<?>> insertRoleId (int empIndex) {
        empService.insertRole(empIndex);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully search", empIndex));
    }







}