package fourjo.idle.goodgame.gg.repository;


import fourjo.idle.goodgame.gg.web.dto.admin.AdminReplySearchDTO;
import fourjo.idle.goodgame.gg.web.dto.admin.ReplyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminReplyRepository {


    public int replyDeleteByUserIndex(int replyIndex);

    public List<ReplyDto> replySearchByUserNickAndReplyContent(AdminReplySearchDTO adminReplySearchDTO);
}
