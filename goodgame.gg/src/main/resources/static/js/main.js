window.onload = () => {
    ComponentEvent.getInstance().addClickSearchButton();
}

let gameNameAndTagLine ="";

class mainApi{
    static #instance = null;
    static getInstance(){
        if(this.#instance == null){
            this.#instance = new mainApi();
        }
        return this.#instance;
    }

    searchSummonerInfoByGameNameAndTagLine(){
        let returnData = null;

        $.ajax({
            async: false,
            type: "post",
            url: `http://localhost:8000/api/record/search/summoner/${gameNameAndTagLine}`,
            contentType: "application/json",
            data: JSON.stringify(gameNameAndTagLine),
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

class PrincipalApi {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new PrincipalApi();
        }
        return this.#instance;
    }

    getPrincipal() {
        let responseData = null;

        $.ajax({
            async: false,
            type: "get",
            url: "http://localhost:8000/api/account/principal",
            dataType: "json",
            success : response => {
                responseData = response.data;
                console.log(responseData);
            },
            error : error => {
                console.log(error);
            }
        });
        
        return responseData;
    }
}

class ComponentEvent {
    static #instance = null;
    static getInstance(){
        if(this.#instance == null){
            this.#instance = new ComponentEvent();
        }
        return this.#instance;
    }

    addClickSearchButton(){
        const searchInput = document.querySelector(".search-value");
        const seachButton = document.querySelector(".search-button");

        const principal = PrincipalApi.getInstance().getPrincipal();
        console.log(principal);
        

        seachButton.onclick = () => {



            gameNameAndTagLine = searchInput.value;
            mainApi.getInstance().searchSummonerInfoByGameNameAndTagLine();
            // location.href = ;
        }

        searchInput.onkeyup = () => {
            if(window.event.keyCode === 13) {
                seachButton.onclick();
    
            }
        }
    }


}