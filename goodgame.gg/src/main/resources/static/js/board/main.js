window.onload = () => {
    HeaderService.getInstance().loadHeader();

    BoardMainService.getInstance().getLoadAllBoardList();

    ComponentEvent.getInstance().addClickEventInsertButton();
    ComponentEvent.getInstance().addClickEventsearchButton();
    ComponentEvent.getInstance().loadViewCountHotButton();
    ComponentEvent.getInstance().loadlikeCountHotButton();

}

let searchObj = {
    page: 1, 
    searchKey: "", 
    searchValue: "", 
    limit: "Y",
    count: 10, 

}



const boardObj = {
    boardIndex: "", 
    boardSubject: "", 
    userIndex: "", 
    userNick: "", 
    replyCount: "", 
    boardContent: "", 
    boardVisit: 0, 
    boardRegDate: "", 
    boardLikeCount:0,
    boardUploadName: "",
    boardUploadSize: "",
    boardUploadLocation:""


}

const boardLikeObj = {
    boardLikeId: 0,
    boardIndex: 0,
    userIndex: 0,
    boardLike: 0,
    boardBad: 0


}


class BoardMainApi {
        static #instance = null;
        static getInstance(){ 
            if(this.#instance == null) {
                this.#instance = new BoardMainApi();
            }
            return this.#instance;
        }

    searchBoard(searchObj){
            let returnData = null;

        $.ajax({
            async : false,
            type: "get",
            url: `http://localhost:8000/api/board/search`,
            data: searchObj, 
            dataType: "json",
            success : response => { 
                console.log("Ajax 요청 성공:");
                console.log(response);
                returnData = response.data;
            },
            error: error => {
                console.error("ajax 요청 오류");
                console.log(error);
            }
        });
        return returnData;
    }



    //페이징
    searchBoardTotalCount(){
                let returnData = null;

        $.ajax({
            async : false,
            type: "get",
            url: `http://localhost:8000/api/board/totalCount`,
            data: {"searchKey":searchObj.searchKey,
            "searchValue":searchObj.searchValue}, 
            dataType: "json",
            success : response => { 
                console.log("Ajax 요청 성공:");
                console.log(response);
                returnData = response.data;
            },
            error: error => {
                console.error("ajax 요청 오류");
                console.log(error);
            }
        });
        return returnData;
    }


    //조회수카운트
    likeCountBoard(boardLikeObj){
    let responseData = null;

        $.ajax({
            async: false,
            type: "get",
            url: `http://localhost:8000/api/board/like/count`,
            data: boardLikeObj,
            dataType: "json",
            success: response => {
                responseData = response.data;
                console.log("likeCountBoard : "+response.data);

            },
            error: error => {
                console.log(error);
            }
    });
        return responseData;
    }


    fileSelectOneBoard(boardIndex) {
        boardObj.boardIndex = boardIndex;
        console.log(boardObj.boardIndex);
             let responseData = null;

                   $.ajax({
                       async: false,
                       type: "get",
                       url: `http://localhost:8000/api/board/update/${boardObj.boardIndex}`,
                       dataType: "json",
                       success: response => {
                           responseData = response;

                           console.log(responseData);
                       },
                       error: error => {
                           console.log(error);
                       }
                   });
                   return responseData;
        }






}

class BoardMainService {
     static #instance = null;
     static getInstance(){
         if(this.#instance == null) {
             this.#instance = new BoardMainService();
         }
            return this.#instance;
     }


    getLoadAllBoardList(){
        const responseData = BoardMainApi.getInstance().searchBoard(searchObj);
        const boardTable = document.querySelector(".board-table tbody");

 
        const formatTimeAgo = timestamp => {
            const currentTime = new Date();
            const previousTime = new Date(timestamp);
        
            const timeDifference = currentTime - previousTime;
        

            const seconds = Math.floor(timeDifference / 1000);
        
            if (seconds < 60) {
                return '방금 전';
            } else if (seconds < 3600) {
                const minutes = Math.floor(seconds / 60);
                return `${minutes}분 전`;
            } else if (seconds < 86400) {
                const hours = Math.floor(seconds / 3600);
                return `${hours}시간 전`;
            } else {
                const days = Math.floor(seconds / 86400);
                return `${days}일 전`;
            }
        };



        boardTable.innerHTML = ''; // 초기화 필수
        responseData.forEach((data,index)=> {
            const formattedRegDate = formatTimeAgo(data.boardRegDate);
            console.log(data.boardIndex);
            const boardIndex = data.boardIndex;
            const thumb = BoardMainApi.getInstance().fileSelectOneBoard(boardIndex);

            console.log(thumb.data);
            console.log(thumb.data.boardUploadLocation);


            boardTable.innerHTML += `
            <tr class="board-row">
                <td>${data.boardIndex}</td>

                <td class="board-info">
                    <a href="/board/selectOne?boardIndex=${data.boardIndex}" class="board-href" value=${data.boardIndex}>${data.boardSubject}</a>
                    <span class = "reply-blue">[${data.replyCount}]</span>
                    <td class = "board-thumb">  ${!thumb.data.boardUploadLocation ? '' : `<img src="/images/${thumb.data.boardUploadLocation}" class="boardFile" alt="boardfile" width="35" height="35">`}</td>

                </td>

                <td>${data.userNick}</td>
                <td>${formattedRegDate}</td>
                <td>${data.boardVisit}</td>
                <td>${data.boardLikeCount}</td>


            </tr>
            `;
        });
        this.loadPageController();


    }

    loadPageController() {
        const pageController = document.querySelector(".page-controller");

        const totalcount = BoardMainApi.getInstance().searchBoardTotalCount(searchObj);
  
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
                this.getLoadAllBoardList();
            }
        }

        if (searchObj.page != maxPageNumber) {
            const preButton = pageController.querySelector(".next-button");
            preButton.classList.remove("disabled");

            preButton.onclick = () => {
                searchObj.page++;

                this.getLoadAllBoardList();
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
                    this.getLoadAllBoardList();
                }
            }
        });
    }

}




class ComponentEvent{
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new ComponentEvent();
        }
        return this.#instance;
    }

    addClickEventInsertButton(){
            
            const insertButton = document.querySelector(".insert-button");
            

            insertButton.addEventListener("click", function() {
                const responseData = BoardMainApi.getInstance().searchBoard();
                const principal = PrincipalApi.getInstance().getPrincipal();

                if(principal === null){
                     window.location.href = "http://localhost:8000/login";
                }else{
                    window.location.href = "http://localhost:8000/board/insert";
                    console.log('버튼이 클릭되었습니다.');
                }   

            });

    }


    addClickEventsearchButton(){


        const searchBtn = document.getElementById("searchBtn");
        console.log(searchBtn);
        searchBtn.addEventListener("click", function() {

            const searchKey = $("#searchKey").val();
            const searchValue = $("#searchValue").val();
            searchObj.searchKey = searchKey;
            searchObj.searchValue = searchValue;

             BoardMainService.getInstance().getLoadAllBoardList();

            console.log('버튼이 클릭되었습니다.');

        });

    }




    loadViewCountHotButton(){

        const likeBtn = document.getElementById("view-btn");
        likeBtn.addEventListener("click", function(){

            const searchKey = $("#view-btn").val();
            const searchValue = 50;
            searchObj.searchKey = searchKey;
            searchObj.searchValue = searchValue;


            BoardMainService.getInstance().getLoadAllBoardList();


        });
            
    }

    loadlikeCountHotButton(){

  
        const viewBtn = document.getElementById("like-btn");
        viewBtn.addEventListener("click", function(){
            

            const searchKey = $("#like-btn").val();
            const searchValue = 3;
            searchObj.searchKey = searchKey;
            searchObj.searchValue = searchValue;
  
            BoardMainService.getInstance().getLoadAllBoardList();
            
        });

    }


}




