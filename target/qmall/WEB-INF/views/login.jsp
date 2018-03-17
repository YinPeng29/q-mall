<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%--%>
<%--String path = request.getContextPath();--%>
<%--String basePath = request.getScheme()+"://" +request.getServerName()+":" +request.getServerPort()+path+"/" ;--%>
<%--System.out.println("getContextPath: "+path+" getCheme: "+request.getScheme()+" getServerName: "+request.getServerName()+" getServerPort: "+request.getServerPort());--%>
<%--System.out.println("basePath="+basePath);--%>
<%--%>--%>
<%--<base href="<%=basePath%>" >--%>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>翻转式用户登录注册界面设计</title>
    <link rel="stylesheet" type="text/css" href="../../statics/css/styles.css">

    <script type="text/javascript" src="http://libs.baidu.com/html5shiv/3.7/html5shiv.min.js"></script>
    <script type="text/javascript" src="../../statics/plugins/jquery-1.5.1.min.js"></script>

</head>

<body>
<form id="loginForm" action="" method="post">
<div class="jq22-container" style="padding-top:100px">
    <div class="login-wrap">
        <div class="login-html">
            <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
            <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
            <div class="login-form">
                <div class="sign-in-htm">
                    <div class="group">
                        <label for="userName" class="label">Username</label>
                        <input id="userName" name="userName" type="text" class="input" />
                    </div>
                    <div class="group">
                        <label for="passWord" class="label">Password</label>
                        <input id="passWord" name="passWord" type="password" class="input" data-type="password" />
                    </div>
                    <div class="group">
                        <input id="check" name="check" type="checkbox" class="check" />
                        <label for="check"><span class="icon"></span> Keep me Signed in</label>
                    </div>
                    <div class="group">
                        <input id="signIn" type="button" class="button" value="Sign In">
                    </div>
                    <div class="hr"></div>
                    <div class="foot-lnk">
                        <a href="#forgot">Forgot Password?</a>
                    </div>
                </div>
                <div class="sign-up-htm">
                    <div class="group">
                        <label for="uuser" class="label">Username</label>
                        <input id="uuser" name="username" type="text" class="input" />
                    </div>
                    <div class="group">
                        <label for="upass" class="label">Password</label>
                        <input id="upass" name="password" type="password" class="input" data-type="password" />
                    </div>
                    <div class="group">
                        <label for="rpass" class="label">Repeat Password</label>
                        <input id="rpass" name="rpassWord" type="password" class="input" data-type="password" />
                    </div>
                    <div class="group">
                        <label for="email" class="label">Email Address</label>
                        <input id="email" name="email" type="text" class="input" />
                    </div>
                    <div class="group">
                        <input id="signUp" type="button" class="button" value="Sign Up" />
                    </div>
                    <div class="hr"></div>
                    <div class="foot-lnk">
                        <a><label for="tab-1">Already Member?></label></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</form>
<script>
    $(function(){
        $("#signIn").click(function(){
            $("#loginForm").attr("action","/user/login");
            $("#loginForm").submit();
        });

//       注册简单验证
        $("#signUp").click(function(){
              var rpass = $("#rpass").val();
              var pass = $("#upass").val();
              if(rpass == pass){
                  $("#loginForm").attr("action","/user/save");
                  $("#loginForm").submit();
              }else{
                  alert("两次输入的密码不同");
              }

        });

    });

</script>
</body>
</html>

