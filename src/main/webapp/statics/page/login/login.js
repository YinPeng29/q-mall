layui.config({
	base : "js/"
}).use(['form','layer'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		$ = layui.jquery;
	//video背景
	$(window).resize(function(){
		if($(".video-player").width() > $(window).width()){
			$(".video-player").css({"height":$(window).height(),"width":"auto","left":-($(".video-player").width()-$(window).width())/2});
		}else{
			$(".video-player").css({"width":$(window).width(),"height":"auto","left":-($(".video-player").width()-$(window).width())/2});
		}
	}).resize();
	
	登录按钮事件
	// form.on("submit(login)",function(data){
	// 	// window.location.href = "../../index.html";
	// 	layer.alert("123");
     //    $.ajax({
     //        type: "POST",//方法类型
     //        dataType: "json",//预期服务器返回的数据类型
     //        url: "/user/login" ,//url
     //        data: $('#form1').serialize(),
     //        success: function(resp){
     //            alert("123");
     //        }
     //    });
	// })

});
