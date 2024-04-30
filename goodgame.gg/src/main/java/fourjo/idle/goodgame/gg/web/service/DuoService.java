package fourjo.idle.goodgame.gg.web.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fourjo.idle.goodgame.gg.repository.DuoRepository;
import fourjo.idle.goodgame.gg.web.dto.duo.*;
import fourjo.idle.goodgame.gg.web.dto.riotKey.RiotApiKeyDto;
import fourjo.idle.goodgame.gg.web.dto.rotation.ChampionEnum;
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
    private final RiotApiKeyDto riotApiKeyDto = new RiotApiKeyDto();
    //위에서 접속할 api 기본 주소 및 api 세팅

    public AccountDto searchPuuidBySummonerNameAndTagLine(String summonerName, String tagLine) {
        //아이디와 #과 태그라인으로 이루어진 값을 활용하여 ACCOUNTS V1에서 encrypted된 puuid를 받아옵니다.

        AccountDto accountDto = new AccountDto();
        try {

            summonerName = summonerName.replaceAll(" ", "%20"); //들어온 데이터 띄어쓰기를 %20 으로 바꿉니다.(주소에는 %20으로 표시됨)
            HttpClient c = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(riotApiKeyDto.getSeverUrlAsia() + "/riot/account/v1/accounts/by-riot-id/" + summonerName + "/" + tagLine + "?api_key=" + riotApiKeyDto.getMykey());
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

    public String searchMostThreeChampionsByPuuid(String puuid) {
        String threeChampionName = "";
        List<ChampionMasteryDto> championMasteryDto = new ArrayList<>();
        try {

            HttpClient c = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(riotApiKeyDto.getServerUrl() + "/lol/champion-mastery/v4/champion-masteries/by-puuid/" + puuid + "?api_key=" + riotApiKeyDto.getMykey());

            HttpResponse response = c.execute(request);

            // riotResponseCodeError(response);

            HttpEntity entity = response.getEntity();
            championMasteryDto = objectMapper.readValue(entity.getContent(), new TypeReference<>() {
            });

        } catch (
                IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 3; i++) {

            threeChampionName += ChampionEnum.findByKey((int) championMasteryDto.get(i).getChampionId());
            if (i == 2) {
                break;
            }
            threeChampionName += "-";


        }

        return threeChampionName;
    }

    public SummonerDto searchEncryptedIdByPuuid(String puuid) {
        //Encrypted된 puuid를 이용하여 summoners V4를 통해 Encrypted된 Id를 받아옵니다.
        SummonerDto summonerDto = new SummonerDto();
        try {

            HttpClient c = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(riotApiKeyDto.getServerUrl() + "/lol/summoner/v4/summoners/by-puuid/" + puuid + "?api_key=" + riotApiKeyDto.getMykey());
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
        //찾아온 EncryptedId를 통해 League V4에 접근하여 티어정보를 가져옵니다.
        List<LeagueEntryDto> leagueEntryDto = new ArrayList<>();

        try {

            HttpClient c = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(riotApiKeyDto.getServerUrl() + "/lol/league/v4/entries/by-summoner/" + encryptedId + "?api_key=" + riotApiKeyDto.getMykey());
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
//듀오 정보를 insert하는 곳입니다.


        String[] idAndTag = duoDto.getDuoGameId().split("#");

        AccountDto accountDto = searchPuuidBySummonerNameAndTagLine(idAndTag[0], idAndTag[1]);
        String threeChampions = searchMostThreeChampionsByPuuid(accountDto.getPuuid());
        duoDto.setDuoThreeChampions(threeChampions);
        SummonerDto summonerDto = searchEncryptedIdByPuuid(accountDto.getPuuid());
        List<LeagueEntryDto> leagueEntryDto = searchTierByEncryptedId(summonerDto.getId());

        //티어 정보를 영어로 반환하기 때문에 js와 html에서 활용하기 위해 한글로 바꾸었습니다.
        if (leagueEntryDto.get(0).getTier().equalsIgnoreCase("challenger")) {
            duoDto.setDuoTier("챌린저");
        } else if (leagueEntryDto.get(0).getTier().equalsIgnoreCase("grandmaster")) {
            duoDto.setDuoTier("그랜드마스터");
        } else if (leagueEntryDto.get(0).getTier().equalsIgnoreCase("master")) {
            duoDto.setDuoTier("마스터");
        } else if (leagueEntryDto.get(0).getTier().equalsIgnoreCase("diamond")) {
            duoDto.setDuoTier("다이아몬드");
        } else if (leagueEntryDto.get(0).getTier().equalsIgnoreCase("emerald")) {
            duoDto.setDuoTier("에메랄드");
        } else if (leagueEntryDto.get(0).getTier().equalsIgnoreCase("platinum")) {
            duoDto.setDuoTier("플래티넘");
        } else if (leagueEntryDto.get(0).getTier().equalsIgnoreCase("gold")) {
            duoDto.setDuoTier("골드");
        } else if (leagueEntryDto.get(0).getTier().equalsIgnoreCase("silver")) {
            duoDto.setDuoTier("실버");
        } else if (leagueEntryDto.get(0).getTier().equalsIgnoreCase("bronze")) {
            duoDto.setDuoTier("브론즈");
        } else if (leagueEntryDto.get(0).getTier().equalsIgnoreCase("iron")) {
            duoDto.setDuoTier("아이언");
        }

        return duoRepository.duoInsert(duoDto);

    }

    public List<DuoDto> duoSearchByQueAndTierAndPosition(DuoSearchDto duoSearchDto) {
        //듀오 검색할 때 게임정보와 티어, 포지션을 각각 받아오는데 비어있는값 ""이 오지 않는 데이터들만 검색할 수 있는 Key를 설정하였습니다.

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
        //인덱스 계산하는 함수에 접근하여 페이지의 첫 시작을 계산해야합니다.
        return duoRepository.duoSearchByQueAndTierAndPosition(duoSearchDto);

    }

    public int getDuoTotalCount(DuoSearchDto duoSearchDto) {
        //마찬가지로 전체 페이지 수를 가져올때 게임정보와 티어, 포지션을 각각 받아오는데 비어있는값 ""이 오지 않는 데이터들만 검색할 수 있는 Key를 설정하였습니다.
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

    public int checkNick(DuoDto duoDto) {
        String[] idAndTag = duoDto.getDuoGameId().split("#");


//아이디와 태그라인을 #으로 분리한 뒤 puuid를 찾아옵니다. 이 때 puuid가 비어 있으면 존재하지 않는 아이디 이므로 1을 return하고 puuid가 존재하면 0을 return합니다.

        AccountDto accountDto = searchPuuidBySummonerNameAndTagLine(idAndTag[0], idAndTag[1]);
        if (accountDto.getPuuid() == null) {
            return 1;
        } else {
            return 0;
        }
    }

}
