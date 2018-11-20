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
        $("#updatePW").click(function(){
            index_index_ops.updatePw();
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
    },

    updatePw: function () {
        var password = prompt("请输入密码","")
        if(password!=null && password!=""){
            $.ajax({
                url: '/api/file/delete/' + password,
                headers: {'Content-Type': 'application/json'},
                contentType: 'application/json;charset=UTF-8',
                type: "GET",
                dataType: 'json',
                success: function (res) {
                    if (res['code'] == 1) {
                        common_ops.alert("修改成功", function () {
                            window.location.href = "/login.html";
                        });
                    } else {
                        if (res['code'] == 7777) {
                            common_ops.alert("请重新登录", function () {
                                window.location.href = "/login.html";
                            });
                        } else {
                            common_ops.alert(res.msg, function () {
                            });
                        }
                    }
                },
                error: function (e) {
                    console.log(e)
                }
            });
        }
    }
};

$(document).ready(function () {
    index_index_ops.init();
});
