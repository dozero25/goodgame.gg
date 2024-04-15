// 서버에서 게시글 목록을 가져오는 AJAX 요청
$(document).ready(function() {
    // 페이지가 로드되면 실행될 함수
    loadBoardList(); // 게시판 목록을 불러오는 함수 호출
});

function loadBoardList() {
    // AJAX를 이용하여 서버로부터 게시판 목록을 가져옵니다
    $.ajax({
        url: '/api/board/search', // 게시판 목록을 가져올 엔드포인트 URL
        type: 'GET', // GET 요청
        dataType: 'json', // JSON 형식의 응답을 기대합니다
       success: response => {
           // 서버에서 응답을 받았을 때 실행될 함수
           if (response.success) {
               // 성공적으로 데이터를 받았을 때 실행될 코드
               displayBoardList(response.data); // 받은 데이터를 화면에 표시하는 함수 호출
               console.log(response);
           } else {
               // 데이터를 받지 못했을 때 실행될 코드
               alert('Failed to load board list.');
           }
       },
        error: function(xhr, status, error) {
            // 요청이 실패했을 때 실행될 함수
            console.error("Error fetching board list:", error);
            console.error(xhr.responseText); // 이거 뭔지 모르겠음
            console.log(error);
        }
    });
}



// 게시글 목록을 HTML에 표시하는 함수
function displayBoardList(boardList) {
    // 받은 게시판 목록을 화면에 표시하는 함수
    var $boardList = $('#boardList'); // 게시판 목록이 표시될 요소 선택

    // 게시판 목록을 표시하기 전에 기존 목록을 지웁니다(이전에 추가된 내용 삭제) // 왜요?
    /*$boardList.empty();*/

    // 받은 게시판 목록을 순회하면서 각 항목을 화면에 추가합니다 / 각 게시글을 HTML에 추가
    boardList.forEach(function(board) {
                var boardHtml = `
                    <div class="board-Search">
                        <h2>${board.boardSubject}</h2>
                        <p>${board.userId}</p>
                        <p>${board.boardRegDate}</p>
                        <p>${board.boardContent}</p>
                    </div>
                `;
         // 생성한 HTML 코드를 게시판 목록 요소에 추가합니다 // $:
         boardList.append(boardItem);
    });

    /*boardList.forEach(function(board) {
        var boardItem = '<div>';
        boardItem += '<h3>' + board.title + '</h3>'; // 게시판 제목
        boardItem += '<p>' + board.content + '</p>'; // 게시판 내용
        boardItem += '</div>';
    });*/

    //클래스 API > js(재가공 class api, class service(html 띄우는곳) // html은 구조만 최소화 //<!-- html, css 화면 구성먼저,js로 가져와서(클래스 - service
}

// -------------------------------------------------
/*
 // 서버에서 게시글 목록을 가져오는 AJAX 요청
$(document).ready(function() {
    // 페이지가 로드되면 실행될 함수
    loadBoardList(); // 게시판 목록을 불러오는 함수 호출
});

function loadBoardList() {
    // AJAX를 이용하여 서버로부터 게시판 목록을 가져옵니다
    $.ajax({
        url: '/api/board/search', // 게시판 목록을 가져올 엔드포인트 URL
        type: 'GET', // GET 요청
        dataType: 'json', // JSON 형식의 응답을 기대합니다
        success: function(response) {
            // 서버에서 응답을 받았을 때 실행될 함수
            if (response.success) {
                // 성공적으로 데이터를 받았을 때 실행될 코드
                displayBoardList(response.data); // 받은 데이터를 화면에 표시하는 함수 호출
                console.log(response);
            } else {
                // 데이터를 받지 못했을 때 실행될 코드
                alert('Failed to load board list.');

            }
        },
        error: function(xhr, status, error) {
            // 요청이 실패했을 때 실행될 함수
            console.error("Error fetching board list:", error);
            console.error(xhr.responseText); // 이거 뭔지 모르겠음
            console.log(error);
        }
    });
}



// 게시글 목록을 HTML에 표시하는 함수
function displayBoardList(boardList) {
    // 받은 게시판 목록을 화면에 표시하는 함수
    var $boardList = $('#boardList'); // 게시판 목록이 표시될 요소 선택

    // 게시판 목록을 표시하기 전에 기존 목록을 지웁니다(이전에 추가된 내용 삭제) // 왜요?
    */
/*$boardList.empty();*//*


    // 받은 게시판 목록을 순회하면서 각 항목을 화면에 추가합니다 / 각 게시글을 HTML에 추가
    boardList.forEach(function(board) {
                var boardHtml = `
                    <div class="board-Search">
                        <h2>${board.boardSubject}</h2>
                        <p>${board.userId}</p>
                        <p>${board.boardRegDate}</p>
                        <p>${board.boardContent}</p>
                    </div>
                `;
         // 생성한 HTML 코드를 게시판 목록 요소에 추가합니다 // $:
         boardList.append(boardItem);
    });

    */
/*boardList.forEach(function(board) {
        var boardItem = '<div>';
        boardItem += '<h3>' + board.title + '</h3>'; // 게시판 제목
        boardItem += '<p>' + board.content + '</p>'; // 게시판 내용
        boardItem += '</div>';
    });*//*


    //클래스 API > js(재가공 class api, class service(html 띄우는곳) // html은 구조만 최소화 //<!-- html, css 화면 구성먼저,js로 가져와서(클래스 - service
}
*/

