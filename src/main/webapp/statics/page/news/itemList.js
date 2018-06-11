/**
 * ...
 */

layui.config({
    base : "news/js/"
}).use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element'],function(){
    var laydate = layui.laydate //日期
        ,laypage = layui.laypage //分页
        ,layer = layui.layer //弹层
        ,table = layui.table //表格
        ,carousel = layui.carousel //轮播
        ,upload = layui.upload //上传
        ,element = layui.element //元素操作
        ,$ = layui.jquery;

    $(".newsAdd_btn").click(function(){
        layer.open({
            title:['添加商品','font-size:18px;'],
            skin:'layui-layer-lan',
//          skin:'layui-layer-molv',
            type: 2,
            maxmin:true,
            content: 'newsAdd.html', //这里content是一个普通的String
            area: ['1350px', '760px'],
            cancel: function(index, layero){
                layer.confirm('确定关闭么', {icon: 6, title:'提示'}, function(confirm_index){
                    //do something
                    layer.close(index);
                    layer.close(confirm_index);
                });
                return false;
            }
        });
    });

    table.render({
        elem: '#itemTable'
        ,height: 685
        ,url: '/item/queryItem' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {type: 'checkbox',width:50,fixed: 'left'}
            ,{field: 'item_number', title: '货号', width:150, sort: true}
            ,{field: 'logo', title: '品牌', width:200}
            ,{field: 'name', title: '名称', width:250}
            ,{field: 'type', title: '类型', width:120}
            ,{field: 'sex', title: '性别', width: 120}
            ,{field: 'size', title: '尺寸', width: 280}
            ,{field: 'price', title: '价格￥', width: 150}
            ,{field: 'is_online', title: '上架', width: 120,templet: '#checkboxTpl', unresize: true}
            ,{field: 'options',title:'操作',toolbar: '#itemBar',width: 250}
        ]],
        request:{pageName: 'curr'},
        page: true, //是否显示分页
        response:{
            statusName:'respcode',
            countName:'total',
            statusCode:0000,
            msgName:'respmsg',
            dataName:'datas'
        }
    });

    table.on('tool(itemList_filter)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        // var tr = obj.tr; //获得当前行 tr 的DOM对象

        if(layEvent === 'detail'){ //查看
            console.log(data);
            // layer.alert(JSON.stringify(data));
            layer.open({
                title:['添加商品','font-size:18px;'],
                skin:'layui-layer-lan',
                type: 1,
                maxmin:true,
                content: $('#detail_id'), //这里content是一个普通的String
                success: function(layero, index){
                    $("#item_number").val(data.item_number);
                    $("#logo_id").val(data.logo);
                    $("#name_id").val(data.name);
                    $("#type_id").val(data.type);
                    if(data.is_online == 1){
                        $("#isOnline").attr("checked");
                    }

                    $("#price_id").val(data.price);
                    $("#description").val(data.description);
                },
                area: ['1350px', '760px'],
                cancel: function(index, layero){
                    layer.confirm('确定关闭么', {icon: 6, title:'提示'}, function(confirm_index){
                        //do something
                        layer.close(index);
                        layer.close(confirm_index);
                    });
                    return false;
                }
            });
        }
        // else if(layEvent === 'del'){ //删除
        //     layer.confirm('真的删除行么', function(index){
        //         obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
        //         layer.close(index);
        //         //向服务端发送删除指令
        //     });
        // } else if(layEvent === 'edit'){ //编辑
        //     //do something
        //
        //     //同步更新缓存对应的值
        //     obj.update({
        //         username: '123'
        //         ,title: 'xxx'
        //     });
        // }
    });

});