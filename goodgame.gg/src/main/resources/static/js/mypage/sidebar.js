class SidebarService {
    static #instance = null;
    static getInstance() {
        if (this.#instance == null) {
            this.#instance = new SidebarService();
        }
        return this.#instance;
    }

    loadSidebar() {
        const headerbox = document.querySelector(".sidebar");
        const principal = PrincipalApi.getInstance().getPrincipal();

        headerbox.innerHTML += `
        <h2><a href="/main" style="color: #ffffff;">Gg.gg </a></h2>
        <ul>
            <li><a href="http://localhost:8000/mypage"><i class="fas fa-regular fa-user"></i> 내 정보</a></li>
            <li><a href="http://localhost:8000/mypage/post"><i class="fas fa-regular fa-file"></i> 내 포스트 목록</a></li>
            <li><a href="http://localhost:8000/mypage/reply"><i class="fas fa-regular fa-tag"></i> 내 댓글 목록</a></li>
            <li><a href="http://localhost:8000/mypage/delete"><i class="fas fa-regular fa-code"></i> 회원탈퇴</a></li>
        </ul>
        <div class="social_media">
            <a href="#"><i class="fab fa-facebook-f"></i></a>
            <a href="#"><i class="fab fa-twitter"></i></a>
            <a href="#"><i class="fab fa-instagram"></i></a>
        </div>

            `;
    }
}