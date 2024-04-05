package fourjo.idle.goodgame.gg.web.api;

import fourjo.idle.goodgame.gg.web.dto.CMRespDto;
import fourjo.idle.goodgame.gg.web.dto.EmpDto;
import fourjo.idle.goodgame.gg.web.service.EmpService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/emp")
@Tag(name ="Emp Api", description = "Emp Api")
public class EmpApi {

    @Autowired
    private EmpService empService;

    @PostMapping("/insert")
    public ResponseEntity<CMRespDto<?>> empInsert (@RequestBody EmpDto empDto, BindingResult bindingResult){
        empService.empInsert(empDto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }
    @PostMapping("/delete")
    public ResponseEntity<CMRespDto<?>> empDelete (int empIndex){
        empService.empDelete(empIndex);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully delete", true));
    }

    @PostMapping("/update")
    public ResponseEntity<CMRespDto<?>> empUpdate (@RequestBody EmpDto empDto, BindingResult bindingResult){
        empService.empUpdate(empDto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully update", true));
    }

}