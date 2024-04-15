window.onload = () => {

    BoardMainService.getInstance().getLoadAllBoardList();
    //BoardMainService.getInstance().getWriteButtonRequirement();
   // BoardMainService.getInstance().getLoadBoardCategoryButton();

    ComponentEvent.getInstance().addClickEventInsertButton();
    ComponentEvent.getInstance().addClickEventsearchButton();
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
    boardVisit: "", // 조회수
    boardRegDate: "", // 작성일
   /* 파일 업로드: "",*/
    boardUploadName: "",
    boardUploadSize: "",
    boardUploadLocation:""



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
        console.log(searchObj);

        boardTable.innerHTML = "";

        responseData.forEach((data,index)=> {
            boardTable.innerHTML += `
            <tr>
                <td>${data.boardIndex}</td>
                <td>
                <a href="/board/selectOne?boardIndex=${data.boardIndex}" value=${data.boardIndex}>${data.boardSubject}</a>
                </td>
                <td>${data.userNick}</td>

                <td>${data.boardRegDate}</td>
                <td>${data.boardVisit}</td>
                <td>${data.replyCount}</td>
                <td>${data.boardLike}</td>
                <td><img src="uploadimg/thumb_${data.boardUploadName}" width="50"></td>

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






}




