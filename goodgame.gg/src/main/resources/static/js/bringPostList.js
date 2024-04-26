window.onload = () => {
    console.log("aaaaaaaaaaa");

    //MyPageService.getInstance().setMyPageObjValues();
   MyPageApi.getInstance().printBoardList();

}
//1.데이터를 저장할 변수 추가하기
let BoardObj = {
    boardIndex: "",
    boardSubject: "",
    userIndex: "",
    boardContent: "",
    userGender: "",
    boardVisit: "",
    boardGood: "",
    boardBad: "",
    boardUploadName: "",
    boardUploadSize: "",
    boardUploadLocation: "",
    boardRegDate:""

}
//2. Api
class MyPageApi {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new MyPageApi();

        }
        return this.#instance;
    }

//(1). 정보 가져오기.
    searchBoardList() {
        let returnData = "";

        $.ajax({
            async: false,
            type: "get",
            url: `http://localhost:8000/api/mypage/searchList/post/${PrincipalApi.getInstance().getPrincipal().user.userIndex}`,
            dataType: "json",
            success: response => {
                returnData = response.data;
                console.log(response);
            },
            error: error => {
                console.log(error);
            }
        });

        return returnData;

    }



//(2). 정보 출력.  주소로 이동했습니다.
    printBoardList(){
        console.log("printBoardList()...",`${PrincipalApi.getInstance().getPrincipal().user.userIndex}`);
        let returnData = null;
        console.log("bbbbbbbbbbb");
        BoardObj.userIndex = `${PrincipalApi.getInstance().getPrincipal().user.userIndex}`;
        $.ajax({
            async: false,
            type: "get",
            url: `http://localhost:8000/api/mypage/searchList/post`,
            contentType: "application/json",
            data: {userIndex:BoardObj.userIndex},
            dataType: "json",
            success: response => {
                console.log("response.data" + response.data);
               // returnData = response.data;

                 MyPageService.getInstance().printBoardElement(response.data);
            },
            error: error => {
                console.log(error);
            }
        });
        console.log("returnData" + returnData);
        return returnData;
    }

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

    printBoardElement(boardData) {
       // const boardData = MyPageApi.getInstance().searchBoardList(userIndex);
      //  console.log("boardData"+boardData);

        const tblContent = document.querySelector(".tbl-content-post");
        console.log("tblContent"+tblContent);

        let rowContent = "";
        // 각 데이터를 반복하여 테이블에 추가
       for (const data of boardData) {
                console.log("data"+data);
                console.log("data.boardIndex:"+data.boardIndex);

                rowContent += `

                        <tr>
                           <td>
                                <div class="getBoardElement-label"></div>
                                <label class="board-input">${data.boardIndex}</label>
                            </td>
                            <td>
                                  <div class="getBoardElement-label"></div>
                                  <label class="board-input">${data.boardSubject}</label>
                            </td>
                            <td>
                                 <div class="getBoardElement-label"></div>
                                 <label class="board-label">${data.boardContent}</label>
                            </td>
                            <td>
                                <div class="getBoardElement-label"></div>
                                <label class="board-input">${data.boardRegDate}</label>
                            </td>
                          </tr> `;


        }
        tblContent.innerHTML = rowContent;
     }//end fn

}