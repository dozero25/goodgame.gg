window.onload = () => {
    HeaderService.getInstance().loadHeader();
    RecordShowInfoService.getInstance().summonerShowInfo();
}

let gameNameAndTagLine = "";

class RecordApi{
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new RecordApi();
        }
        return this.#instance;
    }

    searchAccountInfoPuuid(){
        let returnData = null;

        $.ajax({
            async: false,
            type: "get",
            url: `http://localhost:8000/api/record/get/account/info`,
            dataType: "json",
            success: responese => {
                returnData = responese.data;
            }, 
            error: error => {
                console.log(error);
            }
        });

        return returnData;
    }

    searchSummonerInfoByEncryptedPUUID(){
        let returnData = null;

        $.ajax({
            async: false,
            type: "get",
            url: "http://localhost:8000/api/record/get/summoner/info",
            dataType: "json",
            success: responese => {
                returnData = responese.data;
            }, 
            error: error => {
                console.log(error);
            }
        });

        return returnData;
    }

    searchMatchsByMatchId(){
        let returnData = null;

        $.ajax({
            async: false,
            type: "get",
            url: "http://localhost:8000/api/record/get/matches",
            dataType: "json",
            success: responese => {
                returnData = responese.data;
            }, 
            error: error => {
                console.log(error);
            }
        });

        return returnData;
    }

    searchMatchInfoByMatchId(){
        let returnData = null;

        $.ajax({
            async: false,
            type: "get",
            url: "http://localhost:8000/api/record/get/matches/info",
            dataType: "json",
            success: responese => {
                returnData = responese.data;
            }, 
            error: error => {
                console.log(error);
            }
        });

        return returnData;
    }

    searchLeagueBySummonerName(){
        let returnData = null;

        $.ajax({
            async: false,
            type: "get",
            url: "http://localhost:8000/api/record/get/league",
            dataType: "json",
            success: responese => {
                returnData = responese.data;
            }, 
            error: error => {
                console.log(error);
            }
        });

        return returnData;
    }

    searchChampionMasteryByPuuid(){
        let returnData = null;

        $.ajax({
            async: false,
            type: "get",
            url: "http://localhost:8000/api/record/get/championMastery",
            dataType: "json",
            success: responese => {
                returnData = responese.data;
            }, 
            error: error => {
                console.log(error);
            }
        });

        return returnData;
    }
}

class RecordShowInfoService{
    static #instance = null;
    static getInstance(){
        if(this.#instance == null){
            this.#instance = new RecordShowInfoService();
        }
        return this.#instance;
    }
// const show6 = RecordApi.getInstance().searchChampionMasteryByPuuid();
    summonerShowInfo(){
        const accountData = RecordApi.getInstance().searchAccountInfoPuuid();
        const summonerData = RecordApi.getInstance().searchSummonerInfoByEncryptedPUUID();
        const leagueData = RecordApi.getInstance().searchLeagueBySummonerName();
        RecordApi.getInstance().searchMatchsByMatchId();

        const profilLeft = document.querySelector(".profil-left");
        const profilRight = document.querySelector(".profil-right");
        const daedkkeInfo = document.querySelector(".daedkke-info");

        const winning = ((leagueData[0].wins /(leagueData[0].wins + leagueData[0].losses))*100).toFixed(1);

        profilLeft.innerHTML = `
            <div class="profil-img">
                <img src="https://ddragon.leagueoflegends.com/cdn/14.8.1/img/profileicon/${summonerData.profileIconId}.png" alt="">
            </div>
             <div class="profil-summoner">
                <h2>${accountData.gameName} #${accountData.tagLine}</h2>
                <h4>소환사 레벨 : ${summonerData.summonerLevel}</h1>
            </div>
        `;

        profilRight.innerHTML = `
            <div class="rank-box">
                <div class="rank-img" style="margin-left: 10px;">
                    <div class="img-box">
                        <img src="/static/images/tier/${leagueData[0].tier}.png" alt="">
                    </div>
                </div>
                <div class="rank-info">
                    <div class="rank-score">
                        <div>
                            <h2>${leagueData[0].tier}</h2>
                        </div>
                        <div>
                            <h4>${leagueData[0].leaguePoints} LP</h4>
                        </div>
                        <div>
                            <h2>${leagueData[0].wins}승 ${leagueData[0].losses}패</h2>
                        </div>
                        <div>
                            <h4>승률 ${winning}%</h4>
                        </div>
                    </div>
                </div>
            </div>
        `;

        const matchInfo = RecordApi.getInstance().searchMatchInfoByMatchId();

        console.log(matchInfo);

        matchInfo.forEach((data, index) => {

            const startEndTime = new Date(matchInfo[index].info.gameDuration * 1000);
            const whereTime = new Date(matchInfo[index].info.gameStartTimestamp);
            const participantsInfo = matchInfo[index].info.participants;
            
            const participantObj = {
                parIndex: 0,
                win : ""
            };

            participantsInfo.forEach((data, index1) => {
                if(participantsInfo[index1].puuid == summonerData.puuid){
                    participantObj.parIndex = index1;
                    participantObj.win = participantsInfo[index1].win == "true" ? "WIN": "LOSE";

                    
                }
            });

            daedkkeInfo.innerHTML += `

            <div class="match-box">
                <div class="match-info-fir">
                    <span class="info-text text-fir">${matchInfo[index].info.gameMode}</span>
                    <span class="info-text text-sec">${(whereTime.getMonth()+1).toString.length == 2 ? whereTime.getMonth()+1 : "0"+(whereTime.getMonth()+1)}/${whereTime.getDate()}</span>
                    <span class="info-text text-thi">${participantObj.win == "WIN" 
                    ? document.querySelectorAll('.text-thi').innerHTML = `<font color="#0004ff">${participantObj.win}</font>` 
                    : document.querySelectorAll('.text-thi').innerHTML = `<font color="#f4584e">${participantObj.win}</font>`}
                    </span>
                    <span class="info-text text-four">${startEndTime.getMinutes()}:${startEndTime.getSeconds()}</span>
                </div>
                <div class="match-info-sec">
                    <div class="cop-one">
                        <div class="cop-two">
                            <div class="cop-four">
                                <img src="https://ddragon.leagueoflegends.com/cdn/14.8.1/img/champion/${participantsInfo[participantObj.parIndex].championName}.png"
                                    alt="" class="">
                            </div>
                            <span class="cop-five">${participantsInfo[participantObj.parIndex].champLevel}</span>
                        </div>
                        <div class="cop-three">
                            <div class="disflex diflmar">
                                <div class="disflex"><img src="https://ddragon.leagueoflegends.com/cdn/14.8.1/img/spell/${participantsInfo[participantObj.parIndex].spell1Casts}.png" alt="" class="spell-img"></div>
                                <div class="disflex" style="margin-left: 2px;"><img src="" alt=""class="spell-img"></div>
                            </div>
                            <div class="disflex diflmar" style="margin-top: 2px;">
                                <div class="disflex"><img src="" alt="" class="spell-img"></div>
                                <div class="disflex" style="margin-left: 2px;"><img src="" alt=""
                                        class="spell-img"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="match-info-thi">
                    <div class="text-kidecs">
                        <span>15 / 4 / 14 (7.25)</span>
                    </div>
                    <div class="text-kidecs">
                        <span>CS : 251 (8.37)</span>
                    </div>
                    <div style="display: flex;">
                        <span class="text-ck">연속킬 :</span>
                        <span class="continuity-kill text-ck">트리플</span>
                    </div>
                </div>
                <div class="kda-info">
                    <div class="text-kidecs">
                        <span>킬관여 : 57%</span>
                    </div>
                    <div class="text-kidecs">
                        <span>제어와드 : 3</span>
                    </div>
                    <div class="text-kidecs">
                        <span>골드 : 30000</span>
                    </div>
                    <div class="text-kidecs">
                        <span>피해량 : 31540</span>
                    </div>
                </div>
                <ul>
                    <li class="summoner-list">
                        <div class="sulist-fcc">
                            <div class="sulist-fcc-img">
                                <img src="/static/images/champion/Aatrox.png" alt="">
                            </div>
                            <a target="_blank" href="" class="href-summoner">dd</a>
                        </div>
                        <div class="sulist-fcc">
                            <div class="sulist-fcc-img">
                                <img src="/static/images/champion/Aatrox.png" alt="">
                            </div>
                            <a target="_blank" href="" class="href-summoner">dd</a>
                        </div>
                    </li>
                    <li class="summoner-list">
                        <div class="sulist-fcc">
                            <div class="sulist-fcc-img">
                                <img src="/static/images/champion/Aatrox.png" alt="">
                            </div>
                            <a target="_blank" href="" class="href-summoner">dd</a>
                        </div>
                        <div class="sulist-fcc">
                            <div class="sulist-fcc-img">
                                <img src="/static/images/champion/Aatrox.png" alt="">
                            </div>
                            <a target="_blank" href="" class="href-summoner">dd</a>
                        </div>
                    </li>
                    <li class="summoner-list">
                        <div class="sulist-fcc">
                            <div class="sulist-fcc-img">
                                <img src="/static/images/champion/Aatrox.png" alt="">
                            </div>
                            <a target="_blank" href="" class="href-summoner">dd</a>
                        </div>
                        <div class="sulist-fcc">
                            <div class="sulist-fcc-img">
                                <img src="/static/images/champion/Aatrox.png" alt="">
                            </div>
                            <a target="_blank" href="" class="href-summoner">dd</a>
                        </div>
                    </li>
                    <li class="summoner-list">
                        <div class="sulist-fcc">
                            <div class="sulist-fcc-img">
                                <img src="/static/images/champion/Aatrox.png" alt="">
                            </div>
                            <a target="_blank" href="" class="href-summoner">dd</a>
                        </div>
                        <div class="sulist-fcc">
                            <div class="sulist-fcc-img">
                                <img src="/static/images/champion/Aatrox.png" alt="">
                            </div>
                            <a target="_blank" href="" class="href-summoner">dd</a>
                        </div>
                    </li>
                    <li class="summoner-list">
                        <div class="sulist-fcc">
                            <div class="sulist-fcc-img">
                                <img src="/static/images/champion/Aatrox.png" alt="">
                            </div>
                            <a target="_blank" href="" class="href-summoner">dd</a>
                        </div>
                        <div class="sulist-fcc">
                            <div class="sulist-fcc-img">
                                <img src="/static/images/champion/Aatrox.png" alt="">
                            </div>
                            <a target="_blank" href="" class="href-summoner">dd</a>
                        </div>
                    </li>
                </ul>
                <div class="action">
                    <button class="action active">
                        <i class="fa-solid fa-chevron-up"></i>
                    </button>
                </div>
            </div>
            
            `;
        }); 

        
    }
    
}