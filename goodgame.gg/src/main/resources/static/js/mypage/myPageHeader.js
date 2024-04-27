class MyPageHeaderService {
    static #instance = null;
    static getInstance() {
        if (this.#instance == null) {
            this.#instance = new MyPageHeaderService();
        }
        return this.#instance;
    }

    loadHeader() {
        const headerbox = document.querySelector(".header");
        const principal = PrincipalApi.getInstance().getPrincipal();

        headerbox.innerHTML += `
        <div class="top_menu">
            <a href="/main" style="color: blue;">HOME</a>
            <a href="/rank" style="color: blue;">RANKING</a>
            <a href="/duo" style="color: blue;">DUO</a>
            <a href="/board" style="color: blue;">COMMUNITY</a>
            <a href="/lolbti" style="color: blue;">LOLBTI</a>
            <a href="/logout" style="color: blue;">LOGOUT</a>
        </div>

            `;
    }
}