window.onload = () => {

    DuoMainService.getInstance().getLoadAllDuoList();

    ComponentEvent.getInstance().addClickEventSearchButton();
ComponentEvent.getInstance().addClickEventModalButton();
ComponentEvent.getInstance().addClickEventInsertButton();
}

let searchObj = {
    searchQueValue: "",
    searchPositionValue: "",
    searchTierValue: "",
    limit: "Y",
    count: 12,
    page: 1
};
let duoObj = {

     duoGameId:"",
   duoQue:"",
     duoPosition:"",
     duoContent:""

};

class DuoMainApi{

    static #instance=null;
    static getInstance(){
        if(this.#instance==null){
            this.#instance=new DuoMainApi();

        }
        return this.#instance;
    }

    getAllDuoList(searchObj){
        let returnData = null;

        $.ajax({
            async : false,
            type: "get",
            url: `http://localhost:8000/api/duo/search`,
            data: searchObj,
            dataType: "json",
            success : response => {
                returnData = response.data;
            },
            error : error => {
                console.log(error);
            }
        });
        return returnData;
    }
    getDuoTotalCount(){
        let returnData = null;

        $.ajax({
            async: false,
            type: "get",
            url: "http://localhost:8000/api/duo/totalCount",
            data: {
                "searchQueValue" : searchObj.searchQueValue,
                "searchPositionValue" : searchObj.searchPositionValue,
                "searchTierValue" : searchObj.searchTierValue

            },
            dataType: "json",
            success: response => {
                returnData = response.data;
            },
            error: error => {
                console.log(error);
            }
        });
        return returnData;
    }
    postInsertDuo(){
        let successFlag = null;

        $.ajax({
            async: false,
            type: "post",
            url: "http://localhost:8000/api/duo/insert",
            data: duoObj,
            dataType: "json",
            success: response => {
                console.log(response);
                successFlag = true;
            },
            error: error => {
                console.log(error);
            }
        });
        return successFlag;
    }
}

class DuoMainService {

    static #instance = null;

    static getInstance() {
        if (this.#instance == null) {
            this.#instance = new DuoMainService();
        }
        return this.#instance;
    }

    loadPageController() {
        const pageController = document.querySelector(".page-controller");

        const totalcount = DuoMainApi.getInstance().getDuoTotalCount(searchObj);

        const maxPageNumber = totalcount % searchObj.count == 0
            ? Math.floor(totalcount / searchObj.count)
            : Math.floor(totalcount / searchObj.count) + 1;

        pageController.innerHTML = `
            <a href="javascript:void(0)" class="pre-button disabled">이전</a>
            <ul class="page-numbers">
            </ul>
            <a href="javascript:void(0)" class="next-button disabled">다음</a>
        `;

        if (searchObj.page != 1) {
            const preButton = pageController.querySelector(".pre-button");
            preButton.classList.remove("disabled");

            preButton.onclick = () => {
                searchObj.page--;
                this.getLoadAllDuoList();
            }
        }

        if (searchObj.page != maxPageNumber) {
            const preButton = pageController.querySelector(".next-button");
            preButton.classList.remove("disabled");

            preButton.onclick = () => {
                searchObj.page++;

                this.getLoadAllDuoList();
            }
        }
        const startIndex = searchObj.page % 5 == 0
            ? searchObj.page - 4
            : searchObj.page - (searchObj.page % 5) + 1;

        const endIndex = startIndex + 4 <= maxPageNumber ? startIndex + 4 : maxPageNumber;

        const pageNumbers = document.querySelector(".page-numbers");

        for (let i = startIndex; i <= endIndex; i++) {
            pageNumbers.innerHTML += ` 
                <a href="javascript:void(0)"class ="page-button ${i == searchObj.page ? "disabled" : ""}"><li>${i}</li></a>
            `;
        }

        const pageButtons = document.querySelectorAll(".page-button");
        pageButtons.forEach(button => {
            const pageNumber = button.textContent;
            if (pageNumber != searchObj.page) {
                button.onclick = () => {
                    searchObj.page = pageNumber;
                    this.getLoadAllDuoList();
                }
            }
        });
    }

    getLoadAllDuoList() {

        const responseData = DuoMainApi.getInstance().getAllDuoList(searchObj);
        const duoTable = document.querySelector(".duo-table");

        duoTable.innerHTML = "";

        responseData.forEach((data, index) => {

            duoTable.innerHTML += `
           <td>
             ${data.duoIndex}<br>
             ${data.duoQue} ${data.duoTier} ${data.duoPosition} 구함<br>
            <a href="/duo/view?UserId=${data.duoGameId}">${data.duoGameId}</a><br>
                 ${data.duoQue} <br>
                ${data.duoPosition}<br>
             ${data.duoTier}<br>
           ${data.duoWdate}<br>
              ${data.duoContent}<br>
           </td>
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



        addClickEventSearchButton() {
            const QueValue = document.querySelector(".Que-select");
            const TierValue = document.querySelector(".Tier-select");
            const PositionValue = document.querySelector(".Position-select");

            const searchButton = document.querySelector(".search-button");
            searchButton.onclick = () => {

                searchObj.searchQueValue = QueValue.value;
                searchObj.searchTierValue = TierValue.value;
                searchObj.searchPositionValue = PositionValue.value;
                searchObj.page = 1;

                DuoMainService.getInstance().getLoadAllDuoList();
            }

        }
        addClickEventModalButton() {
           const btn=document.getElementById("insert-btn");
           const modal= document.getElementById("modalWrap");
           const closeBtn=document.getElementById("closeBtn");

           btn.onclick=function(){
               modal.style.display="block";

           };
           closeBtn.onclick=function(){
               modal.style.display="none";
           };
           window.onclick=function(event){
               if(event.target == modal){
                   modal.style.display="none";
               }
           };

        }
addClickEventInsertButton(){

    const insertBtn=document.querySelector(".insert-success-btn");

    insertBtn.onclick=()=>{
        const modal= document.getElementById("modalWrap");
        const GameIdValue = document.getElementById("duoGameid");
        const QueValue = document.querySelector('input[type=radio][name=duoQue]:checked');
        const PositionValue = document.querySelector('input[type=radio][name=duoPosition]:checked');
        const ContentValue=document.getElementById("duoContent");
        console.log(GameIdValue.value);
        console.log(QueValue.value);
        console.log(PositionValue.value);
        console.log(ContentValue.value);
        duoObj.duoGameId=GameIdValue.value;
        duoObj.duoQue=QueValue.value;
        duoObj.duoPosition=PositionValue.value;
        duoObj.duoContent=ContentValue.value;
       console.log(duoObj);


        DuoMainApi.getInstance().postInsertDuo();
        modal.style.display="none";
        searchObj.searchQueValue= "";
        searchObj.searchPositionValue="";
        searchObj.searchTierValue="";
        searchObj.page=1;
        DuoMainService.getInstance().getLoadAllDuoList();
    }
}
        addClickEventCheckButton() {
            const btn=document.getElementById("insert-check");


            btn.onclick=function(){

            }

        }
    }







