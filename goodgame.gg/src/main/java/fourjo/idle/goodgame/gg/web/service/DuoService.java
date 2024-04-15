package fourjo.idle.goodgame.gg.web.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fourjo.idle.goodgame.gg.repository.DuoRepository;
import fourjo.idle.goodgame.gg.web.dto.duo.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DuoService {

    @Autowired
    DuoRepository duoRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    private final String myKey = "RGAPI-aa419218-4682-490a-9930-cd1d08bdc64e";
    private final String serverUrl = "https://kr.api.riotgames.com/";
    private final String serverAsia = "https://asia.api.riotgames.com/";

    public AccountDto searchPuuidBySummonerNameAndTagLine(String summonerName, String tagLine) {
        AccountDto accountDto = new AccountDto();
        try {

            summonerName = summonerName.replaceAll(" ", "%20");
            HttpClient c = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(serverAsia + "riot/account/v1/accounts/by-riot-id/" + summonerName + "/" + tagLine + "?api_key=" + myKey);
            HttpResponse response = c.execute(request);

            // riotResponseCodeError(response);

            HttpEntity entity = response.getEntity();
            accountDto = objectMapper.readValue(entity.getContent(), AccountDto.class);

        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return accountDto;
    }

    public SummonerDto searchEncryptedIdByPuuid(String puuid) {
        SummonerDto summonerDto = new SummonerDto();
        try {

            HttpClient c = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(serverUrl + "/lol/summoner/v4/summoners/by-puuid/" + puuid + "?api_key=" + myKey);
            HttpResponse response = c.execute(request);

            // riotResponseCodeError(response);

            HttpEntity entity = response.getEntity();
            summonerDto = objectMapper.readValue(entity.getContent(), SummonerDto.class);

        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return summonerDto;
    }

    public List<LeagueEntryDto> searchTierByEncryptedId(String encryptedId) {
        List<LeagueEntryDto> leagueEntryDto = new ArrayList<>();

        try {

            HttpClient c = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(serverUrl + "/lol/league/v4/entries/by-summoner/" + encryptedId + "?api_key=" + myKey);
            HttpResponse response = c.execute(request);

            // riotResponseCodeError(response);

            HttpEntity entity = response.getEntity();
            leagueEntryDto = objectMapper.readValue(entity.getContent(), new TypeReference<>() {
            });

        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return leagueEntryDto;
    }

    public int duoInsert(DuoDto duoDto) {




        String[] idAndTag = duoDto.getDuoGameId().split("#");




            AccountDto accountDto = searchPuuidBySummonerNameAndTagLine(idAndTag[0], idAndTag[1]);
            System.out.println(accountDto);

                SummonerDto summonerDto = searchEncryptedIdByPuuid(accountDto.getPuuid());
                List<LeagueEntryDto> leagueEntryDto = searchTierByEncryptedId(summonerDto.getId());
              if(leagueEntryDto.get(0).getTier().equalsIgnoreCase("challenger"))
              {
                  duoDto.setDuoTier("챌린저");
              }
              else if(leagueEntryDto.get(0).getTier().equalsIgnoreCase("grandmaster"))
              {
                  duoDto.setDuoTier("그랜드마스터");
              }
              else if(leagueEntryDto.get(0).getTier().equalsIgnoreCase("master"))
              {
                  duoDto.setDuoTier("마스터");
              }
              else if(leagueEntryDto.get(0).getTier().equalsIgnoreCase("diamond"))
              {
                  duoDto.setDuoTier("다이아몬드");
              }
              else if(leagueEntryDto.get(0).getTier().equalsIgnoreCase("emerald"))
              {
                  duoDto.setDuoTier("에메랄드");
              }
              else if(leagueEntryDto.get(0).getTier().equalsIgnoreCase("platinum"))
              {
                  duoDto.setDuoTier("플래티넘");
              }
              else if(leagueEntryDto.get(0).getTier().equalsIgnoreCase("gold"))
              {
                  duoDto.setDuoTier("골드");
              }
              else if(leagueEntryDto.get(0).getTier().equalsIgnoreCase("silver"))
              {
                  duoDto.setDuoTier("실버");
              }
              else if(leagueEntryDto.get(0).getTier().equalsIgnoreCase("bronze"))
              {
                  duoDto.setDuoTier("브론즈");
              }
              else if(leagueEntryDto.get(0).getTier().equalsIgnoreCase("iron"))
              {
                  duoDto.setDuoTier("아이언");
              }

                return duoRepository.duoInsert(duoDto);


    }

    public List<DuoDto> duoSearchByQueAndTierAndPosition(DuoSearchDto duoSearchDto) {
        if (duoSearchDto.getSearchQueValue() != "" && duoSearchDto.getSearchTierValue() == "" && duoSearchDto.getSearchPositionValue() == "") {
            duoSearchDto.setSearchKey("Que");
        } else if (duoSearchDto.getSearchQueValue() == "" && duoSearchDto.getSearchTierValue() != "" && duoSearchDto.getSearchPositionValue() == "") {
            duoSearchDto.setSearchKey("Tier");
        } else if (duoSearchDto.getSearchQueValue() == "" && duoSearchDto.getSearchTierValue() == "" && duoSearchDto.getSearchPositionValue() != "") {
            duoSearchDto.setSearchKey("Position");
        } else if (duoSearchDto.getSearchQueValue() != "" && duoSearchDto.getSearchTierValue() != "" && duoSearchDto.getSearchPositionValue() == "") {
            duoSearchDto.setSearchKey("QueAndTier");
        } else if (duoSearchDto.getSearchQueValue() != "" && duoSearchDto.getSearchTierValue() == "" && duoSearchDto.getSearchPositionValue() != "") {
            duoSearchDto.setSearchKey("QueAndPosition");
        } else if (duoSearchDto.getSearchQueValue() == "" && duoSearchDto.getSearchTierValue() != "" && duoSearchDto.getSearchPositionValue() != "") {
            duoSearchDto.setSearchKey("TierAndPosition");
        } else if (duoSearchDto.getSearchQueValue() != "" && duoSearchDto.getSearchTierValue() != "" && duoSearchDto.getSearchPositionValue() != "") {
            duoSearchDto.setSearchKey("QueAndTierAndPosition");
        }

        duoSearchDto.setIndex();
        System.out.println(duoSearchDto);
        return duoRepository.duoSearchByQueAndTierAndPosition(duoSearchDto);

    }

    public int getDuoTotalCount(DuoSearchDto duoSearchDto) {
        if (duoSearchDto.getSearchQueValue() != "" && duoSearchDto.getSearchTierValue() == "" && duoSearchDto.getSearchPositionValue() == "") {
            duoSearchDto.setSearchKey("Que");
        } else if (duoSearchDto.getSearchQueValue() == "" && duoSearchDto.getSearchTierValue() != "" && duoSearchDto.getSearchPositionValue() == "") {
            duoSearchDto.setSearchKey("Tier");
        } else if (duoSearchDto.getSearchQueValue() == "" && duoSearchDto.getSearchTierValue() == "" && duoSearchDto.getSearchPositionValue() != "") {
            duoSearchDto.setSearchKey("Position");
        } else if (duoSearchDto.getSearchQueValue() != "" && duoSearchDto.getSearchTierValue() != "" && duoSearchDto.getSearchPositionValue() == "") {
            duoSearchDto.setSearchKey("QueAndTier");
        } else if (duoSearchDto.getSearchQueValue() != "" && duoSearchDto.getSearchTierValue() == "" && duoSearchDto.getSearchPositionValue() != "") {
            duoSearchDto.setSearchKey("QueAndPosition");
        } else if (duoSearchDto.getSearchQueValue() == "" && duoSearchDto.getSearchTierValue() != "" && duoSearchDto.getSearchPositionValue() != "") {
            duoSearchDto.setSearchKey("TierAndPosition");
        } else if (duoSearchDto.getSearchQueValue() != "" && duoSearchDto.getSearchTierValue() != "" && duoSearchDto.getSearchPositionValue() != "") {
            duoSearchDto.setSearchKey("QueAndTierAndPosition");
        }


        return duoRepository.getDuoTotalCount(duoSearchDto);
    }

}
