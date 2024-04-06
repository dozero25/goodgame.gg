package fourjo.idle.goodgame.gg.repository;

import fourjo.idle.goodgame.gg.web.dto.AdminBoardDTO;
import fourjo.idle.goodgame.gg.web.dto.AdminBoardSearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminRepository {

    public int admin_board_insert(AdminBoardDTO dto);

    public int admin_board_update(AdminBoardDTO dto);

    public int admin_board_delete(int board_index);





    public List<AdminBoardDTO> admin_board_selectAll();

    public List<AdminBoardDTO> AdminBoardSearch(AdminBoardSearchDTO adminBoardSearchDTO);
}