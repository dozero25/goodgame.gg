package fourjo.idle.goodgame.gg.repository;

import fourjo.idle.goodgame.gg.web.dto.Ranking.RankingDto;
import fourjo.idle.goodgame.gg.web.dto.Ranking.RankingSearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RankingRepository {

    //highrank_mst
    public int insertRankingLeagueV4(Map<String,Object> insert);

    public int updateRankingSummonerV4(Map<String,Object> update);

    public int updateRankingAccountV1(Map<String,Object> update);

    List<String> pullSummonerIdList();

    List<String> pullPuuidList();

    public int truncateTable();


    public int insertLowRankingLeagueV4(Map<String,Object> insert);

    public int updateLowRankingSummonerV4(Map<String,Object> update);

    public int updateLowRankingAccountV1(Map<String,Object> update);

    List<String> pullLowSummonerIdList();

    List<String> pullLowPuuidList();

    public int truncateLowTable();



    public List<RankingDto> searchRankingList(RankingSearchDto rankingSearchDto);





}