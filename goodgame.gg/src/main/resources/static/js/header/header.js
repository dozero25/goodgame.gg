class HeaderService {
    static #instance = null;
    static getInstance() {
        if (this.#instance == null) {
            this.#instance = new HeaderService();
        }
        return this.#instance;
    }

    loadHeader() {
        const headerbox = document.querySelector(".header-box");
        const principal = PrincipalApi.getInstance().getPrincipal();

        headerbox.innerHTML += `
        <div class="logo-box">
            <a href="/main"><h1>GG.GG</h1></a>
        </div>
        <div class="menu-box">
            <nav>
                <ul class="menu-li">
                    <li class="li-list"><a href="/main">홈</a></li>
                    <li class="li-list"><a href="/record">전적검색</a></li>
                    <li class="li-list"><a href="">랭킹</a></li>
                    <li class="li-list"><a href="">듀오찾기</a></li>
                    <li class="li-list"><a href="">커뮤니티</a></li>
                    ${principal == null
                        ?`
                        <li class="li-list"><a href="/login">로그인</a></li>
                        `:`
                        <li class="li-list"><a href="">MyPage</a></li>
                        <li class="li-list"><a href="/logout">로그아웃</a></li>
                        `
                    }
                </ul>
            </nav> 
        </div>
            `;
    }
}