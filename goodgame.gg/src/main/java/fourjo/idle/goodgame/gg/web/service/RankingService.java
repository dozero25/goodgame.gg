package fourjo.idle.goodgame.gg.web.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fourjo.idle.goodgame.gg.web.dto.ranking.LeagueEntryDTO;
import org.springframework.stereotype.Service;

@Service

public class RankingService {

    private ObjectMapper objectMapper=new ObjectMapper();

    private final String myKey = "RGAPI-99b03a1f-7581-4bcd-aa94-add370a6d76f";
    private final String serverUrl = "https://kr.api.riotgames.com/";
    private final String serverAsia = "https://asia.api.riotgames.com/";

    public set<LeagueEntryDTO>



}
