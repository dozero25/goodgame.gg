window.onload = () => {
    HeaderService.getInstance().loadHeader();
    // RecordShowInfoService.getInstance().summonerShowInfo();
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

    summonerShowInfo(){
        const show1 = RecordApi.getInstance().searchAccountInfoPuuid();
        const show2 = RecordApi.getInstance().searchSummonerInfoByEncryptedPUUID();
        const show3 = RecordApi.getInstance().searchMatchsByMatchId();
        const show4 = RecordApi.getInstance().searchMatchInfoByMatchId();
        const show5 = RecordApi.getInstance().searchLeagueBySummonerName();
        // const show6 = RecordApi.getInstance().searchChampionMasteryByPuuid();
        
        console.log(show1);
        console.log(show2);
        console.log(show3);
        console.log(show4);
        console.log(show5);
        
    }


    
}