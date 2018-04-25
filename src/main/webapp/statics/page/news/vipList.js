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
        ,url: '' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {type: 'checkbox',width:50,fixed: 'left'}
            ,{field: 'item_number', title: '会员ID', width:150, sort: true}
            ,{field: 'tel', title: '手机号', width: 200}
            ,{field: 'logo', title: '邮箱', width:250}
            ,{field: 'type', title: '类型', width:120}
            ,{field: 'sex', title: '性别', width: 120}
            ,{field: 'level', title: '等级', width:120}
            ,{field: 'address', title: '地址', width:320}
            ,{field: 'options',title:'操作',toolbar: '#itemBar',width: 320}
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
});