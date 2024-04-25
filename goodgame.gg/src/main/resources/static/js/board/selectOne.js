window.onload = () => {
    //HeaderService.getInstance().loadHeader();
   

    BoardSelectOneService.getInstance().setBoardSelectOneBoardIndex();
    BoardSelectOneService.getInstance().loadselectOneBoard();
    BoardSelectOneService.getInstance().getLoadBoardReply();
    //BoardSelectOneService.getInstance().openLoadReplyInsert();

    ComponentEvent.getInstance().addClickEventDeleteBoardButton();
    ComponentEvent.getInstance().addClickEventUpdateBoardButton();
    ComponentEvent.getInstance().addClickEventLikeButton();
    ComponentEvent.getInstance().addClickEventReplyInsertButton();//댓글
    ComponentEvent.getInstance().addClickEventReplyInputBtn();//대댓글
    ComponentEvent.getInstance().addClickEventReplyGroupDeleteBtn();
    ComponentEvent.getInstance().addClickEventReplySequenceDeleteBtn();
    ComponentEvent.getInstance().addClickEventOpenLoadBoardReplyUpdate();//댓글오픈앤로드용
    ComponentEvent.getInstance().addClickEventReplyGroupUpdateBtn();
    //ComponentEvent.getInstance().addClickEventReplyGroupButton();

}

function reply_insert(v) {

    
    console.log(v);
    
 }


const boardObj = {
    boardIndex: "", // 게시글 번호
    boardSubject: "", // 게시글 제목
    userIndex: "", // 작성자 번호
    userId: "",
    userNick: "", // 작성자 아이디
    replyCount: "", // 댓글 수
    boardContent: "", // 게시글 내용 (필요시)
    boardVisit: "", // 조회수
    boardRegDate: "", // 작성일
    boardUploadName: "",
    boardUploadSize: "",
    boardUploadLocation: "",
    boardLikeCount:0
    /* 파일 업로드: "",*/

}

const boardLikeObj = {
    boardLikeId: 0,
    boardIndex: 0, //게시글번호
    userIndex: 0, //유저번호
    boardLike: 0, //좋아요1,0
    boardBad: 0 //싫어요1,0
}

const replyObj = {
    replyIndex: 0, //id
    boardIndex: 0, //bnum
    userIndex: 0,
    userId: "",
    userNick:"",
    replyContent: "",
    replyGroup: 0, //댓글 reference
    replySequence: 0, //대댓글 step, level
    replyRegDate: ""
}





class BoardSelectOneApi {
    static #instance = null;
    static getInstance() {
        if (this.#instance == null) {
            this.#instance = new BoardSelectOneApi();
        }
        return this.#instance;
    }

    selectOneBoard() {
        let responseData = null;
        $.ajax({
            async: false,
            type: "get",
            url: `http://localhost:8000/api/board/selectOne/${boardObj.boardIndex}`,
            data: boardObj,
            dataType: "json",
            success: response => {
                // 조회수 증가
                this.visitBoard();
                // 추천수 가져오기
                this.findlikeBoard();
                responseData = response.data;
                console.log(response.data);

            },
            error: error => {
                console.log(error);
            }
        });
        return responseData;
    }



    deleteBoard(boardIndex) {
        let returnFlag = false;

        $.ajax({
            async: false,
            type: "post",
            url: `http://localhost:8000/api/board/delete?boardIndex=${boardObj.boardIndex}`,
            dataType: "json",
            success: response => {
                returnFlag = true;
            },
            error: error => {
                console.log(error);
            }
        });
        return returnFlag;
    }



    visitBoard() {

        $.ajax({
            async: false,
            type: "get",
            url: `http://localhost:8000/api/board/visit?boardIndex=${boardObj.boardIndex}`,
            dataType: "json",
            success: response => {
                console.log("조회수 증가 완료");
                console.log(response.data);
            },
            error: error => {
                console.log(error);
            }
        });

    }

    
    findlikeBoard(){
        let responseData = null;

        $.ajax({
            async: false,
            type: "get",
            url: `http://localhost:8000/api/board/find/like`,
            data: boardLikeObj,
            dataType: "json",
            success: response => {
                responseData = response.data;
                console.log("findlikeBoard : "+response.data);

            },
            error: error => {
                console.log(error);
            }
        });
        return responseData;
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



    likeBoard(boardLikeObj) {
        let returnFlag = false;

        $.ajax({
            async: false,
            type: "post",
            url: `http://localhost:8000/api/board/like`,
            data: boardLikeObj,
            dataType: "json",
            success: response => {
                returnFlag = true;
                console.log("=======좋아요 성공======");
            },
            error: error => {
                console.log("=======좋아요 실패======");
                console.log(error);
            }
        });
        return returnFlag;

    }


    likeBadCancel(boardLikeObj) {
        let returnFlag = false;

        console.log("boardLikeObj : "+boardLikeObj.boardIndex);
        console.log("boardLikeObj : "+boardLikeObj.userIndex);

        $.ajax({
            async: false,
            type: "post",
            url: `http://localhost:8000/api/board/cancel`,
            data: boardLikeObj,
            dataType: "json",
            success: response => {
                returnFlag = true;
                console.log("=======좋아요 취소 성공======");
            },
            error: error => {
                console.log(boardLikeObj)
                console.log("=======좋아요 취소 실패======");
                console.log(error);
            }
        });
        return returnFlag;
    }



    //댓글
    insertBoardReplyGroup(){
        let successFlag = false;

        $.ajax({
            async: false,
            type: "post",
            url: "http://localhost:8000/api/board/selectOne/reply/insert", 
            contentType: "application/json",
            data: JSON.stringify(replyObj),
            dataType: "json",
            success: response => {
                successFlag = true;
            },
            error: error => {
                console.log(error);
            }
        });
        return successFlag;

    }
    
    insertBoardReplySequ(){
        let successFlag = false;
        console.log(replyObj.replyGroup);

        $.ajax({
            async: false,
            type: "post",
            url: `http://localhost:8000/api/board/selectOne/reply/insert/${replyObj.replyGroup}`,
            contentType: "application/json",
            data: JSON.stringify(replyObj),
            dataType: "json",
            success: response => {
                console.log("ajax용 replyGroup",replyObj.replyGroup);
                successFlag = true;
            },
            error: error => {
                console.log(error);
            }
        });
        return successFlag;


    }



    //http://localhost:8000/api/board/selectOne/reply?boardIndex=21
    //`http://localhost:8000/api/board/selectOne/reply/${boardObj.boardIndex}`
    getBoardReply(){
        let responseData = null;

        
        $.ajax({
            async: false,
            tyep:"get",
            url: `http://localhost:8000/api/board/selectOne/reply/${replyObj.boardIndex}`,
            dataType:"json",
            data : replyObj,
            success: response => {
                console.log("ajax replyBoard:"+replyObj.replyRegDate);
                responseData = response.data;
            },
            error: error => {
                console.log(error);
            }
        });
        return responseData;


    }


    updateBoardReply(){
        let successFlag = null;
        console.log(replyObj);

        $.ajax({
            async: false,
            type: "post",
            url: `http://localhost:8000/api/board/selectOne/reply/update`,
            contentType: "application/json",
            data: JSON.stringify(replyObj),
            dataType: "json",
            success: response => {
                successFlag = true;

                console.log("====수정 성공====");
            },
            error: error => {
                console.log("====수정 실패====");
                console.log(error);
                successFlag = false;
            }
        });
        return successFlag;


    }

    
    loadUpdateBoardReply(){
        let responseData = null;

        $.ajax({
            async: false,
            type: "get",
            url: `http://localhost:8000/api/board/selectOne/reply/update/${replyObj.replyIndex}`,
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


    deleteBoardReply(){
        let returnFlag = false;

        $.ajax({
            async: false,
            type: "post",
            url: `http://localhost:8000/api/board/selectOne/replyGroup/delete?replyIndex=${replyObj.replyIndex}&boardIndex=${replyObj.boardIndex}`,
            dataType: "json",
            success: response => {
                returnFlag = true;
                console.log("replyObj:"+replyObj);
            },
            error: error => {
                console.log(error);
            }
        });
        return returnFlag;

    }


    deleteBoardReplySqu(){
        let returnFlag = false;

        $.ajax({
            async: false,
            type: "post",
            url: `http://localhost:8000/api/board/selectOne/replySequence/delete?replyIndex=${replyObj.replyIndex}`,
            dataType: "json",
            success: response => {
                returnFlag = true;
            },
            error: error => {
                console.log(error);
            }
        });
        return returnFlag;

    }


    

}





class BoardSelectOneService {
    static #instance = null;
    static getInstance() {
        if (this.#instance == null) {
            this.#instance = new BoardSelectOneService();
        }
        return this.#instance;
    }

    setBoardSelectOneBoardIndex() {
        const URLSearch = new URLSearchParams(location.search);
        const principal = PrincipalApi.getInstance().getPrincipal();

        boardObj.boardIndex = URLSearch.get("boardIndex");
        boardLikeObj.boardIndex = URLSearch.get("boardIndex");
       // console.log("setBoardSelectOneBoardIndex : " + URLSearch.get("boardIndex"));
        // boardObj.boardIndex = parseInt(URLSearch.get("boardIndex"));
        // boardLikeObj.boardIndex = parseInt(URLSearch.get("boardIndex"));
        boardLikeObj.userIndex = principal.user.userIndex; 
        //console.log("setBoardSelectOneBoardIndex : " + principal.user.userIndex);

        replyObj.boardIndex = URLSearch.get("boardIndex");
        console.log("댓글용 setBoardSelectOneBoardIndex : " + URLSearch.get("boardIndex"));
    }

    //selectOneBoard
    loadselectOneBoard() {
        const responseData = BoardSelectOneApi.getInstance().selectOneBoard();
        const principal = PrincipalApi.getInstance().getPrincipal();
        const boardTitle = document.querySelector(".board-title");

        //  이걸 풀면 새로고침해서 들어왔을때 색적용 등 안됨 필수 코드
        let likeValue =  BoardSelectOneApi.getInstance().findlikeBoard();
        boardLikeObj.boardLike = likeValue; //${boardLikeObj.boardLike}


        // 날짜를 변환하여 원하는 형식으로 표시하기
        const regDate = new Date(responseData.boardRegDate);
        const formattedRegDate = `${regDate.getFullYear()}년 ${regDate.getMonth() + 1}월 ${regDate.getDate()}일 
                                    ${regDate.getHours() < 12 ? '오전' : '오후'} ${regDate.getHours() % 12 || 12}시 ${regDate.getMinutes()}분 `;            

        console.log(responseData);

        boardTitle.innerHTML = `

        <a href="/board">
            <button type="button" class="returnBoard">목록으로</button>
        </a>

            <div class = "sub">
                <table>
                    <tr>
                        <th class="boardSubject" colspan="2">${responseData.boardSubject}</th>
                    </tr>
                </table>
            </div>


            
        `;


        const boardMeta = document.querySelector(".board-meta");
        


        boardMeta.innerHTML = `
             <table>
                <hr class = "selectOne-hr">
                <tr>
                    <td><p>| 게시글 번호: ${responseData.boardIndex}</p></td>
                    <td><p>| 작성자: ${responseData.userNick}</p></td>
                    <td><p>| 작성날짜: ${formattedRegDate}</p></td>
                    <td><p>| 댓글수: ${responseData.replyCount}</p></td>
                    <td><p>| 조회수: ${responseData.boardVisit + 1}</p></td>
                    <td><p>| 추천수: ${responseData.boardLikeCount}</p></td>
                    <br>

                </tr>
            </table>
        `;



        const boardTable = document.querySelector(".board-content");


        boardTable.innerHTML = `
                
                <div class = "boardContent-box">
                    <textarea class="boardContent" id="boardContent" cols="80" rows="30" readonly>${responseData.boardContent}</textarea>
                </div>
                ${!responseData.boardUploadLocation ? '' : `<img src="/images/${responseData.boardUploadLocation}" class="boardFile" alt="boardfile" width="100" height="100">`}

                ${!responseData.boardUploadLocation ? '' : ` <a href="file:///C:/goodgame.gg/images/${responseData.boardUploadLocation}" target="_blank">파일링크</a>`}

                ${principal.user.userIndex == responseData.userIndex?
                `<div>
                    <button type="button" class="update-btn" value="${responseData.boardIndex}">글수정</button>
                    <button type="button" class="delete-btn" value="${responseData.boardIndex}">글삭제</button>
                </div>
                `:`
                <div>
                    <button type="button" class="update-btn" value="${responseData.boardIndex}"  style="display:none;">글수정</button>
                    <button type="button" class="delete-btn" value="${responseData.boardIndex}"  style="display:none;">글삭제</button>
                </div>`
            }
        `

        
        const buttonContainer = document.querySelector(".button-container");


        buttonContainer.innerHTML = `

              <button id="like-btn" class="like-btn">
                     ${boardLikeObj.boardLike == 1 ? '<span class="emoji">👍</span> Like!' : '<span class="emoji">👍</span> Like! '}
              </button> 
              

        `;

        // // 댓글 수정 삭제 버튼히얼
        // const boardReply = document.querySelector(".reply-btns");
      
        // boardReply.innerHTML = `
            
        //     <button type="button" class="reply-btn">댓글등록</button>
            
            
        

        // `;

    }

    // // // 사용자가 로그인되어 있는지 여부를 확인
    // const principal = PrincipalApi.getInstance().getPrincipal(); // 로그인되어 있다고 가정

    // // 텍스트 영역 선택
    // const replyContent = document.querySelector(".reply-content");

    // // 사용자가 로그인되어 있는 경우와 그렇지 않은 경우에 따라 텍스트 변경
    // if (principal.user.userIndex == responseData2.userIndex) {
    //     replyContent.textContent = "내용을 작성해주세요";
    // } else {
    //     replyContent.textContent = "😥OOPS, you need to Log-in";
    // }


    getLoadBoardReply(){
        const principal = PrincipalApi.getInstance().getPrincipal();
        const responseData = BoardSelectOneApi.getInstance().getBoardReply(); //reply_mst의 userIndex필요
        const replyTable = document.querySelector(".reply-table");   

        console.log("조건principal.user.userIndex: "+principal.user.userIndex);
   
        console.log("내용responseData.boardIndex: "+responseData.boardIndex); //undifined
        console.log(responseData);

        let num1 = 0;
        let num2 = 0;

        //여기 html 정리해야할듯

        responseData.forEach((data, index)=>{
            // 날짜를 변환하여 원하는 형식으로 표시하기
        //const repRegDate = new Date(responseData2.replyRegDate); // 댓글 등록 시간 null 
        const repRegDate = new Date(data.replyRegDate); 
        console.log("repRegDate:"+repRegDate);
        const formattedRegDate = `${repRegDate.getFullYear()}-${("0" + (repRegDate.getMonth() + 1)).slice(-2)}-${("0" + repRegDate.getDate()).slice(-2)} 
                                    ${("0" + repRegDate.getHours()).slice(-2)}:${("0" + repRegDate.getMinutes()).slice(-2)}:${("0" + repRegDate.getSeconds()).slice(-2)}`;            



            replyTable.innerHTML += `
            ${data.replySequence == 1 ?
                `
                <br>

                <table class = "reply-table" border=2 rules="none" style="background-color: #181818;">
                    <thead>
                        <tr>
                            <th>【 ${data.userNick} 】</th>
                            <td class="GroupRegDate" font-size: 0.8em;>${formattedRegDate}</td>
                        <tr>
                    </thead>
                    <tbody>
                         <tr>
                            <td class="GroupContent">${data.replyContent}</td>
                        </tr>
                        <tr>
                            <td>
                                <div class="reply-update-box" style="display:none">
                                    <input type="text" class="rply-update-input" value="${data.replyContent}">
                                    <button type="button" class="rply-update-btn" value="${data.replyIndex}">수정완료✔</button>
                                </div>
                            </td>
                        ${principal.user.userIndex == data.userIndex?`
                        </tr>
                    </tbody>
                    <tfoot class ="updateGroup-tfoot">
                        <tr>
                            <td>
                                <button type="button" id="reply-group-update"  data-value1="${data.replyIndex}"  data-value2="${data.boardIndex}" class="replyGroupUpdateBtn">수정</button>                           
                                <button type="button" id="reply-group-delete" class="replyGroupDeleteBtn" data-value1="${data.replyIndex}"  data-value2="${data.boardIndex}" >삭제</button>
                            </td>
                            `:`
                            <td style="display:none;">
                                 <button type="button" id="reply-group-update"  value="${data.replyIndex}" class="replyGroupUpdateBtn">수정</button>                                                     
                                 <button type="button" id="reply-group-delete" class="replyGroupDeleteBtn" value="${data.replyIndex}" style="display:none;">삭제</button>
                            </td>
                        </tr>
                        `
                        }
                    </tfoot>
                </table>
                <div class="replyGroupBtn-box">
                    <input class="replyGroup-input" value = "댓글을 입력하세요">
                    <button id= "rply" class="reg-gro-btn" onclick="reply_insert(${data.replyGroup})" value="${data.replyGroup}">답글쓰기✔</button>
                </div>` //참
                 : `` //거짓
            }

            ${data.replySequence != 1 && (data.replySequence == num2 || data.replyGroup == num1) ?
                `<br>
                 <table class="reply-squTable" style="margin-left:60px; font-size: 0.8em;">
                    <thead>
                        <th> ⤷&nbsp;&nbsp;&nbsp;&nbsp;【 ${data.userNick} 】 </th>
                        <td  style= “font-size: 0.8em;”>${formattedRegDate}</td>
                    </thead>
                        <tbody>
                            <td class = "SquenceContent">${data.replyContent}</td>
                        </tbody>
                    <tfoot>      
                        ${principal.user.userIndex == data.userIndex?
                            `
                            <tr>
                                <td>
                                    <button type="button" id="reply-sequence-update" data-value1="${data.replyIndex}"  data-value2="${data.boardIndex}" class="replySequenceUpdateBtn"  style="display:none;">수정</button>
                                    <button type="button" id="reply-sequence-delete" class="replySequenceDeleteBtn"  value="${data.replyIndex}">삭제</button>
                                </td>
                                `:`
                                <td style="display:none;">
                                     <button type="button" id="reply-sequence-update" value="${data.replyIndex}" class="replySequenceUpdateBtn">수정</button>
                                      <button type="button" id="reply-sequence-delete" class="replySequenceDeleteBtn" value="${data.replyIndex}" style="display:none;">삭제</button>
                                </td>
                            </tr>
                        `
                        }

                    </tfoot>
                </table>
                ` //참
                :`` //거짓
            }    
            `;
           
            if(data.replyGroup != num1) { 
                num1 += 1;
                return num2 = 1;
            }

            if(data.replySequence != num2) {
                num2 += 1;
            }
        });


    }


    setBoardReplyGroupContent(){
        const replyInsert = document.querySelector(".reply-content");
        const principal = PrincipalApi.getInstance().getPrincipal();
        //const insertTime = new Date();
       // const formattedRegDate = `${insertTime.getFullYear()}-${insertTime.getMonth()+1}-${insertTime.getDate()} ${insertTime.getHours()}:${insertTime.getMinutes()}:${insertTime.getSeconds()}`;

        replyObj.boardIndex = boardObj.boardIndex;
        replyObj.userIndex = principal.user.userIndex;
        replyObj.replyContent = replyInsert.value;
        //replyObj.replyRegDate = formattedRegDate;
    }



    
    //대댓글 내용 수정용(내용을 입력해주세요)
    setBoardReplySquenceContent(){ // replyGroup 값 필요
        //이미 replyIndex값 가지고 있기 때문에 replyContent만 주는 역할~

        const responseData = BoardSelectOneApi.getInstance().loadUpdateBoardReply(); //괄호안에 replyIndex?


                    
        //테스용
                    // const replyIndex = updateBtn.value; 
                    // replyObj.replyIndex = replyIndex; //replyIndex 할당
                    
                    
                    // document.querySelectorAll(".rply-update-input").forEach(replyInput => {
                    //     const replyContent = replyInput.value;
                    //     replyObj.replyContent = replyContent;
                        
                    // });
                    // console.log("replyObj.replyContent"+replyObj.replyContent); //null//
        //테스트용

        
        
        const replyUpdateBtns = document.querySelectorAll(".rely-update-btn");
        // replyUpdateBtns.forEach(updateBtn => {
        //     updateBtn.addEventListener("click", function() {
        //         // 현재 클릭된 수정 버튼의 부모 요소인 .reply-update-box 내의 입력란을 찾음
        //         const replyInput = updateBtn.parentElement.querySelector(".rply-update-input");
        //         console.log("1");
        //         console.log("replyInput"+replyInput);
        //         const replyContent = replyInput.value; // 수정된 내용을 가져옴
        //         const replyIndex = updateBtn.value; 
                
        //         console.log(`대댓글 내용 ::::: ${replyContent}`);
                
        //         // 여기서 필요한 처리 수행 (예: replyObj에 값 할당)
        //         // replyObj.userIndex = principal.user.userIndex; // 사용해야하면 프린시펄필요
        //         //replyObj.boardIndex = boardObj.boardIndex; //boardIndex 굳이 필요없는듯 (replyIndex니까)
        //         replyObj.replyIndex = replyIndex;//(이미 버튼 이벤트에서 replyIndex를 가지고 있음)
        //         //replyObj.replyGroup = responseData.data.replyGroup; //굳이 필요한가?
        //         replyObj.replyContent = replyContent; // 대댓글 내용 설정
                
        //         console.log("replyObj★:", replyObj);
       
        //     });
        




        // 포이치를 사용하여 각 입력란의 값을 설정 // 해당 replyIndex만 수정되는 방법이 없나? > 수정되는게 
        document.querySelectorAll(".rply-update-input").forEach(replyInput => {
        const replyContent = replyInput.value;
        console.log(`대댓글 내용 ::: ${replyContent}`);
        console.log("111111");
        console.log("replyInput"+replyInput.value);
  
        //replyObj.boardIndex = boardObj.boardIndex;
        replyObj.replyIndex = responseData.data.replyIndex;
        //replyObj.replyGroup = responseData.data.replyGroup;
        replyObj.replyContent = replyContent; // 대댓글 내용 설정
        
     
       
         


        




        });



        
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

    addClickEventDeleteBoardButton() {
        const deleteBtn = document.querySelector(".delete-btn");

        console.log(deleteBtn.value);

        deleteBtn.onclick = () => {
            if (confirm("정말 삭제하시겠습니까?")) {

                const boardIndex = deleteBtn.value;
                const deleteSuccess = BoardSelectOneApi.getInstance().deleteBoard(boardIndex);
                console.log(boardIndex);
                if (deleteSuccess) {
                    // 삭제 성공한 경우에만 페이지를 새로고침
                    location.href = "/board";
                } else {
                    // 삭제 실패 시 에러 처리
                    console.error("게시물 삭제에 실패했습니다.");
                    console.log(boardIndex);
                }
            }
        }
    }


    addClickEventUpdateBoardButton() {
        const updateBtn = document.querySelector(".update-btn");
        console.log(updateBtn.value);

        updateBtn.addEventListener("click", function () {
            window.location.href = `http://localhost:8000/board/update?boardIndex=${boardObj.boardIndex}`;
            console.log('버튼이 클릭되었습니다.')
        })

    }


    addClickEventLikeButton() {


        console.log("boardIndex : "+boardLikeObj.boardIndex);
        console.log("userIndex : "+boardLikeObj.userIndex);
        console.log("boardLike : "+boardLikeObj.boardLike);

        const likeBtn = document.getElementById('like-btn');

        if (boardLikeObj.boardLike == 1){

            likeBtn.style.backgroundColor = '#543478'; // 보라
            likeBtn.style.borderColor = '#543478'; // 보라
            likeBtn.style.color = '#fff'; // 흰색 텍스트

        }else{

            likeBtn.style.backgroundColor = '#000'; // 검정색
            likeBtn.style.borderColor = '#000'; // 검정색
            likeBtn.style.color = '#000'; // 검정색 텍스트


        }

  
        likeBtn.addEventListener("click", function () {
            

            if (boardLikeObj.boardLike == 1) { // 이미 boardLike=1일때

                BoardSelectOneApi.getInstance().likeBadCancel(boardLikeObj);

                likeBtn.style.backgroundColor = '#000'; // 검정색
                likeBtn.style.borderColor = '#000'; // 검정색
                likeBtn.style.color = '#000'; // 검정색 텍스트

                boardLikeObj.boardLike = 0;
                


            } else { // boardLike != 1일때
                BoardSelectOneApi.getInstance().likeBoard(boardLikeObj);

                likeBtn.style.backgroundColor = '#543478'; // 보라
                likeBtn.style.borderColor = '#543478'; // 보라
                likeBtn.style.color = '#fff'; // 흰색 텍스트


                //boardLikeObj.boardLike = 1;
                boardLikeObj.boardLike++;
                //BoardSelectOneService.getInstance().loadselectOneBoard();

            }

        })
 
    }//end likebutton

    //댓글 등록버튼 
    addClickEventReplyInsertButton(){
        const repInsertBtn = document.querySelector(".repInsert-btn"); // 댓글 등록버튼
        const repContent = document.querySelector(".reply-content"); //댓글html 주석부분 겹치는 이름있음



        repInsertBtn.addEventListener("click", function () {
            repContent.focus();
            BoardSelectOneService.getInstance().setBoardReplyGroupContent();
            const successFlag = BoardSelectOneApi.getInstance().insertBoardReplyGroup();
            
            if(successFlag){
                location.reload();
            }
            
        })

    }

    

    //대댓글 등록버튼
    addClickEventReplyInputBtn(){
        const replyGroupBtns = document.querySelectorAll(".reg-gro-btn"); // 1. 맨첫 댓글만 인식함(해결완). 2. onclick사용 
        const replyGroupInputs = document.querySelectorAll(".replyGroup-input");
        const principal = PrincipalApi.getInstance().getPrincipal();
        replyObj.userIndex = principal.user.userIndex;
        
        
        
        replyGroupBtns.forEach((btn, index) => {
            btn.addEventListener("click", function(){
                const replyGroup = btn.value; // 버튼 벨류값 각 버튼의 replyGroup 
                const replyGroupInput = replyGroupInputs[index];
                
                replyGroupInput.focus();
                console.log("replyGroup=",replyGroup); // replyGroup 값담김
                console.log("boardObj.boardIndex=",boardObj.boardIndex);
                replyObj.boardIndex = boardObj.boardIndex;
                replyObj.replyGroup = replyGroup;
                replyObj.replyContent = replyGroupInput.value;
                //BoardSelectOneService.getInstance().setBoardReplySequenceContent();
                const successFlag = BoardSelectOneApi.getInstance().insertBoardReplySequ();
               
                console.log(replyObj.replyGroup); // 0
                
                if(successFlag){
                    location.reload();
                }
            });
        });
    }


    addClickEventReplyGroupDeleteBtn(){
         const deleteGroupBtns = document.querySelectorAll(".replyGroupDeleteBtn");
         
         deleteGroupBtns.forEach((deleteBtn) => {
            deleteBtn.addEventListener("click", function(){
  
                 if (confirm("정말 삭제하시겠습니까?")) {
                        
                        const replyIndex = deleteBtn.getAttribute('data-value1'); //deleteBtn.value;
                        const boardIndex = deleteBtn.getAttribute('data-value2');
                        console.log("replyIndex, boardIndex:"+replyIndex+ ", " +boardIndex); 
                        replyObj.replyIndex = replyIndex; 
                        replyObj.boardIndex = boardIndex;
                        const deleteSuccess = BoardSelectOneApi.getInstance().deleteBoardReply();
                        if (deleteSuccess) {
                            alert("삭제를 완료했습니다.");
                            location.reload();
                        } else {
                            console.error("게시물 삭제에 실패했습니다.");
                            console.log(replyIndex,boardIndex);
                        }
                    }
                });
        });
        
   
    }

    addClickEventReplySequenceDeleteBtn(){
        const deleteSequenceBtns = document.querySelectorAll(".replySequenceDeleteBtn");

        deleteSequenceBtns.forEach(deleteBtn => {
            deleteBtn.onclick = () => {
                console.log("//");
                console.log("deleteBtn.value: "+deleteBtn.value);//replyboard값 찍힘

                if (confirm("정말 삭제하시겠습니까?")) {
                    
                    replyObj.replyIndex = deleteBtn.value;
                    const deleteSuccess = BoardSelectOneApi.getInstance().deleteBoardReplySqu();
                    if (deleteSuccess) {
                        alert("삭제를 완료했습니다.");
                        location.reload();
                    } else {
                        // 삭제 실패 시 에러 처리
                        console.error("게시물 삭제에 실패했습니다.");
                        console.log(replyIndex);
                    }
                }
            };
        });

    }

    //댓글 수정창 오픈,로드하는 이벤트(service로 load불러오기) 
    addClickEventOpenLoadBoardReplyUpdate(){
        const openReplyUpdateBtns = document.querySelectorAll(".replyGroupUpdateBtn"); // open btn
        const replyUpdateBoxes = document.querySelectorAll(".reply-update-box"); // update area + btn

        //BoardSelectOneService.getInstance().loadUpdateBoardReply();//여기 필요한지 고민좀(오픈하자마자 값이 있어야해서 필요할듯)
        
        //".rply-update-input" //update area
        //".rely-update-btn" //btn
        openReplyUpdateBtns.forEach((openBtn, index) => {
            openBtn.addEventListener("click", function(){
                const replyUpdateBox = replyUpdateBoxes[index];
                console.log("replyUpdateBox:"+replyUpdateBox);
                console.log("abc");

                //로드용 로직

                if(replyUpdateBox.style.display !== "none") {
                    replyUpdateBox.style.display = "none";
                }
                else {
                    replyUpdateBox.style.display = "block";
                }

             })

        })

    }








    //댓글 수정 완료하는 이벤트
    // addClickEventReplyGroupUpdateBtn(){ 
    //     const replyGroupBtns = document.querySelectorAll(".rely-update-btn");
       


    //         replyGroupBtns.forEach(updateBtn => {
    //             updateBtn.addEventListener("click", function(){
                    
                    
    //                 const replyIndex = updateBtn.value; 
    //                 replyObj.replyIndex = replyIndex; //replyIndex 할당
                    
                    
    //                 document.querySelectorAll(".rply-update-input").forEach(replyInput => {
    //                     const replyContent = replyInput.value;
    //                     replyObj.replyContent = replyContent;
                        
    //                 });
    //                 console.log("replyObj.replyContent"+replyObj.replyContent); //null//
    //                 BoardSelectOneService.getInstance().setBoardReplySquenceContent();//삭제하기
    //                 const successFlag = BoardSelectOneApi.getInstance().updateBoardReply();

    //                 if(successFlag) {
    //                     console.log(replyObj.replyIndex);
    //                     console.log(replyObj.replyGroup);
    //                     alert("수정이 완료되었습니다.");
    //                     location.reload();
                    
    //                 } else {
    //                     location.reload();
    //                     alert("수정이 실패되었습니다.");
    //                 }

                    
    
    //              })
    
    //         })

  
       
    // }

    //댓글 수정 완료하는 이벤트 (테스트용) //완료 // 이중으로 포이치문 돌리기가 어려워 한 곳에 만듬
    addClickEventReplyGroupUpdateBtn(){ 
        const replyUpdateBtns = document.querySelectorAll(".rply-update-btn");
        const replyUpdateInputs = document.querySelectorAll(".rply-update-input");
        
            replyUpdateBtns.forEach((btn, index) => {
                btn.addEventListener("click", function(){

                    BoardSelectOneApi.getInstance().loadUpdateBoardReply();
                    const replyIndex = btn.value; // 버튼 벨류값 각 버튼의 replyIndex
                    
                    const replyContent = replyUpdateInputs[index].parentElement.querySelector(".rply-update-input");
                    console.log("replyContent.value"+replyContent.value);
                    
                    
                    replyObj.replyIndex = replyIndex;
                    replyObj.replyContent = replyContent.value;
                    
     
                    const successFlag = BoardSelectOneApi.getInstance().updateBoardReply();

                    if(successFlag) {
                        console.log(replyObj.replyIndex);
                        console.log(replyObj.replyContent);
                        alert("수정이 완료되었습니다.");
                        location.reload();
                        
                    } else {
                        alert("수정이 실패되었습니다.");
                        location.reload();
                    }

                    
    
                 })
    
            })

  
       
    }


      
}