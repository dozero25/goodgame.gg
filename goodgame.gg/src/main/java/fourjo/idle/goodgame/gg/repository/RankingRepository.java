package fourjo.idle.goodgame.gg.repository;

import fourjo.idle.goodgame.gg.web.dto.Admin.AdminBoardDTO;
import fourjo.idle.goodgame.gg.web.dto.Ranking.LeagueEntryDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RankingRepository {

    public int rankingInsert(LeagueEntryDto LeagueDto);

    public int rankingUpdate(AdminBoardDTO adminBoardDTO);

    public int rankingDelete(int boardIndex);



}