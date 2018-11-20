;
var index_index_ops = {
    init: function () {
        this.eventBind();
        this.obtainCookie();
    },
    eventBind: function () {
        $("#logout").click(function () {
            removeCookie("token");
            window.location.href = "/login.html";
        });
    },
    obtainCookie: function () {
        var token = getCookie("token");
        console.log("token:" + token);
        if (token === undefined || token === '') {
            var callback = function () {
                window.location.href = "/login.html";
            };
            common_ops.alert("请先登录", callback);
        }
        var username = token.split("#")[1];
        $('#logo_username').text(username);
    }
};

$(document).ready(function () {
    index_index_ops.init();
});