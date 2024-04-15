window.onload = () => {
    //HeaderService.getInstance().loadHeader();

    BoardupdateService.getInstance().setBoardSelectOneBoardIndex();
    //BoardModifyService .getInstance().getLoadBoard();

    //ComponentEvent.getInstance().addClickEventModifyButton();

}

const boardObj = {
    boardIndex: "", // 게시글 번호
    boardSubject: "", // 게시글 제목
    userIndex: "", // 작성자 번
    userId: "", // 작성자 아이디
    userNick:"",
    replyCount: "", // 댓글 수
    boardContent: "", // 게시글 내용 (필요시)
    boardVisit: "", // 조회수
    boardRegDate: "", // 작성일
   /* 파일 업로드: "",*/
    boardUploadName: "",
    boardUploadSize: "",
    boardUploadLocation:""

}


class BoardUpdateApi{
    static #instance = null;
    static getInstance(){
        if(this.#instance == null) {
            this.#instance = new BoardModifyApi();
        }
        return this.#instance;
    }










}//end BoardUpdateApi














class BoardUpdateService{
    static #instance = null;
    static getInstance(){
        if(this.#instance == null) {
            this.#instance = new BoardModifyService();
        }
        return this.#instance;
    }

    setBoardSelectOneBoardIndex() {
         const URLSearch = new URLSearchParams(location.search);
         boardObj.userIndex = URLSearch.get("boardIndex");
    }


    updateBoard{
        const responseData = BoardupdateApi.getInstance().getBoardUpdateView();

        const viewDetail = document.querySelector(".modify-detail");

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




}//end ComponentEvent

