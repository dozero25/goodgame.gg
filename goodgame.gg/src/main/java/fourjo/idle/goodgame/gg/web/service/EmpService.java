package fourjo.idle.goodgame.gg.web.service;

import fourjo.idle.goodgame.gg.repository.EmpRepository;
import fourjo.idle.goodgame.gg.web.dto.EmpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpService {

    @Autowired
    private EmpRepository empRepository;

    public int empInsert(EmpDto empDto) {
        return empRepository.empInsert(empDto);
    }

    public int empDelete(int empDto) {
        return empRepository.empDelete(empDto);
    }

    public int empUpdate(EmpDto empDto) {
        return empRepository.empUpdate(empDto);
    }

}
