window.onload = () => {

    DuoMainService.getInstance().getLoadAllDuoList();
   ComponentEvent.getInstance().addClickEventInsertButton();
    ComponentEvent.getInstance().addClickEventSearchButton();

}

let searchObj = {
    searchQueValue: "",
    searchPositionValue: "",
    searchTierValue: "",
    limit: "Y",
    count: 5,
    page: 1
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
        const duoTable = document.querySelector(".duo-table tbody");

        duoTable.innerHTML = "";

        responseData.forEach((data, index) => {
            duoTable.innerHTML += `
            <tr>
             <td>${data.duoIndex}</td>
                <td>${data.duoGameId}</td>
                  <td>${data.duoQue}</td>  
                  <td>${data.duoPosition}</td>
              <td>${data.duoTier}</td>
              <td>${data.duoWdate}</td>
               <td>${data.duoContent}</td>
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

        addClickEventInsertButton() {

            const insertButton = document.querySelector(".insert-button");

            insertButton.addEventListener("click", function () {

            window.location.href="http://localhost:8000/duo/insert"
            });

        }

        addClickEventSearchButton() {
            const QueValue = document.querySelector(".Que-select");
            const TierValue = document.querySelector(".Tier-select");
            const PositionValue = document.querySelector(".Position-select");

            const searchButton = document.querySelector(".search-button");
            searchButton.onclick = () => {
                console.log("1");
                searchObj.searchQueValue = QueValue.value;
                searchObj.searchTierValue = TierValue.value;
                searchObj.searchPositionValue = PositionValue.value;
                searchObj.page = 1;

                DuoMainService.getInstance().getLoadAllDuoList();
            }

        }
    }







