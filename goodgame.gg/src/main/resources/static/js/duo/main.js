window.onload = () => {
    HeaderService.getInstance().loadHeader();

    DuoMainService.getInstance().getLoadAllDuoList();                   //듀오 글 전체 불러오기

    ComponentEvent.getInstance().addClickEventModalButton();            //글 작성 버튼 클릭(모달창 활성화)
    ComponentEvent.getInstance().addClickEventInsertButton();           //모달 창에서 입력 완료 버튼
    ComponentEvent.getInstance().addClickEventCheckButton();            //모달 창에서 아이디 확인 버튼
    ComponentEvent.getInstance().addClickEventCopyButton();             //글 정보에서 아이디 복사 버튼
    ComponentEvent.getInstance().addClickEventTierButton();             //티어 검색 클릭
    ComponentEvent.getInstance().addClickEventPositionButton();         //포지션 검색 클릭
    ComponentEvent.getInstance().addClickEventQueButton();              //게임 타입 검색 클릭
    ComponentEvent.getInstance().addClickATag();
}
//검색하기 위한 Obj
let searchObj = {
    searchQueValue: "",
    searchPositionValue: "",
    searchTierValue: "",
    limit: "Y",
    count: 12,
    page: 1
};
//글 입력하기 위한 Obj
let duoObj = {

    duoGameId: "",
    duoQue: "",
    duoPosition: "",
    duoContent: "",
    duoThreeChampions:""

};

//아이디 체크를 위한 Obj
let checkObj = {
    duoGameId: ""
}

//select창 중복으로 열리는 것을 방지하기 위해 만들어놓은 Obj----- 창 열리면 1,닫히면 0
//창 닫힐때 toggle을 두번 거치는 경우가 생겨서 doublein을 설정하여 관리

let checkOpenedObj = {
    positionOpen: 0,

    modalOpen: 0,
    queOpen: 0,
    tierOpen: 0,
    queDoubleIn: 0,
    positionDoubleIn: 0,

}

let gameNameAndTagLine ="";

class DuoMainApi {

    static #instance = null;

    static getInstance() {
        if (this.#instance == null) {
            this.#instance = new DuoMainApi();

        }
        return this.#instance;
    }

    getAllDuoList(searchObj) {
        //모든 정보(searchObj에 부합하는)를 가져온다
        let returnData = null;

        $.ajax({
            async: false,
            type: "get",
            url: `http://localhost:8000/api/duo/search`,
            data: searchObj,
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

    getDuoTotalCount() {
        //정보(searchObj에 부합하는)의 전체 개수를 가져온다

        let returnData = null;

        $.ajax({
            async: false,
            type: "get",
            url: "http://localhost:8000/api/duo/totalCount",
            data: {
                "searchQueValue": searchObj.searchQueValue,
                "searchPositionValue": searchObj.searchPositionValue,
                "searchTierValue": searchObj.searchTierValue

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

    postInsertDuo() {
        //duoObj에 저장된 정보를 저장한다.
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

    CheckNick() {
        //checkObj에 저장된 아이디가 puuid를 가지는 지 확인한 뒤 존재하면 0, 아니면 1을 반환한다
        let returnData = null;

        $.ajax({
            async: false,
            type: "get",
            url: "http://localhost:8000/api/duo/checkNick",
            data: checkObj,
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
                alert("입력데이터를 다시 확인해주세요.");
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
        //페이징 담당: 페이징에 필요한 것들이 전부 들어있고, 주의 해야할 점은 페이지 값이 바뀌면 getLoadAllDuoList를 통해 리스트를 다시 불러와야한다.

        const pageController = document.querySelector(".page-controller");

        const totalcount = DuoMainApi.getInstance().getDuoTotalCount(searchObj);

        const maxPageNumber = totalcount % searchObj.count == 0
            ? Math.floor(totalcount / searchObj.count)
            : Math.floor(totalcount / searchObj.count) + 1;

        pageController.innerHTML = `
            <span class="pre-button disabled"><i class="fa-solid fa-play pre"></i></span>
            <ul class="page-numbers">
            </ul>
            <span class="next-button disabled"><i class="fa-solid fa-play next"></i></span>
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
                <li><span class ="page-button ${i == searchObj.page ? "disabled" : ""}">${i}</span></li>
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
        //리스트 전체 출력(조건에 맞게)
        const responseData = DuoMainApi.getInstance().getAllDuoList(searchObj);
        const duoTable = document.querySelector(".duo-table");

        duoTable.innerHTML = "";

        responseData.forEach((data, index) => {

            const now = new Date();
            const deadLine = new Date(data.duoWdate);
            const deadTime = (Math.floor((deadLine.getTime() - now.getTime()) / 1000 / 60));
            const uploadTime = Math.floor((now.getTime() - (new Date(data.duoWdate).getTime() - 900000)) / 60000);
            var champion=data.duoThreeChampions.toString().split("-");

            duoTable.innerHTML += `
        <div class="duoMiniBoard">
             <div id="miniBoardA">
                <span style="color: #36a2ce;  font-size: 12px">${uploadTime < 60 ? uploadTime + '분 전' : uploadTime < 1440 ? Math.floor(uploadTime / 60) + '시간 전' : Math.floor(uploadTime / 1440) + '일 전'}</span>
                <span style="color: rgb(153,153,153); font-size: 12px">${deadTime < 0 ? '만료' : deadTime + '분 후 만료'}</span>
             </div>
             <div id="miniBoardB">
                <span> ${data.duoQue} ${data.duoTier} 포지션 ${data.duoPosition==""?"상관없이":data.duoPosition} 갑니다</span>
             </div>
             <div id="miniBoardC" >
               <span> ${data.duoContent} </span>
             </div>
             <div id="miniBoardD" >
                  <img src="static/images/duo/position/${data.duoPosition}.png">
                  <a target="_target" href="#" class="nicknameClick">${data.duoGameId}</a>
                  <input type="hidden" class="mini-board-value" value = ${data.duoGameId.replaceAll(" ", "~").replaceAll("#", "-")}>
             </div>
             <div id="miniBoardE" >
                <img src="static/images/duo/tier/${data.duoTier}.png" class="miniBoardEImg"><br>
                 <img src="https://ddragon.leagueoflegends.com/cdn/14.8.1/img/champion/${champion[0]}.png" class="miniBoardEImg"><br>
                  <img src="https://ddragon.leagueoflegends.com/cdn/14.8.1/img/champion/${champion[1]}.png" class="miniBoardEImg"><br>
                    <img src="https://ddragon.leagueoflegends.com/cdn/14.8.1/img/champion/${champion[2]}.png" class="miniBoardEImg"><br>
                       <button type="button" class="copy-button" value="${data.duoGameId}">아이디 복사</button>
             </div>
                
           
        </div>
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


    addClickEventModalButton() {
     //글 입력(모달창 여는 버튼) 누를때

        const btn = document.getElementById("insert-btn");
        const modal = document.getElementById("modalWrap");
        const closeBtn = document.getElementById("closeBtn");
        const modalBody = document.getElementById("modalBody");

        //초기화 변수들
        const idValue = document.getElementById("input-duoGameid");
        const contentValue = document.getElementById("duoContentValue");
        const idCheck = document.getElementById("pass");
        const idCheckMessage = document.getElementById("modalGameIdCheckMessage");


        btn.onclick = function () {

            //글 작성 버튼 눌렸을때 "글 작성"(창이 안열려있는경우)에는 모달창을 보여주고 "글 작성 취소로"변경
            //반대의 경우에는 모달창을 지우면서 안에 있는 데이터들 초기화

            if (btn.innerText == "글 작성") {
                btn.innerText = "글 작성 취소";
                modal.style.display = "block";
            } else if (btn.innerText == "글 작성 취소") {
                btn.innerText = "글 작성";
                modal.style.display = "none";
                idValue.value = "";
                contentValue.value = "";
                idCheck.value = "X";
                idCheckMessage.innerHTML = "";

            }

        };

        //모달 창 안에 있는 X버튼 누를 때 창 닫히고 내부 데이터 초기화

        closeBtn.onclick = function () {
            modal.style.display = "none";
            btn.innerText = "글 작성";
            idValue.value = "";
            contentValue.value = "";
            idCheck.value = "X";
            idCheckMessage.innerHTML = "";
        };

        //모달 창 이외의 부분이 눌릴 때 창 닫히고 내부 데이터 초기화
        window.onclick = function (e) {
            if (!btn.contains(e.target) && !modalBody.contains(e.target)) {
                btn.innerText = "글 작성";
                modal.style.display = "none";
                idValue.value = "";
                contentValue.value = "";
                idCheck.value = "X";
                idCheckMessage.innerHTML = "";
            }
        };

    }

    addClickEventInsertButton() {

        const insertBtn = document.getElementById("insert-success-btn");

        //모든 정보를 입력하고 나서 입력 완료 버튼을 누르면 insert 진행되는 곳
        //하지만 아이디 체크를 진행하지 않았거나 유효하지 않은 아이디를 입력한 경우 passCheck이 "X"로 유지됨(아래의 addClickEventCheckButton에서)
        //유효한 아이디가 입력되면  passCheck이 "O"로 바뀌고 입력받은 데이터들을 duoObj에 저장한 후에 입력 진행
        //끝나고 나면 passCheck을 비롯한 나머지 초기화하고 1페이지 불러오기

        insertBtn.onclick = () => {
            const passCheck = document.getElementById("pass");
            if (passCheck.value.toString() === "X") {
                alert("아이디 확인을 진행해주세요");
            } else {
                const modal = document.getElementById("modalWrap");
                const GameIdValue = document.getElementById("input-duoGameid");
                const QueValue = document.querySelector('input[type=radio][name=duoQue]:checked');
                const PositionValue = document.querySelector('input[type=radio][name=duoPosition]:checked');
                const ContentValue = document.getElementById("duoContentValue");
                const idCheckMessage = document.getElementById("modalGameIdCheckMessage");

                duoObj.duoGameId = GameIdValue.value;
                duoObj.duoQue = QueValue.value;
                duoObj.duoPosition = PositionValue.value;
                duoObj.duoContent = ContentValue.value;


                DuoMainApi.getInstance().postInsertDuo();
                modal.style.display = "none";
                searchObj.searchQueValue = "";
                searchObj.searchPositionValue = "";
                searchObj.searchTierValue = "";
                searchObj.page = 1;
                DuoMainService.getInstance().getLoadAllDuoList();
                location.reload(true);
                //그냥 듀오리스트만 로드하면 복사가 안되는 문제가 있어서 reload로 화면 새로고침 해주었습니다.
                GameIdValue.value = "";
                ContentValue.value = "";
                idCheckMessage.innerHTML = "";
                passCheck.value = "X";
            }
        }
    }

    addClickEventCheckButton() {
        const checkBtn = document.getElementById("check-gameid-btn");
        const printMsg = document.getElementById("modalGameIdCheckMessage");


       //아이디 체크 버튼을 누를 때, 기본적으로 #이 붙어있지 않으면 "아이디 확인 필요" 메세지와 함께 alert 보여줌
        //#이 포함된 값이 들어가면 아이디 유효성 검사를 한 뒤에 puuid가 존재하면 "O"로 값 변경해준다

        checkBtn.onclick = function () {
            const givenNick = document.getElementById("input-duoGameid");
            const passCheck = document.getElementById("pass");

            if (!(givenNick.value.toString().includes("#"))) {
                passCheck.value = "X";
                printMsg.style.color = "red";
                printMsg.innerText = "아이디 확인 필요";
                alert('아이디 형식을 확인해주세요\n(게임 닉네임+#+태그)');
            } else {
                checkObj.duoGameId = givenNick.value;
                let resultflag = (DuoMainApi.getInstance().CheckNick());
                if (resultflag == 1) {

                    passCheck.value = "X";
                    printMsg.style.color = "red";
                    printMsg.innerText = "아이디 확인 필요";
                    alert('아이디가 존재하지 않거나 아이디 형식을 확인해주세요\n(게임 닉네임+#+태그)');
                } else {

                    passCheck.value = "O";
                    printMsg.style.color = "green";
                    printMsg.innerHTML = "아이디 통과";

                    alert('아이디 통과');
                }

            }
        }

    }

    addClickEventCopyButton() {
        const copyBtn = document.querySelectorAll(".copy-button");

        //아이디 옆의 복사 버튼을 누르면 복사완료 라는 alert와 함께 복사가 진행된다.

        copyBtn.forEach(function (data) {
            data.addEventListener('click', function () {

                window.navigator.clipboard.writeText(data.value);

                alert("복사 완료!");
            })


        });
    }

    addClickEventTierButton() {
        const tierSelectField = document.getElementById("tier-selectField");
        const tierList = document.getElementById("tier-list");
        const tierArrowIcon = document.getElementById("tier-arrowIcon");
        const tierOptions = document.querySelectorAll(".tier-options");
        const tierSelectText = document.getElementById("tier-selectText");
        const positionSelectText = document.getElementById("position-selectText");
        const queSelectText = document.getElementById("que-selectText");

        tierSelectField.onclick = function () {
            tierList.classList.toggle("tier-hide");

            tierArrowIcon.classList.toggle("tier-rotate");
            if (checkOpenedObj.tierOpen == 0) {
                checkOpenedObj.tierOpen = 1;
            } else if (checkOpenedObj.tierOpen == 1) {
                checkOpenedObj.tierOpen = 0;
            }

        }


        tierOptions.forEach(function (data) {
            data.addEventListener('click', function () {

                tierSelectText.innerHTML = this.textContent;
                tierList.classList.toggle("tier-hide");
                tierArrowIcon.classList.toggle("tier-rotate");
                checkOpenedObj.tierDoubleIn = 1;
                if (tierSelectText.innerText === "티어 전체") {
                    searchObj.searchTierValue = ""
                } else {
                    searchObj.searchTierValue = tierSelectText.innerText;
                }
                if (queSelectText.innerText === "게임타입 전체") {
                    searchObj.searchQueValue = ""
                } else {
                    searchObj.searchPositionValue = queSelectText.innerText;
                }
                if (positionSelectText.innerText === "상관 없음") {
                    searchObj.searchPositionValue = ""
                } else {
                    searchObj.searchPositionValue = positionSelectText.innerText;
                }

                DuoMainService.getInstance().getLoadAllDuoList();
                checkOpenedObj.tierOpen = 0;
            })


        });

        document.addEventListener('click', function (e) {

            if (!tierSelectField.contains(e.target) && checkOpenedObj.tierOpen == 1 && checkOpenedObj.tierDoubleIn == 0) {
                tierList.classList.toggle("tier-hide");
                tierArrowIcon.classList.toggle("tier-rotate");
                checkOpenedObj.tierOpen = 0;

            }
            checkOpenedObj.tierDoubleIn = 0;
        });

    }

    addClickEventPositionButton() {
        const positionSelectField = document.getElementById("position-selectField");
        //우리가 누르면 아래로 list가 열립니다.
        const positionList = document.getElementById("position-list");
        //selectField가 클릭 되면 list가 출력됩니다.
        const positionArrowIcon = document.getElementById("position-arrowIcon");
        //selectField옆에서 상황을 나타내주는 화살표 이미지 입니다.
        const positionOptions = document.querySelectorAll(".position-options");
        //List 안에 존재하는 모든 옵션들입니다.

        //아래 3개의 변수는 position option중에 하나를 택했을때 해당 조건으로 듀오 정보를 보여주기 위해 각 조건들의 현재 값이 필요해 설정했습니다.
        const tierSelectText = document.getElementById("tier-selectText");
        const positionSelectText = document.getElementById("position-selectText");
        const queSelectText = document.getElementById("que-selectText");

        positionSelectField.onclick = function () {
            //selectField를 누르면 하단에 리스트 출력하고 화살표 회전

            positionList.classList.toggle("position-hide");

            positionArrowIcon.classList.toggle("position-rotate");
            if (checkOpenedObj.positionOpen == 0) {
                checkOpenedObj.positionOpen = 1;
            } else if (checkOpenedObj.positionOpen == 1) {
                checkOpenedObj.positionOpen = 0;
            }
            //checkOpenedObj.positionOpen은 현재 list 창이 열려 있으면 1 닫혀있으면 0의 값을 가집니다.
        }

        positionOptions.forEach(function (data) {
            data.addEventListener('click', function () {

                //옵션이 클릭 되었을때 진행되는 곳입니다.
                // 처음으로 고른 곳의 Text를 SelectText에 입력하여 갱신되도록 해주었습니다.

                positionSelectText.innerHTML = this.textContent;


                positionList.classList.toggle("position-hide");


                positionArrowIcon.classList.toggle("position-rotate");
                checkOpenedObj.positionDoubleIn = 1;
                //이 부분과 아래 있는 window.click부분을 2번  거치는 것을 방지하기 위해 doubleIn을 1로 설정하고 아래에서 doubleIn이 0인지 확인 후 접근할수 있도록 했습니다.

                if (tierSelectText.innerText === "티어 전체") {
                    searchObj.searchTierValue = ""
                } else {
                    searchObj.searchTierValue = tierSelectText.innerText;
                }
                if (queSelectText.innerText === "게임타입 전체") {
                    searchObj.searchQueValue = ""
                } else {
                    searchObj.searchQueValue = queSelectText.innerText;
                }
                if (positionSelectText.innerText === "상관 없음") {
                    searchObj.searchPositionValue = ""
                } else {
                    searchObj.searchPositionValue = positionSelectText.innerText;
                }

                //클릭된 순간 다른 필터의 값을 받아와 그에 맞는 리스트를 출력하였습니다.
                DuoMainService.getInstance().getLoadAllDuoList();
                checkOpenedObj.positionOpen = 0;
            })


        });

        document.addEventListener('click', function (e) {
            if (!positionSelectField.contains(e.target) && checkOpenedObj.positionOpen == 1 && checkOpenedObj.positionDoubleIn == 0) {
                positionList.classList.toggle("position-hide");
                positionArrowIcon.classList.toggle("position-rotate");
                checkOpenedObj.positionOpen = 0;
            }
            checkOpenedObj.positionDoubleIn = 0;
        });
    }

    addClickEventQueButton() {
        //Que
        const queSelectField = document.getElementById("que-selectField");
        const queList = document.getElementById("que-list");
        const queArrowIcon = document.getElementById("que-arrowIcon");
        const queOptions = document.querySelectorAll(".que-options");
        const tierSelectText = document.getElementById("tier-selectText");
        const positionSelectText = document.getElementById("position-selectText");
        const queSelectText = document.getElementById("que-selectText");

        queSelectField.onclick = function () {
            queList.classList.toggle("que-hide");

            queArrowIcon.classList.toggle("que-rotate");
            if (checkOpenedObj.queOpen == 0) {
                checkOpenedObj.queOpen = 1;
            } else if (checkOpenedObj.queOpen == 1) {
                checkOpenedObj.queOpen = 0;
            }

        }

        queOptions.forEach(function (data) {
            data.addEventListener('click', function () {
                queSelectText.innerHTML = this.textContent;
                queList.classList.toggle("que-hide");
                queArrowIcon.classList.toggle("que-rotate");
                checkOpenedObj.queDoubleIn = 1;
                if (tierSelectText.innerText === "티어 전체") {
                    searchObj.searchTierValue = ""
                } else {
                    searchObj.searchTierValue = tierSelectText.innerText;
                }
                if (queSelectText.innerText === "게임타입 전체") {
                    searchObj.searchQueValue = ""
                } else {
                    searchObj.searchQueValue = queSelectText.innerText;
                }
                if (positionSelectText.innerText === "상관 없음") {
                    searchObj.searchPositionValue = ""
                } else {
                    searchObj.searchPositionValue = positionSelectText.innerText;
                }

                DuoMainService.getInstance().getLoadAllDuoList();
                checkOpenedObj.queOpen = 0;
            })


        });

        document.addEventListener('click', function (e) {
            if (!queSelectField.contains(e.target) && checkOpenedObj.queOpen == 1 && checkOpenedObj.queDoubleIn == 0) {

                queList.classList.toggle("que-hide");
                queArrowIcon.classList.toggle("que-rotate");
                checkOpenedObj.queOpen = 0;
            }
            checkOpenedObj.queDoubleIn = 0;
        });

    }

    addClickATag(){
        const aTag = document.querySelectorAll(".nicknameClick");
        const inputValue = document.querySelectorAll(".mini-board-value");
        
        aTag.forEach((tag, index) =>{
            tag.onclick = () =>{
                gameNameAndTagLine = inputValue[index].defaultValue.replaceAll("~", " ");
                let successFlag = DuoMainApi.getInstance().searchSummonerInfoByGameNameAndTagLine();

                if(successFlag){
                    location.href = `/record/${gameNameAndTagLine}`;
                } 
            }
        });
    }
}






