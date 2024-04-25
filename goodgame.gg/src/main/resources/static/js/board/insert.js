window.onload = () => {
    // HeaderService.getInstance().loadHeader();
 
    // BoardInsertService.getInstance().loadBoardCategoryList();
 
     ComponentEvent.getInstance().addClickEventInsertButton();
     ComponentEvent.getInstance().addClickEventInsertCancelButton()
//     ComponentEvent.getInstance().addClickEventEditButton();
     
     
 
 
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
     boardLikeCount: "",
     boardRegDate: "", // 작성일
    /* 파일 업로드: "",*/
     boardUploadName: "",
     boardUploadSize: "",
     boardUploadLocation:"",
 
 }
 
 
 class BoardInsertApi{
     static #instance = null;
     static getInstance(){
         if(this.#instance == null) {
             this.#instance = new BoardInsertApi();
         }
         return this.#instance;
     }
 
 
     insertBoard(){
         let successFlag = false;
 
 
 
         $.ajax({
             async: false,
             type: "post",
             url: `http://localhost:8000/api/board/insert`,
             contentType: "application/json",
             data: JSON.stringify(boardObj),
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
 

     fileInsertBoard(formData){
         let successFlag = false;

         $.ajax({
             async: false,
             type : 'POST',
             url: `http://localhost:8000/api/board/fileInsert`,
             processData : false,
             contentType : false,
             data : formData,
             dataType:"json",
             success: response => {
                 successFlag = true;
             },
             error: error => {

                 console.log(error);
             }
 
         });
 
          return successFlag;
 
     }
 
 }
 
 
 
 
 class BoardInsertService{
     static #instance = null;
     static getInstance(){
         if(this.#instance == null) {
             this.#instance = new BoardInsertService();
         }
         return this.#instance;
     }
 
     setBoardObjValues(){
        const principal = PrincipalApi.getInstance().getPrincipal(); 

        const formData = new FormData();
        const uploadFile = document.getElementById("uploadFile").files[0];
         
        let insertOK;
         if(document.getElementById("subject").value == "" || document.getElementById("content").value == ""){
            insertOK = false;

         }else{
            if(uploadFile == null){
                boardObj.boardSubject = document.getElementById("subject").value;
                boardObj.userIndex = principal.user.userIndex; 
                boardObj.boardContent = document.getElementById("content").value;
                boardObj.boardUploadName = document.getElementById("uploadFile").value;
                insertOK =  BoardInsertApi.getInstance().insertBoard();
    
            }else {
                formData.append('file', uploadFile);
                formData.append('userIndex', principal.user.userIndex);
                formData.append('boardSubject',document.getElementById("subject").value);
                formData.append('boardContent',document.getElementById("content").value);
                insertOK =  BoardInsertApi.getInstance().fileInsertBoard(formData);
             }
         }
        
        return insertOK;
 
     }
 

 
 }
 
 
 class ComponentEvent{
     static #instance = null;
     static getInstance(){
         if(this.#instance == null) {
             this.#instance = new ComponentEvent();
         }
         return this.#instance;
     }
 
 
     addClickEventInsertButton(){
        
        const insertButton = document.querySelector(".insert-complete");

            insertButton.onclick = () => {
                
            let insertSuccess;
            insertSuccess = BoardInsertService.getInstance().setBoardObjValues();

            console.log(insertSuccess);
                if(insertSuccess) {
                    alert("등록이 완료되었습니다.");
                location.href="http://localhost:8000/board";
                } else {
                    alert("제목과 내용은 공백일 수 없습니다.");
                location.reload();
                }

            }
 
     }
     addClickEventInsertCancelButton(){

      const insertCancelButton = document.querySelector(".insert-cancel");

      insertCancelButton.addEventListener('click', function() {
          window.location.href = 'http://localhost:8000/board'; // 게시물 목록 페이지의 URL로 이동
      });
     }

//     addClickEventEditButton(){
//       // Bold, Italic, Underline 버튼에 대한 이벤트 처리
//             document.getElementById('bold-btn').addEventListener('click', function() {
//                 document.execCommand('bold');
//             });
//
//             document.getElementById('italic-btn').addEventListener('click', function() {
//                 document.execCommand('italic');
//             });
//
//             document.getElementById('underline-btn').addEventListener('click', function() {
//                 document.execCommand('underline');
//             });
//     }

 
 
 }