window.onload = () => {
   // HeaderService.getInstance().loadHeader();

   // BoardInsertService.getInstance().loadBoardCategoryList();

    ComponentEvent.getInstance().addClickEventInsertButton();


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

const userObj = {
    userIndex:"",
    userId:"",
    userPw:"",
    userNick:"",
    userGender:"",
    userRegDate:"",
    userEmail:"",
    roleId:""
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

        console.log(boardObj);
        console.log("${boardObj.boardIndex}:"+`${boardObj.boardIndex}`);
        console.log("${boardObj.userIndex}:"+`${boardObj.userIndex}`);
        console.log("${boardObj.boardContent}:"+`${boardObj.boardContent}`);

        $.ajax({
            async: false,
            type: "post",
            url: `http://localhost:8000/api/board/insert`,
            contentType: "application/json",//?
            data: JSON.stringify(boardObj),//?
            dataType: "json",
            success: response => {
                successFlag = true;

                console.log("====등록 성공====");
            },
            error: error => {
                console.log("====등록 실패====");
                console.log(error);
            }
        });


        /*var form = $('#uploadFile')[0].files[0];

        if (form != null) {
            var formData = new FormData();
            console.log(form);
            formData.append('files', form);
            console.log(formData);
              *//*var formData = new FormData();
              const uploadFile = document.getElementById("uploadFile").files[0];
              formData.append('uploadFile', uploadFile);*//*

            $.ajax({
                type : 'POST',
                url: 'http://localhost:8000/api/board/fileInsert',
                processData : false,
                contentType : false,
                data : formData,
                success : function(result){
                    alert("Uploaded");
                }
            });
        }*/


        return successFlag;
    }


    fileInsertBoard(formData){
        let successFlag = false;


        $.ajax({
            type : 'POST',
            url: 'http://localhost:8000/api/board/fileInsert',
            processData : false,
            contentType : false,
            data : formData,
            success : function(result){
                alert(" file Api Uploaded");
                successFlag = true;
            },
            /*success: response => {
                successFlag = true;

                console.log("====등록 성공====");*/
            /*},*/
            error: error => {
                console.log("file Api failed");
                console.log(error);
                successFlag = false;
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
        const insertBoardSubject2 = $("#subject").val();
        console.log(insertBoardSubject2);

        const insertBoardSubject = document.getElementById("subject").value;
          console.log(insertBoardSubject);

        const insertUserIndex = document.getElementById("userIndex").value;
        console.log(insertUserIndex);

        const insertBoardContent = document.getElementById("content").value;
        console.log(insertBoardContent);
        //////////////////////////////////////////////////////////////////////
        const insertBoardUploadName = document.getElementById("uploadFile").value;
        console.log(insertBoardUploadName);

        //const principal = PrincipalApi.getInstance().getPrincipal(); //현재 로그인한 사용자

        boardObj.boardSubject = insertBoardSubject;
        /*boardObj.boardOption = insertInput[1].value;*/
        boardObj.userIndex = insertUserIndex;
        boardObj.boardContent = insertBoardContent;
        boardObj.boardUploadName = insertBoardUploadName;
        /*boardObj.userIndex = principal.user.userIndex;*/

    }

    setFileInsertBoard(){




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
                    const insertBoardSubject = document.querySelector(".boardSubject");
                    console.log(insertBoardSubject);

                    const insertUserIndex = document.querySelector(".userIndex");
                    const insertBoardContent = document.querySelector(".boardContent");
                    //const insertBoardUploadName = document.querySelector(".boardUploadName");

                    BoardInsertService.getInstance().setBoardObjValues();

                    //1.분기처리 해보고 안되면 //2.그냥 값할당해보기
                    //const successFlag =  BoardInsertApi.getInstance().insertBoard();

                    //var form = $('#uploadFile')[0].files[0];
                    const form = $('#uploadFile')[0].files[0];

                    let successFlag;

                    if (form != null) {

                        /*const formData = new FormData();
                        console.log(form);
                        formData.append('files', form);
                        console.log(formData);*/
                            const formData = new FormData();
                            console.log(form);
                            //const uploadFile = document.getElementById("uploadFile").files[0];
                            formData.append('uploadFile', form);
                            for (let key of formData.keys()) {
                                console.log(key);
                            }
                            for (let value of formData.values()) {
                                console.log(value);
                            }
                       successFlag =  BoardInsertApi.getInstance().fileInsertBoard(formData);

                    }else{
                       successFlag =  BoardInsertApi.getInstance().insertBoard();
                    }








                        //// BoardInsertService의 setBoardObjValues 메서드 호출
                        /*BoardInsertService.getInstance().setBoardObjValues(insertBoardSubject, insertUserIndex, insertBoardContent, insertBoardUploadName);
                        const successFlag = BoardInsertApi.getInstance().insertBoard();*/



                    if(successFlag) {
                        alert("등록이 완료되었습니다.");
                        location.href="http://localhost:8000/board";
                    } else {
                        alert(" 컴포넌트 등록 실패");
                        location.reload();
                     }


                    }//insertButton.onclick




            /*insertButton.addEventListener("click", function() {


                 // 필수 입력 필드
                  const boardSubject = document.querySelector(".insert-boardSubject").value;
                  const boardContent = document.querySelector(".insert-boardContent").value;

                if(!boardSubject || !boardContent) {
                    alert("제목 또는 내용을 작성해주세요");

                } else {
                    BoardInsertService.getInstance().setBoardObjValues();
                    const successFlag = BoardInsertApi.getInstance().insertBoard();
                    alert("작성이 완료되었습니다.");
                    window.location.href = "http://localhost:8000/board";
                }
            });*/

    }



}