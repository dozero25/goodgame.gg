package fourjo.idle.goodgame.gg.repository;

import fourjo.idle.goodgame.gg.web.dto.EmpDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpRepository {

    public int empInsert(EmpDto empDto);

    public int empDelete(int empIndex);

    public int empUpdate(EmpDto empDto);

}
