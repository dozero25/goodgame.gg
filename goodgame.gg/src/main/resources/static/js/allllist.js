window.onload = () => {

    RankingMainService.getInstance().getLoadAllRankingList();

}

let searchRankingObj = {

    queueType : "",
    tier : "",
    gameName : "",
    tagLine : "",
    limit : "Y",
    count : 10,
    page : 1
}

const rankingObj = {
    rankingIndex : 0,
    gameName : "",
    tagLine : "",
    summonerLevel : 0,
    profileIconId : 0,
    queueType : "",
    tier : "",
    rank : "",
    leaguePoints : 0,
    wins : 0,
    losses : 0,
    winRate : 0
}

class RankingMainApi {
    static #instance = null;
    static getInstance(){
        if(this.#instance == null) {
            this.#instance = new RankingMainApi();
        }
        return this.#instance;
    }
    getAllRankingList(searchRankingObj){
        let returnData = null;

        $.ajax({
            async : false,
            type: "get",
            url: `http://localhost:8000/api/ranking/searchRankingList`,
            data: searchRankingObj,
            dataType: "json",
            success : response => {
                returnData = response.data;
            },
            error: error => {
                console.log(error);
            }
        });
        return returnData;
    }

}

class RankingMainService {
    static #instance = null;
    static getInstance(){
        if(this.#instance == null) {
            this.#instance = new RankingMainService();
        }
        return this.#instance;
    }
    getLoadAllRankingList(){
        const responeseData = RankingMainApi.getInstance().getAllRankingList(searchRankingObj);
        const rankingTable = document.querySelector(".board-table tbody");

        rankingTable.innerHTML = "";

        responeseData.forEach((data, index) => {
            rankingTable.innerHTML += `
            <tr>
                <td>${data.gameName}</td>
                <td>
                <a href="/view?rankingIndex=${data.rankingIndex}" value=${data.rankingIndex}</a>
                </td>
                <td>${data.tier}</td>
                <td>${data.rank}</td>
                <td>${data.leaguePoints}</td>
            </tr>
            `;
        });
        this.loadPageController();
    }

}

class ComponentEvent {
    static #instance = null;

    static getInstance() {
        if (this.#instance == null) {
            this.#instance = new ComponentEvent();
        }
        return this.#instance;
    }

}