window.onload = () => {

    BoardMainService.getInstance().getLoadAllBoardList();
    //BoardMainService.getInstance().getWriteButtonRequirement();
   // BoardMainService.getInstance().getLoadBoardCategoryButton();

    ComponentEvent.getInstance().addClickEventInsertButton();
    ComponentEvent.getInstance().addClickEventsearchButton();
    ComponentEvent.getInstance().loadViewCountHotButton();
    ComponentEvent.getInstance().loadlikeCountHotButton();
    //ComponentEvent.getInstance().addClickEventBoardListByBoardGrp();
}

let searchObj = {
    page: 1, // 조회하고자 하는 페이지 번호 1
    searchKey: "", // 검색 키: 제목, 작성자, 내용 등
    searchValue: "", // 검색 값: 사용자가 입력한 검색어
    limit: "Y", // 결과 제한 여부
    count: 10, // 한 페이지에 표시할 데이터 수 10

}



const boardObj = {
    boardIndex: "", // 게시글 번호
    boardSubject: "", // 게시글 제목
    userIndex: "", // 작성자 번호
    userNick: "", // 작성자 Nick
    replyCount: "", // 댓글 수
    boardContent: "", // 게시글 내용 (필요시)
    boardVisit: 0, // 조회수
    boardRegDate: "", // 작성일
    boardLikeCount:0,
   /* 파일 업로드: "",*/
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

//서버와 통신하기 위한 API 호출 //AJAX 요청을 보내고 서버로부터 데이터를 받아옴
class BoardMainApi {

       //<!-- 클래스의 정적 프로퍼티와 정적 메서드를 사용하여 싱글톤 패턴을 구현하는 방법 -->
        static #instance = null;
        static getInstance(){ //  getInstance() 메서드를 호출하여 클래스의 인스턴스를 얻을 수 있고, 해당 인스턴스는 애플리케이션 전체에서 공유
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
                    data: searchObj, // data : 서버로 보낼 데이터를 담은 객체
                    dataType: "json",
                    success : response => { //'fuction' 키워드 대신 '=>'로 함수 정의 (return 암묵적)
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
                     "searchValue":searchObj.searchValue}, // data : 서버로 보낼 데이터를 담은 객체
                     dataType: "json",
                     success : response => { //'fuction' 키워드 대신 '=>'로 함수 정의 (return 암묵적)
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









}

// 비즈니스 로직을 처리 // API 클래스에서 받아온 데이터를 가공하고 뷰에 필요한 형태로 변환
//게시판 목록을 불러오는 메서드, 게시글 작성 버튼을 표시하는 메서드 등을 포함
class BoardMainService {
     static #instance = null;
     static getInstance(){
         if(this.#instance == null) {
             this.#instance = new BoardMainService();
         }
            return this.#instance;
     }


    getLoadAllBoardList(){
        console.log(searchObj);
        const responseData = BoardMainApi.getInstance().searchBoard(searchObj);
        const boardTable = document.querySelector(".board-table tbody");
       

        // //ajax를 못타는듯
        // const likeData = BoardMainApi.getInstance().likeCountBoard(boardLikeObj);
        // console.log("LikeData: "+likeData);

        boardTable.innerHTML = ''; // 초기화 필수
        responseData.forEach((data,index)=> {
            

        


            boardTable.innerHTML += `
            <tr class="board-row">
                <td>${data.boardIndex}</td>

                <td class="board-info">
                    <a href="/board/selectOne?boardIndex=${data.boardIndex}" value=${data.boardIndex}>${data.boardSubject}</a>
                    <span class = "reply-blue">[${data.replyCount}]</span>
                    <td class = "board-thumb"><img src="uploadimg/thumb_${data.boardUploadName}" width="50"></td

                </td>

                <td>${data.userNick}</td>
                <td>${data.boardRegDate}</td>
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
        console.log(totalcount);
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

     //작성일 계산
    /*loadBoardRegDate(){

        // 작성일을 계산하여 표시하는 함수
        function formatTimestamp(timestamp) {
            const now = new Date(); // 현재 시간
            const postDate = new Date(timestamp);
            const timeDiff = now - postDate;

            // 1일 전까지는 날짜를, 그 이후에는 몇 시간 전으로 표시
            if (timeDiff < 24 * 60 * 60 * 1000) { // 1일 = 24시간 * 60분 * 60초 * 1000밀리초
                const hoursDiff = Math.floor(timeDiff / (60 * 60 * 1000)); // 시간으로 변환
                return hoursDiff + "시간 전";
            } else {
                // 날짜를 특정 포맷으로 변환하여 반환 (예: 2024-04-13)
                const year = postDate.getFullYear();
                const month = String(postDate.getMonth() + 1).padStart(2, '0');
                const day = String(postDate.getDate()).padStart(2, '0');
                return `${year}-${month}-${day}`;
            }
        }

        // 작성일을 가져와서 포맷팅하여 출력하는 함수
        function displayFormattedDate(boardRegDate) {
            const formattedDate = formatTimestamp(boardRegDate);
            // 작성일을 특정 HTML 요소에 표시
            const boardRegDateElements = document.querySelectorAll(".board-reg-date");
            boardRegDateElements.forEach(element => {
                element.textContent = formattedDate;
            });
        }

        // 작성일을 표시
        displayFormattedDate(data.boardRegDate);
    }*/



  // javascript 를 이용해서 몇일전, 분, 시간, 일, 년 까지 구하는 함수
//     function timeForToday(value) {
//         const today = new Date();
//         const timeValue = new Date(value);

//         const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);
//         if (betweenTime < 1) return '방금전';
//         if (betweenTime < 60) {
//             return `${betweenTime}분전`;
//         }

//         const betweenTimeHour = Math.floor(betweenTime / 60);
//         if (betweenTimeHour < 24) {
//             return `${betweenTimeHour}시간전`;
//         }

//         const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
//         if (betweenTimeDay < 365) {
//             return `${betweenTimeDay}일전`;
//         }

//         return `${Math.floor(betweenTimeDay / 365)}년전`;
//  }











}




// 사용자 동작에 대한 이벤트 ( 검색버튼 클릭 > 검색기능 실행, 카테고리 버튼 클릭 > 해당 카테고리에 대한 게시글 목록 불러오기)
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
                
                //login권한이 없으면 게시글 작성 불가해서 오류가 나버림 아래처럼 안될듯?
                // if(role.getAuthority() == null){
                //    window.location.href = "http://localhost:8000/login" ;
                // }else{
                //    window.location.href = "http://localhost:8000/board/insert" ;
                //    console.log('버튼이 클릭되었습니다.');
                // } 
               
               
               
                window.location.href = "http://localhost:8000/board/insert" ;
                console.log('버튼이 클릭되었습니다.');

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
            console.log(searchKey);
            console.log(searchValue);

            // BoardMainService.getInstance().getLoadSearchBoardList();
             BoardMainService.getInstance().getLoadAllBoardList();



            console.log('버튼이 클릭되었습니다.');

        });

    }




    loadViewCountHotButton(){

        const likeBtn = document.getElementById("view-btn");
        likeBtn.addEventListener("click", function(){

            //BoardMainApi.getInstance().searchBoard();
            
            //boardObj.boardLikeCount = 50;

            const searchKey = $("#view-btn").val();
            const searchValue = 50;
            searchObj.searchKey = searchKey;
            searchObj.searchValue = searchValue;

            //console.log(boardObj.boardLikeCount);
            
            //searchObj.searchKey = boardObj.boardLikeCount;
            //searchObj.searchValue = 50;
            
            //if(boardObj.boardLikeCount >= 50){
                
            BoardMainService.getInstance().getLoadAllBoardList();
            //}
            

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
            //}
            
        });

    }






}




