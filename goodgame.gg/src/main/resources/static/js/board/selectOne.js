window.onload = () => {

    BoardSelectOneService.getInstance().setBoardSelectOneBoardIndex();
    BoardSelectOneService.getInstance().loadselectOneBoard();

    ComponentEvent.getInstance().addClickEventDeleteBoardButton();
    ComponentEvent.getInstance().addClickEventUpdateBoardButton();
    ComponentEvent.getInstance().addClickEventLikeButton();

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



    badBoard(boardLikeObj) {
        let returnFlag = false;

        $.ajax({
            async: false,
            type: "post",
            url: `http://localhost:8000/api/board/bad`,
            data: boardLikeObj,
            dataType: "json",
            success: response => {
                returnFlag = true;
                console.log(boardLikeObj)
                console.log("=======싫어요 성공======");
                
            },
            error: error => {
                console.log("=======싫어요 실패======");
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
        const princiapl = PrincipalApi.getInstance().getPrincipal();

        boardObj.boardIndex = URLSearch.get("boardIndex");
        boardLikeObj.boardIndex = URLSearch.get("boardIndex");
        console.log("setBoardSelectOneBoardIndex : " + URLSearch.get("boardIndex"));
        // boardObj.boardIndex = parseInt(URLSearch.get("boardIndex"));
        // boardLikeObj.boardIndex = parseInt(URLSearch.get("boardIndex"));
        boardLikeObj.userIndex = princiapl.user.userIndex; 
        console.log("setBoardSelectOneBoardIndex : " + princiapl.user.userIndex);


        console.log(boardLikeObj);
    }

    //selectOneBoard
    loadselectOneBoard() {
        const responseData = BoardSelectOneApi.getInstance().selectOneBoard();
        const boardTitle = document.querySelector(".board-title");

        //  이걸 풀면 새로고침해서 들어왔을때 색적용 등 안됨 필수 코드
        let likeValue =  BoardSelectOneApi.getInstance().findlikeBoard();
        boardLikeObj.boardLike = likeValue; //${boardLikeObj.boardLike}

    


        console.log(responseData);

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
        


        boardMeta.innerHTML = `
             <table>
             <tr>
                <td><p>작성자번호: ${responseData.userIndex}</p></td>
                <td><p>&nbsp;| 작성자: ${responseData.userNick}</p></td>
                <td><p>&nbsp;| 작성날짜: ${responseData.boardRegDate}</p></td>
                <td><p>&nbsp;| 게시글 번호: ${responseData.boardIndex}</p></td>
                <td><p>&nbsp;| 댓글수: ${responseData.replyCount}</p></td>
                <td><p>&nbsp;| 조회수: ${responseData.boardVisit + 1}</p></td>
                <td><p>&nbsp;| 추천수: ${responseData.boardLikeCount}</p></td>
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

        // if princiapl != null ? 분기처리하기~!~!~!
        const buttonContainer = document.querySelector(".button-container");


        buttonContainer.innerHTML = `

              <!-- 좋아요 버튼 -->
              <button id="like-btn" class="like-btn">
                     ${boardLikeObj.boardLike == 1 ? '<span class="emoji">👍</span> Like' : '<span class="emoji">👍</span> Like'}
              </button>  

              <!-- 싫어요 버튼 -->
              <!-- <button id="bad-btn" class="bad-btn"> -->
              <!--       ${boardLikeObj.boardBad == 1 ? '<span class="emoji">👍</span> ' : '<span class="emoji">👍</span> Bad'} -->
              <!-- </button> -->
   
        `;

        // 댓글 히얼
        const boardReply = document.querySelector(".reply-btns");
        const princiapl = PrincipalApi.getInstance().getPrincipal();

        
        boardReply.innerHTML = `
            
            ${principal.user.userIndex == responseData.userIndex?
                `<a href="/board/update?boardIndex=${responseData.boardIndex}">
                <button type="button" class="replyUpdate-btn">수정</button>
                </a>
                <a>
                    <button type="button" class="replyDelete-btn" value="${responseData.boardIndex}">삭제</button>
                </a>
                `:`
                <a href="/board/update?boardIndex=${responseData.boardIndex}" style="display:none;">
                <button type="button" class="replyUpdate-btn">수정</button>
                </a>
                <a>
                    <button type="button" class="replyDelete-btn" value="${responseData.boardIndex}" style="display:none;">삭제</button>
                </a>
                `
        }

                
        
        
        
        
        
                <button type="button" class="reply-btn">댓글 등록</button>


        `;

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

            likeBtn.style.backgroundColor = '#e74c3c'; // 빨간색
            likeBtn.style.borderColor = '#e74c3c'; // 빨간색
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

                likeBtn.style.backgroundColor = '#e74c3c'; // 빨간색
                likeBtn.style.borderColor = '#e74c3c'; // 빨간색
                likeBtn.style.color = '#fff'; // 흰색 텍스트


                boardLikeObj.boardLike = 1;

            }

        





















        // console.log("boardIndex : "+boardLikeObj.boardIndex);
        // console.log("userIndex : "+boardLikeObj.userIndex);
        // console.log("boardLike : "+boardLikeObj.boardLike);

        // const likeBtn = document.getElementById('like-btn');
        // const badBtn = document.getElementById('bad-btn');

        // if (boardLikeObj.boardLike == 1){
            

        // }else{


        // }

        // likeBtn.style.backgroundColor = '#000'; // 검정색
        // likeBtn.style.borderColor = '#000'; // 검정색
        // likeBtn.style.color = '#000'; // 검정색 텍스트

        // likeBtn.addEventListener("click", function () {
            

        //     if (boardLikeObj.boardLike == 1) { // 이미 boardLike=1일때

        //         BoardSelectOneApi.getInstance().likeBadCancel(boardLikeObj);

        //         likeBtn.style.backgroundColor = '#000'; // 검정색
        //         likeBtn.style.borderColor = '#000'; // 검정색
        //         likeBtn.style.color = '#000'; // 검정색 텍스트

        //         boardLikeObj.boardLike = 0;

        //     } else { // boardLike != 1일때
        //         BoardSelectOneApi.getInstance().likeBoard(boardLikeObj);

        //         likeBtn.style.backgroundColor = '#e74c3c'; // 빨간색
        //         likeBtn.style.borderColor = '#e74c3c'; // 빨간색
        //         likeBtn.style.color = '#fff'; // 흰색 텍스트


        //         boardLikeObj.boardLike = 1;

        //     }


        })
        
        //아래는 Bad

        // badBtn.addEventListener("click", function () {


        //     if (boardLikeObj.boardLike == 1) { // 이미 boardBad=1일때

        //         BoardSelectOneApi.getInstance().likeBadCancel(boardLikeObj);

        //         badBtn.style.backgroundColor = '#000'; // 검정색
        //         badBtn.style.borderColor = '#000'; // 검정색
        //         badBtn.style.color = '#000'; // 검정색 텍스트

        //         boardLikeObj.boardBad = 0;

        //     } else { // boardLike != 1일때
        //         BoardSelectOneApi.getInstance().BadBoard(boardLikeObj);

        //         badBtn.style.backgroundColor = '#e74c3c'; // 빨간색
        //         badBtn.style.borderColor = '#e74c3c'; // 빨간색
        //         badBtn.style.color = '#fff'; // 흰색 텍스트


        //         boardLikeObj.boardBad = 1;

        //     }


        // })


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