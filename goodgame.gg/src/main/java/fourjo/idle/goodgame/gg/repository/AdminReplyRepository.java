package fourjo.idle.goodgame.gg.repository;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminReplyRepository {


    public int AdminReplyDelete(int reply_index);


}
