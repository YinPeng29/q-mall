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
            ,{field: 'order_number', title: '订单编号', width:200, sort: true}
            ,{field: 'logo', title: '品牌', width:200}
            ,{field: 'name', title: '货品名称', width:280}
            ,{field: 'date', title: '订单日期', width: 210}
            ,{field: 'price', title: '价格￥', width: 150}
            ,{field: 'vipId', title: '会员ID', width: 180}
            ,{field: 'status', title: '订单状态', width:150}
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
});