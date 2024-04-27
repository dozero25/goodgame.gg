window.onload = () => {
    MyPageHeaderService.getInstance().loadHeader();
    SidebarService.getInstance().loadSidebar();

    //MyPageService.getInstance().setMyPageObjValues();
    MyPageService.getInstance().printReplyElement();

}
//1.데이터를 저장할 변수 추가하기
let ReplyObj = {
    replyIndex: "",
    boardIndex: "",
    userIndex: "",
    replyContent: "",
    replyGroup: "",
    replySequence: "",
    replyRegDate: "",
};


const boardObj = {
    boardIndex: "",
    boardSubject: "",
    userIndex: "",
    boardContent: "",
    boardVisit: "",
    boardRegDate: "",
    boardUploadName: "",
    boardUploadSize: "",
    boardUploadLocation: ""
}

let searchObj={
limit:"Y",
count :5,
page:1,
userIndex:""
};
//2. Api
class MyPageApi {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new MyPageApi();

        }
        return this.#instance;
    }



//(2). 정보 출력.  주소로 이동했습니다.
    printReplyList(){
        let returnData = null;
         searchObj.userIndex = `${PrincipalApi.getInstance().getPrincipal().user.userIndex}`;
        $.ajax({
            async: false,
            type: "get",
            url: `http://localhost:8000/api/mypage/searchList/reply`,
            contentType: "application/json",
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
getTotalReplyCount() {
    //정보(searchObj에 부합하는)의 전체 개수를 가져온다

        let returnData = null;

        ReplyObj.userIndex = `${PrincipalApi.getInstance().getPrincipal().user.userIndex}`;


    $.ajax({
        async: false,
        type: "get",
        url: "http://localhost:8000/api/mypage/getTotalReplyCount",
 data: {userIndex:ReplyObj.userIndex},
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

//getBoardData(){
//
// let responseData = null;
//        $.ajax({
//            async: false,
//            type: "get",
//            url: `http://localhost:8000/api/board/selectOne/${boardObj.boardIndex}`, // BoardData 필요
//            data: boardObj,
//            dataType: "json",
//            success: response => {
//                responseData = response.data;
//                console.log(response.data);
//
//            },
//            error: error => {
//                console.log(error);
//            }
//        });
//        return responseData;
//
//}







}///searchList/post
//3. Servicce
class MyPageService {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new MyPageService();
        }
        return this.#instance;

    }

    printReplyElement() {
const replyData=MyPageApi.getInstance().printReplyList();

        const tblContent = document.querySelector(".tbl-content-reply");
        console.log("tblContent"+tblContent);

        let rowContent = "";
        // 각 데이터를 반복하여 테이블에 추가
       for (const data of replyData) {
                console.log("data"+data);
//       const boardData = MyPageApi.getInstance().getBoardData();//replyIndex boardSubject변경예정
//       console.log(boardData);

                rowContent += `

                        <tr>
                           <td>
                                <div class="getReplyElement-label"></div>
                                <label class="reply-input">${data.boardIndex}</label>
                            </td>
                            <td>
                                  <div class="getReplyElement-label"></div>
                                  <label class="reply-input">${data.replyIndex}</label>
                            </td>
                            <td>
                                 <div class="getReplyElement-label"></div>
                                 <label class="reply-label">${data.replyContent}</label>
                            </td>
                            <td>
                                <div class="getReplyElement-label"></div>
                                <label class="reply-input">${data.replyRegDate}</label>
                            </td>
                          </tr> `;


        }
        tblContent.innerHTML = rowContent;
          this.loadPageController();
     }//end fn




loadPageController() {
    //페이징 담당: 페이징에 필요한 것들이 전부 들어있고, 주의 해야할 점은 페이지 값이 바뀌면 getLoadAllDuoList를 통해 리스트를 다시 불러와야한다.

    const pageController = document.querySelector(".page-controller");

    const totalcount = MyPageApi.getInstance().getTotalReplyCount();


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
            this.printReplyElement();
        }
    }

    if (searchObj.page != maxPageNumber) {
        const preButton = pageController.querySelector(".next-button");
        preButton.classList.remove("disabled");

        preButton.onclick = () => {
            searchObj.page++;

            this.printReplyElement();
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
                this.printReplyElement();
            }
        }
    });
}
}
