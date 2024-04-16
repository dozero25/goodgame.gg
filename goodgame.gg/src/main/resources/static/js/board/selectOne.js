window.onload = () => {
    /*HeaderService.getInstance().loadHeader();*/

    BoardSelectOneService.getInstance().setBoardSelectOneBoardIndex();
    BoardSelectOneService.getInstance().selectOneBoard();
    //BoardSelectOneService.getInstance().getLoadBoardReply();
   // BoardSelectOneService.getInstance().openloadBoardReplyWrite();

    ComponentEvent.getInstance().addClickEventDeleteBoardButton();
    ComponentEvent.getInstance().addClickEventUpdateBoardButton();
    ComponentEvent.getInstance().addClickEventLikeButton();
    //ComponentEvent.getInstance().addClickEventBadButton();

    //ComponentEvent.getInstance().addClickEventReplyRegisterButton();
    //ComponentEvent.getInstance().addClickEventReplyFirButton();
    //ComponentEvent.getInstance().addClickEventReplySecButton();
}


const boardObj = {
    boardIndex: "", // 게시글 번호
    boardSubject: "", // 게시글 제목
    userIndex: "", // 작성자 번호
    userId:"",
    userNick: "", // 작성자 아이디
    replyCount: "", // 댓글 수
    boardContent: "", // 게시글 내용 (필요시)
    boardVisit: "", // 조회수
    boardRegDate: "", // 작성일
    boardUploadName: "",
    boardUploadSize : "",
    boardUploadLocation: ""
   /* 파일 업로드: "",*/

}

const boardLikeObj = {
    boardLikeId: "",
    boardIndex: "", //게시글번호
    userIndex: "", //유저번호
    boardLike: "", //좋아요1,0
    boardBad:"" //싫어요1,0
}

const replyObj ={
    replyIndex: "", //id
    boardIndex: "", //bnum
    userIndex: "",
    userId: "",
    replyContent: "",
    replyGroup: "", //댓글 reference
    replySequence: "", //대댓글 step, level
    replyRegDate: ""
}



class BoardSelectOneApi {
    static #instance = null;
    static getInstance(){
        if(this.#instance == null) {
            this.#instance = new BoardSelectOneApi();
        }
        return this.#instance;
    }

    selectOneBoard(){
        let responseData = null;
         console.log("${boardObj.boardIndex}:"+`${boardObj.boardIndex}`);
         $.ajax({
            async: false,
            type: "get",
            url: `http://localhost:8000/api/board/selectOne`,
            data: boardObj,
            dataType: "json",
            success: response => {
                // 조회수 증가
                this.visitBoard();
                responseData = response.data;
                console.log(response.data);

            },
            error: error => {
                console.log(error);
            }
        });
        return responseData;
    }



    deleteBoard(boardIndex){
            let returnFlag = false;

            $.ajax({
                async: false,
                type: "post",
                url: `http://localhost:8000/api/board/delete?boardIndex=${boardObj.boardIndex}`,
                dataType:"json",
                success: response => {
                    returnFlag = true;
                },
                error: error => {
                    console.log(error);
                }
            });
            return returnFlag;
    }



    visitBoard(){
            console.log("${boardObj.boardIndex}:"+`${boardObj.boardIndex}`);
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


    likeBoard(){
        let returnFlag = false;
        console.log('returnFlag...', returnFlag);   //
        //const URLSearch = new URLSearchParams(location.search);
        boardLikeObj.boardLike = 1; // 콘솔 확인용으로 그냥 1 찍어둔것 값넘어가면 DB에서 1로 취급
//        boardLikeObj.boardIndex = 68;
//        boardLikeObj.userIndex = 10;
        console.log('boardLikeObj...', boardLikeObj);   //
        $.ajax({
            async: false,
            type: "post",
            url: `http://localhost:8000/api/board/like`,
            contentType:"application/json",
            data: JSON.stringify(boardLikeObj),
            dataType:"json",
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



    badBoard(){
         let returnFlag = false;

        $.ajax({
            async: false,
            type: "post",
            url: `http://localhost:8000/api/board/bad`,
            contentType:"application/json",
            data: JSON.stringify(boardLikeObj),
            dataType:"json",
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



    likeBadCancel(){
            let returnFlag = false;
            console.log('boardLikeObj...', boardLikeObj);
            console.log(boardLikeObj.boardLike);
            console.log(boardLikeObj.boardIndex);
            console.log(boardLikeObj.userIndex);
            $.ajax({
                async: false,
                type: "post",
                url: `http://localhost:8000/api/board/cancel`,
                dataType:"json",
                contentType: "application/json",
                data: boardLikeObj,
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




}






class BoardSelectOneService{
    static #instance = null;
    static getInstance(){
        if(this.#instance == null) {
            this.#instance = new BoardSelectOneService();
        }
        return this.#instance;
    }

    setBoardSelectOneBoardIndex() {
         const URLSearch = new URLSearchParams(location.search);
         boardObj.boardIndex = URLSearch.get("boardIndex");
         boardLikeObj.boardIndex = URLSearch.get("boardIndex");
         boardLikeObj.userIndex = URLSearch.get("userIndex");
    }

    //selectOneBoard
    selectOneBoard(){
        console.log(boardLikeObj);
        const responseData = BoardSelectOneApi.getInstance().selectOneBoard(boardObj);
        const boardTitle = document.querySelector(".board-title");

            boardTitle.innerHTML = `
            <div>
                <table>
                    <tr>
                        <th class="boardSubject" colspan="2">${responseData.boardSubject}</th>
                    </tr>
                </table>
            </div>
            `;


        const boardMeta = document.querySelector(".board-meta");
        //여기다가 ? 조회수


             boardMeta.innerHTML = `
             <table>
             <tr>
                <td><p>작성자번호: ${responseData.userIndex}</p></td>
                <td><p>&nbsp;| 작성자: ${responseData.userNick}</p></td>
                <td><p>&nbsp;| 작성날짜: ${responseData.boardRegDate}</p></td>
                <td><p>&nbsp;| 게시글 번호: ${responseData.boardIndex}</p></td>
                <td><p>&nbsp;| 댓글수: ${responseData.replyCount}</p></td>
                <td><p>&nbsp;| 조회수: ${responseData.boardVisit+1}</p></td>
                <td><p>&nbsp;| 추천수: ${boardLikeObj.boardLike}</p></td>
                <br>

             </tr>

            </table>
            `;



        const boardTable = document.querySelector(".board-content");


             boardTable.innerHTML = `



                <label>내용</label>
                    <div>
                        <textarea class="boardContent" id="boardContent" cols="100" rows="30" readonly>${responseData.boardContent}</textarea>
                    </div>
                /*<input type="file" class="boardUploadName" value="${responseData.boardUploadName}">
                <input type="hidden" value="${responseData.boardIndex}">*/


                <div>

                    <button type="button" class="update-btn" value="${responseData.boardIndex}">글수정</button>
                    <button type="button" class="delete-btn" value="${responseData.boardIndex}">글삭제</button>


                </div>
             `;


        const buttonContainer = document.querySelector(".button-container");


            buttonContainer.innerHTML = `


             <!-- 좋아요 버튼 -->
            <button id="like-btn" class="like-btn" value=${boardLikeObj.boardLike}>
                <span class="emoji">👍</span> Like
            </button>

            <!-- 관리자 주요 관리 -->
            <button id="bad-btn" class="bad-btn" >
                <span class="emoji">👎</span> Bad
            </button>



            `;


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

    addClickEventDeleteBoardButton(){
            const deleteBtn = document.querySelector(".delete-btn");

            console.log(deleteBtn.value);

            deleteBtn.onclick = () => {
                    if(confirm("정말 삭제하시겠습니까?")) {

                        const boardIndex = deleteBtn.value;
                        const deleteSuccess = BoardSelectOneApi.getInstance().deleteBoard(boardIndex);
                        console.log(boardIndex);
                        if(deleteSuccess) {
                            // 삭제 성공한 경우에만 페이지를 새로고침
                            location.href="/board";
                        } else {
                            // 삭제 실패 시 에러 처리
                            console.error("게시물 삭제에 실패했습니다.");
                            console.log(boardIndex);
                        }
                    }
            }
    }


    addClickEventUpdateBoardButton(){
            const updateBtn = document.querySelector(".update-btn");
            console.log(updateBtn.value);

            updateBtn.addEventListener("click", function() {
            window.location.href = "http://localhost:8000/board/update";
            console.log('버튼이 클릭되었습니다.')
            })

    }


    addClickEventLikeButton(){
            const likeBtn = document.getElementById('like-btn');
            const badBtn = document.getElementById('bad-btn');


            console.log(likeBtn.value);
            console.log(badBtn.value);

            likeBtn.style.backgroundColor = '#000'; // 검정색
            likeBtn.style.borderColor = '#000'; // 검정색
            likeBtn.style.color = '#000'; // 검정색 텍스트

            badBtn.style.backgroundColor = '#000'; // 검정색
            badBtn.style.borderColor = '#000'; // 검정색
            badBtn.style.color = '#000'; // 검정색 텍스트


            likeBtn.addEventListener("click", function() {
                 console.log("addEventListener...");
                //BoardSelectOneApi.getInstance().likeBoard();


                if(boardLikeObj.boardLike == 1){ // 이미 boardLike=1일때
                    console.log("★"+boardLikeObj.userIndex.value);
                    console.log("★"+boardLikeObj.boardLike.value);
                    console.log("★"+boardLikeObj.boardIndex.value);
                    console.log(boardLikeObj.boardLike); //1
                    BoardSelectOneApi.getInstance().likeBadCancel();
                    console.log(" boardLike = 1 >> ${boardLikeObj.boardLike} : " + `${boardLikeObj.boardLike}`);
                    console.log(boardLikeObj);

                    likeBtn.style.backgroundColor = '#000'; // 검정색
                    likeBtn.style.borderColor = '#000'; // 검정색
                    likeBtn.style.color = '#000'; // 검정색 텍스트

                    boardLikeObj.boardLike = 0;


                }else{ // boardLike != 1일때
                    BoardSelectOneApi.getInstance().likeBoard();
                    console.log(" boardLike = 0 >> ${boardLikeObj.boardLike} : " + `${boardLikeObj.boardLike}`);
                    console.log(boardLikeObj);

                    likeBtn.style.backgroundColor = '#e74c3c'; // 빨간색
                    likeBtn.style.borderColor = '#e74c3c'; // 빨간색
                    likeBtn.style.color = '#fff'; // 흰색 텍스트

                    boardLikeObj.boardLike = 1;

                }


            })
    //클릭하면 색변경 >> 색깔 지정? 여기서?
    //like api
    //like bad cancel api


    }//end likebutton



   /* addClickEventBadButton(){
            const likeBtn = document.getElementById('like-btn');
            const badBtn = document.getElementById('bad-btn');

             console.log(likeBtn.value);
             console.log(badBtn.value);

             likeBtn.style.backgroundColor = '#000'; // 검정색
             likeBtn.style.borderColor = '#000'; // 검정색
             likeBtn.style.color = '#000'; // 검정색 텍스트

             badBtn.style.backgroundColor = '#000'; // 검정색
             badBtn.style.borderColor = '#000'; // 검정색
             badBtn.style.color = '#000'; // 검정색 텍스트
    //bad api
    //like bad cancel api
             likeBtn.addEventListener("click", function() {
                 BoardSelectOneApi.getInstance().likeBoard();

                 if(boardLikeObj.boardBad == 1){
                     BoardSelectOneApi.getInstance().likeBadCancel();
                     console.log("${boardLikeObj.boardBad} : " + `${boardLikeObj.boardBad}`);
                     console.log(boardLikeObj);


                 }else{
                     BoardSelectOneApi.getInstance().badBoard();
                     console.log("${boardLikeObj.boardBad} : " + `${boardLikeObj.boardBad}`);
                     console.log(boardLikeObj);

                     badBtn.style.backgroundColor = '#3498db'; // 파란색
                     badBtn.style.borderColor = '#3498db'; // 파란색
                     badBtn.style.color = '#fff'; // 흰색 텍스트
                 }


             })

    }//end badbutton
*/



}