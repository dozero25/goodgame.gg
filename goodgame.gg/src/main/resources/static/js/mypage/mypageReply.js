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

let searchObj = {
    limit: "Y",
    count: 5,
    page: 1,
    userIndex: ""
}
//2. Api
class MyPageApi {
    static #instance = null;
    static getInstance() {
        if (this.#instance == null) {
            this.#instance = new MyPageApi();

        }
        return this.#instance;
    }



    //(2). 정보 출력.  주소로 이동했습니다.
    printReplyList() {
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
            data: { userIndex: ReplyObj.userIndex },
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
        if (this.#instance == null) {
            this.#instance = new MyPageService();
        }
        return this.#instance;

    }

    printReplyElement() {
        const replyData = MyPageApi.getInstance().printReplyList();

        const tblContent = document.querySelector(".tbl-content-reply");
        console.log("tblContent" + tblContent);

        let rowContent = "";
        // 각 데이터를 반복하여 테이블에 추가
        for (const data of replyData) {
            console.log("data" + data);
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
}
