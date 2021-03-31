var main = new Vue({
    el: "#main",
    data: {
        username: "",
        password: "",
        tips: false,
    },
    methods: {
        login: function() {
            if (this.username == "admin" && this.password == "admin") {
                window.location.href = './main.html';
            } else {
                this.tips = true;
            }
        },
        NClick: function() {
            this.tips = false;
        }
    }

})