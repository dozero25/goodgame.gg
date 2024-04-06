package fourjo.idle.goodgame.gg.web.service;

import fourjo.idle.goodgame.gg.repository.AdminReplyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminReplyService {

    @Autowired
    private AdminReplyRepository adminReplyRepository;


    public int AdminReplyDelete(int reply_index) {
        return adminReplyRepository.AdminReplyDelete(reply_index);
    }

}
