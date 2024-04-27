window.onload = () => {
    console.log("aaaaaaaaaaa");

    //MyPageService.getInstance().setMyPageObjValues();
//   MyPageApi.getInstance().printBoardList();
MyPageService.getInstance().printBoardElement();
}
//1.데이터를 저장할 변수 추가하기
let BoardObj = {
    boardIndex: "",
    boardSubject: "",
    userIndex: "",
    boardContent: "",
    userGender: "",
    boardVisit: "",
    boardGood: "",
    boardBad: "",
    boardUploadName: "",
    boardUploadSize: "",
    boardUploadLocation: "",
    boardRegDate:""

};
let searchObj={
limit:"Y",
count :5,
page:1,
userIndex:""
};
//2. Api
class MyPageApi {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new MyPageApi();

        }
        return this.#instance;
    }



//(2). 정보 출력.  주소로 이동했습니다.
    printBoardList(){

        let returnData = null;
        searchObj.userIndex = `${PrincipalApi.getInstance().getPrincipal().user.userIndex}`;
        $.ajax({
            async: false,
            type: "get",
            url: `http://localhost:8000/api/mypage/searchList/board`,
            contentType: "application/json",
            data: searchObj,
            dataType: "json",
            success: response => {
               returnData=response.data;


            },
            error: error => {
                console.log(error);
            }
        });
        console.log("returnData" + returnData);
        return returnData;
    }
getTotalBoardCount() {
    //정보(searchObj에 부합하는)의 전체 개수를 가져온다

        let returnData = null;

        BoardObj.userIndex = `${PrincipalApi.getInstance().getPrincipal().user.userIndex}`;


    $.ajax({
        async: false,
        type: "get",
        url: "http://localhost:8000/api/mypage/getTotalBoardCount",
 data: {userIndex:BoardObj.userIndex},
        dataType: "json",
        success: response => {
            returnData = response.data;
        },
        error: error => {
            console.log(error);
        }
    });
    console.log(returnData)
    return returnData;
}





}///searchList/post
//3. Servicce
class MyPageService {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new MyPageService();
        }
        return this.#instance;

    }

    printBoardElement() {
       // const boardData = MyPageApi.getInstance().searchBoardList(userIndex);
      //  console.log("boardData"+boardData);
const boardData=MyPageApi.getInstance().printBoardList();
        const tblContent = document.querySelector(".tbl-content-post");
        console.log("tblContent"+tblContent);

        let rowContent = "";
        // 각 데이터를 반복하여 테이블에 추가
       for (const data of boardData) {
                console.log("data"+data);
                console.log("data.boardIndex:"+data.boardIndex);

                rowContent += `

                        <tr>
                           <td class="mp-board-td">
                                <span>${data.boardIndex}</span>
                            </td>
                            <td class="mp-board-td">
                                  <a class="ahref" href="/board/selectOne?boardIndex=${data.boardIndex}">${data.boardSubject}
                            </td>
                            <td class="mp-board-td">
                                 <span>${data.boardContent}</span>
                            </td>
                            <td class="mp-board-td">
                                <span>${data.boardRegDate}</span>
                            </td>
                          </tr> `;


        }
        tblContent.innerHTML = rowContent;
        this.loadPageController();
     }//end fn



loadPageController() {
    //페이징 담당: 페이징에 필요한 것들이 전부 들어있고, 주의 해야할 점은 페이지 값이 바뀌면 getLoadAllDuoList를 통해 리스트를 다시 불러와야한다.

    const pageController = document.querySelector(".page-controller");

    const totalcount = MyPageApi.getInstance().getTotalBoardCount();


    const maxPageNumber = totalcount % searchObj.count == 0
        ? Math.floor(totalcount / searchObj.count)
        : Math.floor(totalcount / searchObj.count) + 1;

    pageController.innerHTML = `
        <a href="javascript:void(0)" class="pre-button disabled">이전</a>
        <ul class="page-numbers">
        </ul>
        <a href="javascript:void(0)" class="next-button disabled">다음</a>
    `;

    if (searchObj.page != 1) {
        const preButton = pageController.querySelector(".pre-button");
        preButton.classList.remove("disabled");

        preButton.onclick = () => {
            searchObj.page--;

            this.printBoardElement();
        }
    }

    if (searchObj.page != maxPageNumber) {
        const preButton = pageController.querySelector(".next-button");
        preButton.classList.remove("disabled");

        preButton.onclick = () => {
            searchObj.page++;

            this.printBoardElement();
        }
    }
    const startIndex = searchObj.page % 5 == 0
        ? searchObj.page - 4
        : searchObj.page - (searchObj.page % 5) + 1;


    const endIndex = startIndex + 4 <= maxPageNumber ? startIndex + 4 : maxPageNumber;

    const pageNumbers = document.querySelector(".page-numbers");

    for (let i = startIndex; i <= endIndex; i++) {
        pageNumbers.innerHTML += `
            <a href="javascript:void(0)"class ="page-button ${i == searchObj.page ? "disabled" : ""}"><li>${i}</li></a>
        `;
    }

    const pageButtons = document.querySelectorAll(".page-button");
    pageButtons.forEach(button => {
        const pageNumber = button.textContent;
        if (pageNumber != searchObj.page) {
            button.onclick = () => {
                searchObj.page = pageNumber;

                this.printBoardElement();
            }
        }
    });
}


}