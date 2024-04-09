$(document).ready(function() {
    $.ajax({
        type: "GET",
        url: "/board", // 컨트롤러에서 목록을 반환하는 엔드포인트
        success: function(boardList) {
            // 서버에서 받아온 목록 값을 이용하여 동적으로 HTML을 생성하여 보드 컨테이너에 추가
            var boardContainer = $("#board-container");
            $.each(boardList, function(index, boardItem) {
                var boardItemHtml = "<div>" + boardItem.title + "</div>"; // 예시로 제목만 표시
                boardContainer.append(boardItemHtml);
            });
        }
    });
});

/* 예시

$(document).ready(function() {
    // 서버에서 게시글 목록을 가져오는 AJAX 요청
    $.ajax({
        url: "/selectAllBoard",
        type: "GET",
        success: function(boardList) {
            displayBoardList(boardList);
        },
        error: function(error) {
            console.error("Error fetching board list:", error);
        }
    });

    // 게시글 목록을 HTML에 표시하는 함수
    function displayBoardList(boardList) {
        var boardContainer = $("#board-container");
        boardContainer.empty(); // 이전에 추가된 내용 삭제

        // 각 게시글을 HTML에 추가
        boardList.forEach(function(board) {
            var boardHtml = `
                <div class="board-item">
                    <h2>${board.boardSubject}</h2>
                    <p>${board.userId}</p>
                    <p>${board.createDate}</p>
                </div>
            `;
            boardContainer.append(boardHtml);
        });
    }
});*/
