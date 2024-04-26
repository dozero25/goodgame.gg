window.onload = () => {
    console.log("aaaaaaaaaaa");

    //MyPageService.getInstance().setMyPageObjValues();
   MyPageApi.getInstance().printReplyList();

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
    searchReplyList() {
        let returnData = "";

        $.ajax({
            async: false,
            type: "get",
            url: `http://localhost:8000/api/mypage/searchList/reply/${PrincipalApi.getInstance().getPrincipal().user.userIndex}`,
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
    printReplyList(){
        console.log("printReplyList()...",`${PrincipalApi.getInstance().getPrincipal().user.userIndex}`);
        let returnData = null;
        console.log("bbbbbbbbbbb");
        ReplyObj.userIndex = `${PrincipalApi.getInstance().getPrincipal().user.userIndex}`;
        $.ajax({
            async: false,
            type: "get",
            url: `http://localhost:8000/api/mypage/searchList/reply`,
            contentType: "application/json",
            data: {userIndex:ReplyObj.userIndex},
            dataType: "json",
            success: response => {
                console.log("response.data" + response.data);
               // returnData = response.data;

                 MyPageService.getInstance().printReplyElement(response.data);
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

    printReplyElement(replyData) {

        const tblContent = document.querySelector(".tbl-content-reply");
        console.log("tblContent"+tblContent);

        let rowContent = "";
        // 각 데이터를 반복하여 테이블에 추가
       for (const data of replyData) {
                console.log("data"+data);


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
     }//end fn

}
