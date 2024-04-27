window.onload = () => {
    MyPageService.getInstance().printMyPage();
//   MyPageService.getInstance().setMyPageObjValues();

    ComponentEvent.getInstance().addClickEventModificationButton();



}
//1.데이터를 저장할 변수 추가하기
const MyPageObj = {
    userIndex: "",
    userId: "",
    userPw: "",
    userNick: "",
    userGender: "",
    userEmail: "",
    roleId: 1
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

    //정보를 수정.
    modifyUserInfo() {
        let successFlag = false;


        $.ajax({
            async: false,
            type: "patch",
            url: `http://localhost:8000/api/mypage/update/${PrincipalApi.getInstance().getPrincipal().user.userIndex}`,
            contentType: "application/json",
            data: JSON.stringify(MyPageObj),
           /* data: MyPageObj*/
            dataType: "json",
            success: response => {
//                returnData = response.data;
                successFlag = true;
                console.log(response);
            },
            error: error => {
                console.log(error);
            }
        });

        return successFlag;

    }

    //유저정보 출력
    printMyPageUser(){
        let returnData = null;

        $.ajax({
            async: false,
            type: "GET",
            url: `http://localhost:8000/api/mypage/selectOne/${PrincipalApi.getInstance().getPrincipal().user.userIndex}`,

            dataType: "json",
            success: response => {
                console.log("response.data:",response.data);
                returnData = response.data;

            },
            error: error => {
                console.log(error);
            }
        });

        return returnData;
    }



}

//3. Service
class MyPageService{
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new MyPageService();
        }
        return this.#instance;

    }

    //수정할 때 사용하는 메서드
    setMyPageObjValues() {

        const modificationInputs = document.querySelectorAll(".modification-input");
        console.log(modificationInputs);

        MyPageObj.userNick = modificationInputs[0].value;
        MyPageObj.userEmail = modificationInputs[1].value;
        MyPageObj.userGender = modificationInputs[2].checked ? "m" : "w";

    }


    printMyPage() {
        const responseData = MyPageApi.getInstance().printMyPageUser();
         console.log(responseData);

        const modificationInputs = document.querySelector(".user-modify");

            modificationInputs.innerHTML = `
                <div>
                    <div class="getUserInfo">
                        <label class="modification-label">NICK</label>
                        <input type="text" class="modification-input" style="cursor: default;" value="${responseData?.userNick}" disabled>
                    </div>
                    <div class="getUserInfo">
                        <label class="modification-label">EMAIL</label>
                        <input type="text" class="modification-input" style="cursor: default;" value="${responseData?.userEmail}" disabled>
                    </div>
                    <div class="getUserInfo">
                        <label class="modification-label">GENDER</label>
                        <input type="radio" class="modification-input" name="gender" style="cursor: default;" value="${responseData?.userGender}" disabled>M
                        <input type="radio" class="modification-input" name="gender" style="cursor: default;" value="${responseData?.userGender}" disabled>W
                    </div>
                </div>
            `;

    }

}
//이벤트나 동작을 담당
//4. ComponentEvent
class ComponentEvent {
    static #instance = null;
    static getInstance() {
        if (this.#instance == null) {
            this.#instance = new ComponentEvent();
        }
        return this.#instance;
    }

    //
    addClickEventModificationButton() {
        const modificationButton = document.querySelector(".modification-button");

        /*modificationButton.addEventListener("click", function() {*/
        modificationButton.onclick = () => {
            const modificationInputs = document.querySelectorAll('.modification-input');
            const modificationOKButton = document.querySelector('.modificationOK-button');

            modificationInputs.forEach(input => {
                input.disabled = false;
            });

            modificationButton.disabled = true;
            modificationOKButton.disabled = false;

            modificationOKButton.onclick = () => {
                        MyPageService.getInstance().setMyPageObjValues();

            let successFlag = MyPageApi.getInstance().modifyUserInfo();

            if (successFlag) {
                alert("수정 완료되었습니다.");

                modificationInputs.forEach(input => {
                     input.disabled = true;
                });
                modificationButton.disabled = false;
                modificationOKButton.disabled = true;
                MyPageService.getInstance().printMyPage();
            } else {
                alert("수정에 실패했습니다. 다시 시도해주세요.");
                location.reload();
            }
        };
    }
}
    //
    alertChangeInfo() {
        const modificationInputs = document.querySelectorAll(".modification-input");
        modificationInputs.forEach(input => {
             input.addEventListener("input", function(event) {
                 console.log("입력 내용이 변경되었습니다:", event.target.value);

                 /* window.location.href = "http://localhost:8000/update";*/
             });
        });
    }

    changeProfileImage() {
            const profileImage = document.querySelector('.Profile-img');
            const imgFile = document.querySelector('.img-file');

            imgFile.onchange = () => {
                const file = imgFile.files;

                if (file) {
                    const reader = new FileReader();

                    reader.onload = () => {
                        profileImage.src = reader.result;
                    };

                    reader.readAsDataURL(file);
                }
            };
    }










}

