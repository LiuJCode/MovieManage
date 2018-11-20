;
var index_info_ops = {
    page_size: 10,
    data: {},
    init: function () {
        this.eventBind();
        Pagination.init($("#pageBar"), index_info_ops.pageChange);
    },
    click_delete: function (a) {
        console.log(a);
    },
    pageChange: function (i) {
        index_info_ops.createTableHtml(i);
        Pagination.Page($(".ht-page"), i, index_info_ops.data.length, index_info_ops.page_size);
    },
    createTableHtml: function (page) {
        var htmls = "";
        if (index_info_ops.data.length < 0) {
            return
        }
        htmls += '<div class="col-lg-12">';
        htmls += '<table class="table table-bordered m-t">';
        htmls += '<thead>';
        htmls += '<tr>';
        htmls += '<th>序号</th>';
        htmls += '<th>id</th>';
        htmls += '<th>文件名</th>';
        htmls += '<th>地址</th>';
        htmls += '<th>短连接</th>';
        htmls += '<th>上传时间</th>';
        htmls += '<th>删除</th>';
        htmls += '</tr>';
        htmls += '</thead>';
        htmls += '<tbody>';
        for (var i = page; (i < (page + index_info_ops.page_size) && (i < index_info_ops.data.length)); i++) {
            var item = index_info_ops.data[i];
            htmls += '<tr>' +
                '<td>' + i + '</td>' +
                '<td>' + item["id"] + '</td>' +
                '<td>' + item["name"] + '</td>' +
                '<td>' + item["path"] + '</td>' +
                '<td>' + item["shortPath"] + '</td>' +
                '<td>' + item["createAt"] + '</td>' +
                '<td>' +
                '<a class="m-l remove" href="javascript:void(0);" >' +
                '<i class="fa fa-trash fa-lg" id="file_delete" attach="' + item["id"] + '"></i>' +
                '</a>' +
                '</td>' +
                '</tr>';

        }

        htmls += '</tbody>';
        htmls += '</table>';
        htmls += '</div>';
        $("#table").html(htmls);
        // index_info_ops.eventDeleteBind();
    },
    // eventDeleteBind: function (id) {
    //     console.log("被点击了:" + id);
    //     $.ajax({
    //         url: '/api/file/delete/' + id,
    //         headers: {'Content-Type': 'application/json'},
    //         contentType: 'application/json;charset=UTF-8',
    //         type: JSON.stringify({'id': id}),
    //         dataType: 'json',
    //         success: function (res) {
    //             if (res['code'] == 1) {
    //                 common_ops.alert("删除成功", function () {
    //                     window.location.href = "/";
    //                 });
    //             } else {
    //                 if (res['code'] == 7777) {
    //                     common_ops.alert("请重新登录", function () {
    //                         window.location.href = "/login.html";
    //                     });
    //                 } else {
    //                     common_ops.alert(res.msg, function () {
    //                     });
    //                 }
    //             }
    //         }
    //     });
    // },
    eventBind: function () {
        $("#table").html("init ...");
        $.ajax({
            url: '/api/file/obtain',
            headers: {'Content-Type': 'application/json'},
            contentType: 'application/json;charset=UTF-8',
            type: 'GET',
            dataType: 'json',
            success: function (res) {
                if (res['code'] == 1) {
                    if (res['result'].length == 0) {
                        $("#table").html("没有视频");
                    } else {
                        index_info_ops.data = res['result'];
                        /*
                         * 初始化插件
                         * @param  object:翻页容器对象
                         * @param  function:回调函数
                         * */
                        index_info_ops.createTableHtml(0);
                        Pagination.Page($("#pageBar"), 0, res['result'].length, index_info_ops.page_size);
                    }
                } else {
                    if (res['code'] == 7777) {
                        common_ops.alert("请重新登录", function () {
                            window.location.href = "/login.html";
                        });
                    } else {
                        $("#table").html(res['msg']);
                    }
                }
            }
        })
    }

};

$(document).ready(function () {
    index_info_ops.init();
    $(document).on("click", "#file_delete", function () {
        var id = $(this).attr("attach");
        common_ops.alert("确定删除?", function () {
            $.ajax({
                url: '/api/file/delete/' + id,
                headers: {'Content-Type': 'application/json'},
                contentType: 'application/json;charset=UTF-8',
                type: "GET",
                dataType: 'json',
                success: function (res) {
                    if (res['code'] == 1) {
                        common_ops.alert("删除成功", function () {
                            index_nav_ops.info();
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
        });
    });
});
// eventDeleteBind = function (id) {
//     console.log("被点击了:" + id);
//     $.ajax({
//         url: '/api/file/delete/' + id,
//         headers: {'Content-Type': 'application/json'},
//         contentType: 'application/json;charset=UTF-8',
//         type: JSON.stringify({'id': id}),
//         dataType: 'json',
//         success: function (res) {
//             if (res['code'] == 1) {
//                 common_ops.alert("删除成功", function () {
//                     window.location.href = "/";
//                 });
//             } else {
//                 if (res['code'] == 7777) {
//                     common_ops.alert("请重新登录", function () {
//                         window.location.href = "/login.html";
//                     });
//                 } else {
//                     common_ops.alert(res.msg, function () {
//                     });
//                 }
//             }
//         }
//     });
// };