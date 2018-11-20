;
var index_nav_ops = {
    init: function () {
        index_nav_ops.upload();
        this.eventBind();
    },
    upload: function () {
        $("#nav_default").addClass("active");
        $("#nav_warehouse").removeClass("active");
        $("#container").load("./upload.html");
    },
    info: function () {
        $("#nav_warehouse").addClass("active");
        $("#nav_default").removeClass("active");
        $("#container").load("./info.html");
    },
    eventBind: function () {

        $("#nav_default").click(function () {
            index_nav_ops.upload();
        });
        $("#nav_warehouse").click(function () {
            index_nav_ops.info();
        });
    }

};

$(document).ready(function () {
    index_nav_ops.init();
});