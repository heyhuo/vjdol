window.onload = function () {
    $(".insertButton").hide();
    alert("dsa");
}
//标题编辑按钮
$("#editTitleButton").onclick(createEditor('topicTitle', 'title', 'titleEditButton'));
//内容编辑按钮


//编辑按钮函数
function createEditor(id, area, insert) {
    //id:     panel -> id
    //area:   li -> id
    //insert: insert -> id

    var editor = "<script id='container' name='content' type='text/plain'><\/script>";
    var a = $("#" + id).html();
    var b = $("#" + area);
    b.append(editor);
    <!-- 实例化编辑器 -->
    var editor = UE.getEditor('container');
    $(".editButton").hide();
    $("#" + insert).show();
}

//完成编辑插入
function insert(id, area, panel, url) {
    var editor = UE.getEditor('container');
    var content = editor.getContent();
    var panelContent = $("#" + panel);
    panelContent.html(content);
    // panelContent.append();
    panelContent.val(content);
    UE.getEditor('container').destroy();
    $("textarea").remove();
    $(".editButton").show();
    $("#" + id).hide();
    getjson(content, url);
}


function getjson(content, url) {
    var d = {};
    d.topicId = 1;
    d.topicContent = content;
    $.ajax({
        url: url,
        data: JSON.stringify(d),
        //type、contentType必填,指明传参方式
        type: "POST",
        contentType: "application/json;charset=utf-8",
        success: function (response) {
            //前端调用成功后，可以处理后端传回的json格式数据。
            if (response.success) {
                alert(response.message);
            }
        }, error: function (response) {
            alert("emm...." + response.message);
        }

    });
}

//Ajax插入获取到的数据
function ajaxInsert(content) {
    $.ajax({
        url: 'aaa/1',
        data: {
            user: content,
            pass: $('.pass').val()
        },
        type: 'POST',
        success: function (str) {
            alert(str);
        },
        error: function (err) {
            alert(err);
        }
    });
}