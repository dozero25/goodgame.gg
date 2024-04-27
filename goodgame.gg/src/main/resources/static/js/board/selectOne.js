window.onload = () => {

   
    BoardSelectOneService.getInstance().setBoardSelectOneBoardIndex();
    BoardSelectOneService.getInstance().loadselectOneBoard();
    BoardSelectOneService.getInstance().getLoadBoardReply();


    ComponentEvent.getInstance().addClickEventDeleteBoardButton();
    ComponentEvent.getInstance().addClickEventUpdateBoardButton();
    ComponentEvent.getInstance().addClickEventLikeButton();
    ComponentEvent.getInstance().addClickEventReplyInsertButton();
    ComponentEvent.getInstance().addClickEventReplyInputBtn();
    ComponentEvent.getInstance().addClickEventReplyGroupDeleteBtn();
    ComponentEvent.getInstance().addClickEventReplySequenceDeleteBtn();
    ComponentEvent.getInstance().addClickEventOpenLoadBoardReplyUpdate();
    ComponentEvent.getInstance().addClickEventReplyGroupUpdateBtn();


}



const boardObj = {
    boardIndex: "",
    boardSubject: "", 
    userIndex: "", 
    userId: "",
    userNick: "", 
    replyCount: "", 
    boardContent: "",
    boardVisit: "", 
    boardRegDate: "", 
    boardUploadName: "",
    boardUploadSize: "",
    boardUploadLocation: "",
    boardLikeCount:0
  

}

const boardLikeObj = {
    boardLikeId: 0,
    boardIndex: 0, 
    userIndex: 0, 
    boardLike: 0, 
    boardBad: 0 
}

const replyObj = {
    replyIndex: 0, 
    boardIndex: 0, 
    userIndex: 0,
    userId: "",
    userNick:"",
    replyContent: "",
    replyGroup: 0,
    replySequence: 0, 
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

        $.ajax({
            async: false,
            type: "post",
            url: `http://localhost:8000/api/board/selectOne/reply/insert/${replyObj.replyGroup}`,
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



    getBoardReply(){
        let responseData = null;

        $.ajax({
            async: false,
            tyep:"get",
            url: `http://localhost:8000/api/board/selectOne/reply/${replyObj.boardIndex}`,
            dataType:"json",
            data : replyObj,
            success: response => {
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

        boardLikeObj.userIndex = principal.user.userIndex; 

        replyObj.boardIndex = URLSearch.get("boardIndex");
    }


    loadselectOneBoard() {
        const responseData = BoardSelectOneApi.getInstance().selectOneBoard();
        const principal = PrincipalApi.getInstance().getPrincipal();
        const boardTitle = document.querySelector(".board-title");

        let likeValue =  BoardSelectOneApi.getInstance().findlikeBoard();
        boardLikeObj.boardLike = likeValue; 


        const regDate = new Date(responseData.boardRegDate);
        const formattedRegDate = `${regDate.getFullYear()}년 ${regDate.getMonth() + 1}월 ${regDate.getDate()}일 
                                    ${regDate.getHours() < 12 ? '오전' : '오후'} ${regDate.getHours() % 12 || 12}시 ${regDate.getMinutes()}분 `;            


        boardTitle.innerHTML = `
            <div>
            <a href="/board">
                 <button type="button" class="returnBoard">목록으로</button>
            </a>
             ${principal.user.userIndex == responseData.userIndex?
            `
                <button type="button" class="update-btn" value="${responseData.boardIndex}">글수정</button>
                <button type="button" class="delete-btn" value="${responseData.boardIndex}">글삭제</button>
            </div>
            `:`
            <div>
                <button type="button" class="update-btn" value="${responseData.boardIndex}"  style="display:none;">글수정</button>
                <button type="button" class="delete-btn" value="${responseData.boardIndex}"  style="display:none;">글삭제</button>
            </div>`
            }

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
             <table class="board-info-bar">
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
                ${!responseData.boardUploadLocation ? '' : `<img src="/images/${responseData.boardUploadLocation}" class="boardFile" alt="boardfile">`}

                
        `

        
        const buttonContainer = document.querySelector(".button-container");


        buttonContainer.innerHTML = `

              <button id="like-btn" class="like-btn">
                     ${boardLikeObj.boardLike == 1 ? '<span class="emoji">👍</span> Like!' : '<span class="emoji">👍</span> Like! '}
              </button> 
              

        `;


    }

  
    getLoadBoardReply(){
        const principal = PrincipalApi.getInstance().getPrincipal();
        const responseData = BoardSelectOneApi.getInstance().getBoardReply();
        const replyTable = document.querySelector(".reply-table");   


        let num1 = 0;
        let num2 = 0;


        responseData.forEach((data, index)=>{

        const repRegDate = new Date(data.replyRegDate); 
        
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
                                    <input type="text" class="rply-update-input" maxlength="300" value="${data.replyContent}">
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
                    <input class="replyGroup-input" value = "댓글을 입력하세요" maxlength="300">
                    <button id= "rply" class="reg-gro-btn" onclick="reply_insert(${data.replyGroup})" value="${data.replyGroup}">답글쓰기✔</button>
                </div>` 
                 : `` 
            }

            ${data.replySequence != 1 && (data.replySequence == num2 || data.replyGroup == num1) ?
                `<br>
                 <table class="reply-squTable" style="margin-left:70px; margin-right:10px; font-size: 0.8em;">
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
                `
                :``
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

        replyObj.boardIndex = boardObj.boardIndex;
        replyObj.userIndex = principal.user.userIndex;
        replyObj.replyContent = replyInsert.value;

    }



    
    //대댓글 내용 수정용
    setBoardReplySquenceContent(){ 
    

        const responseData = BoardSelectOneApi.getInstance().loadUpdateBoardReply(); 
        const replyUpdateBtns = document.querySelectorAll(".rely-update-btn");

        document.querySelectorAll(".rply-update-input").forEach(replyInput => {
        const replyContent = replyInput.value;
  
        replyObj.replyIndex = responseData.data.replyIndex;
        replyObj.replyContent = replyContent; 


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

        deleteBtn.onclick = () => {
            if (confirm("정말 삭제하시겠습니까?")) {

                const boardIndex = deleteBtn.value;
                const deleteSuccess = BoardSelectOneApi.getInstance().deleteBoard(boardIndex);

                if (deleteSuccess) {
                    location.href = "/board";
                } else {
                    console.error("게시물 삭제에 실패했습니다.");
                    console.log(boardIndex);
                }
            }
        }
    }


    addClickEventUpdateBoardButton() {
        const updateBtn = document.querySelector(".update-btn");

        updateBtn.addEventListener("click", function () {
            window.location.href = `http://localhost:8000/board/update?boardIndex=${boardObj.boardIndex}`;
            console.log('버튼이 클릭되었습니다.')
        })

    }


    addClickEventLikeButton() {


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
                likeBtn.style.color = '#fff'; // 흰색



                boardLikeObj.boardLike++;


            }

        })
 
    }

    //댓글 등록버튼 
    addClickEventReplyInsertButton(){
        const repInsertBtn = document.querySelector(".repInsert-btn"); 
        const repContent = document.querySelector(".reply-content"); 



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
        const replyGroupBtns = document.querySelectorAll(".reg-gro-btn");
        const replyGroupInputs = document.querySelectorAll(".replyGroup-input");
        const principal = PrincipalApi.getInstance().getPrincipal();
        replyObj.userIndex = principal.user.userIndex;
        
        
        
        replyGroupBtns.forEach((btn, index) => {
            btn.addEventListener("click", function(){
                const replyGroup = btn.value; 
                const replyGroupInput = replyGroupInputs[index];
                
                replyGroupInput.focus();

                replyObj.boardIndex = boardObj.boardIndex;
                replyObj.replyGroup = replyGroup;
                replyObj.replyContent = replyGroupInput.value;

                const successFlag = BoardSelectOneApi.getInstance().insertBoardReplySequ();

                
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


                if (confirm("정말 삭제하시겠습니까?")) {
                    
                    replyObj.replyIndex = deleteBtn.value;
                    const deleteSuccess = BoardSelectOneApi.getInstance().deleteBoardReplySqu();
                    if (deleteSuccess) {
                        alert("삭제를 완료했습니다.");
                        location.reload();
                    } else {
                        console.error("게시물 삭제에 실패했습니다.");
                        console.log(replyIndex);
                    }
                }
            };
        });

    }

    //댓글 수정창 오픈,로드하는 이벤트(service로 load불러오기) 
    addClickEventOpenLoadBoardReplyUpdate(){
        const openReplyUpdateBtns = document.querySelectorAll(".replyGroupUpdateBtn");
        const replyUpdateBoxes = document.querySelectorAll(".reply-update-box"); 

        openReplyUpdateBtns.forEach((openBtn, index) => {
            openBtn.addEventListener("click", function(){
                const replyUpdateBox = replyUpdateBoxes[index];


                if(replyUpdateBox.style.display !== "none") {
                    replyUpdateBox.style.display = "none";
                }
                else {
                    replyUpdateBox.style.display = "block";
                }

             })

        })

    }


    //댓글 수정 완료하는 이벤트 (테스트용) 
    addClickEventReplyGroupUpdateBtn(){ 
        const replyUpdateBtns = document.querySelectorAll(".rply-update-btn");
        const replyUpdateInputs = document.querySelectorAll(".rply-update-input");
        
            replyUpdateBtns.forEach((btn, index) => {
                btn.addEventListener("click", function(){

                    BoardSelectOneApi.getInstance().loadUpdateBoardReply();
                    const replyIndex = btn.value; // 버튼 벨류값 각 버튼의 replyIndex
                    
                    const replyContent = replyUpdateInputs[index].parentElement.querySelector(".rply-update-input");
                

                    replyObj.replyIndex = replyIndex;
                    replyObj.replyContent = replyContent.value;
                    
     
                    const successFlag = BoardSelectOneApi.getInstance().updateBoardReply();

                    if(successFlag) {            
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