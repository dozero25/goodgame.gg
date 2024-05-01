window.onload = () => {
    HeaderService.getInstance().loadHeader();

    ComponentEvent.getInstance().ClickEventPopupForCheckUserPw();
    ComponentEvent.getInstance().ClickEventDeleteButton();

}

//1.삭제할 데이터 선택
let DeleteObj = {
  userIndex: "",
  userPw: ""
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




//(1). 유저의 데이터 삭제
    deleteUserData() {
        // 성공 여부를 나타내는 플래그 초기화
        let successFlag = false;
        console.log("bbbbbbbbbbb");
        // AJAX 요청
        $.ajax({
            async: false, // 비동기 여부 설정

            type: "DELETE", // HTTP 요청 메서드 설정
            url: `http://localhost:8000/api/mypage/delete/${PrincipalApi.getInstance().getPrincipal().user.userIndex}`, // 삭제할 데이터에 대한 URL 설정
            contentType: "application/json",

            dataType: "json", // 서버에서 받아올 데이터의 타입 설정서버에서 받아올 데이터의 타입 설정
            success: response => { // 요청이 성공했을 때의 콜백 함수
                successFlag = response.data; // 성공 플래그를 true로 설정

            },
            error: error => { // 요청이 실패했을 때의 콜백 함수
                console.log(error); // 에러 메시지를 콘솔에 출력
            }
        });

        return successFlag; // 성공 여부 플래그 반환
    }

        pwCheck(inputPassword) {
            console.log(">>>",`${PrincipalApi.getInstance().getPrincipal().user.userIndex}`);
            console.log('inputPassword:',inputPassword);
            DeleteObj.userIndex = `${PrincipalApi.getInstance().getPrincipal().user.userIndex}`;
            DeleteObj.userPw = `${inputPassword}`;
            console.log('DeleteObj:',DeleteObj);
            // 성공 여부를 나타내는 플래그 초기화
            let returnData = null;
            // AJAX 요청
            $.ajax({
                async: false, // 비동기 여부 설정
                type: "get", // HTTP 요청 메서드 설정
                url: `http://localhost:8000/api/mypage/delete/pwCheck`, // 삭제할 데이터에 대한 URL 설정
               data:DeleteObj,
                contentType: "application/json",
                dataType: "json", // 서버에서 받아올 데이터의 타입 설정
                success: response => { // 요청이 성공했을 때의 콜백 함수
                   console.log("2. response.data:",response.data);
                   returnData=response.data;
                   console.log("333. returnData:",returnData);

                },
                error: error => { // 요청이 실패했을 때의 콜백 함수
                    console.log(error); // 에러 메시지를 콘솔에 출력
                }
            });

            return returnData; // 성공 여부 플래그 반환
        }

}//endclass

//3. Servicce
class MyPageService{
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new MyPageService();
        }
        return this.#instance;
    }

        // 1. 본인인증 팝업창 메서드
        popupForCheckUserPw() {
            const popupWindow = window.open('', '_blank', 'width=600,height=400');

            const popupContent = `
             <h1>비밀번호를 입력해주세요.</h1>
                <input type="password" id="pop-upForDelete">
             <button id="confirmButton">확인</button>

             `
            popupWindow.document.write(popupContent);


            setTimeout(() => {
                const confirmButton = popupWindow.document.getElementById('confirmButton');

                confirmButton.onclick = () => {
                    const inputPassword = popupWindow.document.getElementById('pop-upForDelete').value;
                    const isPasswordMatch = MyPageApi.getInstance().pwCheck(inputPassword);
                    if (isPasswordMatch) {
                        alert("인증이 완료되었습니다.");
                        document.getElementById('check-user-pw-button').disabled = true;
                        document.getElementById('delete-button').disabled = false;
                        popupWindow.close();
                    } else {
                        popupWindow.alert("비밀번호가 일치하지 않습니다.");
                       popupWindow.close();
                    }
                }
            }, 100);

            
        };

        deleteAll() {
            let successFlagB=MyPageApi.getInstance().deleteUserData();
            console.log("sFB="+successFlagB);
            return successFlagB;
        }
}//endclass

//4. ComponentEvent
class ComponentEvent {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new ComponentEvent();
        }
        return this.#instance;
    }

    // 1. 팝업창을 띄우는 이벤트 버튼
    ClickEventPopupForCheckUserPw() {
        const confirmButton = MyPageService.getInstance().popupWindow;
        const checkUserPwButton = document.getElementById('check-user-pw-button');
                checkUserPwButton.onclick = () => {
                 MyPageService.getInstance().popupForCheckUserPw();
                };
    };


    // 2. 삭제 이벤트 버튼
    ClickEventDeleteButton() {
        const DeleteButton = document.getElementById("delete-button");
        console.log("2323 .DeleteButton:"+ DeleteButton);
        DeleteButton.onclick = () => {

         let successFlagA= MyPageService.getInstance().deleteAll();
           console.log("successFlag111:"+successFlagA); //false
           if(successFlagA) {
                document.getElementById('check-user-pw-button').disabled = false;
                document.getElementById('delete-button').disabled = true;
                alert("회원탈퇴 완료되었습니다.");
                window.location.href = "/main";
                window.location.href = "/logout";
           } else {
               alert("회원탈퇴 실패하었습니다aaaaaaaaaa.");
               location.reload();
           }
        };
    }

}//endclass

    /*changeProfileImage() {
        const profileImage = document.getElementByClass('Profile-img');
        const imgFile = document.getElementByClass('img-file');

        imgFile.onchange = () => {
            profileImage =
        }
    }*/




