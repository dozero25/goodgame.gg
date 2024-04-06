package fourjo.idle.goodgame.gg.repository;


import fourjo.idle.goodgame.gg.web.dto.AdminReplySearchDTO;
import fourjo.idle.goodgame.gg.web.dto.ReplyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminReplyRepository {


    public int AdminReplyDelete(int reply_index);

    public List<ReplyDto> AdminReplySearch(AdminReplySearchDTO adminReplySearchDTO);
}
