window.onload = () => {
    //HeaderService.getInstance().loadHeader();

    BoardUpdateService.getInstance().setBoardUpdateBoardIndex();
    BoardUpdateService.getInstance().getLoadBoard();

    ComponentEvent.getInstance().addClickEventUpdateButton();

}

const boardObj = {
    boardIndex: 0, // 게시글 번호
    boardSubject: "", // 게시글 제목
    userIndex: 0, // 작성자 번
    userId: "", // 작성자 아이디
    userNick:"",
    replyCount: 0, // 댓글 수
    boardContent: "", // 게시글 내용 (필요시)
    boardVisit: 0, // 조회수
    boardRegDate: "", // 작성일
    boardLikeCount:0,
   /* 파일 업로드: "",*/
    boardUploadName: "",
    boardUploadSize: "",
    boardUploadLocation:""

}


class BoardUpdateApi{
    static #instance = null;
    static getInstance(){
        if(this.#instance == null) {
            this.#instance = new BoardUpdateApi();
        }
        return this.#instance;
    }



    updateSelectOneBoard(){

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


    updateBoard(){
        let successFlag = null;
        console.log(boardObj);

        $.ajax({
            async: false,
            type: "post",
            url: `http://localhost:8000/api/board/update`,
            contentType: "application/json",
            data: JSON.stringify(boardObj),
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
    


    loadUpdateBoard(){
        let responseData = null;

        $.ajax({
            async: false,
            type: "get",
            url: `http://localhost:8000/api/board/update/${boardObj.boardIndex}`,
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










}//end BoardUpdateApi














class BoardUpdateService{
    static #instance = null;
    static getInstance(){
        if(this.#instance == null) {
            this.#instance = new BoardUpdateService();
        }
        return this.#instance;
    }

    setBoardUpdateBoardIndex() {
        const URLSearch = new URLSearchParams(location.search);
        boardObj.boardIndex = URLSearch.get("boardIndex");
        console.log("맨처음 setBoardUpdateBoardIndex: " + boardObj.boardIndex);
        const principal = PrincipalApi.getInstance().getPrincipal(); // 여기 가장 먼저
        
    }


    getLoadBoard(){    
        const responseData = BoardUpdateApi.getInstance().loadUpdateBoard();

        console.log(responseData);
        console.log(responseData.data.boardSubject);

        const updateDetail = document.querySelector(".update-detail");
       
    
            
            updateDetail.innerHTML = `

            
            
            <label>제목</label>
            <div>
                <input type="text" id="boardSubject" class="boardSubject" autocomplete="off" value=${responseData.data.boardSubject}>
            </div>
            <label>작성자</label>
            <span>
                <input type="text" class="userNick" id="userNick" autocomplete="off" value="${responseData.data.userNick}" readonly>
            </span>
            <label>작성일</label>
            <span>
                <input type="datetime" id="boardRegDate" class="boardRegDate" autocomplete="off" value="${responseData.data.boardRegDate}" readonly>
            </span>
            <br>
            <label>내용</label>
            <div>
                <textarea class="boardContent" id = "boardContent" cols="100" rows="30">${responseData.data.boardContent}</textarea>
            </div>
            <input type="file" class="boardUploadName" value="${responseData.data.boardUploadName}">
            <input type="hidden" value="${responseData.data.boardIndex}"> 
            <div class = "update-btn">
                    <button type="submit" id = "update-complete" class="update-complete">수정완료</button>
            </div>
            
    
            `;
     

      

    }

    setUpdateBoardObjValues(){
        console.log("setUpdateBoardObjValues...");

        const responseData = BoardUpdateApi.getInstance().loadUpdateBoard();
        
       
        
        boardObj.boardSubject = document.querySelector(".boardSubject").value;
        boardObj.userNick = document.querySelector(".userNick").value;
        boardObj.boardRegDate = document.querySelector(".boardRegDate").value;
        boardObj.boardContent = document.querySelector(".boardContent").value;
        boardObj.boardIndex = responseData.data.boardIndex;

        
    }



}//end BoardUpdateService












class ComponentEvent{
    static #instance = null;
    static getInstance(){
        if(this.#instance == null) {
            this.#instance = new ComponentEvent();
        }
        return this.#instance;
    }

    addClickEventUpdateButton(){
        const updateButton = document.querySelector(".update-complete");

        updateButton.addEventListener("click", function () {

           BoardUpdateService.getInstance().setUpdateBoardObjValues();
           const successFlag = BoardUpdateApi.getInstance().updateBoard();

            if(successFlag) {
                console.log(boardObj);
                console.log(boardObj.boardIndex);
                alert("수정이 완료되었습니다.");
                
                location.href=`http://localhost:8000/board/selectOne?boardIndex=${boardObj.boardIndex}` // board? selectOne?
            } else {
                location.reload();
                alert("수정이 실패되었습니다.");
            }
            
        })
        
    }        
  

}//end ComponentEvent

