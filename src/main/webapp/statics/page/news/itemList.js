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
//				skin:'layui-layer-molv',
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
        elem: '#itemList'
//			,height: 315
        ,url: '' //数据接口
        ,cellMinWidth: 50
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'id', title: '货号', sort: true,width:100, fixed: 'left'}
            ,{field: 'username', title: '用户名',width:180}
            ,{field: 'sex', title: '性别', sort: true,width:180}
            ,{field: 'city', title: '城市',width:180}
            ,{field: 'sign', title: '签名',width:180}
            ,{fixed: 'option',title:'操作', toolbar: '#itemBar',width:280}
        ]]
    });

});