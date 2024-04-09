package fourjo.idle.goodgame.gg.web.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fourjo.idle.goodgame.gg.repository.RankingRepository;
import fourjo.idle.goodgame.gg.web.dto.Ranking.LeagueEntryDto;
import fourjo.idle.goodgame.gg.web.dto.Ranking.LeagueListDto;
import fourjo.idle.goodgame.gg.web.dto.Ranking.SummonerDto;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RankingService {

    @Autowired
    RankingRepository rankingRepository;

    private ObjectMapper objectMapper = new ObjectMapper();
    private final HttpClient client = HttpClientBuilder.create().build();

    private final String ApiKey = "RGAPI-0fdd97d4-5567-4eba-89fc-404501a3a849";
    private final String serverUri = "https://kr.api.riotgames.com";
    private final String serverUriAsia = "https://asia.api.riotgames.com";

    private final String solo = "/RANKED_SOLO_5x5";
    private final String flex = "/RANKED_FLEX_SR";



    public SummonerDto searchSummonerInfoBySummonerName(String summonerName) {
        SummonerDto summonerDto = new SummonerDto();

        try {
            HttpGet request = new HttpGet(serverUri + "/lol/summoner/v4/summoners/by-name/" + summonerName + "?api_key=" + ApiKey);
            HttpResponse response = client.execute(request);

//            riotResponseCodeError(response);

            HttpEntity entity = response.getEntity();
            summonerDto = objectMapper.readValue(entity.getContent(), SummonerDto.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return summonerDto;
    }

    public List<LeagueEntryDto> searchLeagueById(String encryptedSummonerId) {
        List<LeagueEntryDto> leagueInfo = new ArrayList<>();

        try {
            HttpGet request = new HttpGet(serverUri + "/lol/league/v4/entries/by-summoner/" + encryptedSummonerId + "?api_key=" + ApiKey);
            HttpResponse response = client.execute(request);

//            riotResponseCodeError(response);

            HttpEntity entity = response.getEntity();
            leagueInfo = objectMapper.readValue(entity.getContent(), new TypeReference<>() {
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        return leagueInfo;
    }

    public LeagueListDto challengerLeaguesByQueue(String queue) {
        LeagueListDto challengerRanking = new LeagueListDto();

        if (queue.equals("solo")) {
            queue = solo;
        } else if (queue.equals("flex")) {
            queue = flex;
        }

        try {
            HttpGet request = new HttpGet(serverUri + "/lol/league/v4/challengerleagues/by-queue" + queue + "?api_key=" + ApiKey);
            HttpResponse response = client.execute(request);

//            riotResponseCodeError(response);

            HttpEntity entity = response.getEntity();
            challengerRanking = objectMapper.readValue(entity.getContent(), new TypeReference<>() {});
            Collections.sort(challengerRanking.getEntries());
//            challengerRanking.getEntries().sort(Comparator.comparingInt(LeagueItemDto::getLeaguePoints).reversed());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return challengerRanking;
    }



    public LeagueListDto grandmasterLeaguesByQueue(String queue){
            LeagueListDto grandmasterRanking = new LeagueListDto();

            if (queue.equals("solo")) {
                queue = solo;
            } else if (queue.equals("flex")) {
                queue = flex;
            }

            try {
                HttpGet request = new HttpGet(serverUri + "/lol/league/v4/grandmasterleagues/by-queue" + queue + "?api_key=" + ApiKey);
                HttpResponse response = client.execute(request);

//            riotResponseCodeError(response);

                HttpEntity entity = response.getEntity();
                grandmasterRanking = objectMapper.readValue(entity.getContent(), new TypeReference<>() {});
                Collections.sort(grandmasterRanking.getEntries());
//                grandmasterRanking.getEntries().sort(Comparator.comparingInt(LeagueItemDto::getLeaguePoints).reversed());

            } catch (IOException e){
                e.printStackTrace();
            }
            return grandmasterRanking;
    }

    public LeagueListDto masterLeaguesByQueue(String queue){
        LeagueListDto masterRanking = new LeagueListDto();

        if (queue.equals("solo")) {
            queue = solo;
        } else if (queue.equals("flex")) {
            queue = flex;
        }

        try {
            HttpGet request = new HttpGet(serverUri + "/lol/league/v4/masterleagues/by-queue" + queue + "?api_key=" + ApiKey);
            HttpResponse response = client.execute(request);

//            riotResponseCodeError(response);

            HttpEntity entity = response.getEntity();
            masterRanking = objectMapper.readValue(entity.getContent(), new TypeReference<>() {});

            System.out.println(masterRanking.getEntries().size());
            Collections.sort(masterRanking.getEntries());
//            masterRanking.getEntries().sort(Comparator.comparingInt(LeagueItemDto::getLeaguePoints).reversed());

        } catch (IOException e){
            e.printStackTrace();
        }
        return masterRanking;
    }




    public List<LeagueEntryDto> entriesLeagues(String tier, String division, String queue, int page){
        List<LeagueEntryDto> entriesRanking = new ArrayList<>();

        if (queue.equals("solo")) {
            queue = solo;
        } else if (queue.equals("flex")) {
            queue = flex;
        }

        try {
            HttpGet request = new HttpGet(serverUri + "/lol/league/v4/entries" + queue +"/"+ tier +"/"+ division + "?page=" + page +"&api_key=" + ApiKey);
            HttpResponse response = client.execute(request);

//            riotResponseCodeError(response);

            HttpEntity entity = response.getEntity();
            entriesRanking = objectMapper.readValue(entity.getContent(), new TypeReference<>() {});
//            Collections.sort(entriesRanking);
//            entriesRanking.getEntries().sort(Comparator.comparingInt(LeagueItemDto::getLeaguePoints).reversed());

        } catch (IOException e){
            e.printStackTrace();
        }
        return entriesRanking;
    }

    public int rankingInsert(LeagueEntryDto LeagueDto) {
        return rankingRepository.rankingInsert(LeagueDto);
    }
}