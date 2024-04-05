package fourjo.idle.goodgame.gg.web.service;

import fourjo.idle.goodgame.gg.repository.AdminRepository;
import fourjo.idle.goodgame.gg.web.dto.AdminBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public int admin_board_insert(AdminBoardDTO dto) {
        return adminRepository.admin_board_insert(dto);
    }

    public int admin_board_update(AdminBoardDTO dto) {
        return adminRepository.admin_board_update(dto);
    }

    public int admin_board_delete(int board_index) {
        return adminRepository.admin_board_delete(board_index);
    }

    public List<AdminBoardDTO> admin_board_selectAll() {
        return adminRepository.admin_board_selectAll();
    }
}
