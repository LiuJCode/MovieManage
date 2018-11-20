;
var setCookie = function (name, value, days) {    //封装一个设置cookie的函数
    var oDate = new Date();
    oDate.setDate(oDate.getDate() + days);   //days为保存时间长度
    document.cookie = name + '=' + value + ';expires=' + oDate;
};
var getCookie = function (name) {
    var arr = document.cookie.split(';');
    for (var i = 0; i < arr.length; i++) {
        var arr2 = arr[i].split('=');
        if (trim(arr2[0]) == trim(name)) {
            return arr2[1];  //找到所需要的信息返回出来
        }
    }
    return '';        //找不到就返回空字符串
};
var removeCookie = function (name) {
    setCookie(name, 1, -1);    //-1表示昨天过期,系统自动删除
};
var trim = function (str) {
    return str.replace(/^(\s|\u00A0)+/, '').replace(/(\s|\u00A0)+$/, '');
};