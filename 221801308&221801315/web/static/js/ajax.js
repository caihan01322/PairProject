$("#login").click(function () {
    $.ajax({
        url: "http://127.0.0.1:5000/login",
        method: 'POST',
        type: "json",
        async: false,
        success: function (message) {
            alert(message.message);
        }
    })
})
$("#register").click(function () {
    $.ajax({
        url: "/register",
        method: 'POST',
        type: 'text',
        async: false,
        cache: false,
        success: function (msg) {
            alert(msg);
        },
        error: function (message) {
            alert(message);

        }
    })
})