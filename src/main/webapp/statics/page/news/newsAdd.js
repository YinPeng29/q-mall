var layer,laydate,form;
layui.use('form', function(){
    form = layui.form;
});

function itemAdd(){
    $.ajax({
        type: "post",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/item/additem",//url
        async:false,
        data: {"title":"123"},
        success: function(data){
            alert("123");
        },
        error:function(){
            alert("baocuo");
        }
    });
}
