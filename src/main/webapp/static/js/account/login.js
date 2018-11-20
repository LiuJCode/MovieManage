;
var account_login_ops = {
    init: function () {
        this.eventBind();
    },
    eventBind: function () {
        $(".login_wrap .do-login").click(function () {
            var btn_target = $(this);
            if (btn_target.hasClass("disabled")) {
                common_ops.alert("正在处理!!请不要重复提交~~");
                return;
            }

            var login_name = $(".login_wrap input[name=login_name]").val();
            var login_pwd = $(".login_wrap input[name=login_pwd]").val();

            if (login_name === undefined || login_name.length < 1) {
                common_ops.alert("请输入正确的登录用户名~~");
                return;
            }
            if (login_pwd === undefined || login_pwd.length < 1) {
                common_ops.alert("请输入正确的密码~~");
                return;
            }

            btn_target.addClass("disabled");
            $.ajax({
                url: '/api/account/login',
                headers: {'Content-Type': 'application/json'},
                contentType: 'application/json;charset=UTF-8',
                type: 'POST',
                data: JSON.stringify({'account': login_name, 'password': login_pwd}),
                dataType: 'json',
                success: function (res) {
                    btn_target.removeClass("disabled");
                    var callback = null;
                    if (res.code == 1) {
                        callback = function () {
                            setCookie("token", res.result.token);
                            setCookie("key", res.result.key);
                            window.location.href = "/";
                        }
                    }
                    common_ops.alert(res.msg, callback);
                }
            });
        });
    }
};

$(document).ready(function () {
    account_login_ops.init();
});