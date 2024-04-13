window.onload = () => {

}

class RecordApi{
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new RecordApi();
        }
        return this.#instance;
    }

    searchSummonerInfoByEncryptedPUUID(){
        let returnData = null;

        $.ajax({
            async: false,
            type: "get",
            url: "http://localhost:8000/api/record/get/summoner/info",
            dataType: "json",
            success: responese => {
                console.log(responese);
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
            url: "http://localhost:8000/api/record/get/matches",
            dataType: "json",
            success: responese => {
                console.log(responese);
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
                console.log(responese);
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
                console.log(responese);
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
                console.log(responese);
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


}