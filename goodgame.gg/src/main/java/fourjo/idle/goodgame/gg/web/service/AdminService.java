package fourjo.idle.goodgame.gg.web.service;

import fourjo.idle.goodgame.gg.repository.AdminRepository;
import fourjo.idle.goodgame.gg.web.dto.Admin.AdminBoardDTO;
import fourjo.idle.goodgame.gg.web.dto.Admin.AdminBoardSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public int boardInsertByUserIndex(AdminBoardDTO adminBoardDTO) {
        return adminRepository.boardInsertByUserIndex(adminBoardDTO);
    }

    public int boardUpdateByBoardIndex(AdminBoardDTO adminBoardDTO) {
        return adminRepository.boardUpdateByBoardIndex(adminBoardDTO);
    }

    public int boardDeleteByBoardIndex(int boardIndex) {
        return adminRepository.boardDeleteByBoardIndex(boardIndex);
    }

    public List<AdminBoardDTO> boardSearchAll4pm(AdminBoardSearchDTO adminBoardSearchDTO) {
        return adminRepository.boardSearchAll4pm(adminBoardSearchDTO);
    }
}