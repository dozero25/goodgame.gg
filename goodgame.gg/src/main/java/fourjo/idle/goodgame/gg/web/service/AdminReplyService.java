package fourjo.idle.goodgame.gg.web.service;

import fourjo.idle.goodgame.gg.repository.AdminReplyRepository;
import fourjo.idle.goodgame.gg.web.dto.AdminReplySearchDTO;
import fourjo.idle.goodgame.gg.web.dto.ReplyDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AdminReplyService {

    @Autowired
    private AdminReplyRepository adminReplyRepository;


    public int replyDeleteByUserIndex(int replyIndex) {
        return adminReplyRepository.replyDeleteByUserIndex(replyIndex);
    }
    public List<ReplyDto> replySearchByUserNickAndReplyContent(AdminReplySearchDTO adminReplySearchDTO) {
        return adminReplyRepository.replySearchByUserNickAndReplyContent(adminReplySearchDTO);
    }


}
